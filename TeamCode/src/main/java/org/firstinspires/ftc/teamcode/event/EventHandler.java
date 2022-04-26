package org.firstinspires.ftc.teamcode.event;

@FunctionalInterface
public interface EventHandler<T extends Event> {
    /**
     * Filters an event to be received. If false is returned the event
     * is not filtered.
     *
     * @param event The event to filter.
     *
     * @return If the event given should be filtered, true if should be filtered.
     */
    default boolean filter(T event) { return false; }

    /**
     * Handles event, guaranteed to not be a filtered event.
     *
     * @param event The event to handle.
     */
    void handle(T event);
}
