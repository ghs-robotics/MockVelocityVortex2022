package org.firstinspires.ftc.teamcode.robot_component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveBase {
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor leftRearDrive;
    public DcMotor rightRearDrive;

    public Telemetry telemetry;
    public HardwareMap hardwareMap;
    BNO055IMU imu;
    Orientation angles;
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    public DriveBase (HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "leftRearDrive");
        rightRearDrive = hardwareMap.get(DcMotor.class, "rightRearDrive");

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        this.telemetry = telemetry;
    }

    public double getGyroAngle() {
        return angles.firstAngle;
    }
}
