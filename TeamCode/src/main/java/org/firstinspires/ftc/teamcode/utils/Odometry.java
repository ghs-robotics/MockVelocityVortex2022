package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.teamcode.utils.Vec;

public class Odometry {

    static final Vec va = new Vec(Math.sqrt(2)/2, Math.sqrt(2)/2);
    static final Vec vb = new Vec(-Math.sqrt(2)/2, Math.sqrt(2)/2);

    public static Vec translated(double a, double b) {
        // a is front left
        // b is back right
        return va.mul(a).add(vb.mul(b));
    }

    public static double encoderTicksToMeters(int ticks) {
        double revs = ticks / 537.7;
        final double circ = .096 * Math.PI;
        return revs*circ;
    }
}
