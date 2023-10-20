package dev.ckateptb.minecraft.deluxeactions.config;

import dev.ckateptb.common.tableclothconfig.hocon.HoconConfig;
import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.common.tableclothcontainer.annotation.PostConstruct;
import dev.ckateptb.minecraft.deluxeactions.DeluxeActions;
import dev.ckateptb.minecraft.varflex.internal.org.spongepowered.configurate.objectmapping.meta.Comment;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class DeluxeScheduledActionsConfig extends HoconConfig {
    @Comment("!do not touch!")
    private Map<String, Long> schedules = new HashMap<>();

    public void updateSchedules(String task) {
        this.schedules.put(task, Instant.now().toEpochMilli());
    }

    public Instant getSchedules(String task) {
        Long epochMilli = this.schedules.get(task);
        if (epochMilli == null) return null;
        return Instant.ofEpochMilli(epochMilli);
    }

    @SneakyThrows
    @PostConstruct
    public void init() {
        this.load();
        this.save();
    }

    @Override
    public File getFile() {
        return DeluxeActions.getPlugin().getDataFolder().toPath().resolve("scheduled.json").toFile();
    }
}
