package com.sosa.factions2.Commands.SubCommands;

import com.sosa.factions2.Factions2;
import com.sosa.factions2.Managers.FPlayerManager;
import com.sosa.factions2.Managers.FactionManager;
import com.sosa.factions2.Objects.Factions.FPlayer;
import com.sosa.factions2.Objects.Factions.Faction;
import com.sosa.factions2.Objects.Option;
import com.sosa.factions2.Objects.PluginMessage;
import com.velocitypowered.api.proxy.Player;

import java.util.Collections;
import java.util.List;

/**
 * This subcommand is used for creating a new faction.
 */
public class CreateCommand extends SubCommand {

    /**
     * This constructor creates the subcommand object and assigns a label.
     *
     * @param label This is the label that the subcommand is referred to by.
     */
    public CreateCommand(String label) {
        super(label);
    }

    /**
     * This function creates a new faction with the command sender as leader
     * and the first argument as the tag.
     *
     * @param source This is the sender of the subcommand.
     * @param args These are the arguments of the subcommand.
     */
    @Override
    public void execute(Player source, String[] args) {
        if (args.length == 0)
        {
            PluginMessage.sendMessage(source, PluginMessage.SYNTAX_CREATE);
            return;
        }

        FPlayer fPlayer = FPlayerManager.getFPlayer(source);

        if (!fPlayer.getFactionTag().equals("na"))
        {
            PluginMessage.sendMessage(source, PluginMessage.CREATE_ALREADY_IN_FAC);
            return;
        }

        String tag = args[0];

        Faction faction = FactionManager.getFactionsByTag().get(tag);

        if (faction != null)
        {
            PluginMessage.sendMessage(source, PluginMessage.CREATE_FACTION_ALREADY_EXISTS);
            return;
        }

        int minLength = (int) Option.getOptions().get(Option.MINIMUM_TAG_LENGTH);

        if (tag.length() < minLength)
        {
            PluginMessage.sendMessage(source, PluginMessage.CREATE_FACTION_TAG_TOO_SHORT,
                    Collections.singletonList("<length>"), Collections.singletonList("" + minLength));
            return;
        }

        int maxLength = (int) Option.getOptions().get(Option.MAXIMUM_TAG_LENGTH);

        if (tag.length() > maxLength)
        {
            PluginMessage.sendMessage(source, PluginMessage.CREATE_FACTION_TAG_TOO_LONG,
                    Collections.singletonList("<length>"), Collections.singletonList("" + maxLength));
            return;
        }

        PluginMessage.sendMessage(source, PluginMessage.CREATE_FACTION_SUCCESS, Collections.singletonList("<tag>"),
                Collections.singletonList(tag));

        FactionManager.registerNewFaction(fPlayer, tag);
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

        if (currentArgs.length == 2) {
            return Collections.singletonList("[name]");
        }

        return null;
    }
}
