package com.sosa.factions2server.Objects.Factions;

import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.UUID;

/**
 * This class represents a player's faction data as an object.
 */
public class FPlayer {

    private String uuid;
    private String factionTag;
    private String name;

    /**
     * This is used to create a fPlayer object with a uuid and faction tag.
     * @param uuid
     * @param factionTag
     */
    public FPlayer(String uuid, String factionTag)
    {
        this.uuid = uuid;
        this.name = Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName();
        this.factionTag = factionTag;
    }

    //Getters and setter for the various fields.
    public String getFactionTag() {
        return factionTag;
    }

    public void setFactionTag(String factionTag) {
        this.factionTag = factionTag;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
