package com._xxprodudexx_.easybans.utils;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

public class Validate {

    public static boolean isPlayer(Permissible p) {
        return p instanceof Player;
    }

    public static boolean hasPermission(Permissible p, String permission) {
        return p.hasPermission(permission);
    }
}
