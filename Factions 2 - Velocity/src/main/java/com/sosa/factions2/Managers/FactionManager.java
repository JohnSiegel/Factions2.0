package com.sosa.factions2.Managers;

import com.sosa.factions2.Factions2;
import com.sosa.factions2.Objects.Factions.FPlayer;
import com.sosa.factions2.Objects.Factions.Faction;
import com.sosa.factions2.Objects.PluginMessage;
import com.velocitypowered.api.proxy.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

/**
 * This class is used to manage faction data.
 */
public class FactionManager {

    private static final HashMap<String, Faction> factionsByTag = new HashMap<>();

    /**
     * This method returns a map of all factions, indexed by their tags.
     *
     * @return The map of all factions, indexed by their tags.
     */
    public static HashMap<String, Faction> getFactionsByTag()
    {
        return factionsByTag;
    }

    /**
     * This method registers and returns a new Faction object with the specified leader and tag.
     *
     * @param leader This is the FPlayer to be leader of the faction.
     * @param tag This will be the name of the faction.
     *
     */
    public static void registerNewFaction(FPlayer leader, String tag)
    {
        Faction faction = new Faction(leader, tag);
        factionsByTag.put(tag, faction);
        leader.setFaction(tag);

        FPlayerManager.synchronize();
    }

    /**
     * This function is called whenever a pre established faction needs to be registered to the map above.
     *
     * @param faction This is the faction to register.
     */
    public static void registerFaction(Faction faction)
    {
        factionsByTag.put(faction.getTag(), faction);
    }

    /**
     * This function disbands a faction and notifies all of its players.
     *
     * @param disbander The player who disbanded the faction.
     * @param faction The faction to be disbanded.
     */
    public static void disbandFaction(Player disbander, Faction faction)
    {
        if (faction == null)
        {
            PluginMessage.sendMessage(disbander, PluginMessage.COULD_NOT_FIND_FACTION);
        }
        for (FPlayer fPlayer : faction.getMembers())
        {
            fPlayer.setFaction("na");
            Factions2.getInstance().getProxyServer().getPlayer(UUID.fromString(fPlayer.getUuid()))
                    .ifPresent(player -> {
                PluginMessage.sendMessage(player, PluginMessage.DISBANDED_YOUR_FACTION,
                        Collections.singletonList("<disbander>"),
                        Collections.singletonList(disbander.getUsername()));
            });
        }

        factionsByTag.remove(faction.getTag());

        FPlayerManager.synchronize();
    }
}
