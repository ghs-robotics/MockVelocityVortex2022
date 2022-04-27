package org.firstinspires.ftc.teamcode.event.fsm;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.event.Event;

public interface FsmState {
    @Nullable
    FsmState handle(Event event);

   default void onEnter(Event event) {}
}
