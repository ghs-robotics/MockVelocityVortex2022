package org.firstinspires.ftc.teamcode.utils;

public class Vec {
    double x, y;

    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec add(Vec other) {
        return new Vec(this.x + other.x, this.y + other.y);
    }

    public Vec mul(double other) {
        return new Vec(this.x * other, this.y * other);
    }
}
