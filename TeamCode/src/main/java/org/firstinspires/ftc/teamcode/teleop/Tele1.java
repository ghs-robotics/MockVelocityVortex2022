package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.robot_components.input.Btn;
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

    //Variable for the changeable shooter power
    double shooterPower = 0.5;
    double shooterIncrement = 0.01;

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

        while (opModeIsActive()){
            //////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////           Controller 1           ////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////

            //gyro angle setting
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            robot.calculateDrivePower(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            //lifts big ball - y button
            robot.liftYogaBall(gamepad1.y);

            //////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////           Controller 2           ////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////

            //Changing the shooter power using the bumpers
            if (gamepad2.left_bumper)
                shooterPower -= shooterIncrement;
            if (gamepad2.right_bumper)
                shooterPower += shooterIncrement;
            if (shooterPower < 0)
                shooterPower = 0;
            if (shooterPower > 1)
                shooterPower = 1;

            //changeable shooter power button - b button
            robot.setShooterPower(shooterPower, gamepad2.b);

            //set shooter power button - x button
            robot.setShooterPower(0.5, gamepad2.x);

            robot.setIntakePower(gamepad2.right_stick_y);

            /////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////           Telemetry           /////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////

            telemetry.addData("z axis", angles.firstAngle);
            telemetry.addData("y axis", angles.secondAngle);
            telemetry.addData("x axis", angles.thirdAngle);
            telemetry.addData("shooter power variable", shooterPower);
            telemetry.update();
        }
    }
}
