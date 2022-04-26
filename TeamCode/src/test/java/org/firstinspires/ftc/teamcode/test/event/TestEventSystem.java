package org.firstinspires.ftc.teamcode.test.event;

import org.firstinspires.ftc.teamcode.event.Event;
import org.firstinspires.ftc.teamcode.event.EventHandler;
import org.firstinspires.ftc.teamcode.event.EventManager;
import org.junit.Test;

public class TestEventSystem {
    @Test
    public void testEventHandling() {
        EventManager.getInstance().register(new TestEventHandler());
        EventManager.getInstance().register(new TestEventHandler());

        EventManager.getInstance().send(new TestEvent("Testing something"));
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

}
