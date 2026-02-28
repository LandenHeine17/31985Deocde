package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutoMain extends LinearOpMode {
    ElapsedTime runTime;
    RobotHardware rob = new RobotHardware();

    @Override
    public void runOpMode() {
        rob.init(hardwareMap);
        runTime.reset();
        waitForStart();

        rob.leftRear.setPower(0.5);
        rob.rightRear.setPower(0.5);
        rob.leftFront.setPower(0.5);
        rob.rightFront.setPower(0.5);

        sleep(500);

        rob.leftRear.setPower(0);
        rob.rightRear.setPower(0);
        rob.leftFront.setPower(0);
        rob.rightFront.setPower(0);

        sleep(500);
    }
}
