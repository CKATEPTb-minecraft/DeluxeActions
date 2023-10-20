package dev.ckateptb.minecraft.deluxeactions.util;

import me.clip.placeholderapi.PlaceholderAPIPlugin;

public class PlaceholderBoolean {
    private static String booleanFalse;
    private static String booleanTrue;

    public static boolean toBoolean(String value) {
        if (booleanFalse == null || booleanTrue == null) {
            booleanTrue = PlaceholderAPIPlugin.booleanTrue();
            booleanFalse = PlaceholderAPIPlugin.booleanFalse();
        }
        return value.equalsIgnoreCase(booleanTrue) || Boolean.parseBoolean(value);
    }
}
