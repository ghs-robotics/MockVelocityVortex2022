package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.robot.Robot;

import org.firstinspires.ftc.teamcode.utils.Odometry;
import org.firstinspires.ftc.teamcode.utils.Vec;

@Autonomous
public class Auto1 extends LinearOpMode {

    Robot robot;
    Odometry odo;
    Vec robotPos;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        odo = new Odometry(hardwareMap, telemetry);
        robotPos = new Vec(0, 0);

        telemetry.addData("Status", "Initialized");
        waitForStart();
        runtime.reset();
        while (opModeIsActive()){
            robotPos = odo.update(robot.getGyroAngle());
            telemetry.addData("distX", robotPos.getX());
            telemetry.addData("distY", robotPos.getY());
            //robot.releaseLiftAtStart(opModeIsActive());
            double sec = runtime.seconds();

            /*if ((sec >= 3 && sec <= 5) || (sec >= 6 && sec <= 7.2))
                robot.sendDrivePower(.5, .5, .5, .5);
            if (sec > 5 && sec <= 6)
                robot.turnRight(.83);
            robot.sendDrivePower(0, 0, 0, 0); */
            //robot.setShooterPower(0.5, true);
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
