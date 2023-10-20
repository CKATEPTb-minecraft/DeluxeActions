package dev.ckateptb.minecraft.deluxeactions.integration.scheduled;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.config.DeluxeActionsConfig;
import dev.ckateptb.minecraft.deluxeactions.task.Task;
import dev.ckateptb.minecraft.deluxeactions.task.service.TaskService;
import dev.ckateptb.minecraft.deluxeactions.task.type.TaskType;
import dev.ckateptb.minecraft.deluxeactions.task.type.event.TaskTypeRequestEvent;
import dev.ckateptb.minecraft.deluxeactions.task.type.service.TaskTypeService;
import dev.ckateptb.minecraft.deluxeactions.util.ChronoUnitParser;
import dev.ckateptb.minecraft.nicotine.annotation.Schedule;
import dev.ckateptb.minecraft.varflex.internal.org.spongepowered.configurate.ConfigurateException;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@CustomLog
@RequiredArgsConstructor
public class ScheduledActionService implements Listener {
    public static final String TYPE = "SCHEDULED";

    private final TaskService service;
    private final TaskTypeService typeService;
    private final DeluxeActionsConfig config;
    private final ActionService actionService;

    private TaskType<?> type;

    @EventHandler
    public void on(TaskTypeRequestEvent event) {
        this.type = null;
        TaskTypeService service = event.getService();
        if (service.registerType(TYPE, Task::new)) {
            this.type = this.typeService.findType(TYPE).orElse(null);
            log.info("TaskType named {} successful registered", TYPE);
        } else {
            log.warn("Failed to register task type: {}", TYPE);
        }
    }

    @Schedule(initialDelay = 0, fixedRate = 0)
    public void tick() {
        if (this.type == null) return;
        AtomicBoolean update = new AtomicBoolean(false);
        this.service.getTasks(type)
                .filter(task -> {
                    String name = task.getName();
                    Instant now = Instant.now();
                    Duration duration = ChronoUnitParser.parse(task.getHandler());
                    Instant schedules = this.config.getSchedules(name);
                    boolean result = schedules == null || now.isAfter(schedules.plus(duration));
                    if (result) {
                        this.config.updateSchedules(name);
                        update.set(true);
                    }
                    return result;
                })
                .forEach(task -> this.actionService.process(task.getActions()));
        if (update.get()) {
            try {
                this.config.save();
            } catch (ConfigurateException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
