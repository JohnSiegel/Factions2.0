package com.sosa.factions2server.Communication.Listeners;

import dev.westernpine.pipelines.api.Message;

import java.util.List;

public interface CommunicationListener {

    void onMessageReceived(List<String> arguments);

}
