package com.sosa.factions2.Commands;

import com.sosa.factions2.Managers.CommandManager;
import com.sosa.factions2.Commands.SubCommands.SubCommand;
import com.sosa.factions2.Factions2;
import com.sosa.factions2.Objects.PluginMessage;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the functionality of the /f command.
 */
public class BaseCommand implements Command {

    /**
     * This function is called when the /f command is sent.
     *
     * @param source This is the sender of the command.
     * @param args These are the arguments of the command.
     */
    @Override
    public void execute(CommandSource source, String[] args) {

        //This denies the console executing the /f command.
        if (!(source instanceof Player))
        {
            Factions2.getInstance().getLogger().info("Faction commands can only be executed by players.");
            return;
        }

        /*
        If a player sends the /f command with no arguments, he will be sent the
        first page of the help menu.
         */
        if(args.length == 0)
        {
            PluginMessage.sendMessage((Player) source, PluginMessage.HELP_MESSAGE_1);
            return;
        }

        //Loop through all registered subcommands.
        for (SubCommand cmd : CommandManager.getSubCommands())
        {
            //Check if the executed command matches the subcommand or one of its aliases.
            if (args[0].equalsIgnoreCase(cmd.getLabel()) || cmd.getAliases().contains(args[0].toLowerCase()))
            {
                //Create a array of the arguments following the subcommand.
                String[] subArgs = new String[args.length - 1];
                System.arraycopy(args, 1, subArgs, 0, args.length - 1);

                //Execute the subcommand.
                cmd.execute((Player) source, subArgs);
                return;
            }
        }

        for (SubCommand cmd : CommandManager.getSubCommands())
        {
            boolean run = false;

            if (cmd.getLabel().contains(args[0].toLowerCase()))
            {
                run = true;
            }
            else
            {
                for (String alias : cmd.getAliases())
                {
                    if (alias.contains(args[0].toLowerCase()))
                    {
                        run = true;
                        break;
                    }
                }
            }

            if (run)
            {
                //Create a array of the arguments following the subcommand.
                String[] subArgs = new String[args.length - 1];
                System.arraycopy(args, 1, subArgs, 0, args.length - 1);

                //Execute the subcommand.
                cmd.execute((Player) source, subArgs);
                return;
            }
        }

        //If no subcommand was found, execute the help command.
        CommandManager.getSubCommandByLabel("help").execute((Player) source, new String[]{});
    }

    /**
     * This method returns the list of currently suggested arguments for a command.
     *
     * @param source This is the sender of the command.
     * @param currentArgs These are the current arguments of the command.
     *
     * @return The list of suggested arguments for the command.
     */
    @Override
    public List<String> suggest(CommandSource source, String[] currentArgs) {
        int argCount = currentArgs.length;

        ArrayList<String> suggestions = new ArrayList<>();

        for (SubCommand cmd : CommandManager.getSubCommands()) {
            if (argCount > 0)
            {
                if (cmd.getLabel().equalsIgnoreCase(currentArgs[0]))
                {
                    return cmd.suggest((Player) source, currentArgs);
                }
                else if (argCount == 1 && cmd.getLabel().contains(currentArgs[0].toLowerCase())) {
                    suggestions.add(cmd.getLabel());
                }
                else {
                    for (String alias : cmd.getAliases())
                    {
                        if (alias.equalsIgnoreCase(currentArgs[0]))
                        {
                            return cmd.suggest((Player) source, currentArgs);
                        }
                        if (alias.contains(currentArgs[0].toLowerCase()))
                        {
                            suggestions.add(alias);
                        }
                    }
                }
            }
            else
            {
                suggestions.add(cmd.getLabel());
            }
        }
        return suggestions;
    }
}
