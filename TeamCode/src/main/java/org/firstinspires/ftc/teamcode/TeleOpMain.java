package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp
public class TeleOpMain extends OpMode {
    RobotHardware rob = new RobotHardware();
    double flywheelSpeed;
    double drivetrainSpeed = 0.8;
    double angle;
    double magnitude;
    double feedforward = 10;
    double rotateScalar = 0.02;
    String teamColor = "Blue";

    // This runs once when the user presses init
    @Override
    public void init() {
        rob.init(hardwareMap);
        rob.flywheelMotor.setVelocityPIDFCoefficients(140, 0, 0.0001, feedforward);
    }

    @Override
    public void start() {
        rob.limelight.start();
    }

    // This runs in a loop after the user presses play
    @Override
    public void loop() {
        LLResult result = rob.limelight.getLatestResult();
        if (result.isValid()) {
            double tx = result.getTx();
            double ty = result.getTy();
            double ta = result.getTa();
        }

        // handles drivetrain
        handleDrivetrain(result);

        // Controls flywheels
        handleFlywheel();

        // Controls feeding servos
        handleServos();

        // controls limelight pipeline
        handleColorSwitching();

        // extra telemetry
        telemetry.addData("Detecting Tag", result.isValid());
        telemetry.addData("Current Pipeline", teamColor);
        telemetry.addData("Drivetrain speed", drivetrainSpeed);
        telemetry.addData("Flywheel ticks/sec", rob.flywheelMotor.getVelocity());
        telemetry.addData("Flywheel power", rob.flywheelMotor.getPower());
        telemetry.update();
    }

    void handleColorSwitching() {
        if (gamepad1.leftStickButtonWasPressed()) {
            teamColor = "Red";
            rob.limelight.pipelineSwitch(0);
        } else if (gamepad1.rightStickButtonWasPressed()) {
            teamColor = "Blue";
            rob.limelight.pipelineSwitch(1);
        }
    }


    void handleFlywheel() {
        if (gamepad1.dpadUpWasPressed()) {
            flywheelSpeed += 0.02;
        } else if (gamepad1.dpadDownWasPressed()) {
            flywheelSpeed -= 0.02;
        } else if (gamepad1.dpadRightWasPressed()) {
            flywheelSpeed = 0.6;
        }
        rob.flywheelMotor.setPower(flywheelSpeed);
    }
    void handleServos() {
        if (gamepad1.left_bumper) {
            rob.leftServo.setPosition(0);
            rob.rightServo.setPosition(1);
        } else {
            rob.leftServo.setPosition(1);
            rob.rightServo.setPosition(0);
        }
    }

    void handleDrivetrain(LLResult result) {
        // Handles speed
        if (gamepad1.squareWasPressed()) {
            drivetrainSpeed += 0.02;
        } else if (gamepad1.circleWasPressed()) {
            drivetrainSpeed -= 0.02;
        } else if (gamepad1.triangleWasPressed()) {
            drivetrainSpeed = 0.8;
        } else if (gamepad1.crossWasPressed()) {
            drivetrainSpeed = 0.25;
        }

        double forward = -gamepad1.right_stick_y * drivetrainSpeed;
        double sideways = gamepad1.right_stick_x * drivetrainSpeed;
        double rotate = gamepad1.left_stick_x * drivetrainSpeed;

        if (gamepad1.left_trigger_pressed) {
            rotate += rotateScalar * result.getTx();
        }

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

}
