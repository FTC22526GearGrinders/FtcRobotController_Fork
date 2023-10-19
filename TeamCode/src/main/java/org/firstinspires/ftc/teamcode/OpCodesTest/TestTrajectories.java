package org.firstinspires.ftc.teamcode.OpCodesTest;

import android.net.wifi.aware.PublishDiscoverySession;
import android.view.View;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Auto.SelectAndRunTrajectory;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;

@Config
@Autonomous(name = "Auto Main Test Trajectories", group = "Test")

public class TestTrajectories extends CommandOpMode {
    private IO_Subsystem ioss;
    boolean redAlliance;

    boolean backBoardStart;

    boolean lcr_one_bit;

    boolean lcr_two_bit;

    boolean useStageDoor;

    boolean centerPArk;

    int lcr;

    Drive_Subsystem drive;
    PixelHandlerSubsystem phss;

    @Override
    public void initialize() {

        ioss = new IO_Subsystem(this);

        drive = new Drive_Subsystem(this);

        phss = new PixelHandlerSubsystem(this);


    }

    // Put run blocks here.
    public void run() {

        redAlliance = !ioss.dc0.getState();

        backBoardStart = !ioss.dc1.getState();

        useStageDoor = !ioss.dc4.getState();

        centerPArk = !ioss.dc5.getState();



        lcr_one_bit = !ioss.dc2.getState();

        lcr_two_bit = !ioss.dc3.getState();

        if (!lcr_one_bit && !lcr_two_bit) lcr = 0;
        if (lcr_one_bit && !lcr_two_bit) lcr = 1;
        if (!lcr_one_bit && lcr_two_bit) lcr = 2;
        if (lcr_one_bit && lcr_two_bit) lcr = 3;


        ActiveMotionValues.setRedAlliance(redAlliance);
        ActiveMotionValues.setBBStart(backBoardStart);
        ActiveMotionValues.setLcrpos(lcr);
        ActiveMotionValues.setUseStageDoor(useStageDoor);
        ActiveMotionValues.setCenterPark(centerPArk);

        new SequentialCommandGroup(
                new SelectValues(),
                new SelectAndRunTrajectory(drive, phss)).schedule();


        telemetry.addData("RedAlliance", redAlliance);
        telemetry.addData("BackboardStart", backBoardStart);
        telemetry.addData("LCR 1 bit", lcr_one_bit);
        telemetry.addData("LCR 2 bit", lcr_two_bit);
        telemetry.addData("LCR", lcr);


        telemetry.update();

        CommandScheduler.getInstance().run();

    }
}
