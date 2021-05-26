package com.sosa.factions2.Communication.listeners;

import dev.westernpine.pipelines.api.Message;

import java.util.List;

public interface CommunicationListener {

    void onMessageReceived(List<String> arguments);

}
