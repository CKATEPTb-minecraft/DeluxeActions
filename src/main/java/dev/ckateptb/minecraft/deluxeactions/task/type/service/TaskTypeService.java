package dev.ckateptb.minecraft.deluxeactions.task.type.service;

import com.mojang.datafixers.util.Function3;
import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.event.DeluxeActionsReloadEvent;
import dev.ckateptb.minecraft.deluxeactions.task.Task;
import dev.ckateptb.minecraft.deluxeactions.task.type.TaskType;
import dev.ckateptb.minecraft.deluxeactions.task.type.event.TaskTypeRegisterEvent;
import dev.ckateptb.minecraft.deluxeactions.task.type.event.TaskTypeRequestEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class TaskTypeService implements Listener {
    private final Map<String, TaskType<? extends Task>> types = new HashMap<>();

    public <T extends Task> boolean registerType(String type, Function3<String, TaskType<T>, String, T> createTask) {
        return this.registerType(type, createTask, false);
    }

    public <T extends Task> boolean registerType(String type, Function3<String, TaskType<T>, String, T> createTask, boolean override) {
        String name = type.toLowerCase().trim();
        TaskType<T> taskType = new TaskType<>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public T createTask(String name, String handler) {
                return createTask.apply(name, this, handler);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof TaskType<?> other)) return false;
                return other.getName().equalsIgnoreCase(this.getName());
            }

            @Override
            public int hashCode() {
                return Objects.hashCode(name);
            }
        };
        return this.registerType(taskType, override);
    }

    public <T extends Task> boolean registerType(TaskType<T> type, boolean override) {
        String name = type.getName().toLowerCase().trim();
        if (override || !this.types.containsKey(name)) {
            TaskTypeRegisterEvent event = new TaskTypeRegisterEvent(type);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                this.types.put(name, type);
                return true;
            }
        } else throw new RuntimeException("Failed to register task type " + name +
                ". Reason: type with specified name already registered");
        return false;
    }

    public Optional<TaskType<? extends Task>> findType(String name) {
        return Optional.ofNullable(this.types.get(name.toLowerCase().trim()));
    }

    @EventHandler(priority = EventPriority.LOW)
    public void on(DeluxeActionsReloadEvent event) {
        this.types.clear();
        Bukkit.getPluginManager().callEvent(new TaskTypeRequestEvent(this));
    }
}
