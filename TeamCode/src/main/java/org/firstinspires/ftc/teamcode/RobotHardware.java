package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class RobotHardware {
    DcMotor leftMotor, rightMotor;
    DcMotorEx flywheelMotor;
    Servo leftServo, rightServo, angleServo;

    public void init(HardwareMap HwMap) {
        // MOTORS
        leftMotor = HwMap.get(DcMotor.class, "leftMotor");
        rightMotor = HwMap.get(DcMotor.class, "rightMotor");
        flywheelMotor = HwMap.get(DcMotorEx.class, "flywheelMotor");

        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // SERVOS
        leftServo = HwMap.get(Servo.class, "leftServo");
        rightServo = HwMap.get(Servo.class, "rightServo");
        angleServo = HwMap.get(Servo.class, "centerServo");

        // DRIVE TRAIN MOTORS
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
    }
}
