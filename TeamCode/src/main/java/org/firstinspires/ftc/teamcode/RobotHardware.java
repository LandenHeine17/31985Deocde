package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    DcMotorEx flywheelMotor;
    Servo leftServo, rightServo;
    // Movement motors
    DcMotor rightFront, rightRear, leftFront, leftRear;



    public void init(HardwareMap HwMap) {
        // MOTORS
        rightFront = HwMap.get(DcMotor.class, "rightFront");
        rightRear = HwMap.get(DcMotor.class, "rightBack");
        leftFront = HwMap.get(DcMotor.class, "leftFront");
        leftRear = HwMap.get(DcMotor.class, "leftBack");

        // Handle reverse motor directions
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behaviors
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // flywheel
        flywheelMotor = HwMap.get(DcMotorEx.class, "flywheel");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // SERVOS
        leftServo = HwMap.get(Servo.class, "leftPusher");
        rightServo = HwMap.get(Servo.class, "rightPusher");


    }


}
