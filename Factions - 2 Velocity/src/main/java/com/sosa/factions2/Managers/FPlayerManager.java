package com.sosa.factions2.Managers;

import com.sosa.factions2.Objects.Factions.FPlayer;
import com.velocitypowered.api.proxy.Player;

import java.util.HashMap;

/**
 * This class is used to manage FPlayer objects.
 */
public class FPlayerManager {

    private static HashMap<String, FPlayer> fPlayers = new HashMap<>();

    /**
     * This method is used to get a list of all FPlayers indexed by their uuids.
     *
     * @return A list of all FPlayers indexed by their uuids.
     */
    public static HashMap<String, FPlayer> getfPlayers() {
        return fPlayers;
    }

    /**
     * This function sets the list of all FPlayers.
     *
     * @param fPlayers This is a list of FPlayers to set the list to.
     */
    public static void setfPlayers(HashMap<String, FPlayer> fPlayers) {
        FPlayerManager.fPlayers = fPlayers;
    }

    /**
     * This method registers and returns a new player as an FPlayer.
     *
     * @param player This is the player to register.
     *
     * @return The created FPlayer object.
     */
    public static FPlayer registerNewFPlayer(Player player)
    {
        FPlayer fPlayer = new FPlayer(player);
        fPlayers.put(fPlayer.getUuid(), fPlayer);

        return fPlayer;
    }

    /**
     * This method returns an FPlayer by a reference to their player object.
     *
     * @param player This is the player object to get an FPlayer from.
     *
     * @return The FPlayer object associated with the player, or null if there is no such.
     */
    public static FPlayer getFPlayer(Player player)
    {
        return getfPlayers().get(player.getUniqueId().toString());
    }
}
