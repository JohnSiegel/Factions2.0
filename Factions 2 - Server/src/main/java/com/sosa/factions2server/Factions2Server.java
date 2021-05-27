package com.sosa.factions2server;

import com.sosa.factions2server.Communication.PluginCommunication;
import com.sosa.factions2server.GUI.BaseGUI;
import org.bukkit.plugin.java.JavaPlugin;

public final class Factions2Server extends JavaPlugin {

    private static Factions2Server plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        PluginCommunication.registerCommunication();
        getServer().getPluginManager().registerEvents(new BaseGUI(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Returns an instance of this plugin.
     *
     * @return An instance of this plugin
     */
    public static Factions2Server getInstance() {
        return plugin;
    }
}
