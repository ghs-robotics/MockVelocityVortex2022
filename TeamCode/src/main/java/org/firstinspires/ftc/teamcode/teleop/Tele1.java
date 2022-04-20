package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.robot_component.Robot;
import org.firstinspires.ftc.teamcode.robot_components.input.Controller;

@TeleOp
//Want to try using Opmode instead of LinearOp since I heard this is better for TeleOp
public class Tele1 extends OpMode {

    Robot robot;
    Controller gp1;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        gp1 = new Controller(gamepad1);
    }

    @Override
    public void loop() {
        robot.calculateDrivePower(gp1.left_stick_x, gp1.left_stick_y, gp1.right_stick_x);
        telemetry.update();
    }
}
