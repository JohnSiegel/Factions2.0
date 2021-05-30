package com.sosa.factions2.Managers;

import com.sosa.factions2.Commands.SubCommands.SubCommand;
import com.sosa.factions2.Objects.Factions.FPlayer;
import com.sosa.factions2.Objects.Factions.Faction;
import com.sosa.factions2.Objects.Factions.FactionRole;
import com.sosa.factions2.Objects.Option;
import com.sosa.factions2.Objects.PluginMessage;
import com.sosa.factions2.Utils.Config.Configuration;
import com.sosa.factions2.Utils.Config.ConfigurationProvider;
import com.sosa.factions2.Utils.Config.JsonConfiguration;
import com.sosa.factions2.Utils.Config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class is used to load and save config data.
 */
public class ConfigManager {

    //Path to plugin file.
    private static final String path = "plugins/Factions2/";

    //Used for saving and loading yaml files.
    private static final ConfigurationProvider yamlProvider = YamlConfiguration.getProvider(YamlConfiguration.class);
    private static final ConfigurationProvider jsonProvider = JsonConfiguration.getProvider(JsonConfiguration.class);

    /**
     * This function is called on plugin initialization and loads all configs.
     */
    public static void loadConfigData()
    {
        loadAliases();
        loadPluginMessages();
        loadOptions();

        loadFPlayers();
        loadFactions();
    }

    /**
     * This function is called on plugin disable and saves all faction and player data.
     */
    public static void saveData()
    {
        saveFPlayers();
        saveFactions();
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

    /**
     * This function loads all players.
     */
    private static void loadFPlayers()
    {
        File parentFile = new File(path + "players");
        parentFile.mkdir();

        HashMap<String, FPlayer> players = new HashMap<>();

        for (File playerFile : parentFile.listFiles())
        {
            Configuration configuration;
            String uuid = playerFile.getName().replace(".json", "");
            try {
                configuration = jsonProvider.load(playerFile);
            } catch (IOException ignored) {
                continue;
            }

            String name = configuration.getString("name");
            String factionTag = configuration.getString("faction");

            FPlayer player = new FPlayer(uuid, factionTag, name);

            players.put(uuid, player);
        }

        FPlayerManager.setfPlayers(players);
    }

    /**
     * This function loads all Factions.
     */
    private static void loadFactions()
    {
        File parentFile = new File(path + "factions");
        parentFile.mkdir();

        FactionManager.getFactionsByTag().clear();

        for (File factionFile : parentFile.listFiles()) {
            Configuration factionConfig;
            String tag = factionFile.getName().replace(".json", "");

            try {
                factionConfig = jsonProvider.load(factionFile);
            } catch (Exception ignored) {
                continue;
            }

            FPlayer leader = FPlayerManager.getfPlayers().get(factionConfig.getString("leader"));
            String desc = factionConfig.getString("description");

            ArrayList<FPlayer> members = new ArrayList<>();

            for (String playerUUID : factionConfig.getStringList("members"))
            {
                members.add(FPlayerManager.getfPlayers().get(playerUUID));
            }

            HashMap<FPlayer, FactionRole> roles = new HashMap<>();

            Configuration roleConfig = factionConfig.getSection("roles");

            for (String uuid : roleConfig.getKeys())
            {
                FPlayer player = FPlayerManager.getfPlayers().get(uuid);

                roles.put(player, FactionRole.valueOf(roleConfig.getString(uuid)));
            }

            HashMap<FPlayer, String> titles = new HashMap<>();

            Configuration titleConfig = factionConfig.getSection("titles");

            for (String uuid : titleConfig.getKeys())
            {
                FPlayer player = FPlayerManager.getfPlayers().get(uuid);

                titles.put(player, titleConfig.getString(uuid));
            }

            Faction f = new Faction(leader, tag, desc, members, roles, titles);

            FactionManager.registerFaction(f);
        }


    }

    /**
     * This function saves all player data.
     */
    private static void saveFPlayers()
    {
        File parentFile = new File(path + "players");
        parentFile.mkdir();

        Configuration fPlayerConfig = new Configuration();

        ArrayList<FPlayer> players = new ArrayList<>(FPlayerManager.getfPlayers().values());

        for (FPlayer fPlayer : players)
        {
            File playerFile = new File(parentFile, fPlayer.getUuid() + ".json");
            fPlayerConfig.set("name", fPlayer.getName());
            fPlayerConfig.set("faction", fPlayer.getFactionTag());
            try {
                jsonProvider.save(fPlayerConfig, playerFile);
            } catch (Exception ignored) { }
        }
    }

    /**
     * This function saves all faction data.
     */
    private static void saveFactions()
    {
        File parentFile = new File(path + "factions");
        parentFile.mkdir();

        Configuration factionConfig = new Configuration();
        HashMap<String, Faction> factions = FactionManager.getFactionsByTag();

        for (File file : parentFile.listFiles())
        {
            if (!factions.containsKey(file.getName().replace(".json", "")))
            {
                file.delete();
            }
        }

        for (Faction faction : factions.values())
        {
            File factionFile = new File(parentFile, faction.getTag() + ".json");

            factionConfig.set("leader", faction.getLeader().getUuid());
            factionConfig.set("description", faction.getDesc());

            ArrayList<String> memberUUIDS = new ArrayList<>();
            for (FPlayer member : faction.getMembers())
            {
                memberUUIDS.add(member.getUuid());
            }

            factionConfig.set("members", memberUUIDS);

            String rolePath = "roles.";

            HashMap<FPlayer, FactionRole> roles = faction.getMemberRoles();

            for (Map.Entry<FPlayer, FactionRole> role : roles.entrySet())
            {
                factionConfig.set(rolePath + role.getKey().getUuid(), role.getValue().name());
            }

            String titlePath = "titles.";

            HashMap<FPlayer, String> titles = faction.getMemberTitles();

            for (Map.Entry<FPlayer, String> title : titles.entrySet())
            {
                factionConfig.set(titlePath + title.getKey().getUuid(), title.getValue());
            }

            try {
                jsonProvider.save(factionConfig, factionFile);
            } catch (Exception ignored) {}
        }
    }
}
