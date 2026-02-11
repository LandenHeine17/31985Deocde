package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class TestingTeleOp extends OpMode {

    DcMotor leftFront, rightFront, leftBack, rightBack;
    DcMotorEx flywheelMotor;
    Servo leftServo, rightServo, centerServo;

    double flywheelPower = 0;
    double centerPos = .5;

    ElapsedTime timer = new ElapsedTime();

    // This is a comment
    @Override
    public void init(){
        telemetry.addData("Status", "This is initialized (:");
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheelMotor");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
        centerServo = hardwareMap.get(Servo.class, "centerServo");

        flywheelMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        timer.reset();
    }
    

    @Override
    public void loop() {
        // Change motor power
        if (gamepad1.dpadDownWasPressed()) {
            flywheelPower -= 0.05;
        } else if (gamepad1.dpadUpWasPressed()) {
            flywheelPower += 0.05;
        }

        // Controls flywheels
        if (gamepad1.right_bumper) {
            flywheelMotor.setPower(flywheelPower);
        } else {
            flywheelMotor.setPower(0);
        }

        // Controls Servos
        if (gamepad1.left_bumper) {
            leftServo.setPosition(1);
            rightServo.setPosition(0);
        } else {
            leftServo.setPosition(0);
            rightServo.setPosition(1);
        }
        if (gamepad1.dpadLeftWasPressed()) {
            centerPos += 0.01;
        }
        if (gamepad1.dpadRightWasPressed()) {
            centerPos -= 0.01;
        }
        centerServo.setPosition(centerPos);
        telemetry.addData("Flywheel ticks/sec", flywheelMotor.getVelocity());
        telemetry.addData("CenterServo Position", centerPos);
        telemetry.addData("Flywheel power", flywheelMotor.getPower());
        telemetry.update();
    }

}
