package org.firstinspires.ftc.teamcode.OpModes_Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Auto.SelectValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;

@Config
@Autonomous(name = "Drive: Values", group = "Test")

public class TestMotionData extends CommandOpMode {

    FtcDashboard dashboard;
    private IO_Subsystem ioss;

    private int lcr = 0;

    private boolean lcr_one_bit;
    private boolean lcr_two_bit;
    private boolean redAlliance;

    private boolean bbStart;


    public void initialize() {

        CommandScheduler.getInstance().cancelAll();

        ioss = new IO_Subsystem(this);

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addData("Initted", true);


        telemetry.update();

        redAlliance = !ioss.dc0.getState();

        bbStart = !ioss.dc1.getState();

        lcr_one_bit = !ioss.dc3.getState();

        lcr_two_bit = !ioss.dc2.getState();

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

                new WaitCommand(60000)).schedule();


    }


    // Put run blocks here.
    public void run() {

        telemetry.addData("RedAlliance", redAlliance);
        telemetry.addData("BBstary", bbStart);
        telemetry.addData("LCR", lcr);

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
//
//
        telemetry.update();

        CommandScheduler.getInstance().run();
    }
}