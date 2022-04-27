package org.firstinspires.ftc.teamcode.event;

public interface EventDispatcher {
    <T extends Event> boolean dispatch(T event);
}
