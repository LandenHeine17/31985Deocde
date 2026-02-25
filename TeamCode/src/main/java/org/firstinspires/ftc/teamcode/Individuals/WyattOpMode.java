package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class WyattOpMode extends OpMode {
    @Override
    public void init() {
        telemetry.addLine("Status: Initialized:");

    }
    @Override
    public void loop() {
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
//
//        gamepad1.left_bumper;
//        gamepad1.left_trigger;
    }
}
