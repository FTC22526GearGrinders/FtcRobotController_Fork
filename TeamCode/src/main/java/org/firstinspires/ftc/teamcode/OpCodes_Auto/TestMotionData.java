package org.firstinspires.ftc.teamcode.OpCodes_Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Auto.SelectMotionValuesBlue;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectMotionValuesRed;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;

@Config
@Autonomous(name = "Drive: Values", group = "Test")

public class TestMotionData extends CommandOpMode {

    FtcDashboard dashboard;
    private IO_Subsystem ioss;

    public static int lcr = 2;
    public  boolean redAlliance;

    public  boolean bbStart;


    public void initialize() {

        CommandScheduler.getInstance().cancelAll();

        ioss = new IO_Subsystem(this);

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addData("Initted", true);


        telemetry.update();

        redAlliance = !ioss.dc0.getState();

        bbStart = !ioss.dc1.getState();


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
        telemetry.addData("LCR", ActiveMotionValues.getLcrpos());

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