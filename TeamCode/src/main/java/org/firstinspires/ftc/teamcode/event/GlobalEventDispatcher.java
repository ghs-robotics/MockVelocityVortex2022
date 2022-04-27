package org.firstinspires.ftc.teamcode.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalEventDispatcher implements EventDispatcher {
    private static GlobalEventDispatcher instance;
    private final Map<String, List<EventHandler<? extends Event>>> handlers = new HashMap<>();

    private GlobalEventDispatcher() {
    }

    public static synchronized GlobalEventDispatcher getInstance() {
        if (instance == null) instance = new GlobalEventDispatcher();
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> void register(EventHandler<T> handler) {
        final Method[] methods = handler.getClass().getDeclaredMethods();
        Method handleMethod = null;

        for (Method method : methods) {
            if ("handle".equals(method.getName()) && !Event.class.getName().equals(method.getParameterTypes()[0].getName())) {
                handleMethod = method;
                break;
            }
        }

        if (handleMethod == null)
            throw new IllegalArgumentException("Failed to find appropiate event type for handler: " + handler);

        final Class<T> eventType = (Class<T>) handleMethod.getParameterTypes()[0];

        register(eventType, handler);
    }

    public <T extends Event> void register(Class<T> type, EventHandler<T> handler) {
        final List<EventHandler<? extends Event>> existing = getAllFor(type);

        existing.add(handler);

        handlers.put(type.getName(), existing);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Event> boolean dispatch(T event) {
        final List<EventHandler<? extends Event>> existing = getAllFor(event.getClass());

        for (EventHandler<? extends Event> handler : existing) {
            ((EventHandler<T>) handler).handle(event);
        }

        return !existing.isEmpty();
    }

    private List<EventHandler<? extends Event>> getAllFor(Class<? extends Event> type) {
        final List<EventHandler<? extends Event>> existingOrNull = handlers.get(type.getName());

        return existingOrNull == null ? new ArrayList<>() : existingOrNull;
    }
}
