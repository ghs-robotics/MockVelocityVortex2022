package org.firstinspires.ftc.teamcode.test.event;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.event.Event;
import org.firstinspires.ftc.teamcode.event.EventDispatcher;
import org.firstinspires.ftc.teamcode.event.EventHandler;
import org.firstinspires.ftc.teamcode.event.GlobalEventDispatcher;
import org.firstinspires.ftc.teamcode.event.fsm.FsmBuilder;
import org.firstinspires.ftc.teamcode.event.fsm.FsmState;
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

        final FsmState initial = builder.placeHoldState();
        final FsmState second = new SecondState(initial);

        builder.setupPlaceholder(initial, new FirstState(second));

        final EventDispatcher dispatcher =builder.build(initial);

        dispatcher.dispatch(new TestEvent("Did this work?"))
    }

    private static class FirstState implements FsmState {
        private FsmState first;

        public FirstState(FsmState first) {
            this.first = first;
        }

        @Nullable
        @Override
        public FsmState handle(Event event) {
            return null;
        }
    }

    private static class SecondState implements FsmState {
        private FsmState initial;

        public SecondState(FsmState initial) {
            this.initial = initial;
        }

        @Nullable
        @Override
        public FsmState handle(Event event) {
            return null;
        }
    }
}
