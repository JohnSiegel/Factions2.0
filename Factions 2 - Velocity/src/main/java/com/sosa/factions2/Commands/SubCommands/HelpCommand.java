package com.sosa.factions2.Commands.SubCommands;

import com.sosa.factions2.Objects.PluginMessage;
import com.velocitypowered.api.proxy.Player;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends SubCommand{

    //This is the number of pages for the help menu.
    private int pages = 0;

    /**
     * This constructor creates the subcommand object, counts the number of pages,
     * and assigns a label.
     *
     * @param label This is the label that the subcommand is referred to by.
     */
    public HelpCommand(String label) {
        super(label);
        for (PluginMessage message : PluginMessage.values())
        {
            if (message.name().startsWith("HELP"))
            {
                pages++;
            }
        }
    }

    /**
     * This function sends the proper help message to the player based on the
     * specified page argument.
     *
     * @param source This is the sender of the subcommand.
     * @param args These are the arguments of the subcommand.
     */
    @Override
    public void execute(Player source, String[] args) {
        PluginMessage.sendMessage(source, PluginMessage.HELP_MESSAGE_1);
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

        if (currentArgs.length == 2)
        {
            ArrayList<String> pageCounter = new ArrayList<>();
            for (int i = 1; i <= pages; i++)
            {
                pageCounter.add("" + i);
            }

            return pageCounter;
        }

        return null;
    }
}
