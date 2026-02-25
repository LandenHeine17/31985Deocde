package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class intakeTest extends LinearOpMode {
    RobotHardware rob;

    private double servoPosition = 0.5;     // Start in middle
    private final double SERVO_INCREMENT = 0.05;  // Change amount per press

    @Override
    public void runOpMode() {
        rob = new RobotHardware();
        rob.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            // Motor power = right trigger - left trigger
            double power = gamepad1.right_trigger - gamepad1.left_trigger;
            rob.intake.setPower(power);

            // Servo position increase
            if (gamepad1.dpadUpWasPressed()) {
                servoPosition += SERVO_INCREMENT;
            }

            // Servo position decrease
            if (gamepad1.dpadDownWasPressed()) {
                servoPosition -= SERVO_INCREMENT;
            }

            // Clamp servo position between 0 and 1
            servoPosition = Math.max(0.0, Math.min(1.0, servoPosition));

            rob.angleServo.setPosition(servoPosition);

            telemetry.addData("Motor Power", power);
            telemetry.addData("Servo Position", servoPosition);
            telemetry.update();
        }
    }
}