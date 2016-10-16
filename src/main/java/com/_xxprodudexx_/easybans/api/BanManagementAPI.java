package com._xxprodudexx_.easybans.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.UUID;

public interface BanManagementAPI {

    void kick(Player t, BanType type, Timestamp date, String reason);

    void ban(UUID uuid, Timestamp timestamp, String reason);

    void sqlBan(Player p, OfflinePlayer op, Timestamp timestamp, String reason);

    void unban(UUID uuid);

    void sqlUnban(OfflinePlayer p);

    String getBanInfo(UUID uuid);

    String getSqlBanInfo(OfflinePlayer p);
}
