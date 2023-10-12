package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Auto.PickAndRunTrajectories;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;

@Config
@Autonomous(name = "Test Run Trajs", group = "Test")

public class TestTrajMode extends CommandOpMode {
    private IO_Subsystem ioss;

    private Drive_Subsystem drive;

    FtcDashboard dashboard;


    public static int lcr = 1;

    public static boolean redAlliance;

    public static boolean bbStart;

    @Override
    public void initialize() {


        drive = new Drive_Subsystem(this);

        ioss = new IO_Subsystem(this);

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());


        redAlliance = !ioss.dc0.getState();

        bbStart = !ioss.dc1.getState();

        boolean lcr_one_bit = !ioss.dc3.getState();

        boolean lcr_two_bit = !ioss.dc2.getState();

        if (!lcr_one_bit && !lcr_two_bit) lcr = 0;
        if (lcr_one_bit && !lcr_two_bit) lcr = 1;
        if (!lcr_one_bit && lcr_two_bit) lcr = 2;
        if (lcr_one_bit && lcr_two_bit) lcr = 3;

        if (lcr == 0) lcr = 2;

        ActiveMotionValues.setRedAlliance(redAlliance);
        ActiveMotionValues.setBBStart(bbStart);
        ActiveMotionValues.setLcrpos(lcr);
        new SequentialCommandGroup(

                new SelectValues(),

                new PickAndRunTrajectories(drive)).schedule();


    }

    // Put run blocks here.
    public void run() {


        telemetry.addData("RedAlliance", redAlliance);
        telemetry.addData("BBstary", bbStart);
        telemetry.addData("LCR", lcr);

telemetry.addData("LFPos", drive.drive.getLeftFrontPosn());
        telemetry.addData("StartPoseX", ActiveMotionValues.getStartPose().getX());
        telemetry.addData("StartPoseY", ActiveMotionValues.getStartPose().getY());
        telemetry.addData("StartPoseAng", ActiveMotionValues.getStartPose().getHeading());
        telemetry.addData("StartPose", ActiveMotionValues.getStartPose().toString());

        telemetry.addData("XFirst", ActiveMotionValues.getxFirstPoint());
        telemetry.addData("Y First", ActiveMotionValues.getyFirstPoint());

        telemetry.addData("Y Secnd", ActiveMotionValues.getySecondPoint());

        telemetry.addData("TagLAPoseX", ActiveMotionValues.getFinalPose().getX());
        telemetry.addData("TagLAPoseY", ActiveMotionValues.getFinalPose().getY());
        telemetry.addData("TagLAPoseAng", ActiveMotionValues.getFinalPose().getHeading());

        telemetry.addData("RetctDist", ActiveMotionValues.getRetractDistance());

        telemetry.addData("XOffset", ActiveMotionValues.getxOffset());
        telemetry.addData("YOffset", ActiveMotionValues.getyOffset());
        telemetry.addData("Atag", ActiveMotionValues.getActTag());


        telemetry.update();



        CommandScheduler.getInstance().run();

    }
}
