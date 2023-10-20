package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Auto.SelectAndRunTrajectory;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;

@Config
@Autonomous(name = "Auto Main Test Trajectories", group = "Test")

public class TestTrajectories extends CommandOpMode {
    boolean redAlliance;

    boolean backBoardStart;

    boolean useStageDoor;

    boolean centerPark;

    boolean secondPixel;
    int lcr;

    Drive_Subsystem drive;
    PixelHandlerSubsystem phss;

    @Override
    public void initialize() {

        drive = new Drive_Subsystem(this);

        phss = new PixelHandlerSubsystem(this);
        redAlliance = ActiveMotionValues.getRedAlliance();

        backBoardStart = ActiveMotionValues.getBBStart();

        useStageDoor = ActiveMotionValues.getUseStageDoor();

        centerPark = ActiveMotionValues.getCenterPark();

        secondPixel = ActiveMotionValues.getSecondpixel();

        lcr = ActiveMotionValues.getLcrpos();

        new SequentialCommandGroup(
                new SelectValues(),
                new SelectAndRunTrajectory(drive, phss)).schedule();



    }

    // Put run blocks here.
    public void run() {


        telemetry.addData("RedAlliance", redAlliance);
        telemetry.addData("BackboardStart", backBoardStart);
        telemetry.addData("LCR", lcr);

        telemetry.addData("Stage Door", useStageDoor);
        telemetry.addData("Center Park", centerPark);
        telemetry.addData("SeondPixe;", secondPixel);


        telemetry.update();

        CommandScheduler.getInstance().run();

    }
}
