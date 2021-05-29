package com.sosa.factions2server.Managers;

import com.sosa.factions2server.Objects.Factions.FPlayer;

import java.util.HashMap;

/**
 * This class is used to store references to all fplayer objects.
 */
public class FPlayerManager {

    private static HashMap<String, FPlayer> fPlayersByUUID = new HashMap<>();

    public static void syncFPlayers(HashMap<String, FPlayer> input)
    {
        fPlayersByUUID = input;
    }

    public static HashMap<String, FPlayer> getfPlayers()
    {
        return fPlayersByUUID;
    }

}
