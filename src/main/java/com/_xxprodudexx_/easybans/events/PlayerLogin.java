package com._xxprodudexx_.easybans.events;

import com._xxprodudexx_.easybans.EasyBans;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (EasyBans.getAPI().getSqlBanInfo(e.getPlayer()) != null) {

            String reason = EasyBans.getAPI().getSqlBanInfo(e.getPlayer());

            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, reason);
            return;
        }
    }

}
