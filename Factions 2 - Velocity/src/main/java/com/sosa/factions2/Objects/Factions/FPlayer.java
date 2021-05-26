package com.sosa.factions2.Objects.Factions;

import com.velocitypowered.api.proxy.Player;

/**
 * This class represents a player's faction data as an object.
 */
public class FPlayer {

    private String uuid;
    private Faction faction;
    private Player player;
    private String name;

    /**
     * This constructor is used to create a new FPlayer object.
     *
     * @param player The player to create an object for.
     */
    public FPlayer(Player player)
    {
        this.uuid = player.getUniqueId().toString();
        this.player = player;
        this.name = player.getUsername();
        this.faction = null;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
