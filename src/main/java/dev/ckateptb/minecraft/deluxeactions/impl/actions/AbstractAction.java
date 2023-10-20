package dev.ckateptb.minecraft.deluxeactions.impl.actions;

import dev.ckateptb.minecraft.deluxeactions.action.Action;
import dev.ckateptb.minecraft.deluxeactions.action.event.ActionRequestEvent;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@CustomLog
@RequiredArgsConstructor
public abstract class AbstractAction implements Action, Listener {
    private final ActionService actionService;

    @EventHandler
    public void on(ActionRequestEvent event) {
        if (this.actionService.registerAction(this)) {
            log.info("Action named {} successful registered", this.getName());
        } else {
            log.warn("Failed to register action named: {}", this.getName());
        }
    }
}
