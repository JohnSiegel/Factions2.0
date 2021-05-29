package com.sosa.factions2server.Communication.Listeners;

import com.sosa.factions2server.Managers.FPlayerManager;
import com.sosa.factions2server.Objects.Factions.FPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

/**
 * This listener synchronizes FPlayer data with the proxy.
 */
public class SynchronizationListener implements CommunicationListener{
    @Override
    public void onMessageReceived(Player recipient, List<String> arguments) {
        HashMap<String, FPlayer> updatedMap = new HashMap<>();

        for (String data : arguments)
        {
            int splitIndex = data.indexOf("=");
            String uuid = data.substring(0, splitIndex);
            String tag = data.substring(splitIndex + 1);

            FPlayer fPlayer = new FPlayer(uuid, tag);
            updatedMap.put(uuid, fPlayer);
        }

        FPlayerManager.syncFPlayers(updatedMap);
    }
}
