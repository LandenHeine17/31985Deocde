package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp
public class TeleOpMain extends OpMode {
    RobotHardware rob = new RobotHardware();
    double power;
    double flywheelSpeed;
    double leftMotorPower, rightMotorPower;

    double turnCoeff;
    int drivingMode = 0;
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
        handleDrivetrain();

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
        telemetry.addData("Drivemode", drivingMode);
        telemetry.addData("Flywheel ticks/sec", rob.flywheelMotor.getVelocity());
        telemetry.addData("Flywheel power", rob.flywheelMotor.getPower());
        telemetry.update();
    }


    void handleDrivetrain() {
        if (drivingMode == 1) { // single stick
            power = -gamepad1.right_stick_y * 0.6;
            turn = -gamepad1.left_stick_x;
            turnCoeff = 1;
            // Controls drivetrain
            if (turn>=0) {
                leftMotorPower = power*((1-turn)*turnCoeff+(1-turnCoeff));
                rightMotorPower = power;

                rob.rightMotor.setPower(rightMotorPower);
                rob.leftMotor.setPower(leftMotorPower);
            } else if (turn<0) {
                leftMotorPower = power;

                rightMotorPower = power*((1+turn)*turnCoeff+(1-turnCoeff));

                rob.rightMotor.setPower(rightMotorPower);
                rob.leftMotor.setPower(leftMotorPower);
            }

            if (gamepad1.aWasPressed()) {
                drivingMode = 0;
            }
        } else if (drivingMode == 0) {
            rob.rightMotor.setPower(-gamepad1.right_stick_y * 0.5);
            rob.leftMotor.setPower(-gamepad1.left_stick_y * 0.5);
            if (gamepad1.aWasPressed()) {
                drivingMode = 1;
            }
        }
    }

    void handleFlywheel() {
        if (gamepad1.dpadUpWasPressed()) {
            flywheelSpeed += 0.02;
        } else if (gamepad1.dpadDownWasPressed()) {
            flywheelSpeed -= 0.02;
        }
        rob.flywheelMotor.setPower(flywheelSpeed);
    }
}
