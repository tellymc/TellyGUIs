package me.tellymc.pagesAPI;

import org.bukkit.ChatColor;

public class Utils {

    public static String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}