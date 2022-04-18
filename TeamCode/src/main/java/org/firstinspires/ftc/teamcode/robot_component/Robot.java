package org.firstinspires.ftc.teamcode.robot_component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot extends DriveBase{
    public Robot (HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
    }
}
