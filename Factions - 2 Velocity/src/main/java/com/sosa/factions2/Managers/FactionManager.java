package com.sosa.factions2.Managers;

import com.sosa.factions2.Objects.Factions.FPlayer;
import com.sosa.factions2.Objects.Factions.Faction;

import java.util.HashMap;

/**
 * This class is used to manage faction data.
 */
public class FactionManager {

    private static HashMap<String, Faction> factions = new HashMap<>();

    /**
     * This method returns a map of all factions, indexed by their tags.
     *
     * @return The map of all factions, indexed by their tags.
     */
    public static HashMap<String, Faction> getFactions()
    {
        return factions;
    }

    /**
     * This function sets the map of all factions.
     *
     * @param factions This is a map of all factions.
     */
    public static void setFactions(HashMap<String, Faction> factions)
    {
        FactionManager.factions = factions;
    }

    /**
     * This method registers and returns a new Faction object with the specified leader and tag.
     *
     * @param leader This is the FPlayer to be leader of the faction.
     * @param tag This will be the name of the faction.
     *
     * @return The newly created Faction object.
     */
    public static Faction registerNewFaction(FPlayer leader, String tag)
    {
        Faction faction = new Faction(leader, tag);
        factions.put(tag, faction);
        leader.setFaction(faction);
        return faction;
    }
}
