package dev.ckateptb.minecraft.deluxeactions.integration.player;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.task.Task;
import dev.ckateptb.minecraft.deluxeactions.task.type.event.TaskTypeRequestEvent;
import dev.ckateptb.minecraft.deluxeactions.task.type.service.TaskTypeService;
import lombok.CustomLog;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Component
@CustomLog
public class PlayerActionService implements Listener {
    public static final String TYPE = "PLAYER_ACTION";

    @EventHandler
    public void on(TaskTypeRequestEvent event) {
        TaskTypeService service = event.getService();
        if (service.registerType(TYPE, Task::new)) {
            log.info("TaskType named {} successful registered", TYPE);
        } else {
            log.warn("Failed to register task type: {}", TYPE);
        }
    }
}
