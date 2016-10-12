package com._xxprodudexx_.easybans.cmds;

import com._xxprodudexx_.easybans.EasyBans;
import com._xxprodudexx_.easybans.utils.BanMetrics;
import com._xxprodudexx_.easybans.utils.MessageManager;
import com._xxprodudexx_.easybans.utils.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanInfoCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String permission = "easybans.commands." + command.getName().toLowerCase();

        if (command.getName().equalsIgnoreCase("baninfo")) {
            if (!Validate.isPlayer(sender)) {
                MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOPLAYER);
                return true;
            } else {
                if (Validate.hasPermission(sender, permission)) {

                    if (args.length == 0) {
                        MessageManager.getManager().message(sender, ChatColor.RED, "Usage: /baninfo <player>");
                        return true;
                    }

                    OfflinePlayer op = Bukkit.getPlayer(args[0]);

                    if (op == null) {
                        MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOTBANNED);
                        return true;
                    } else {
                        if (BanMetrics.getBanInfo(op.getUniqueId()) == null) {
                            MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOTBANNED);
                            return true;
                        } else {
                            String banInfo = EasyBans.getAPI().getBanInfo(op.getUniqueId());
                            sender.sendMessage(MessageManager.getPrefix() + banInfo);
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
