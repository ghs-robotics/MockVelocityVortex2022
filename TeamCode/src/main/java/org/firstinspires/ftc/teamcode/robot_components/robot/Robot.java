package org.firstinspires.ftc.teamcode.robot_components.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot extends DriveBase {

    public DcMotor intakeMotor;
    public Robot (HardwareMap hardwareMap, Telemetry telemetry){

        super(hardwareMap, telemetry);

        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

    }

    public void turnRight(double speed) {
        this.speed = speed;
        leftFrontDrive.setPower(speed);
        rightFrontDrive.setPower(-speed);
        leftRearDrive.setPower(speed);
        rightRearDrive.setPower(-speed);
    }
}
