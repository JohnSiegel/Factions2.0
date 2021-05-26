package com.sosa.factions2.Commands.SubCommands;

import com.velocitypowered.api.proxy.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class serves as a parent to all sub command objects.
 */
public abstract class SubCommand {

    private List<String> aliases = new ArrayList<>();
    private String label;

    /**
     * This constructor creates the subcommand object and assigns a label.
     *
     * @param label This is the label that the subcommand is referred to by.
     */
    public SubCommand(String label)
    {
        this.label = label;
    }

    /**
     * This method returns the label of the subcommand.
     *
     * @return The label of the subcommand.
     */
    public String getLabel() {
        return label;
    }

    /**
     * This function sets the label of a subcommand.
     *
     * @param label The label to set for the subcommand.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * This method returns the list of all aliases for this subcommand.
     *
     * @return A list of all aliases for this subcommand.
     */
    public List<String> getAliases()
    {
        return aliases;
    }

    /**
     * This function sets the list of aliases for this subcommand.
     *
     * @param aliases This is the list of aliases to set for this subcommand.
     */
    public void setAliases(List<String> aliases)
    {
        this.aliases = aliases;
    }

    /**
     * This function gets called when the subcommand is executed.
     *
     * @param source This is the sender of the subcommand.
     * @param args These are the arguments of the subcommand.
     */
    public void execute(Player source, String[] args){};

    /**
     * This method returns the list of currently suggested arguments for a subcommand.
     *
     * @param source This is the sender of the command.
     * @param currentArgs These are the current arguments of the command.
     *
     * @return The list of suggested arguments for the subcommand.
     */
    public List<String> suggest(Player source, String[] currentArgs)
    {
        return null;
    }

}
