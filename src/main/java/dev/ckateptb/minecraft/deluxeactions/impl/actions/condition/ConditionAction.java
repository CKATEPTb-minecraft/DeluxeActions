package dev.ckateptb.minecraft.deluxeactions.impl.actions.condition;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.impl.actions.AbstractAction;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static dev.ckateptb.minecraft.deluxeactions.util.PlaceholderBoolean.toBoolean;

@Component
public class ConditionAction extends AbstractAction {
    public ConditionAction(ActionService actionService) {
        super(actionService);
    }

    @Override
    public String getName() {
        return "CONDITION";
    }

    @Override
    public boolean process(@Nullable Player player, String value) {
        String[] split = value.split(";");
        String condition = PlaceholderAPI.setPlaceholders(player, split[0]);
        boolean result = true;
        if (split.length > 1) {
            result = toBoolean(split[split.length - 1]);
        }
        return toBoolean(condition) == result;
    }
}
