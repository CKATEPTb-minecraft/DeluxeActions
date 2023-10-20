package dev.ckateptb.minecraft.deluxeactions.task;

import dev.ckateptb.minecraft.deluxeactions.action.Action;
import dev.ckateptb.minecraft.deluxeactions.task.type.TaskType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Task {
    private final String name;
    private final TaskType<? extends Task> type;
    private final String handler;
    private final Map<Pair<String, Action>, String> actions = new LinkedHashMap<>();

    public Map<Pair<String, Action>, String> getActions() {
        return Collections.unmodifiableMap(this.actions);
    }

    public void registerAction(String declaration, Action action, String value) {
        this.actions.put(Pair.of(declaration, action), value);
    }

}
