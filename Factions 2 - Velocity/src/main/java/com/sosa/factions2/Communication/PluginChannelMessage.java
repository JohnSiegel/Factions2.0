package com.sosa.factions2.Communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginChannelMessage {

    private String channel;
    private List<String> arguments = new ArrayList<>();

    /**
     * This constructor creates a plugin message to be sent on a specified channel with specified arguments.
     *
     * @param channel This is the channel to be sent on.
     * @param arguments These are the arguments to send.
     */
    public PluginChannelMessage(String channel, List<String> arguments)
    {
        this.channel = channel;
        this.arguments = arguments;
    }

    /**
     * This constructor creates a plugin message object from a string.
     *
     * @param fromString The string to translate into an object.
     */
    public PluginChannelMessage(String fromString)
    {
        int splitIndex = fromString.indexOf(":");
        this.channel = fromString.substring(0, splitIndex);
        this.arguments.addAll(Arrays.asList(fromString.substring(splitIndex + 1).split(" ")));
    }

    //Getters and setters for the various fields.
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method is called to translate a plugin communication message into a string to be sent.
     *
     * @return The string to be sent.
     */
    @Override
    public String toString() {
        String formatted = channel + ":";
        for (int i = 0; i < arguments.size(); i++)
        {
            formatted += arguments.get(i);
            if (i != arguments.size() - 1)
            {
                formatted += " ";
            }
        }
        return formatted;
    }
}
