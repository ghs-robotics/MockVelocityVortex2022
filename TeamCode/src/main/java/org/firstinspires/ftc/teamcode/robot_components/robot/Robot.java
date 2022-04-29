package org.firstinspires.ftc.teamcode.robot_components.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
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
        shooterMotor = hardwareMap.get(DcMotor.class, "shooter"); //port 2 expansion hub
        extendingLiftMotor = hardwareMap.get(DcMotor.class, "extender"); //port 1 expansion hub
        indexingServo = hardwareMap.get(Servo.class, "index"); //port 0 control hub
        liftingServo = hardwareMap.get(Servo.class, "lift"); //port 0 expansion hub

        extendingLiftMotor.setTargetPosition(0);
        extendingLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extendingLiftMotor.setPower(1);
    }

    public void setIntakePower(double ip){
        intakeMotor.setPower(ip);
    }

    public void releaseLiftAtStart (boolean init){
        if (init)
            liftingServo.setPosition(0.6);
        else
            liftingServo.setPosition(0.75);
    }

    public void liftBall (boolean lift) {
        telemetry.addData("lift position", extendingLiftMotor.getCurrentPosition());
        telemetry.addData("extender mode", extendingLiftMotor.getMode());
        if (lift) {
            extendingLiftMotor.setTargetPosition(3500);
            telemetry.addData("target position", 3500);
        } else {
            extendingLiftMotor.setTargetPosition(0);
            telemetry.addData("target position", 0);
        }
    }

    public void setShooterPower(double sp, boolean run){
        shooterMotor.setPower(-1 * sp);
        indexShooter(run);
    }

    public void indexShooter (boolean run){
        telemetry.addData("index", indexingServo.getDirection());

        if (run)
            indexingServo.setPosition(0.45); //Cleared position
        else
            indexingServo.setPosition(0.7); //Blocking position
    }
}
