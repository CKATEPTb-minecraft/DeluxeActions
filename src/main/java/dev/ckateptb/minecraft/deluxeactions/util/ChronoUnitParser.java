package dev.ckateptb.minecraft.deluxeactions.util;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ChronoUnitParser {
    public static Duration parse(String value) {
        String[] split = value.split(";");
        if (split.length != 2) return Duration.ZERO;
        ChronoUnit unit = ChronoUnit.valueOf(split[0].toLowerCase());
        return Duration.of(Long.parseLong(split[1]), unit);
    }
}
