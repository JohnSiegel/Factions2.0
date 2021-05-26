package com.sosa.factions2.Objects.Factions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.sosa.factions2.Objects.Factions.FactionRole.*;

/**
 * This class represents a faction as an object.
 */
public class Faction {

    private FPlayer leader;
    private String tag;
    private String desc;
    private ArrayList<FPlayer> members;
    private HashMap<FPlayer, FactionRole> memberRoles;
    private HashMap<FPlayer, String> memberTitles;

    /**
     * This constructor is used to create a new faction.
     *
     * @param leader This is the player who created the faction.
     * @param tag This will be the name of the faction.
     */
    public Faction(FPlayer leader, String tag)
    {
        this.leader = leader;
        this.tag = tag;
        this.desc = "Use /f desc to update faction description.";
        this.members = new ArrayList<>(Collections.singletonList(leader));
        this.memberRoles = new HashMap<>();
        this.memberRoles.put(leader, LEADER);
        this.memberTitles = new HashMap<>();
        this.memberTitles.put(leader, "");
    }

    public FPlayer getLeader() {
        return leader;
    }

    public void setLeader(FPlayer leader) {
        this.leader = leader;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<FPlayer> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<FPlayer> members) {
        this.members = members;
    }

    public HashMap<FPlayer, FactionRole> getMemberRoles() {
        return memberRoles;
    }

    public void setMemberRoles(HashMap<FPlayer, FactionRole> memberRoles) {
        this.memberRoles = memberRoles;
    }

    public HashMap<FPlayer, String> getMemberTitles() {
        return memberTitles;
    }

    public void setMemberTitles(HashMap<FPlayer, String> memberTitles) {
        this.memberTitles = memberTitles;
    }
}
