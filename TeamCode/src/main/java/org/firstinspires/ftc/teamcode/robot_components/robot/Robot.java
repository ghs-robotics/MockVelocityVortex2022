package org.firstinspires.ftc.teamcode.robot_components.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//Got rid of all the other functions
public class Robot extends DriveBase {

    public DcMotor shooterLF;
    public DcMotor shooterLR;
    public DcMotor shooterRF;
    public DcMotor shooterRR;

    public Robot (HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);

        shooterLF = hardwareMap.get(DcMotor.class, "shooterLF");
        shooterLR = hardwareMap.get(DcMotor.class, "shooterLR");
        shooterRF = hardwareMap.get(DcMotor.class, "shooterRF");
        shooterRR = hardwareMap.get(DcMotor.class, "shooterRR");
    }

    //Need to test if the
    public void shoot(double sp){
        double leftPower = sp;
        double rightPower = -sp;

        shooterLF.setPower(leftPower);
        shooterLR.setPower(leftPower);

        shooterRF.setPower(rightPower);
        shooterRR.setPower(rightPower);
    }

    public void shootMaxPower(boolean run){
        if (run)
            shoot(1);
        else
            shoot(0);
    }
}
