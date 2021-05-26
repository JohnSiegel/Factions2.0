package com.sosa.factions2.Managers;

import com.sosa.factions2.Commands.SubCommands.SubCommand;
import com.sosa.factions2.Objects.Option;
import com.sosa.factions2.Objects.PluginMessage;
import com.sosa.factions2.Utils.Config.Configuration;
import com.sosa.factions2.Utils.Config.ConfigurationProvider;
import com.sosa.factions2.Utils.Config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class is used to load and save config data.
 */
public class ConfigManager {

    //Path to plugin file.
    private static final String path = "plugins/Factions2/";

    //Used for saving and loading yaml files.
    private static final ConfigurationProvider yamlProvider = YamlConfiguration.getProvider(YamlConfiguration.class);

    /**
     * This function is called on plugin initialization and loads all configs.
     */
    public static void loadConfigData()
    {
        loadAliases();
        loadPluginMessages();
        loadOptions();
    }

    /**
     * This function is used to load options and assign them to the above map.
     */
    private static void loadOptions()
    {
        File file = new File(path + "options.yml");

        if (!file.exists())
        {
            createDefaultOptions(file);
        }

        Configuration optionConfig;

        try
        {
            optionConfig = yamlProvider.load(file);
        } catch (IOException e)
        {
            return;
        }

        HashMap<Option, Object> options = Option.getOptions();

        for (String key : optionConfig.getKeys())
        {
            options.put(Option.valueOf(key), optionConfig.get(key));
        }
    }

    /**
     * This function is used to create default options.
     */
    private static void createDefaultOptions(File file)
    {
        Configuration optionConfig = new Configuration();

        for (Option option : Option.values())
        {
            optionConfig.set(option.name(), Option.getDefaultValue(option));
        }

        try {
            file.getParentFile().mkdir();
            yamlProvider.save(optionConfig, file);
        } catch (IOException ignored) {
        }
    }

    /**
     * This function is used to load command aliases and assign them to their
     * associated sub command.
     */
    private static void loadAliases()
    {
        File file = new File(path + "aliases.yml");

        if (!file.exists())
        {
            createDefaultAliases(file);
        }

        Configuration aliasConfig;

        try {
            aliasConfig = yamlProvider.load(file);
        } catch (IOException e) {
            return;
        }

        for (String cmdLabel : aliasConfig.getKeys())
        {
            SubCommand cmd = CommandManager.getSubCommandByLabel(cmdLabel);

            if (cmd != null)
            {
                cmd.setAliases(aliasConfig.getStringList(cmdLabel));
            }
        }
    }

    /**
     * This function is used to load plugin messages.
     */
    private static void loadPluginMessages()
    {
        File file = new File(path + "messages.yml");

        if (!file.exists())
        {
            createDefaultPluginMessages(file);
        }

        Configuration messageConfig = new Configuration();

        try {
            messageConfig = yamlProvider.load(file);
        } catch (IOException e) {
            for (PluginMessage message : PluginMessage.values())
            {
                PluginMessage.setMessage(message, Collections.emptyList());
            }
        }
        
        for (String messageName : messageConfig.getKeys())
        {
            PluginMessage.setMessage(PluginMessage.valueOf(messageName), messageConfig.getStringList(messageName));
        }
    }

    /**
     * This function saves the default plugin messages to the specified file.
     *
     * @param file This is the file to save the default plugin messages to.
     */
    private static void createDefaultPluginMessages(File file)
    {
        Configuration messageConfig = new Configuration();

        for (PluginMessage message : PluginMessage.values())
        {
            messageConfig.set(message.toString(), PluginMessage.getDefaultMessage(message));
        }

        try {
            file.getParentFile().mkdir();
            yamlProvider.save(messageConfig, file);
        } catch (IOException ignored) { }
    }

    /**
     * This function saves the default aliases to the specified file.
     *
     * @param file This is the file to save the default aliases to.
     */
    private static void createDefaultAliases(File file)
    {
        Configuration aliasConfig = new Configuration();

        aliasConfig.set("help", Arrays.asList("?",
                "h"));

        try {
            file.getParentFile().mkdir();
            yamlProvider.save(aliasConfig, file);
        } catch (IOException ignored) { }
    }

}
