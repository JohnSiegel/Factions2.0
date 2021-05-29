package com.sosa.factions2.Communication;

import com.sosa.factions2.Communication.RequestListeners.RequestListener;
import com.sosa.factions2.Communication.MessageListeners.MessageListener;
import com.sosa.factions2.Communication.MessageListeners.DisbandMessageListener;
import com.sosa.factions2.Communication.MessageListeners.SynchronizeMessageListener;
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
    private static final HashMap<String, MessageListener> messageListeners = new HashMap<>();
    private static final HashMap<String, RequestListener> requestListeners = new HashMap<>();

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

        MessageListener listener = messageListeners.get(channel);

        if (listener == null) return;

        listener.onMessageReceived(message.getCarrier(), pluginMessage.getArguments());
    }

    /**
     * This function is called when a request is received from another plugin.
     *
     * @param request This is the request received.
     */
    private static void handleRequestReceived(Request request)
    {
        String channel = (String) request.getPayload().get(0);

        RequestListener listener = requestListeners.get(channel);

        if (listener == null) return;

        listener.respondToRequest(request).send(pipeline);
    }

    /**
     * This function registers all plugin messaging listeners.
     */
    public static void registerListeners()
    {
        registerMessageListener("disband", new DisbandMessageListener());
        registerMessageListener("synchronize", new SynchronizeMessageListener());
    }

    /**
     * This function registers a plugin communication listener to a specified channel.
     *
     * @param channel This is the channel to register to.
     * @param listener This is the listener to register.
     */
    private static void registerMessageListener(String channel, MessageListener listener)
    {
        messageListeners.put(channel, listener);
    }

    /**
     * This function registers a plugin communication listener to a specified channel.
     *
     * @param channel This is the channel to register to.
     * @param listener This is the listener to register.
     */
    private static void registerRequestListener(String channel, RequestListener listener)
    {
        requestListeners.put(channel, listener);
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
