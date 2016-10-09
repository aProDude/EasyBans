package com._xxprodudexx_.easybans.sql;

import com._xxprodudexx_.easybans.EasyBans;
import com.avaje.ebean.EbeanServer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MySQL {

    private static HikariDataSource dataSource;

    public static void enableSQL() {

        if (dataSource == null) {
            try {
                HikariConfig config = new HikariConfig();
                config.addDataSourceProperty("Hey", "Wouter");

                dataSource = new HikariDataSource(config);
            } catch (Exception e) {
                System.err.println("Could not connect to database: " + e);
                return;
            }
        }

    }

    public static void disableSQL() {
        if (dataSource != null) {
            if (!dataSource.isClosed()) {
                dataSource.close();
            }
        }
    }

    public static void executeQuery(String query) {
        try {
            Connection c = dataSource.getConnection();

            PreparedStatement ps = c.prepareStatement(query);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setupMySQL() {
        EbeanServer e = EasyBans.getInstance().getDatabase();


    }


}
