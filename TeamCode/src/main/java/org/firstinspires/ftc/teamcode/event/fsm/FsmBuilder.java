package org.firstinspires.ftc.teamcode.event.fsm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.event.Event;
import org.firstinspires.ftc.teamcode.event.ticking.Ticker;
import org.firstinspires.ftc.teamcode.event.ticking.Ticking;
import org.firstinspires.ftc.teamcode.event.TickingState;

public class FsmBuilder {

    public FsmState placeHoldState() {
        return new ProxyState();
    }

    public TickingState placeHoldTickingState() {
        return new TickingProxyState();
    }

    public void setupPlaceholder(FsmState proxy, FsmState actual) {
        if (proxy instanceof ProxyState) ((ProxyState) proxy).setState(actual);
    }

    public FsmDispatcher build(FsmState initial) {
        return new FsmDispatcher(initial);
    }

    public static class TickingProxyState extends ProxyState implements TickingState {
        @Override
        public void setState(@NonNull FsmState state) {
            assert state instanceof Ticking;

            super.setState(state);
        }

        @Override
        public void tick() {
            if (state == null) return;
            ((Ticking) state).tick();
        }
    }

    private static class ProxyState implements FsmState {
        @Nullable
        protected FsmState state = null;

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
