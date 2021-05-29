package com.sosa.factions2.Objects;

import com.velocitypowered.api.proxy.Player;

import net.kyori.text.TextComponent;
import net.kyori.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to manage and send messages to the player.
 */
public enum PluginMessage {

    //These are the various messages that can be sent
    HELP_MESSAGE_1,
    CREATE_ALREADY_IN_FAC,
    CREATE_FACTION_ALREADY_EXISTS,
    CREATE_FACTION_SUCCESS,
    CREATE_FACTION_TAG_TOO_SHORT,
    CREATE_FACTION_TAG_TOO_LONG,
    DISBAND_YOU_ARE_NOT_IN_A_FACTION,
    ONLY_ROLE_CAN_DO_THIS,
    DISBANDED_YOUR_FACTION,
    SYNTAX_CREATE;

    //This is a mapping of each message to its contents
    private static final HashMap<PluginMessage, List<TextComponent>> messages = new HashMap<>();

    /**
     * This function is used to first translate a list of formatted strings into a list
     * of text components, and then maps the specified plugin messages contents
     * to said components.
     *
     * @param pluginMessage This is the message you will be setting the contents of.
     * @param messageArray This is a list of strings to be translated into text components
     *                     and then set as the contents of the message.
     */
    public static void setMessage(PluginMessage pluginMessage, List<String> messageArray)
    {
        List<TextComponent> components = new ArrayList<>();
        for (String message : messageArray)
        {
            components.add(LegacyComponentSerializer.legacy().deserialize(message, '&'));
        }
        messages.put(pluginMessage, components);
    }

    /**
     * This function is used to send the contents of a plugin message to a player via chat.
     *
     * @param player This is the recipient of the message.
     * @param pluginMessage This is the message to send.
     */
    public static void sendMessage(Player player, PluginMessage pluginMessage)
    {
        List<TextComponent> messageArray = messages.get(pluginMessage);
        for (TextComponent component : messageArray)
        {
            player.sendMessage(component);
        }
    }

    /**
     * This function is used to send the contents of a plugin message, replacing strings
     * with other specified strings, to a player via chat.
     *
     * @param player This is the recipient of the message.
     * @param pluginMessage This is the message to send.
     */
    public static void sendMessage(Player player, PluginMessage pluginMessage, List<String> toReplace, List<String> replaceWith)
    {
        List<TextComponent> messageArray = messages.get(pluginMessage);
        for (TextComponent component : messageArray)
        {
            String componentToString = LegacyComponentSerializer.legacy().serialize(component);
            for (int i = 0; i < toReplace.size(); i++)
            {
                componentToString = componentToString.replaceAll(toReplace.get(i), replaceWith.get(i));
            }
            player.sendMessage(LegacyComponentSerializer.legacy().deserialize(componentToString));
        }
    }

    /**
     * This method returns the default value of each plugin message.
     *
     * @param pluginMessage This is the message to get the default value of.
     *
     * @return The default value of this message
     */
    public static List<String> getDefaultMessage(PluginMessage pluginMessage)
    {
        switch (pluginMessage)
        {
            case HELP_MESSAGE_1:
                return Arrays.asList("test",
                        "test2",
                        "test3");
            case SYNTAX_CREATE:
                return Arrays.asList("Syntax: /f create [name]");
            case CREATE_ALREADY_IN_FAC:
                return Arrays.asList("you are already in a faction.");
            case CREATE_FACTION_SUCCESS:
                return Arrays.asList("created faction with tag: <tag>");
            case CREATE_FACTION_TAG_TOO_LONG:
                return Arrays.asList("factions tags must be at most <length> characters.");
            case CREATE_FACTION_TAG_TOO_SHORT:
                return Arrays.asList("factions tags must be at least <length> characters.");
            case CREATE_FACTION_ALREADY_EXISTS:
                return Arrays.asList("a faction with this tag already exists.");
            case DISBAND_YOU_ARE_NOT_IN_A_FACTION:
                return Arrays.asList("You are not in a faction.");
            case ONLY_ROLE_CAN_DO_THIS:
                return Arrays.asList("Only <role>s can do this.");
            case DISBANDED_YOUR_FACTION:
                return Arrays.asList("<disbander> has disbanded your faction.");
            default:
                return new ArrayList<>();
        }
    }

}
