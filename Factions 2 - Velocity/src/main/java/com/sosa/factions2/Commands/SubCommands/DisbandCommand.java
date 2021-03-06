package com.sosa.factions2.Commands.SubCommands;

import com.sosa.factions2.Communication.PluginChannelMessage;
import com.sosa.factions2.Communication.PluginCommunication;
import com.sosa.factions2.Factions2;
import com.sosa.factions2.Managers.FPlayerManager;
import com.sosa.factions2.Managers.FactionManager;
import com.sosa.factions2.Objects.Factions.FPlayer;
import com.sosa.factions2.Objects.Factions.Faction;
import com.sosa.factions2.Objects.Factions.FactionRole;
import com.sosa.factions2.Objects.PermissionNode;
import com.sosa.factions2.Objects.PluginMessage;
import com.velocitypowered.api.proxy.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DisbandCommand extends SubCommand{
    /**
     * This constructor creates the subcommand object and assigns a label.
     *
     * @param label This is the label that the subcommand is referred to by.
     */
    public DisbandCommand(String label) {
        super(label);
    }

    /**
     * This function disbands the players faction if they are leader.
     *
     * @param source This is the sender of the subcommand.
     * @param args These are the arguments of the subcommand.
     */
    @Override
    public void execute(Player source, String[] args) {

        FPlayer fPlayer = FPlayerManager.getFPlayer(source);
        Faction faction = FactionManager.getFactionsByTag().get(fPlayer.getFactionTag());

        if (args.length > 0)
        {
            if (PermissionNode.hasPermission(source, PermissionNode.DISBAND_OTHERS))
            {
                faction = FactionManager.getFactionsByTag().get(args[0]);
                if (faction == null)
                {
                    PluginMessage.sendMessage(source, PluginMessage.COULD_NOT_FIND_FACTION, Arrays.asList("<tag>"), Arrays.asList(args[0]));
                }
                else
                {
                    PluginChannelMessage msg = new PluginChannelMessage("gui", Arrays.asList("disband", "" + faction.getTag()));
                    PluginCommunication.sendMessage(source, msg);
                    return;
                }
            }
        }

        if (faction == null)
        {
            PluginMessage.sendMessage(source, PluginMessage.DISBAND_YOU_ARE_NOT_IN_A_FACTION);
            return;
        }

        if (!faction.getMemberRoles().get(fPlayer).equals(FactionRole.LEADER) && !PermissionNode.hasPermission(source, PermissionNode.DISBAND_OTHERS))
        {
            PluginMessage.sendMessage(source, PluginMessage.ONLY_ROLE_CAN_DO_THIS, Collections.singletonList("<role>"),
                    Collections.singletonList("Leader"));
            return;
        }

        PluginChannelMessage msg = new PluginChannelMessage("gui", Arrays.asList("disband", "" + faction.getTag()));
        PluginCommunication.sendMessage(source, msg);
    }

    /**
     * This method returns the list of currently suggested arguments for a subcommand.
     *
     * @param source This is the sender of the command.
     * @param currentArgs These are the current arguments of the command.
     *
     * @return The list of suggested arguments for the subcommand.
     */
    @Override
    public List<String> suggest(Player source, String[] currentArgs) {
        return super.suggest(source, currentArgs);
    }
}
