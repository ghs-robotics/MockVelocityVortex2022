package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.robot_components.robot.Robot;
import org.firstinspires.ftc.teamcode.robot_components.input.Controller;

//test comment

@TeleOp
//Want to try using Opmode instead of LinearOp since I heard this is better for TeleOp
public class Tele1 extends LinearOpMode {

    Robot robot;
    Controller gp1;
    Controller gp2;
    BNO055IMU imu;
    Orientation angles;
    Double EXP;

    @Override
    public void runOpMode() throws InterruptedException {
        EXP = 1.0;
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
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double lx = gamepad1.left_stick_x;
            double ly = gamepad1.left_stick_y;
            double rx = -1 * gamepad1.right_stick_x;
            robot.calculateDrivePower(Math.pow(lx, EXP),
                    Math.pow(ly, EXP),
                    Math.pow(rx, EXP));
            //robot.setIntakePower(gamepad2.left_stick_x);
            //robot.setShooterPower(gamepad1.left_stick_y);
            telemetry.addData("z axis", angles.firstAngle);
            telemetry.addData("y axis", angles.secondAngle);
            telemetry.addData("x axis", angles.thirdAngle);
            telemetry.addData("gamepad-lx", lx);
            telemetry.addData("gamepad-lx", ly);
            telemetry.addData("gamepad-rx", rx);
            telemetry.update();
        }
    }
}
