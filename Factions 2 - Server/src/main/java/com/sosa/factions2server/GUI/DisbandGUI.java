package com.sosa.factions2server.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public class DisbandGUI extends BaseGUI implements InventoryHolder {

    private Inventory inv;

    public DisbandGUI(Player player, List<String> arguments)
    {
        super(player, arguments);

        String tag = arguments.get(0);
        inv = Bukkit.createInventory(this, 36, ChatColor.DARK_RED + "Confirm Disband?");

        player.openInventory(inv);

        player.sendMessage(tag);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    @Override
    public void onClick(InventoryClickEvent e)
    {
        Inventory clickedInv = e.getInventory();

        if (e.getSlot() > 53)
        {
            e.setCancelled(true);
        }
    }
}
