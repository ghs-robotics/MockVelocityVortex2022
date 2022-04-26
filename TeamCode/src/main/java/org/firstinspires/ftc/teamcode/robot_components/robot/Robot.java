package org.firstinspires.ftc.teamcode.robot_components.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot extends DriveBase {

   // public DcMotor intakeMotor;
    //public DcMotor shooterMotor;
    public Robot (HardwareMap hardwareMap, Telemetry telemetry){

        super(hardwareMap, telemetry);

       // intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        //shooterMotor = hardwareMap.get(DcMotor.class, "shooter");

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

  /*  public void setIntakePower(double ip){
        intakeMotor.setPower(ip);
    }

    public void setShooterPower(double sp){
        intakeMotor.setPower(sp);
    } */
}
