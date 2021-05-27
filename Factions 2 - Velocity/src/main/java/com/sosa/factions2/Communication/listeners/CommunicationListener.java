package com.sosa.factions2.Communication.listeners;

import java.util.List;
import java.util.UUID;

public interface CommunicationListener {

    void onMessageReceived(UUID recipient, List<String> arguments);

}
