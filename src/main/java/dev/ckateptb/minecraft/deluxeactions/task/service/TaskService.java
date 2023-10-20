package dev.ckateptb.minecraft.deluxeactions.task.service;

import dev.ckateptb.common.tableclothcontainer.annotation.Component;
import dev.ckateptb.minecraft.deluxeactions.action.Action;
import dev.ckateptb.minecraft.deluxeactions.action.service.ActionService;
import dev.ckateptb.minecraft.deluxeactions.config.DeluxeActionsConfig;
import dev.ckateptb.minecraft.deluxeactions.event.DeluxeActionsReloadEvent;
import dev.ckateptb.minecraft.deluxeactions.task.Task;
import dev.ckateptb.minecraft.deluxeactions.task.type.TaskType;
import dev.ckateptb.minecraft.deluxeactions.task.type.service.TaskTypeService;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
@CustomLog
@RequiredArgsConstructor
public class TaskService implements Listener {
    public final Map<String, Task> tasks = new HashMap<>();
    private final DeluxeActionsConfig config;
    private final TaskTypeService typeService;
    private final ActionService actionService;

    public void registerTask(Task task) {
        String name = task.getName().toLowerCase().trim();
        this.tasks.put(name, task);
        log.info("Task {} was successful registered", name);
    }

    public Optional<Task> findTask(String name) {
        return Optional.ofNullable(this.tasks.get(name.toLowerCase().trim()));
    }

    public Stream<Task> getTasks() {
        return Collections.unmodifiableCollection(this.tasks.values()).stream();
    }

    public Stream<Task> getTasks(TaskType<?> type) {
        return this.getTasks()
                .filter(task -> task.getType().equals(type));
    }

    @EventHandler
    public void on(DeluxeActionsReloadEvent event) {
        System.out.println("task");
        this.tasks.clear();
        this.config.getTasks().forEach((name, declaration) -> {
            String type = declaration.type();
            TaskType<? extends Task> taskType = this.typeService.findType(type).orElse(null);
            if (taskType != null) {
                Task task = taskType.createTask(name.toLowerCase().trim(), declaration.handle());
                for (String actionDeclaration : declaration.actions()) {
                    Matcher matcher = Pattern.compile("^\\[(.+)](.+)").matcher(actionDeclaration);
                    if (matcher.find()) {
                        String actionName = matcher.group(1).toLowerCase().trim();
                        String value = matcher.group(2).trim();
                        Action action = this.actionService.findAction(actionName).orElse(null);
                        if (action != null) {
                            task.registerAction(action, value);
                        } else {
                            log.warn("Task {} has unknown action {} with value {}", name, actionName, value);
                            return;
                        }
                    } else {
                        log.warn("Task {} has unknown action {}", name, actionDeclaration);
                        return;
                    }
                }
                this.registerTask(task);
            } else log.warn("Task {} has unknown type {}", name, type);
        });
    }
}
