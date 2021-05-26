package com.sosa.factions2.Communication;

import com.sosa.factions2.Communication.listeners.CommunicationListener;
import com.sosa.factions2.Communication.listeners.GuiMessageListener;
import com.sosa.factions2.Factions2;
import com.velocitypowered.api.proxy.Player;
import dev.westernpine.pipelines.api.Message;
import dev.westernpine.pipelines.api.Pipeline;
import dev.westernpine.pipelines.api.Request;
import dev.westernpine.pipelines.live.proxy.VelocityPipeline;

import java.util.HashMap;

/**
 * This class is used to communicate with server-side spigot plugins.
 */
public class PluginCommunication {

    private static Pipeline pipeline;
    private static final HashMap<String, CommunicationListener> listeners = new HashMap<>();

    /**
     * This function is used to register the plugin messaging pipeline.
     */
    public static void registerPipeline()
    {
        pipeline = new VelocityPipeline(Factions2.getInstance(), Factions2.getInstance().getProxyServer(),
                "factions", "outgoing", "incoming");
        pipeline.onMessage(PluginCommunication::handleMessageReceived);
        pipeline.onRequest(PluginCommunication::handleRequestReceived);
        registerListeners();
    }

    /**
     * This method returns the plugin messaging pipeline.
     *
     * @return The plugin messaging pipeline.
     */
    public static Pipeline getPipeline() {
        return pipeline;
    }

    /**
     * This function is called when a plugin message is received by this plugin
     *
     * @param message This is the received message.
     */
    private static void handleMessageReceived(Message message)
    {
        PluginChannelMessage pluginMessage = new PluginChannelMessage(message.read(String.class));

        String channel = pluginMessage.getChannel();

        CommunicationListener listener = listeners.get(channel);

        if (listener == null) return;

        listener.onMessageReceived(pluginMessage.getArguments());
    }

    /**
     * This function is called when a request is received from another plugin.
     *
     * @param request This is the request received.
     */
    private static void handleRequestReceived(Request request)
    {

    }

    /**
     * This function registers all plugin messaging listeners.
     */
    public static void registerListeners()
    {
        registerListener("gui", new GuiMessageListener());
    }

    /**
     * This function registers a plugin communication listener to a specified channel.
     *
     * @param channel This is the channel to register to.
     * @param listener This is the listener to register.
     */
    private static void registerListener(String channel, CommunicationListener listener)
    {
        listeners.put(channel, listener);
    }

    /**
     * This function is used to send a message to a player through the pipeline.
     *
     * @param player The player to send it to.
     * @param message The plugin message to send.
     */
    public static void sendMessage(Player player, PluginChannelMessage message)
    {
        new Message(player.getUniqueId()).append(message.toString()).send(pipeline);
    }
}
