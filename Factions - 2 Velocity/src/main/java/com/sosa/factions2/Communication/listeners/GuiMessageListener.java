package com.sosa.factions2.Communication.listeners;

import com.sosa.factions2.Factions2;

import java.util.List;

public class GuiMessageListener implements CommunicationListener {
    @Override
    public void onMessageReceived(List<String> arguments) {
        for (String arg : arguments)
        {
            Factions2.getInstance().getLogger().info(arg);
        }
    }
}
