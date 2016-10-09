package com._xxprodudexx_.easybans;

import com._xxprodudexx_.easybans.api.BanInfo;
import com._xxprodudexx_.easybans.api.BanManagementAPI;
import com._xxprodudexx_.easybans.sql.MySQL;
import com._xxprodudexx_.easybans.utils.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class EasyBans extends JavaPlugin implements BanManagementAPI {

    private static EasyBans instance;
    private static BanManagementAPI api;
    private static BanInfo info;

    /*
    Todo: BanInfo afmaken: Bans storen, waarde opvragen en returnen bij BanInfo getBanInfo(UUID uuid); (Zie API)
    Todo: Kick, Ban, Unban, Report, Warn, StaffChat en testen!
    Todo: Configuration class afmaken, en testen !
    Todo: Testen!
    Todo: Pushen naar GitHub!
    Todo: PlayerLoginEvents, StaffChat en testen!
    Todo: README.md afmaken (Zie GitHub)
    Todo: Finished? Upload naar Spigiot
     */

    @Override
    public void onEnable() {
        instance = this;

        if (api == null) {
            api = this;
        }

        Configuration.setup(this);

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public final void ban(UUID uuid, String reason) {
        MySQL.getInstance().banPlayer(Bukkit.getPlayer(uuid), reason);
    }

    public final void unban(UUID uuid) {
        MySQL.getInstance().unbanPlayer(Bukkit.getPlayer(uuid));
    }

    public final void getBanInfo(UUID uuid) {
        MySQL.getInstance().getPlayerBanInfo(Bukkit.getPlayer(uuid));
    }


}
