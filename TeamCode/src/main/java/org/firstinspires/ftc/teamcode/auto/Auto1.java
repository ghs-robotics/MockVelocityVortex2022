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
        double sec = runtime.seconds();
        while (opModeIsActive()){
            //robot.releaseLiftAtStart(opModeIsActive());


            robot.extendingLiftMotor.setTargetPosition(1000);
            telemetry.addData("extendingLiftMotor currentPosition", robot.extendingLiftMotor.getCurrentPosition());

            //turn x degrees
            //shoot
            //turn back
            //move straight into the side team ramp

            telemetry.addData("Timer", runtime.seconds());
            telemetry.update();

        }

    }
}
