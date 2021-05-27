package com.sosa.factions2server.Communication.Listeners;

import org.bukkit.entity.Player;

import java.util.List;

public interface CommunicationListener {

    void onMessageReceived(Player recipient, List<String> arguments);

}
