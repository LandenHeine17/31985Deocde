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
        waitForStart();
        runTime.reset();
    }
}
