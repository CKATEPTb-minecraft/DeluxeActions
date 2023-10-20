package dev.ckateptb.minecraft.deluxeactions.action;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public interface Action {
    String getName();

    boolean process(@Nullable Player player, String value);
}
