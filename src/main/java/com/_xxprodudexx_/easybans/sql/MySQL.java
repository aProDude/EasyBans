package com._xxprodudexx_.easybans.sql;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.time.Instant;
import java.util.UUID;

public class MySQL {

    private static Connection connection;

    public static void setupSQL(String ip, String database, String password, String userName) {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database + "?user=" + userName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ban(Player p, OfflinePlayer op, Timestamp timestamp, String reason) {

        String playerName;
        UUID playerUuid;

        if (p == null) {
            playerName = op.getName();
            playerUuid = op.getUniqueId();
        } else if (op == null) {
            playerName = p.getName();
            playerUuid = p.getUniqueId();
        } else {
            playerName = null;
            playerUuid = null;
        }

        try {
            PreparedStatement statement = connection.prepareStatement("insert into bans (uuid, name, date, reason)\nvalues('"
                    + playerUuid + "', '" + playerName + "', '" + timestamp.from(Instant.now()) + "', '" + reason + "');");

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unban(OfflinePlayer p) {
        try {

            PreparedStatement statement = connection.prepareStatement("delete from bans where uuid = '" + p.getUniqueId() + "';");

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBanInfo(OfflinePlayer p) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from bans where uuid = '" + p.getUniqueId() + "';");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String uuid = result.getString("uuid");
                String name = result.getString("name");
                String date = result.getString("date");
                String reason = result.getString("reason");

                String banInfo =
                        "§aSQLBan Information for Player: " + uuid +
                                "\n§cName: §7" + name +
                                "\n§cDate of ban: §7" + date +
                                "\n§cReason: §7" + reason;

                return banInfo;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
