package com.sosa.factions2.Objects.Factions;

import com.velocitypowered.api.proxy.Player;

/**
 * This class represents a player's faction data as an object.
 */
public class FPlayer {

    private String uuid;
    private String factionTag;
    private String name;

    /**
     * This constructor is used to create a new FPlayer object.
     *
     * @param player The player to create an object for.
     */
    public FPlayer(Player player)
    {
        this.uuid = player.getUniqueId().toString();
        this.name = player.getUsername();
        this.factionTag = "na";
    }

    /**
     * This constructor is used to create an FPlayer from pre established data.
     *
     * @param uuid This is the players uuid.
     * @param faction This is the faction the player belongs too.
     * @param name This is the name of the player.
     */
    public FPlayer(String uuid, String faction, String name)
    {
        this.uuid = uuid;
        this.factionTag = faction;
        this.name = name;
    }

    //Getters and setters for the various fields.
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFactionTag() {
        return factionTag;
    }

    public void setFaction(String faction) {
        this.factionTag = faction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
