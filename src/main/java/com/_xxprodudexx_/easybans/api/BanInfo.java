package com._xxprodudexx_.easybans.api;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.UUID;

public class BanInfo {

    private UUID playerUUID;
    private String reason;
    private File file;
    private FileConfiguration config;
    ;

    public BanInfo(UUID playerUUID, String reason) {
        this.playerUUID = playerUUID;
        this.reason = reason;

        // todo store data in config
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getReason() {
        return reason;
    }


}
