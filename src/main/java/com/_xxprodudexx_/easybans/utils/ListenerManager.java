package com._xxprodudexx_.easybans.utils;

import com._xxprodudexx_.easybans.events.PlayerLogin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager implements Listener {

    public static List<Listener> listeners = new ArrayList<Listener>();

    public static void registerListeners(Plugin p) {
        listeners.add(new PlayerLogin());

        for (Listener l : listeners) {
            Bukkit.getPluginManager().registerEvents(l, p);
        }
    }

}
