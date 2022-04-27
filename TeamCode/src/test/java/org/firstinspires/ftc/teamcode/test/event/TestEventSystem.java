package org.firstinspires.ftc.teamcode.test.event;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.event.Event;
import org.firstinspires.ftc.teamcode.event.EventDispatcher;
import org.firstinspires.ftc.teamcode.event.EventHandler;
import org.firstinspires.ftc.teamcode.event.GlobalEventDispatcher;
import org.firstinspires.ftc.teamcode.event.TickingState;
import org.firstinspires.ftc.teamcode.event.fsm.FsmBuilder;
import org.firstinspires.ftc.teamcode.event.fsm.FsmState;
import org.firstinspires.ftc.teamcode.event.ticking.Ticker;
import org.junit.Test;

public class TestEventSystem {
    @Test
    public void testEventHandling() {
        GlobalEventDispatcher.getInstance().register(new TestEventHandler());
        GlobalEventDispatcher.getInstance().register(new TestEventHandler());

        GlobalEventDispatcher.getInstance().dispatch(new TestEvent("Testing something"));
    }

    private static class TestEventHandler implements EventHandler<TestEvent> {

        @Override
        public void handle(TestEvent event) {
            System.out.println(event.test);
        }
    }

    private static class TestEvent implements Event {
        private final String test;

        public TestEvent(String test) {
            this.test = test;
        }

        public String getTest() {
            return test;
        }
    }

    @Test
    public void testFsmSystem() {
        final FsmBuilder builder = new FsmBuilder();

        final TickingState initial = builder.placeHoldTickingState();
        final FsmState second = new SecondState(initial);

        builder.setupPlaceholder(initial, new FirstState(second));

        final EventDispatcher dispatcher = builder.build(initial);


        final Ticker ticker = new Ticker();
        ticker.register(initial);

        for (int i = 0; i < 20; i++) {
            ticker.tick();
        }

        dispatcher.dispatch(new TestEvent("Did this work?"));

    }

    private static class FirstState implements TickingState {
        private FsmState first;

        public FirstState(FsmState first) {
            this.first = first;
        }

        @Nullable
        @Override
        public FsmState handle(Event event) {
            if (event instanceof TestEvent) {
                System.out.println("Got event 1 with: " + ((TestEvent) event).test);
                return first;
            }
            return null;
        }

        @Override
        public void tick() {
            System.out.println("First ticking!");
        }
    }

    private static class SecondState implements TickingState {
        private FsmState initial;

        public SecondState(FsmState initial) {
            this.initial = initial;
        }

        @Nullable
        @Override
        public FsmState handle(Event event) {
            return null;
        }

        @Override
        public void onEnter(Event event) {
            System.out.println("Received event!");
        }

        @Override
        public void tick() {
            System.out.println("Second ticking!");
        }
    }
}
