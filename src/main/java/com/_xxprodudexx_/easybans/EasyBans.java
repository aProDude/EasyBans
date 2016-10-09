package com._xxprodudexx_.easybans;

import com._xxprodudexx_.easybans.api.BanManagementAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class EasyBans extends JavaPlugin implements BanManagementAPI {

    private static BanManagementAPI api;

    @Override
    public void onEnable() {
        if (api == null) {
            api = this;
        }
    }

    public void ban(UUID uuid, String reason) {
        Bukkit.getPlayer(uuid).kickPlayer(reason);
        Bukkit.getPlayer(uuid).setBanned(true);
    }

    public void unban(UUID uuid) {
        Bukkit.getPlayer(uuid).setBanned(false);
    }

}
