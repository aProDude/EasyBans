package com._xxprodudexx_.easybans.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.Timestamp;
import java.util.UUID;

public interface BanManagementAPI {

    void kick(Player t, BanType type, Timestamp date, String reason);

    void ban(UUID uuid, Timestamp timestamp, String reason);

    void unban(UUID uuid);

    void report(UUID uuid, Player reporter, Timestamp timestamp, String reason);

    String getBanInfo(UUID uuid);
}
