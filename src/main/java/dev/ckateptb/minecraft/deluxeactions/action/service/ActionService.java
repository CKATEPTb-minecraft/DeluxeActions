package dev.ckateptb.minecraft.deluxeactions.action.service;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.DeluxeActions;
import dev.ckateptb.minecraft.deluxeactions.action.Action;
import dev.ckateptb.minecraft.deluxeactions.action.delay.DelayedAction;
import dev.ckateptb.minecraft.deluxeactions.action.event.ActionRegisterEvent;
import dev.ckateptb.minecraft.deluxeactions.action.event.ActionRequestEvent;
import dev.ckateptb.minecraft.deluxeactions.event.DeluxeActionsReloadEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.*;
import java.util.stream.Stream;

@Component
public class ActionService implements Listener {
    private final Map<String, Action> actions = new HashMap<>();

    public boolean registerAction(Action action) {
        return this.registerAction(action, false);
    }

    public boolean registerAction(Action action, boolean override) {
        String name = action.getName().toLowerCase().trim();
        if (override || !this.actions.containsKey(name)) {
            ActionRegisterEvent event = new ActionRegisterEvent(action);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                this.actions.put(name, action);
                return true;
            }
        } else throw new RuntimeException("Failed to register task action " + name +
                ". Reason: action with specified name already registered");
        return false;
    }

    public Optional<Action> findAction(String name) {
        return Optional.ofNullable(this.actions.get(name.toLowerCase().trim()));
    }

    public Stream<Action> getActions() {
        return Collections.unmodifiableCollection(this.actions.values()).stream();
    }

    @EventHandler(priority = EventPriority.LOW)
    public void on(DeluxeActionsReloadEvent event) {
        this.actions.clear();
        Bukkit.getPluginManager().callEvent(new ActionRequestEvent(this));
    }

    public void process(LinkedList<Map.Entry<Action, String>> actions, Player player) {
        if(actions.isEmpty()) return;
        Map.Entry<Action, String> entry = actions.remove(0);
        Action action = entry.getKey();
        String value = entry.getValue();
        Runnable runnable = () -> {
            if (action.process(player, value)) {
                this.process(actions, player);
            }
        };
        if (action instanceof DelayedAction delayed) {
            long ticks = delayed.getDelay().toMillis() / 50;
            Bukkit.getScheduler().runTaskLater(DeluxeActions.getPlugin(), runnable, ticks);
        } else runnable.run();
    }
}
