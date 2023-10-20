package dev.ckateptb.minecraft.deluxeactions.task.type.event;

import dev.ckateptb.minecraft.deluxeactions.task.type.service.TaskTypeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public class TaskTypeRequestEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final TaskTypeService service;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
