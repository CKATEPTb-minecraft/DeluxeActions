package dev.ckateptb.minecraft.deluxeactions.command;

import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.DeluxeActions;
import dev.ckateptb.minecraft.deluxeactions.command.sender.DeluxeActionCommandSender;
import dev.ckateptb.minecraft.deluxeactions.config.DeluxeActionsConfig;
import dev.ckateptb.minecraft.supervisor.Command;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

@Getter
@Component
@RequiredArgsConstructor
public class DeluxeActionsCommand implements Command<DeluxeActions> {
    private final DeluxeActions plugin;
    private final DeluxeActionsConfig config;

    @CommandMethod("deluxeaction|da reload|r")
    @CommandPermission("deluxeactions.command.reload")
    public void processReload(CommandSender commandSender) {
        this.config.load();
        this.plugin.reload();
        DeluxeActionCommandSender sender = DeluxeActionCommandSender.of(commandSender);
        sender.sendMessage("&6DeluxeActions &bsuccessful reloaded");
    }
}
