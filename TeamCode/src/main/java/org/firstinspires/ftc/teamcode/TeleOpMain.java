package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp
public class TeleOpMain extends OpMode {
    RobotHardware rob = new RobotHardware();
    double power;
    double flywheelSpeed;
    double leftMotorPower, rightMotorPower;
    double turn;
    double anglePos = 0.5;   // starting position — adjust if needed

    private void handleAngleServo() {
        // D-pad UP → increase angle
        if (gamepad1.dpad_up) {
            anglePos += 0.05;
        }

        // D-pad DOWN → decrease angle
        if (gamepad1.dpad_down) {
            anglePos -= 0.05;
        }

        // Clamp between 0 and 1
        anglePos = Math.max(0, Math.min(1, anglePos));

        // Apply to servo
        rob.angleServo.setPosition(anglePos);
    }

    // This runs once when the user presses init
    @Override
    public void init() {
        rob.init(hardwareMap);
    }

    // This runs in a loop after the user presses play
    @Override
    public void loop() {
        handleAngleServo();
        power = -gamepad1.right_stick_y;
        turn = gamepad1.left_stick_x;
        // Controls drivetrain
        if (turn>=0) {
            leftMotorPower = power*((1-turn)/2+.5);
            rightMotorPower = power;

            rob.rightMotor.setPower(rightMotorPower);
            rob.leftMotor.setPower(leftMotorPower);
        } else if (turn<0) {
            leftMotorPower = power;

            rightMotorPower = power*((1+turn)/2+.5);

            rob.rightMotor.setPower(rightMotorPower);
            rob.leftMotor.setPower(leftMotorPower);
        }

        // Controls flywheels
        handleFlywheel();

        // Controls Servos
        if (gamepad1.left_bumper) {
            rob.leftServo.setPosition(1);
            rob.rightServo.setPosition(0);
        } else {
            rob.leftServo.setPosition(0);
            rob.rightServo.setPosition(1);
        }
        telemetry.addData("Flywheel ticks/sec", rob.flywheelMotor.getVelocity());
        telemetry.addData("Flywheel power", rob.flywheelMotor.getPower());
        telemetry.update();
    }

    void handleFlywheel() {
        if (gamepad2.dpadUpWasPressed()) {
            flywheelSpeed += 0.2;
        } else if (gamepad2.dpadDownWasPressed()) {
            flywheelSpeed -= 0.2;
        }
        rob.flywheelMotor.setPower(flywheelSpeed);
    }
}
