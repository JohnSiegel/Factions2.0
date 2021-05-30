package com.sosa.factions2;

import com.google.inject.Inject;
import com.sosa.factions2.Commands.BaseCommand;
import com.sosa.factions2.Communication.PluginCommunication;
import com.sosa.factions2.Listeners.JoinListener;
import com.sosa.factions2.Managers.CommandManager;
import com.sosa.factions2.Managers.ConfigManager;
import com.sosa.factions2.Managers.FPlayerManager;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

/**
 * This is the main class for this plugin. This class handles the construction and
 * initialization phases of the plugins loading process.
 */
@Plugin(
        id = "factions",
        name = "Factions2",
        version = "1.0",
        authors = {"Sosa"}
)
public class Factions2 {

    private final ProxyServer proxyServer;
    private final Logger logger;
    private static Factions2 instance;

    /**
     * This constructor is called in the construction phase of plugin loading, and
     * it gets passed a reference to the proxy server and the logger objects which
     * are stored in the fields above along with a static reference to this plugin.
     *
     * @param proxyServer This is a reference to the proxy server.
     * @param logger This is a reference to the logger object.
     */
    @Inject
    public Factions2(ProxyServer proxyServer, Logger logger)
    {
        this.proxyServer = proxyServer;
        this.logger = logger;
        instance = this;
    }

    /**
     * This function serves as an event handler for the plugin initialization phase.
     *
     * @param event This is a reference to the initialization event.
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        proxyServer.getCommandManager().register(new BaseCommand(), "f", "factions", "faction", "fac");
        proxyServer.getEventManager().register(this, new JoinListener());
        CommandManager.registerSubCommands();
        ConfigManager.loadConfigData();
        PluginCommunication.registerPipeline();
        FPlayerManager.synchronize();
    }

    /**
     * This function is an event handler for the server shutting down. It saves all player and faction data.
     * @param event
     */
    @Subscribe
    public void onProxyStop(ProxyShutdownEvent event)
    {
        ConfigManager.saveData();
    }

    /**
     * This method returns a reference to the proxy server.
     *
     * @return A reference to the proxy server.
     */
    public ProxyServer getProxyServer() {
        return proxyServer;
    }

    /**
     * This method returns a reference to the logger.
     *
     * @return A reference to the logger.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * This method returns a static reference to the plugin.
     *
     * @return A static reference to the plugin.
     */
    public static Factions2 getInstance()
    {
        return instance;
    }
}
