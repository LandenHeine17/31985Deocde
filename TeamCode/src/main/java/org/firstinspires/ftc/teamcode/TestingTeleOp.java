package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class TestingTeleOp extends OpMode {
    RobotHardware rob;

    double flywheelPower = 0;

    ElapsedTime timer = new ElapsedTime();

    // This is a comment
    @Override
    public void init(){
        rob = new RobotHardware();
        rob.init(hardwareMap);

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
            rob.flywheelMotor.setPower(flywheelPower);
        } else {
            rob.flywheelMotor.setPower(0);
        }
        drivetrain();
    }

    public void drivetrain() {
        double forward = gamepad1.right_stick_y;
        double sideways = gamepad1.right_stick_x;
        double rotate = gamepad1.left_stick_x;

        rob.leftFront.setPower(forward + sideways + rotate);
        rob.leftRear.setPower(forward - sideways + rotate);
        rob.rightFront.setPower(forward - sideways - rotate);
        rob.rightRear.setPower(forward + sideways - rotate);
    }

}
