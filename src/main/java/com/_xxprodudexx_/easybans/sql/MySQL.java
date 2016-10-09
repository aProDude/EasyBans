package com._xxprodudexx_.easybans.sql;

import org.bukkit.entity.Player;

import java.sql.Connection;

public class MySQL {

    private Connection connection;

    // todo setup MySQL support

    private static MySQL instance;

    public MySQL(String ip, String username, String password, String db) {

    }


    public void banPlayer(Player p, String reason) {

    }

    public void unbanPlayer(Player p){

    }

    public void getPlayerBanInfo(Player p){

    }

    public static MySQL getInstance(){
        return instance;
    }

}
