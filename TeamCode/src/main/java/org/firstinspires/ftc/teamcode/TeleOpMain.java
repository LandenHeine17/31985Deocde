package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp
public class TeleOpMain extends OpMode {
    RobotHardware rob = new RobotHardware();
    double flywheelSpeed;
    double angle;

    double magnitude;

    // This runs once when the user presses init
    @Override
    public void init() {
        rob.init(hardwareMap);
    }

    // This runs in a loop after the user presses play
    @Override
    public void loop() {
        handleDrivetrain();

        // Controls flywheels
        handleFlywheel();

        // Controls feeding servos
        handleServos();


        telemetry.addData("Flywheel ticks/sec", rob.flywheelMotor.getVelocity());
        telemetry.addData("Flywheel power", rob.flywheelMotor.getPower());
        telemetry.update();
    }


    void handleDrivetrain() {
        double forward = gamepad1.right_stick_y;
        double sideways = gamepad1.right_stick_x;
        double rotate = gamepad1.left_stick_x;

        rob.leftFront.setPower(forward + sideways + rotate);
        rob.leftRear.setPower(forward - sideways + rotate);
        rob.rightFront.setPower(forward - sideways - rotate);
        rob.rightRear.setPower(forward + sideways - rotate);
    }
    void strafeNoTurn() {
        if (gamepad1.right_stick_y>0) {
            angle=Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x);
        }
        else {
            angle=Math.PI+Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x);
        }
        magnitude =Math.pow((Math.pow((gamepad1.right_stick_y),2)+Math.pow((gamepad1.right_stick_x),2)),.5);

        if (angle>0 && Math.PI/2>=angle) {
            rob.rightRear.setPower(magnitude);
            rob.rightFront.setPower(magnitude *((angle-(Math.PI/4))/(Math.PI/4)));
            rob.leftRear.setPower(magnitude *((angle-(Math.PI/4))/(Math.PI/4)));
            rob.leftFront.setPower(magnitude);
        }
        if (angle>Math.PI/2 && Math.PI>=angle) {
            rob.rightRear.setPower(magnitude *((angle-(Math.PI/4+Math.PI/2))/(Math.PI/4)));
            rob.rightFront.setPower(magnitude);
            rob.leftRear.setPower(magnitude);
            rob.leftFront.setPower(magnitude *((angle-(Math.PI/4+Math.PI/2))/(Math.PI/4)));
        }
        if (angle>Math.PI && 3*Math.PI/2>=angle) {
            rob.rightRear.setPower(-magnitude *((angle-(Math.PI/4+2*Math.PI/2))/(Math.PI/4)));
            rob.rightFront.setPower(-magnitude);
            rob.leftRear.setPower(-magnitude);
            rob.leftFront.setPower(-magnitude *((angle-(Math.PI/4+2*Math.PI/2))/(Math.PI/4)));
        }
        if (angle>3*Math.PI/2 && Math.PI*2>=angle) {
            rob.rightRear.setPower(magnitude);
            rob.rightFront.setPower(magnitude *((angle-(Math.PI/4+3*Math.PI/2))/(Math.PI/4)));
            rob.leftRear.setPower(magnitude *((angle-(Math.PI/4+3*Math.PI/2))/(Math.PI/4)));
            rob.leftFront.setPower(magnitude);
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

    void handleServos() {
        if (gamepad1.left_bumper) {
            rob.leftServo.setPosition(1);
            rob.rightServo.setPosition(1);
        } else {
            rob.leftServo.setPosition(0);
            rob.rightServo.setPosition(0);
        }
    }
}
