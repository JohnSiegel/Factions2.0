package com.sosa.factions2server.Communication.Listeners;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * This interface serves as a base for plugin channel message listeners.
 */
public interface CommunicationListener {

    void onMessageReceived(Player recipient, List<String> arguments);

}
