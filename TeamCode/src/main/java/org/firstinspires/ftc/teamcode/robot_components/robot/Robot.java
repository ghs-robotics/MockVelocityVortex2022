package org.firstinspires.ftc.teamcode.robot_components.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot extends DriveBase {

    public DcMotor intakeMotor;
    //public DcMotor shooterMotor;
    public Servo indexingServo;
    public Servo liftingServo;
    public Robot (HardwareMap hardwareMap, Telemetry telemetry){

        super(hardwareMap, telemetry);

        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        //shooterMotor = hardwareMap.get(DcMotor.class, "shooter");
        //indexingServo = hardwareMap.get(Servo.class, "index");
        //liftingServo = hardwareMap.get(Servo.class, "lift");

    }

    public void setIntakePower(double ip){
        intakeMotor.setPower(ip);
    }
/*
    public void setShooterPower(double sp){
        intakeMotor.setPower(sp);
    } */
}
