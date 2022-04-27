package org.firstinspires.ftc.teamcode.event.fsm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.event.Event;
import org.firstinspires.ftc.teamcode.event.EventDispatcher;

public class FsmDispatcher implements EventDispatcher {
    @NonNull
    private FsmState current;

    FsmDispatcher(@NonNull FsmState initial) {
        current = initial;
    }

    @Override
    public <T extends Event> boolean dispatch(T event) {
        @Nullable final FsmState res = current.handle(event);

        if (res != null) {
            current = res;
            res.onEnter(event);
        }

        return true;
    }
}
