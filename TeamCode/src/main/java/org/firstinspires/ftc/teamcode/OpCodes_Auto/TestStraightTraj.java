package org.firstinspires.ftc.teamcode.OpCodes_Auto;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Auto.CenterTapeTest;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;

@Config
@Autonomous(name = "Auto Straight Traj", group = "Test")

public class TestStraightTraj extends CommandOpMode {


    private Drive_Subsystem drive;


    @Override
    public void initialize() {


        drive = new Drive_Subsystem(this);

        new CenterTapeTest(drive).schedule();


    }


    // Put run blocks here.
    public void run() {
drive .showtelemetry(telemetry);

        CommandScheduler.getInstance().run();

    }
}
