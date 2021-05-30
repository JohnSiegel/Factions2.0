package com.sosa.factions2.Objects;

import com.velocitypowered.api.proxy.Player;

public enum PermissionNode {

    DISBAND_OTHERS;

    public static String getNode(PermissionNode node)
    {
        switch (node)
        {
            case DISBAND_OTHERS:
                return "factions.disband.others";
            default:
                return "";
        }
    }

    public static boolean hasPermission(Player player, PermissionNode node)
    {
        return (player.hasPermission(PermissionNode.getNode(node)));
    }

}
