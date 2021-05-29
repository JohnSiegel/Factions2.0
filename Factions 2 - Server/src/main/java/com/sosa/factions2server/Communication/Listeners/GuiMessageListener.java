package com.sosa.factions2server.Communication.Listeners;

import com.sosa.factions2server.GUI.BaseGUI;
import com.sosa.factions2server.GUI.DisbandGUI;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

/**
 * This listener listens for gui messages and opens the correct gui for the corresponding message.
 */
public class GuiMessageListener implements CommunicationListener{

    private final HashMap<String, Class<? extends BaseGUI>> GUICommandMap = new HashMap<>();

    //This is where you register a command to a gui to open.
    public GuiMessageListener()
    {
        registerGUICommand("disband", DisbandGUI.class);
    }

    private void registerGUICommand(String key, Class<? extends BaseGUI> base)
    {
        GUICommandMap.put(key, base);
    }

    @Override
    public void onMessageReceived(Player recipient, List<String> arguments) {
        for (String key : GUICommandMap.keySet())
        {
            if (arguments.get(0).equalsIgnoreCase(key))
            {
                try {
                    BaseGUI base = GUICommandMap.get(key).getConstructor(Player.class, List.class)
                            .newInstance(recipient, arguments);
                } catch (Exception ignored)
                {}
                break;
            }
        }
    }
}
