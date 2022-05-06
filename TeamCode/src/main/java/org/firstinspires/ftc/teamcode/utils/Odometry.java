package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    Telemetry telemetry;

    public Odometry(HardwareMap hardwareMap, Telemetry telemetry) {
        leftFront = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFront = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftRear = hardwareMap.get(DcMotor.class, "leftRearDrive");
        rightRear = hardwareMap.get(DcMotor.class, "rightRearDrive");
        this.telemetry = telemetry;
    }

    public Vec translated(Vec input) {
        double a = input.getX(); // a is front left
        double b = input.getY(); // b is back right
        return VA.multiply(a).add(VB.multiply(b));
    }

    public int[] getRawEncoderValues() {
        int rF = rightFront.getCurrentPosition();
        int lF = -1 * leftFront.getCurrentPosition();
        int rR = rightRear.getCurrentPosition();
        int lR = -1 * leftRear.getCurrentPosition();
        int[] output = new int[] {
                rF - rfPrevTicks,
                lF - lfPrevTicks,
                rR - rrPrevTicks,
                lR - lrPrevTicks
        };
        rfPrevTicks = rF;
        lfPrevTicks = lF;
        rrPrevTicks = rR;
        lrPrevTicks = lR;
        return output;
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

    private Vec triSum(Vec[] input) { //Ignores the third value in Vec[] because the encoder is broken
        Vec frontSum = input[0].add(input[1]);
        int isRotation = (int) (input[0].y * input[1].y);//negative means rotation, positive means no rotation
        telemetry.addData("isRotation", isRotation);
        if (isRotation > 0) {
            return frontSum;
        } else {
            return new Vec(0, 0);
        }
    }

    private double encoderTicksToMeters(int ticks) {
        double revs = ticks / 537.7;
        final double circ = .096 * Math.PI;
        return revs*circ;
    }

    public Vec update(double gyroAngle) {
        int[] deltaEncoded = getRawEncoderValues();
        Vec[] tickVectors = getTickVectors(deltaEncoded);
        Vec sumVector = triSum(tickVectors);
        //Vec sumVector = quadSum(tickVectors);
        Vec sumVectorRotated = sumVector.rotate(gyroAngle);
        currentPos.add(sumVectorRotated);
        telemetry.addData("encoder", deltaEncoded[0] + ", " + deltaEncoded[1] + ", " + deltaEncoded[2] + ", " + deltaEncoded[3]);
        telemetry.addData("sumVector", sumVector.x + ", " + sumVector.y);
        return currentPos;
    }
}
