package dev.ckateptb.minecraft.deluxeactions.impl.actions.command;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.impl.actions.AbstractAction;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Component
public class ConsoleCommandAction extends AbstractAction {
    public ConsoleCommandAction(ActionService actionService) {
        super(actionService);
    }

    @Override
    public String getName() {
        return "CONSOLE_COMMAND";
    }

    @Override
    public boolean process(@Nullable Player player, String value) {
        return Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                PlaceholderAPI.setPlaceholders(player, value));
    }
}
