package org.firstinspires.ftc.teamcode.auto;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot_component.Robot;
import org.firstinspires.ftc.teamcode.utils.Odometry;
import org.firstinspires.ftc.teamcode.utils.Vec;

@Autonomous
public class Auto1 extends LinearOpMode {
    Odometry odo;
    Vec robotPos;
    Robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        odo = new Odometry(hardwareMap);
        robotPos = new Vec(0, 0);

        waitForStart();

        while (opModeIsActive()){
            robotPos = odo.update(robot.getGyroAngle());
            telemetry.addData("distX", robotPos.getX());
            telemetry.addData("distY", robotPos.getY());
            telemetry.update();
        }
    }
}
