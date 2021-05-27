package com.sosa.factions2server.GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public class BaseGUI implements Listener, InventoryHolder {

    public BaseGUI(Player player, List<String> args)
    {
        args.remove(0);
    }

    public BaseGUI()
    {

    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        InventoryHolder gui = e.getInventory().getHolder();

        if (gui instanceof BaseGUI)
        {
            ((BaseGUI) gui).onClick(e);
        }

    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
