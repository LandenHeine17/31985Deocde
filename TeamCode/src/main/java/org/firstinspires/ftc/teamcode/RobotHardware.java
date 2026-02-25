package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    DcMotor leftMotor, rightMotor;
    DcMotorEx flywheelMotor;
    Servo leftServo, rightServo, angleServo;
    // Movement motors
    DcMotor rightFront, rightRear, leftFront, leftRear;
    // intake and flywheel
    DcMotor intake;



    public void init(HardwareMap HwMap) {
        // MOTORS
//        leftMotor = HwMap.get(DcMotor.class, "leftMotor");
//        rightMotor = HwMap.get(DcMotor.class, "rightMotor");

        rightFront = HwMap.get(DcMotor.class, "rightFront");
        rightRear = HwMap.get(DcMotor.class, "rightBack");
        leftFront = HwMap.get(DcMotor.class, "leftFront");
        leftRear = HwMap.get(DcMotor.class, "leftBack");

        intake = HwMap.get(DcMotor.class, "intake");

        flywheelMotor = HwMap.get(DcMotorEx.class, "flywheel");

        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // SERVOS
        leftServo = HwMap.get(Servo.class, "leftPusher");
        rightServo = HwMap.get(Servo.class, "rightPusher");
        angleServo = HwMap.get(Servo.class, "intakeServo");


    }


}
