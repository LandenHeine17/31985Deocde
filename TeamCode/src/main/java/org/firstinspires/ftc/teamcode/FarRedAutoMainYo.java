package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class FarRedAutoMainYo extends LinearOpMode {
    ElapsedTime thyme = new ElapsedTime();
    RobotHardware rob = new RobotHardware();
    LLResult result;

    @Override
    public void runOpMode() {
        rob.init(hardwareMap);
        waitForStart();
        sleep(3000);
        thyme.reset();
        rob.limelight.pipelineSwitch(0);
        while (thyme.seconds()<2.5) {
            moveRobot(.4,0);
        }
        thyme.reset();
        while  (thyme.seconds()<1.4) {
            rob.leftRear.setPower(.15);
            rob.leftFront.setPower(.15);
            rob.rightRear.setPower(-.15);
            rob.rightFront.setPower(-.15);
        }
        while  (thyme.seconds()<2) {
            moveRobot(.2,1);
        }
        thyme.reset();
        rob.flywheelMotor.setPower(.56);
        sleep(2000);
        for (int i=0;i<4;i++) {
            rob.leftServo.setPosition(0);
            rob.rightServo.setPosition(1);
            sleep(600);
            rob.leftServo.setPosition(1);
            rob.rightServo.setPosition(0);
            sleep(600);
        }
        rob.leftRear.setPower(-0.5);
        rob.leftFront.setPower(-0.5);

        sleep(5000);
        rob.flywheelMotor.setPower(0);



    }

    void moveRobot(double speed, double correction) {
        result = rob.limelight.getLatestResult();
        double tx = result.getTx();
        rob.leftRear.setPower(speed+correction*.02*tx);
        rob.leftFront.setPower(speed+correction*.02*tx);
        rob.rightRear.setPower(speed-correction*.02*tx);
        rob.rightFront.setPower(speed-correction*.02*tx);
    }

}

