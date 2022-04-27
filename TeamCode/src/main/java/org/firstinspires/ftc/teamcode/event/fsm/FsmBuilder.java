package org.firstinspires.ftc.teamcode.event.fsm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.event.Event;

public class FsmBuilder {

    public FsmState placeHoldState() {
        return new ProxyState();
    }

//    public class StateBuilder {
//        private final FsmState state;
//
//        protected StateBuilder(FsmState state) {
//            this.state = state;
//        }
//    }

    public void setupPlaceholder(FsmState proxy, FsmState actual) {
        if (proxy instanceof ProxyState) ((ProxyState) proxy).setState(actual);
    }

    public FsmDispatcher build(FsmState initial) {
        return new FsmDispatcher(initial);
    }

    private static class ProxyState implements FsmState {
        @Nullable
        private FsmState state = null;

        public void setState(@NonNull FsmState state) {
            this.state = state;
        }

        @Nullable
        @Override
        public FsmState handle(Event event) {
            if (state != null) return state.handle(event);
            return null;
        }

        @Override
        public void onEnter(Event event) {
            if (state != null) state.onEnter(event);
        }
    }
}
