package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotHardware;

@Disabled
@TeleOp
public class JacobOpMode extends OpMode {
    DcMotor leftMotor;
    RobotHardware robot = new RobotHardware();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        leftMotor.setPower(gamepad1.right_trigger);


    }
}
