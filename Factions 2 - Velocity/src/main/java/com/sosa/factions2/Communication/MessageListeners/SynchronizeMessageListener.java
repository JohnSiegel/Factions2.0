package com.sosa.factions2.Communication.MessageListeners;

import com.sosa.factions2.Managers.FPlayerManager;

import java.util.List;
import java.util.UUID;

/**
 * This listener synchronizes all servers whenever it receives a message instructing it to do so.
 */
public class SynchronizeMessageListener implements MessageListener{
    @Override
    public void onMessageReceived(UUID recipient, List<String> arguments) {
        FPlayerManager.synchronize();
    }
}
