package dev.ckateptb.minecraft.deluxeactions.integration.actions.command;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.integration.actions.AbstractAction;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Component
public class CommandAction extends AbstractAction {
    public CommandAction(ActionService actionService) {
        super(actionService);
    }

    @Override
    public String getName() {
        return "COMMAND";
    }

    @Override
    public boolean process(@Nullable Player player, String value) {
        if (player == null) return false;
        return Bukkit.getServer().dispatchCommand(
                player,
                PlaceholderAPI.setPlaceholders(player, value));
    }

    @Override
    public String getInstruction() {
        return "Write a command that should be executed on behalf of the player. Example: menu open";
    }
}
