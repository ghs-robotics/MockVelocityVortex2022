package org.firstinspires.ftc.teamcode.event.ticking;

import java.util.ArrayList;
import java.util.List;

public class Ticker {
    private final List<Ticking> tickers = new ArrayList<>();

    public void register(Ticking ticker) {
        tickers.add(ticker);
    }

    public void tick() {
        for (Ticking ticker : tickers) {
            ticker.tick();
        }
    }
}
