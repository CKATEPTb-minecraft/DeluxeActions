package dev.ckateptb.minecraft.deluxeactions.config;

import dev.ckateptb.common.tableclothconfig.hocon.HoconConfig;
import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.common.tableclothcontainer.annotation.PostConstruct;
import dev.ckateptb.minecraft.deluxeactions.DeluxeActions;
import dev.ckateptb.minecraft.varflex.internal.org.spongepowered.configurate.objectmapping.meta.Comment;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.annotation.Nullable;
import java.io.File;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class DeluxeActionsConfig extends HoconConfig {
    @Comment("""
            Examples:
                example-task {
                    actions=[
                        "[PERMISSION] example.permission;true",
                        "[CONDITION] %player_is_sneaking%;true",
                        "[CONSOLE_COMMAND] say Hello, %player_name%"
                    ]
                    handle="HAND_SWAP"
                    type="PLAYER_ACTION"
                }
            """)
    private Map<String, TaskDeclaration> tasks = new HashMap<>();

    @SneakyThrows
    @PostConstruct
    public void init() {
        this.load();
        this.save();
    }

    @Override
    public File getFile() {
        return DeluxeActions.getPlugin().getDataFolder().toPath().resolve("config.json").toFile();
    }

    public record TaskDeclaration(String type, String handle, List<String> actions, @Nullable Instant lastIssued) {
    }
}
