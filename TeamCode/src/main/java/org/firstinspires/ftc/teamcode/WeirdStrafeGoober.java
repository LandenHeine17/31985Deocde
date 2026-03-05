package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class WeirdStrafeGoober extends LinearOpMode {
    ElapsedTime thyme = new ElapsedTime();
    RobotHardware rob = new RobotHardware();
    LLResult result;

    @Override
    public void runOpMode() {
        rob.init(hardwareMap);
        waitForStart();
        thyme.reset();
        while (thyme.seconds()<10) {
            rob.rightRear.setPower(-.2);
            rob.rightFront.setPower(-.2);
            rob.leftRear.setPower(.2);
            rob.leftFront.setPower(.2);
        }

    }
}

