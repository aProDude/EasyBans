package com._xxprodudexx_.easybans.sql;

import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;

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

    public static void ban(Player p, Timestamp timestamp, String reason) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into bans (uuid, name, date, reason)\nvalues('"
                    + p.getUniqueId() + "', '" + p.getName() + "', '" + timestamp.from(Instant.now()) + "', '" + reason + "');");

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unban(Player p) {
        try {

            PreparedStatement statement = connection.prepareStatement("delete from bans where uuid = '" + p.getUniqueId() + "';");

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getBanInfo(Player p) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from bans where uuid = '" + p.getUniqueId() + "';");
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
