package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.robot_components.robot.Robot;
import org.firstinspires.ftc.teamcode.robot_components.input.Controller;

@TeleOp
//Want to try using Opmode instead of LinearOp since I heard this is better for TeleOp
public class Tele1 extends LinearOpMode {

    Robot robot;
    Controller gp1;
    Controller gp2;
    BNO055IMU imu;
    Orientation angles;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        gp1 = new Controller(gamepad1);
        gp2 = new Controller(gamepad2);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();
        while (opModeIsActive()){
            //////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////           Controller 1           ////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////

            //Scalable shooter - left stick y
            robot.shoot(gamepad1.left_stick_y);

            //max power shooter - a button
            robot.shootMaxPower(gamepad1.a);


            //////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////           Controller 2           ////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////

            //driving
            robot.calculateDrivePower(gamepad2.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            /////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////           Telemetry           /////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////

            telemetry.addData("gamepad2.left_stick_y", gamepad2.left_stick_y);
            telemetry.addData("gamepad2.a", gamepad2.a);
            /*telemetry.addData("z axis", angles.firstAngle);
            telemetry.addData("y axis", angles.secondAngle);
            telemetry.addData("x axis", angles.thirdAngle);
            telemetry.addData("shooter power variable", shooterPower); */
            telemetry.update();
        }
    }
}
