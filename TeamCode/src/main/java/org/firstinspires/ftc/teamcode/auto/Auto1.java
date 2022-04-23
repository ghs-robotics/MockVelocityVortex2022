package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.robot.Robot;

@Autonomous
public class Auto1 extends LinearOpMode {

    Robot robot;
    public ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);

        telemetry.addData("Status", "Initialized");
        waitForStart();
        runtime.reset();
        while (opModeIsActive()){
            double sec = runtime.seconds();

            robot.turn(true, .83, 90);

            robot.brake();

            //turn x degrees
            //shoot
            //turn back
            //move straight into the side team ramp

            telemetry.addData("Timer", runtime.seconds());
            telemetry.update();

        }
    }
}
