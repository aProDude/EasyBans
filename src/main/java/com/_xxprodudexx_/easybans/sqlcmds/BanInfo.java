package com._xxprodudexx_.easybans.sqlcmds;

import com._xxprodudexx_.easybans.EasyBans;
import com._xxprodudexx_.easybans.sql.MySQL;
import com._xxprodudexx_.easybans.utils.MessageManager;
import com._xxprodudexx_.easybans.utils.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanInfo implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String permission = "easybans.commands." + command.getName().toLowerCase();

        if (command.getName().equalsIgnoreCase("sqlbaninfo")) {
            if (!Validate.isPlayer(sender)) {
                MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOPLAYER);
                return true;
            } else {
                if (Validate.hasPermission(sender, permission)) {

                    if (args.length == 0) {
                        MessageManager.getManager().message(sender, ChatColor.RED, "Usage: /baninfo <player>");
                        return true;
                    }

                    OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
                    if (MySQL.getBanInfo(p) == null) {
                        MessageManager.getManager().severeMessage(sender, MessageManager.MessageType.NOTBANNED);
                        return true;
                    } else {
                        String info = EasyBans.getAPI().getSqlBanInfo(p);
                        sender.sendMessage(info);
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
