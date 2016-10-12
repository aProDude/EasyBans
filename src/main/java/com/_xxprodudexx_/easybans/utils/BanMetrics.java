package com._xxprodudexx_.easybans.utils;

import com._xxprodudexx_.easybans.api.BanType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class BanMetrics {

    private static BanMetrics metrics = new BanMetrics();
    private static File file;
    private static File configFile;
    private static FileConfiguration config;
    private static FileConfiguration mainConfig;

    public static FileConfiguration getBanMetrics() {
        return config;
    }

    public static void setupBanConfiguration(Plugin p) {
        loadBanConfiguration(p);
    }

    public static void loadBanConfiguration(Plugin p) {
        try {
            File dataFolder = p.getDataFolder();

            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

            file = new File(dataFolder, "bans.yml");

            if (!file.exists()) {
                file.createNewFile();
            }

            config = YamlConfiguration.loadConfiguration(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (config.options().header() == null) {
            config.options().header("This is the bans.yml - file. Here will all the bans be stored!");
        }

        saveBanConfiguration();
    }

    public static void saveBanConfiguration() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reloadBanConfiguration() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static File getFile() {
        return file;
    }

    @SuppressWarnings("all")
    public static void ban(UUID uuid, Timestamp date, String reason) {
        if (config.get(uuid.toString()) != null) {
            return;
        } else {
            config.createSection(uuid.toString());
            config.set(uuid.toString() + ".Name", Bukkit.getPlayer(uuid).getName());
            config.set(uuid.toString() + ".Date", date.from(Instant.now()));
            config.set(uuid.toString() + ".Reason", reason);
            saveBanConfiguration();
        }
        saveBanConfiguration();
    }

    public static void unban(UUID uuid) {
        if (config.get(uuid.toString()) == null) {
            return;
        } else {
            config.set(uuid.toString(), null);
            saveBanConfiguration();
        }
        saveBanConfiguration();
    }

    public static String kick(Player target, BanType type, Timestamp date, String reason) {
        String finalReason =
                MessageManager.getPrefix() + "§cYou have been punished!" +
                        "\n\n§cPunishment Type: §7" + type.getName() +
                        "\n§cDate: §7" + date.toString() +
                        "\n§cReason: §7" + reason +
                        "\n§cExpires in: §7" + type.getExpires() +
                        "\n\n" + MessageManager.getPrefix() + "§cPlease follow our rules!";
        target.kickPlayer(finalReason);
        return finalReason;
    }

    public static String getBanInfo(UUID uuid) {
        if (config.get(uuid.toString()) == null || uuid == null) {
            return null;
        }
        String name = config.getString(uuid.toString() + ".Name");
        String date = config.get(uuid.toString() + ".Date").toString();
        String reason = config.getString(uuid.toString() + ".Reason");

        String banInfo =
                "§aBan Information for Player: " + uuid.toString() +
                        "\n§cName: §7" + name +
                        "\n§cDate of ban §7" + date +
                        "\n§cReason: §7" + reason;

        return banInfo;

    }


}
