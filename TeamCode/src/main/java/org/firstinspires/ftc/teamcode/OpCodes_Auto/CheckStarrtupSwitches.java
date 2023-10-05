package org.firstinspires.ftc.teamcode.OpCodes_Auto;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;

@Config
@Autonomous(name = "Auto Main CheckSwitches", group = "Test")

public class CheckStarrtupSwitches extends CommandOpMode {
    private IO_Subsystem ioss;
    boolean redAlliance;

    boolean backBoardStart;

    @Override
    public void initialize() {

        ioss = new IO_Subsystem(this);


    }

    // Put run blocks here.
    public void run() {

        redAlliance = !ioss.dc0.getState();

        backBoardStart = !ioss.dc1.getState();

        telemetry.addData("RedAlliance", redAlliance);
        telemetry.addData("BackboardStart", backBoardStart);
        telemetry.update();

        CommandScheduler.getInstance().run();

    }
}
