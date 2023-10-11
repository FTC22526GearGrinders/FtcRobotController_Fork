package org.firstinspires.ftc.teamcode.SetupOpCodes;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;

@Config
@Autonomous(name = "Auto Main CheckSwitches", group = "Test")

public class CheckStartupSwitches extends CommandOpMode {
    private IO_Subsystem ioss;
    boolean redAlliance;

    boolean backBoardStart;

    boolean lcr_one_bit;

    boolean lcr_two_bit;

    int lcr;

    @Override
    public void initialize() {

        ioss = new IO_Subsystem(this);


    }

    // Put run blocks here.
    public void run() {

        redAlliance = !ioss.dc0.getState();

        backBoardStart = !ioss.dc1.getState();


        lcr_one_bit = !ioss.dc2.getState();

        lcr_two_bit = !ioss.dc3.getState();

        if (!lcr_one_bit && !lcr_two_bit) lcr = 0;
        if (lcr_one_bit && !lcr_two_bit) lcr = 1;
        if (!lcr_one_bit && lcr_two_bit) lcr = 2;
        if (lcr_one_bit && lcr_two_bit) lcr = 3;

        telemetry.addData("RedAlliance", redAlliance);
        telemetry.addData("BackboardStart", backBoardStart);
        telemetry.addData("LCR 1 bit",lcr_one_bit);
        telemetry.addData("LCR 2 bit",lcr_two_bit);
        telemetry.addData("LCR",lcr);


        telemetry.update();

        CommandScheduler.getInstance().run();

    }
}
