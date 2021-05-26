package com.sosa.factions2server.Communication.Listeners;

import com.sosa.factions2server.Factions2Server;

import java.util.List;

public class GuiMessageListener implements CommunicationListener{
    @Override
    public void onMessageReceived(List<String> arguments) {
        for (String arg : arguments)
        {
            Factions2Server.getInstance().getLogger().info(arg);
        }
    }
}
