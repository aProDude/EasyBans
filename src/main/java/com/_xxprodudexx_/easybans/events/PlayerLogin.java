package com._xxprodudexx_.easybans.events;

import com._xxprodudexx_.easybans.EasyBans;
import com._xxprodudexx_.easybans.api.BanType;
import com._xxprodudexx_.easybans.sql.MySQL;
import com._xxprodudexx_.easybans.utils.BanMetrics;
import com._xxprodudexx_.easybans.utils.MessageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.UUID;

public class PlayerLogin implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (BanMetrics.getBanInfo(e.getPlayer().getUniqueId()) != null) {

            UUID uuid = e.getPlayer().getUniqueId();

            String date = BanMetrics.getBanMetrics().getString(uuid.toString() + ".Date").toString();
            String reason = BanMetrics.getBanMetrics().getString(uuid.toString() + ".Reason");

            BanType type = BanType.BAN;

            String finalReason = MessageManager.getPrefix() + "§cYou have been punished!" +
                    "\n\n§cPunishment Type: §7" + type.getName() +
                    "\n§cDate: §7" + date.toString() +
                    "\n§cReason: §7" + reason +
                    "\n§cExpires in: §7" + type.getExpires() +
                    "\n\n" + MessageManager.getPrefix() + "§cPlease follow our rules!";

            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, finalReason);
            return;
        }
    }

}
