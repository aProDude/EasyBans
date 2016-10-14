package com._xxprodudexx_.easybans.sql;

import org.bukkit.entity.Player;

import java.sql.*;
import java.time.Instant;

public class MySQL {

    private static Connection connection;

    public static void setupSQL(String ip, String userName, String password, String database) {
        try {

           connection = DriverManager.getConnection("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ban(Player p, Timestamp timestamp, String reason) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into bans (uuid, date, reason)\nvalues('"
                    + p.getUniqueId() + "', '" + timestamp.from(Instant.now()) + "', '" + reason + "');");

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
