package com.sosa.factions2.Commands.SubCommands;

import com.sosa.factions2.Communication.PluginChannelMessage;
import com.sosa.factions2.Communication.PluginCommunication;
import com.sosa.factions2.Factions2;
import com.sosa.factions2.Managers.FPlayerManager;
import com.sosa.factions2.Objects.Factions.FPlayer;
import com.sosa.factions2.Objects.Factions.Faction;
import com.sosa.factions2.Objects.Factions.FactionRole;
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

    @Override
    public void execute(Player source, String[] args) {

        FPlayer fPlayer = FPlayerManager.getFPlayer(source);
        Faction faction = fPlayer.getFaction();

        if (faction == null)
        {
            PluginMessage.sendMessage(source, PluginMessage.DISBAND_YOU_ARE_NOT_IN_A_FACTION);
            return;
        }

        if (!faction.getMemberRoles().get(fPlayer).equals(FactionRole.LEADER))
        {
            PluginMessage.sendMessage(source, PluginMessage.ONLY_ROLE_CAN_DO_THIS, Collections.singletonList("<role>"),
                    Collections.singletonList("Leader"));
            return;
        }

        PluginChannelMessage msg = new PluginChannelMessage("gui", Arrays.asList("disband", faction.getTag()));
        PluginCommunication.sendMessage(source, msg);
    }

    @Override
    public List<String> suggest(Player source, String[] currentArgs) {
        return super.suggest(source, currentArgs);
    }
}
