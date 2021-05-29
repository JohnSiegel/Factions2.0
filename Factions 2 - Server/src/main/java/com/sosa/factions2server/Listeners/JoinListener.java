package com.sosa.factions2server.Listeners;

import com.sosa.factions2server.Communication.PluginChannelMessage;
import com.sosa.factions2server.Communication.PluginCommunication;
import com.sosa.factions2server.Factions2Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * This listener is for when players join the server.
 */
public class JoinListener implements Listener {

    /**
     * This event handler will synchronize the fPlayer data if the server has less than 2 people online.
     * The player who joins will be used as the payload to request synchronization.
     *
     * @param e This is the join event.
     */
    @EventHandler
    public void requestSynchronization(PlayerJoinEvent e)
    {
        if (Factions2Server.getInstance().getServer().getOnlinePlayers().size() < 2)
        {
            PluginCommunication.sendMessage(e.getPlayer(), new PluginChannelMessage("synchronize:sync"));
        }
    }

}
