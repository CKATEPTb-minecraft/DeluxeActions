package dev.ckateptb.minecraft.deluxeactions.task.type;

import dev.ckateptb.minecraft.deluxeactions.task.Task;

public interface TaskType<T extends Task> {
    String getName();

    T createTask(String name, String handler);
}
