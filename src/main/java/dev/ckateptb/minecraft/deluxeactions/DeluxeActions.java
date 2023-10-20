package dev.ckateptb.minecraft.deluxeactions;

import dev.ckateptb.common.tableclothcontainer.IoC;
import dev.ckateptb.minecraft.deluxeactions.event.DeluxeActionsReloadEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public class DeluxeActions extends JavaPlugin {
    private static Logger logger;
    @Getter
    private static DeluxeActions plugin;

    public static Logger log() {
        return logger;
    }

    public DeluxeActions() {
        plugin = this;
        logger = this.getSLF4JLogger();
        IoC.registerBean(this, DeluxeActions.class);
        IoC.scan(DeluxeActions.class);
    }

    @Override
    public void onEnable() {
        this.reload();
    }

    public void reload() {
        Bukkit.getScheduler().runTaskLater(this, () -> Bukkit.getPluginManager().callEvent(new DeluxeActionsReloadEvent()), 0);
    }
}