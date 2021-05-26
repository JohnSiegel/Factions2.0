package com.sosa.factions2.Commands.SubCommands;

import com.sosa.factions2.Communication.PluginChannelMessage;
import com.sosa.factions2.Communication.PluginCommunication;
import com.sosa.factions2.Factions2;
import com.velocitypowered.api.proxy.Player;

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
        Factions2.getInstance().getLogger().info("sent");
        PluginChannelMessage msg = new PluginChannelMessage("gui", Collections.singletonList("disband_gui"));
        PluginCommunication.sendMessage(source, msg);
    }

    @Override
    public List<String> suggest(Player source, String[] currentArgs) {
        return super.suggest(source, currentArgs);
    }
}
