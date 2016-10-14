package com._xxprodudexx_.easybans.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

public interface BanManagementAPI {

    void kick(Player t, BanType type, Timestamp date, String reason);

    void ban(UUID uuid, Timestamp timestamp, String reason);

    void sqlBan(Player p, Timestamp timestamp, String reason);

    void unban(UUID uuid);

    void sqlUnban(Player p);

    String getBanInfo(UUID uuid);
}
