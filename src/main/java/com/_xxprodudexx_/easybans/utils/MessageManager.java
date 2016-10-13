package com._xxprodudexx_.easybans.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageManager {

    private static String prefix = "§c[§9EasyBans§c]§r §8§l>>§r §r";

    public static MessageManager getManager() {
        return new MessageManager();
    }

    public static String getPrefix() {
        return prefix;
    }

    public enum MessageType {

        NOPERMISSION(ChatColor.RED, "Invalid Permissions."),
        NOPLAYER(ChatColor.RED, "You must be a player to perform this command.\n If you believe this is an error, please contact _xXProDudeXx_"),
        NOTONLINE(ChatColor.RED, "This player is not online!"),
        NOTBANNED(ChatColor.RED, "This player does not have an active ban!"),
        ALREADYBANNED(ChatColor.RED, "This player already has an active ban!");

        private ChatColor color;
        private String message;

        MessageType(ChatColor color, String message) {
            this.color = color;
            this.message = prefix + color + message;
        }

        public ChatColor getColor() {
            return color;
        }

        public String getMessage() {
            return message;
        }


    }

    public void severeMessage(CommandSender sender, MessageType type) {
        sender.sendMessage(type.getColor() + type.getMessage());
        return;
    }

    public void message(CommandSender sender, ChatColor color, String message) {
        sender.sendMessage(prefix + color + message);
        return;
    }

}

