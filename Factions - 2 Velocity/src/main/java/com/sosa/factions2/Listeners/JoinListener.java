package com.sosa.factions2.Listeners;

import com.sosa.factions2.Managers.FPlayerManager;
import com.sosa.factions2.Objects.Factions.FPlayer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;

/**
 * This class is used to listen for player login events.
 */
public class JoinListener {

    /**
     * This event handler listens for player logins and if they are new,
     * it registers their FPlayer object.
     *
     * @param event This is a reference to the login event.
     */
    @Subscribe
    public void joinEvent(LoginEvent event)
    {
        Player player = event.getPlayer();
        FPlayer fPlayer = FPlayerManager.getFPlayer(player);

        if (fPlayer == null)
        {
            fPlayer = FPlayerManager.registerNewFPlayer(player);
        }
    }

}
