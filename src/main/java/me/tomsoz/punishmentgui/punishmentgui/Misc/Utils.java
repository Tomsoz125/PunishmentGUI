package me.tomsoz.punishmentgui.punishmentgui.Misc;

import org.bukkit.ChatColor;

public class Utils {
    public static String chat(String textToTranslate) {
        return ChatColor.translateAlternateColorCodes('&', textToTranslate);
    }
}
