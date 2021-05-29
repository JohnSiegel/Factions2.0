package com.sosa.factions2server.GUI;

import com.sosa.factions2server.Communication.PluginChannelMessage;
import com.sosa.factions2server.Communication.PluginCommunication;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * This gui acts as a confirmation for disbanding a faction.
 */
public class DisbandGUI extends BaseGUI {

    private final String tag;
    private final Player player;

    public DisbandGUI(Player player, List<String> args) {
        this.player = player;

        tag = args.get(1);

        inv = Bukkit.createInventory(this, 45, ChatColor.DARK_RED + "WARNING: THIS CANNOT BE UNDONE");

        buildButtons();
        buildInventory();

        player.openInventory(inv);
    }

    public Player getPlayer() {
        return player;
    }

    public void buildButtons() {
        ItemMeta im;

        ItemStack confirm = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        im = confirm.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Confirm disband?");
        confirm.setItemMeta(im);

        ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        im = cancel.getItemMeta();
        im.setDisplayName(ChatColor.RED + "Cancel disband?");
        cancel.setItemMeta(im);

        ItemStack fac = new ItemStack(Material.CRIMSON_SIGN);
        im = fac.getItemMeta();
        im.setDisplayName(ChatColor.BLUE + "Are you sure you would like");
        im.setLore(Arrays.asList(ChatColor.BLUE + "to disband " + ChatColor.GREEN + tag));

        for (int i = 0; i < inv.getSize(); i++)
        {
            if (i % 9 > ((i > inv.getSize() / 2) ? 3 : 4))
            {
                getButtons().put(i, cancel);
                getButtonActions().put(i, (Object input) -> {
                    getPlayer().closeInventory();
                    return null;
                });
            }
            else if (i == (inv.getSize() - 1) / 2)
            {
                getButtons().put(i, fac);
            }
            else {
                getButtons().put(i, confirm);
                getButtonActions().put(i, (Object input) -> {
                    getPlayer().closeInventory();
                    PluginCommunication.sendMessage(getPlayer(), new PluginChannelMessage("disband",
                            Collections.singletonList(tag)));
                    return null;
                });
            }
        }
    }

    @Override
    public void onClick(InventoryClickEvent e)
    {
        e.setCancelled(true);

        Function func = getButtonActions().get(e.getSlot());

        if (func == null) return;

        func.apply(null);
    }
}
