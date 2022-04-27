package org.firstinspires.ftc.teamcode.robot_components.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot extends DriveBase {

    public DcMotor intakeMotor;
    public DcMotor shooterMotor;
    public DcMotor extendingLiftMotor;

    public Servo indexingServo;
    public Servo liftingServo;

    ElapsedTime running = new ElapsedTime();
    double milli = running.milliseconds();

    public Robot (HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);

        intakeMotor = hardwareMap.get(DcMotor.class, "intake"); //port 0 expansion hub
        //shooterMotor = hardwareMap.get(DcMotor.class, "shooter"); //port 1 expansion hub
        //liftMotor = hardwareMap.get(DcMotor.class, "extender"); //port 2 expansion hub
        //indexingServo = hardwareMap.get(Servo.class, "index"); //port 0 control hub
        //liftingServo = hardwareMap.get(Servo.class, "lift"); //port 1 control hub

    }

    public void setIntakePower(double ip){
        intakeMotor.setPower(ip);
    }

    public void releaseLiftAtStart (boolean init){
        if (init)
            liftingServo.setPosition(1);
        else
            liftingServo.setPosition(0);
    }

    public void liftBall (boolean lift){
        if (lift)
            extendingLiftMotor.setTargetPosition(500);
        else
            extendingLiftMotor.setTargetPosition(0);
    }

    public void setShooterPower(double sp, boolean run){
        shooterMotor.setPower(sp);
        indexShooter(run);
    }

    public void indexShooter (boolean run){
        if (run)
            indexingServo.setPosition(1);
        else
            indexingServo.setPosition(0);
    }
}
