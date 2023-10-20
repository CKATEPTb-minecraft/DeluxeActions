package dev.ckateptb.minecraft.deluxeactions.impl.player.listener;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.impl.player.PlayerActionService;
import dev.ckateptb.minecraft.deluxeactions.impl.player.hadler.PlayerActivationHandler;
import dev.ckateptb.minecraft.deluxeactions.task.service.TaskService;
import dev.ckateptb.minecraft.deluxeactions.task.type.service.TaskTypeService;
import io.papermc.paper.event.player.PlayerArmSwingEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.LinkedList;

@Component
@RequiredArgsConstructor
public class PlayerActionListener implements Listener {
    private final TaskService service;
    private final TaskTypeService typeService;
    private final ActionService actionService;

    public void activate(Player player, PlayerActivationHandler handler) {
        this.typeService.findType(PlayerActionService.TYPE)
                .ifPresent(type -> this.service.getTasks(type)
                        .filter(task -> task.getHandler().equalsIgnoreCase(handler.name()))
                        .forEach(task -> this.actionService.process(new LinkedList<>(task.getActions().entrySet()), player)));
    }

    @EventHandler
    public void on(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        boolean sneaking = event.isSneaking();
        this.activate(player, sneaking ?
                PlayerActivationHandler.SNEAK :
                PlayerActivationHandler.SNEAK_RELEASE);
    }

    @EventHandler
    public void on(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player player)) return;
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            this.activate(player, PlayerActivationHandler.FALL);
        }
    }

    @EventHandler
    public void on(PlayerArmSwingEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.ADVENTURE) return;
        Entity targetEntity = player.getTargetEntity(5);
        if (targetEntity == null) return;
        this.on(new PlayerInteractEvent(player, Action.LEFT_CLICK_AIR, null, null, BlockFace.UP));
    }

    @EventHandler
    public void on(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        Player player = event.getPlayer();
        boolean isBlock = player.getTargetBlockExact(5) != null;
        boolean isEntity = player.getTargetEntity(5) != null;
        Action action = event.getAction();
        PlayerActivationHandler activationMethod = null;
        if (action.isLeftClick()) {
            activationMethod = isEntity ? PlayerActivationHandler.LEFT_CLICK_ENTITY : isBlock ? PlayerActivationHandler.LEFT_CLICK_BLOCK : PlayerActivationHandler.LEFT_CLICK;
        } else if (action.isRightClick()) {
            activationMethod = isEntity ? PlayerActivationHandler.RIGHT_CLICK_ENTITY : isBlock ? PlayerActivationHandler.RIGHT_CLICK_BLOCK : PlayerActivationHandler.RIGHT_CLICK;
        }
        if (activationMethod != null) this.activate(player, activationMethod);
    }

    @EventHandler
    public void on(PlayerSwapHandItemsEvent event) {
        this.activate(event.getPlayer(), PlayerActivationHandler.HAND_SWAP);
    }
}
