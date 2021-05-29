package com.sosa.factions2server.GUI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Extending this class will make an object into a gui. This class also listens for click events and
 * passes them to the correct gui class.
 */
public class BaseGUI implements Listener, InventoryHolder {

    public Inventory inv;
    //These are the item stacks present in each inventory slot.
    private final HashMap<Integer, ItemStack> buttons = new HashMap<>();
    //These are the actions to perform when you click a certain slot.
    private final HashMap<Integer, Function> buttonActions = new HashMap<>();

    /**
     * This function sets the contents of the gui to the contents of the buttons map.
     */
    public void buildInventory()
    {
        for (Map.Entry<Integer, ItemStack> button : buttons.entrySet())
        {
            getInventory().setItem(button.getKey(), button.getValue());
        }
    }

    /**
     * This function calls the correct gui on click event.
     *
     * @param e The click event.
     */
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        InventoryHolder gui = e.getInventory().getHolder();

        if (gui instanceof BaseGUI)
        {
            ((BaseGUI) gui).onClick(e);
        }

    }

    //Getters and setters for the various fields.
    @Override
    public Inventory getInventory() {
        return inv;
    }

    public HashMap<Integer, ItemStack> getButtons() {
        return buttons;
    }

    public HashMap<Integer, Function> getButtonActions() {
        return buttonActions;
    }
}
