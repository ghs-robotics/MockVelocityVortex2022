package org.firstinspires.ftc.teamcode.utils;

public class Vec {
    double x, y;

    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}
    public double getY() {return y;}

    public Vec add(Vec other) {
        return new Vec(this.x + other.x, this.y + other.y);
    }

    public Vec multiply(double other) {
        return new Vec(this.x * other, this.y * other);
    }

    public Vec rotate(double degrees) {
        double rad = Math.toRadians(degrees);
        double newX = this.x * Math.cos(rad) - this.y * Math.sin(rad);
        double newY = this.x * Math.sin(rad) + this.y * Math.cos(rad);
        return new Vec(newX, newY);
    }
}
