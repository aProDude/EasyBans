package com._xxprodudexx_.easybans.sqlcmds;

import com._xxprodudexx_.easybans.EasyBans;
import com._xxprodudexx_.easybans.utils.MessageManager;
import com._xxprodudexx_.easybans.utils.Validate;
import com.avaje.ebeaninternal.server.cluster.mcast.MessageAck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.time.Instant;

public class Ban implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String permission = "easybans.commands." + command.getName().toLowerCase();

        if (command.getName().equalsIgnoreCase("sqlban")) {
            if (!Validate.isPlayer(sender)) {
                MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOPLAYER);
                return true;
            } else {
                if (Validate.hasPermission(sender, permission)) {

                    if (args.length < 2) {
                        MessageManager.getManager().message(sender, ChatColor.RED, "Usage: /sqlban <player> <reason>");
                        return true;
                    }

                    Player t = Bukkit.getPlayer(args[0]);

                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        sb.append(args[i]).append(" ");
                    }

                    String reason = sb.toString().trim();

                    if (t == null) {
                        OfflinePlayer ot = Bukkit.getOfflinePlayer(args[0]);

                        if (EasyBans.getAPI().getSqlBanInfo(ot) != null){
                            MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.ALREADYBANNED);
                            return true;
                        }
                        EasyBans.getAPI().sqlBan(null, ot, Timestamp.from(Instant.now()), reason);
                        MessageManager.getManager().message(sender, ChatColor.GREEN, "Player " + ot.getName() + " has been banned!");
                        return true;
                    } else {
                        if (EasyBans.getAPI().getSqlBanInfo(t) != null){
                            MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.ALREADYBANNED);
                            return true;
                        }
                        EasyBans.getAPI().sqlBan(t, null, Timestamp.from(Instant.now()), reason);
                        MessageManager.getManager().message(sender, ChatColor.GREEN, "Player " + t.getName() + " has been banned!");
                        return true;
                    }

                } else {
                    MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOPERMISSION);
                    return true;
                }
            }
        }

        return true;
    }
}
