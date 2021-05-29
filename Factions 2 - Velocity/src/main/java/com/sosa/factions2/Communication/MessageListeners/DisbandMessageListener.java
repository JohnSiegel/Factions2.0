package com.sosa.factions2.Communication.MessageListeners;

import com.sosa.factions2.Factions2;
import com.sosa.factions2.Managers.FactionManager;
import com.sosa.factions2.Objects.Factions.Faction;
import com.velocitypowered.api.proxy.Player;

import java.util.List;
import java.util.UUID;

/**
 * This listener responds to the confirmation of a faction disband.
 */
public class DisbandMessageListener implements MessageListener {
    @Override
    public void onMessageReceived(UUID recipient, List<String> arguments) {
        Player player = Factions2.getInstance().getProxyServer().getPlayer(recipient).get();

        String tag = arguments.get(0);
        Faction faction = FactionManager.getFactionsByTag().get(tag);

        FactionManager.disbandFaction(player, faction);
    }
}
