package com._xxprodudexx_.easybans.api;

import org.bukkit.Bukkit;

import java.util.UUID;

public class BanInfo {

    public enum BanType {

        BAN("Ban"), KICK("Kick"), UNBAN("Unban");

        private String name;

        BanType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private UUID uuid;
    private String name;
    private boolean isBanned;

    public BanInfo(UUID uuid, BanType type) {
        this.uuid = uuid;
        this.name = type.getName();
    }

    public BanInfo(UUID uuid){
        this.uuid = uuid;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public String getPlayerName() {
        return Bukkit.getPlayer(uuid).getName();
    }

    public String getName() {
        return name;
    }

}
