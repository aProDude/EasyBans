package com._xxprodudexx_.easybans.sqlcmds;

import com._xxprodudexx_.easybans.EasyBans;
import com._xxprodudexx_.easybans.api.BanType;
import com._xxprodudexx_.easybans.utils.MessageManager;
import com._xxprodudexx_.easybans.utils.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.time.Instant;

public class Kick implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String permission = "easybans.commands." + command.getName().toLowerCase();

        if (command.getName().equalsIgnoreCase("sqlkick")) {
            if (!Validate.isPlayer(sender)) {
                MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOPLAYER);
                return true;
            } else {
                if (Validate.hasPermission(sender, permission)) {

                    if (args.length < 2) {
                        MessageManager.getManager().message(sender, ChatColor.RED, "Usage: /sqlkick <player> <reason>");
                        return true;
                    }

                    Player t = Bukkit.getPlayer(args[0]);

                    if (t == null) {
                        MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOTONLINE);
                        return true;
                    }

                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        sb.append(args[i]).append(" ");
                    }

                    String reason = sb.toString().trim();

                    EasyBans.getAPI().sqlKick(t, BanType.KICK, Timestamp.from(Instant.now()), reason);

                } else {
                    MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOPERMISSION);
                    return true;
                }
            }
        }

        return true;
    }
}
