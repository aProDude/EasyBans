package com._xxprodudexx_.easybans.cmds;

import com._xxprodudexx_.easybans.api.BanType;
import com._xxprodudexx_.easybans.utils.BanMetrics;
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

public class KickCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String permission = "easybans.commands." + command.getName().toLowerCase();

        if (command.getName().equalsIgnoreCase("kick")) {
            if (!Validate.isPlayer(sender)) {
                MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOPLAYER);
                return true;
            } else {
                if (Validate.hasPermission(sender, permission)) {
                    if (args.length < 2) {
                        MessageManager.getManager().message(sender, ChatColor.RED, "Usage: /kick <player> <reason>");
                        return true;
                    } else {
                        Player t = Bukkit.getPlayer(args[0]);

                        if (t == null) {
                            MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOTONLINE);
                            return true;
                        } else {

                            StringBuilder sb = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                sb.append(args[i]).append(" ");
                            }

                            String reason = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());

                            BanMetrics.kick(t, BanType.KICK, Timestamp.from(Instant.now()), reason);

                            MessageManager.getManager().message(sender, ChatColor.GREEN, "Player " + t.getName() + " has been kicked!");
                            return true;
                        }
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
