package dev.ckateptb.minecraft.deluxeactions.action.event;

import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public class ActionRequestEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ActionService service;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
