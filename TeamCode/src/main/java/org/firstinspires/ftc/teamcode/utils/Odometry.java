package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Vec;

public class Odometry {
    static final Vec VA = new Vec(Math.sqrt(2)/2, Math.sqrt(2)/2); //lf and rr
    static final Vec VB = new Vec(-Math.sqrt(2)/2, Math.sqrt(2)/2); //rf and lr
    int rfPrevTicks;
    int lfPrevTicks;
    int rrPrevTicks;
    int lrPrevTicks;
    DcMotor rightFront;
    DcMotor leftFront;
    DcMotor rightRear;
    DcMotor leftRear;
    Vec currentPos = new Vec(0, 0);

    public Odometry(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFront = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftRear = hardwareMap.get(DcMotor.class, "leftRearDrive");
        rightRear = hardwareMap.get(DcMotor.class, "rightRearDrive");
    }

    public Vec translated(Vec input) {
        double a = input.getX(); // a is front left
        double b = input.getY(); // b is back right
        return VA.multiply(a).add(VB.multiply(b));
    }

    public int[] getRawEncoderValues() {
        return new int[] {
                rightFront.getCurrentPosition() - rfPrevTicks,
                leftFront.getCurrentPosition() - lfPrevTicks,
                rightRear.getCurrentPosition() - rrPrevTicks,
                leftRear.getCurrentPosition() - lrPrevTicks
                };
    }

    private Vec[] getTickVectors(int[] deltaEncoded) {
        Vec rfV = VB.multiply(deltaEncoded[0]);
        Vec lfV = VA.multiply(deltaEncoded[1]);
        Vec rrV = VA.multiply(deltaEncoded[2]);
        Vec lrV = VB.multiply(deltaEncoded[3]);
        return new Vec[] {rfV, lfV, rrV, lrV};
    }

    private Vec quadSum(Vec[] input) { //Summation of four vectors
        return input[0].add(input[1]).add(input[2]).add(input[3]);
    }

    private double encoderTicksToMeters(int ticks) {
        double revs = ticks / 537.7;
        final double circ = .096 * Math.PI;
        return revs*circ;
    }

    public Vec update(double gyroAngle) {
        int[] deltaEncoded = getRawEncoderValues();
        Vec[] tickVectors = getTickVectors(deltaEncoded);
        Vec sumVector = quadSum(tickVectors);
        Vec sumVectorRotated = sumVector.rotate(gyroAngle);
        currentPos.add(sumVectorRotated);
        return currentPos;
    }
}
