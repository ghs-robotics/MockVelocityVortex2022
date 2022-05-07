package org.firstinspires.ftc.teamcode.robot_components.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveBase {
    public double speed = 1.0;

    public DcMotor leftFrontDrive;
    public DcMotor leftRearDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightRearDrive;

    private final double[] PWRADJ  = {1.0, 1.0, 1.0, 1.0};

    public Telemetry telemetry;
    public HardwareMap hardwareMap;

    public DriveBase (HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "leftRearDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        rightRearDrive = hardwareMap.get(DcMotor.class, "rightRearDrive");

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.telemetry = telemetry;
    }

    public void calculateDrivePower(double x, double y, double r){
        r = -r;
        double lf = r - x + y;
        double lr = r + x + y;
        double rf = r - x - y;
        double rr = r + x - y;
        
        sendDrivePower(lf, lr, rf, rr);
    }

    public void sendDrivePower(double lf, double lr, double rf, double rr){
        leftFrontDrive.setPower(lf * PWRADJ[0]);
        leftRearDrive.setPower(lr * PWRADJ[1]);
        rightFrontDrive.setPower(rf * PWRADJ[2]);
        rightRearDrive.setPower(rr * PWRADJ[3]);
    }

    public void driveForward(double speed) {
        this.speed = speed;
        leftFrontDrive.setPower(speed);
        rightFrontDrive.setPower(speed);
        leftRearDrive.setPower(speed);
        rightRearDrive.setPower(speed);
    }
    public void driveRight(double speed) {
        this.speed = speed;
        leftFrontDrive.setPower(speed);
        rightFrontDrive.setPower(-speed);
        leftRearDrive.setPower(-speed);
        rightRearDrive.setPower(speed);
    }
    public void turnRight(double speed) {
        this.speed = speed;
        leftFrontDrive.setPower(speed);
        rightFrontDrive.setPower(-speed);
        leftRearDrive.setPower(speed);
        rightRearDrive.setPower(-speed);
    }

}
