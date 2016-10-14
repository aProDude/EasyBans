package com._xxprodudexx_.easybans.sql;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class SQLMetrics {

    private static File file;
    private static FileConfiguration config;

    public static void setupSQLMetrics(Plugin p) {
        loadSQLMetrics(p);
        enterSQLOptions();
    }

    public static void loadSQLMetrics(Plugin p) {
        try {

            File dataFolder = p.getDataFolder();

            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

            file = new File(dataFolder, "mysql.yml");

            if (!file.exists()) {
                file.createNewFile();
            }

            config = YamlConfiguration.loadConfiguration(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (config.options().header() == null) {
            config.options().header("This is the mysql.yml - file. Here you can enter your MySQL information");
        }
        save();
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enterSQLOptions() {
        if (config.getConfigurationSection("MySQL-Data") == null) {
            config.createSection("MySQL-Data");
            config.set("MySQL-Data.IP", "EnterYourIP");
            config.set("MySQL-Data.Username", "EnterYourUsername");
            config.set("MySQL-Data.Password", "EnterYourPassword");
            config.set("MySQL-Data.Database", "EnterYourDatabase");
            save();
        } else {
            return;
        }
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, String value) {
        config.set(path, value);
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static File getFile() {
        return file;
    }


}
