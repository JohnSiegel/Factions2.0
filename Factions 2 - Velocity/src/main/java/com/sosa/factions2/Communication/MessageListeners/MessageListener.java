package com.sosa.factions2.Communication.MessageListeners;

import java.util.List;
import java.util.UUID;

/**
 * This interface serves as a base for all plugin communication messages.
 */
public interface MessageListener {

    void onMessageReceived(UUID recipient, List<String> arguments);

}
