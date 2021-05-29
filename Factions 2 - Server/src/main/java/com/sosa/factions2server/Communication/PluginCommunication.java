package com.sosa.factions2server.Communication;

import com.sosa.factions2server.Communication.Listeners.CommunicationListener;
import com.sosa.factions2server.Communication.Listeners.SynchronizationListener;
import com.sosa.factions2server.Factions2Server;
import com.sosa.factions2server.Communication.Listeners.GuiMessageListener;
import dev.westernpine.pipelines.api.Message;
import dev.westernpine.pipelines.api.Request;
import dev.westernpine.pipelines.api.Response;
import dev.westernpine.pipelines.live.server.BukkitPipeline;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class PluginCommunication {

    private static BukkitPipeline communication;
    private static final HashMap<String, CommunicationListener> listeners = new HashMap<>();

    /**
     * This function registers the plugin messaging pipeline.
     */
    public static void registerCommunication()
    {
        communication = new BukkitPipeline(Factions2Server.getInstance(), "factions", "incoming", "outgoing");
        communication.onMessage(PluginCommunication::handleMessageReceived);
        communication.onRequest(PluginCommunication::handleRequestReceived);
        registerListeners();
    }

    /**
     * This function calls the correct communication listener based on the channel of the message.
     *
     * @param message This is the received message.
     */
    private static void handleMessageReceived(Message message)
    {
        PluginChannelMessage pluginMessage = new PluginChannelMessage(message.read(String.class));

        CommunicationListener listener = listeners.get(pluginMessage.getChannel());

        if (listener == null) return;

        listener.onMessageReceived(Bukkit.getPlayer(message.getCarrier()), pluginMessage.getArguments());
    }

    /**
     * This function sends a request to the proxy using a player as a carrier.
     * The first object in the delivered request payload will be a string object used as a "channel"
     *
     * @param player This is the player to carry the request.
     * @param message This is the message to send as a request.
     *
     * @return A response to the request.
     */
    private static Response sendRequest(Player player, PluginChannelMessage message)
    {
        Request request = new Request(player.getUniqueId());
        request.getPayload().add(message.getChannel());
        request.getPayload().addAll(message.getArguments());

        AtomicReference<Response> returnValue = new AtomicReference<>();

        try {
            request.send(communication).get().ifPresent(returnValue::set);
        } catch (Exception ignored){}

        return returnValue.get();
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
     * This method returns the plugin messaging pipeline.
     *
     * @return An instance of the plugin messaging pipeline.
     */
    public static BukkitPipeline getCommunication()
    {
        return communication;
    }

    /**
     * This function registers all plugin message listeners.
     */
    public static void registerListeners()
    {
        registerListener("gui", new GuiMessageListener());
        registerListener("synchronize", new SynchronizationListener());
    }

    /**
     * This function registers a plugin message listener.
     */
    private static void registerListener(String name, CommunicationListener listener)
    {
        listeners.put(name, listener);
    }

    /**
     * This function is used to send a message to a player through the pipeline.
     *
     * @param player The player to send it to.
     * @param message The plugin message to send.
     */
    public static void sendMessage(Player player, PluginChannelMessage message)
    {
        new Message(player.getUniqueId()).append(message.toString()).send(communication);
    }
}
