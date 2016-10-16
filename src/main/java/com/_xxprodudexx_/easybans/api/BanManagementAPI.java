package com._xxprodudexx_.easybans.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.UUID;

public interface BanManagementAPI {

    void sqlKick(Player t, BanType type, Timestamp timestamp, String reason);

    void sqlBan(Player p, OfflinePlayer op, Timestamp timestamp, String reason);

    void sqlUnban(OfflinePlayer p);

    String getSqlBanInfo(OfflinePlayer p);
}
