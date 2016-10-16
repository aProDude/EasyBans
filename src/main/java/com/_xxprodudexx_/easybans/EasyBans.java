package com._xxprodudexx_.easybans;

import com._xxprodudexx_.easybans.api.BanManagementAPI;
import com._xxprodudexx_.easybans.api.BanType;
import com._xxprodudexx_.easybans.cmds.BanCommand;
import com._xxprodudexx_.easybans.cmds.BanInfoCommand;
import com._xxprodudexx_.easybans.cmds.KickCommand;
import com._xxprodudexx_.easybans.cmds.UnbanCommand;
import com._xxprodudexx_.easybans.sql.MySQL;
import com._xxprodudexx_.easybans.sql.SQLMetrics;
import com._xxprodudexx_.easybans.sqlcmds.Ban;
import com._xxprodudexx_.easybans.sqlcmds.BanInfo;
import com._xxprodudexx_.easybans.sqlcmds.Unban;
import com._xxprodudexx_.easybans.utils.BanMetrics;
import com._xxprodudexx_.easybans.utils.ListenerManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Timestamp;
import java.util.UUID;

public class EasyBans extends JavaPlugin implements BanManagementAPI {

    private static EasyBans instance;
    private static BanManagementAPI api;

    /*
    todo: test out the bancommand, if a player is already banned, if yes, cancel the ban!
     */

    @Override
    public void onEnable() {
        instance = this;

        if (api == null) {
            api = this;
        }
        BanMetrics.setupBanConfiguration(this);
        SQLMetrics.setupSQLMetrics(this);
        setupMySQL();
        ListenerManager.registerListeners(this);
        registerSQLCommands();

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static EasyBans getInstance() {
        return instance;
    }

    public final void kick(Player t, BanType type, Timestamp date, String reason) {
        BanMetrics.kick(t, type, date, reason);
    }

    public final void ban(UUID uuid, Timestamp timestamp, String reason) {
        BanMetrics.ban(uuid, timestamp, reason);
    }

    public final void sqlBan(Player p, OfflinePlayer op, Timestamp timestamp, String reason) {
        MySQL.ban(p, op, timestamp, reason);
    }

    public final void unban(UUID uuid) {
        BanMetrics.unban(uuid);
    }

    public final void sqlUnban(OfflinePlayer p) {
        MySQL.unban(p);
    }

    public final String getBanInfo(UUID uuid) {
        String banInfo = BanMetrics.getBanInfo(uuid);
        return banInfo;
    }

    public final String getSqlBanInfo(OfflinePlayer p) {
        String banInfo = MySQL.getBanInfo(p);
        return banInfo;
    }

    public static BanManagementAPI getAPI() {
        return api;
    }

    public void registerCommands() {
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("baninfo").setExecutor(new BanInfoCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("sqlbaninfo").setExecutor(new BanInfo());
    }

    public void registerSQLCommands() {
        getCommand("sqlbaninfo").setExecutor(new BanInfo());
        getCommand("sqlunban").setExecutor(new Unban());
        getCommand("sqlban").setExecutor(new Ban());
    }

    public void setupMySQL() {
        FileConfiguration c = SQLMetrics.getConfig();
        String ip = c.getString("MySQL-Data.IP");
        String username = c.getString("MySQL-Data.Username");
        String password = c.getString("MySQL-Data.Password");
        String database = c.getString("MySQL-Data.Database");
        MySQL.setupSQL(ip, database, password, username);
    }

}
