package dev.ckateptb.minecraft.deluxeactions.integration.actions.delay;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.delay.DelayedAction;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.integration.actions.AbstractAction;
import dev.ckateptb.minecraft.deluxeactions.util.ChronoUnitParser;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

@Component
public class DelayAction extends AbstractAction implements DelayedAction {
    public DelayAction(ActionService actionService) {
        super(actionService);
    }

    @Override
    public String getName() {
        return "DELAY";
    }

    @Override
    public boolean process(@Nullable Player player, String value) {
        return true;
    }

    @Override
    public String getInstruction() {
        return "Pass two arguments via ';', the first is ChronoUnit, the second amount. Example SECONDS;5";
    }

    @Override
    public Duration getDelay(String value) {
        return ChronoUnitParser.parse(value);
    }
}
