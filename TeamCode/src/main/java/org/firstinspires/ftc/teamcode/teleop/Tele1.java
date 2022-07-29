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
            //robot.shoot(gamepad1.left_stick_y);

            //max power shooter - a button
            //robot.shootMaxPower(gamepad1.a);


            //////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////           Controller 2           ////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////

            //driving
            //robot.calculateDrivePower(gamepad2.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            /////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////           Telemetry           /////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////

            telemetry.addData("a", gamepad1.a);
            telemetry.addData("b", gamepad1.b);
            telemetry.addData("y", gamepad1.y);
            telemetry.addData("x", gamepad1.x);
            telemetry.addData("l bumper", gamepad1.left_bumper);
            telemetry.addData("r bumper", gamepad1.right_bumper);
            telemetry.addData("l stick x", gamepad1.left_stick_x);
            telemetry.addData("l stick y", gamepad1.left_stick_y);
            telemetry.addData("r stick x", gamepad1.right_stick_x);
            telemetry.addData("r stick y", gamepad1.right_stick_y);
            telemetry.addData("dpad u", gamepad1.dpad_up);
            telemetry.addData("dpad d", gamepad1.dpad_down);
            telemetry.addData("dpad l", gamepad1.dpad_left);
            telemetry.addData("dpad r", gamepad1.dpad_right);
            telemetry.addData("l trigger", gamepad1.left_trigger);
            telemetry.addData("r trigger", gamepad1.right_trigger);
            telemetry.update();
            //telemetry.addData("", gamepad1.);
        }
    }
}
