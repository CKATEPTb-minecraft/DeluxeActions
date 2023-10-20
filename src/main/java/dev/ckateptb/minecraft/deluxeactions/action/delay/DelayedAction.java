package dev.ckateptb.minecraft.deluxeactions.action.delay;

import dev.ckateptb.minecraft.deluxeactions.action.Action;

import java.time.Duration;

public interface DelayedAction extends Action {
    Duration getDelay();
}
