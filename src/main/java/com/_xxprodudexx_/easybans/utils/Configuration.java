package com._xxprodudexx_.easybans.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private File file;
    private FileConfiguration config;

    public Configuration(Plugin p, String name) {
        try {
            if (file.getName().endsWith(".yml")) {
                file = new File(p.getDataFolder(), name + ".yml");
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        save();
    }

    public static void setup(Plugin p){
        Configuration config = new Configuration(p, "config");
        config.createSection("Configuration");
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String path, String value) {
        config.set(path, value);
        save();
    }

    public void createSection(String section){
        config.createSection(section);
        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }


}
