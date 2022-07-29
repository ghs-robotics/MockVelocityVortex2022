package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.robot.Robot;

@Autonomous
public class Auto1 extends LinearOpMode {

    Robot robot;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);

        telemetry.addData("Status", "Initialized");
        waitForStart();
        runtime.reset();

    }

    private void stop(double sec) {
        runtime.reset();
        while (runtime.seconds() < sec) {}
    }
}

//launching wiffle balls
//rotate to correct angle
//launch preloaded wiffle ball
//start launcher motor
//release indexer
//park on vortex base, pushing cap ball in doing so
//drive to correct point