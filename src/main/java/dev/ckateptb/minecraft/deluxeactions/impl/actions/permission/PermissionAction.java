package dev.ckateptb.minecraft.deluxeactions.impl.actions.permission;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.impl.actions.AbstractAction;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static dev.ckateptb.minecraft.deluxeactions.util.PlaceholderBoolean.toBoolean;

@Component
public class PermissionAction extends AbstractAction {
    public PermissionAction(ActionService actionService) {
        super(actionService);
    }

    @Override
    public String getName() {
        return "PERMISSION";
    }

    @Override
    public boolean process(@Nullable Player player, String value) {
        if (player == null) return false;
        String[] split = value.split(";");
        String permission = split[0];
        boolean result = true;
        if (split.length > 1) {
            result = toBoolean(split[split.length - 1]);
        }
        return player.hasPermission(permission) == result;
    }
}
