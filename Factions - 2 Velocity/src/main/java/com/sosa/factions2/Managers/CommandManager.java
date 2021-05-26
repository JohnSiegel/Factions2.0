package com.sosa.factions2.Managers;

import com.sosa.factions2.Commands.SubCommands.CreateCommand;
import com.sosa.factions2.Commands.SubCommands.DisbandCommand;
import com.sosa.factions2.Commands.SubCommands.HelpCommand;
import com.sosa.factions2.Commands.SubCommands.SubCommand;

import java.util.HashSet;

/**
 * This class provides static methods for managing subcommands.
 */
public class CommandManager {

    //This is a static set of references to all registered subcommand objects.
    private static final HashSet<SubCommand> subCommands = new HashSet<>();

    /**
     * This function registers a given subcommand to the above field.
     *
     * @param cmd This is the subcommand to register.
     */
    private static void registerCommand(SubCommand cmd)
    {
        subCommands.add(cmd);
    }

    /**
     * This function creates all the sub command objects and registers them all.
     */
    public static void registerSubCommands()
    {
        registerCommand(new CreateCommand("create"));
        registerCommand(new HelpCommand("help"));
        registerCommand(new DisbandCommand("disband"));
    }

    /**
     * This method returns the subcommand with the given label.
     *
     * @param label This is the label to search for a subcommand by.
     *
     * @return A subcommand with the matching label, or null if it does not exist.
     */
    public static SubCommand getSubCommandByLabel(String label)
    {
        for (SubCommand cmd : subCommands)
        {
            if (cmd.getLabel().equalsIgnoreCase(label))
            {
                return cmd;
            }
        }

        return null;
    }

    /**
     * This method returns a static reference to the set of all registered subcommands.
     *
     * @return A static reference to the set of all registered subcommands.
     */
    public static HashSet<SubCommand> getSubCommands(){
        return subCommands;
    }

}
