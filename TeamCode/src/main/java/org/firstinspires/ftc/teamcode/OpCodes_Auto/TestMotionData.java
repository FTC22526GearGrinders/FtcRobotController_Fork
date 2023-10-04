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
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;

@Config
@Autonomous(name = "Drive: Values", group = "Test")

public class TestMotionData extends CommandOpMode {

    FtcDashboard dashboard;

    public static boolean redAlliance;
    public static int lcr = 1;

    public static boolean bbstart=true;

    public void initialize() {

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addData("Initted", true);


        telemetry.update();

        ActiveMotionValues.setRedAlliance(redAlliance);

        ActiveMotionValues.setLcrpos(lcr);

        ActiveMotionValues.setBBStart(!bbstart);

        new SequentialCommandGroup(

                new ConditionalCommand(

                        new SelectMotionValuesRed(bbstart,lcr), new SelectMotionValuesBlue(bbstart,lcr), () -> redAlliance),


                new WaitCommand(60000));



    }


    // Put run blocks here.
    public void run() {

        telemetry.addData("StartPoseX", ActiveMotionValues.startPose.getX());
        telemetry.addData("StartPoseY", ActiveMotionValues.startPose.getY());
        telemetry.addData("StartPoseAng", ActiveMotionValues.startPose.getHeading());

        telemetry.addData("XFirst", ActiveMotionValues.xFirstPoint);
        telemetry.addData("Y Secnd", ActiveMotionValues.ySecondPoint);

           telemetry.addData("TagLAPoseX", ActiveMotionValues.finalPose.getX());
//        telemetry.addData("TagLAPoseY", ActiveMotionValues.tagLookAheadPose.getY());
//        telemetry.addData("TagLAPoseAng", ActiveMotionValues.tagLookAheadPose.getHeading());

        telemetry.addData("RetctDist", ActiveMotionValues.retractDistance);

        telemetry.addData("XOffset", ActiveMotionValues.xOffset);
        telemetry.addData("YOffset", ActiveMotionValues.yOffset);
       telemetry.addData("Atag", ActiveMotionValues.getActTag());
//
//
       telemetry.update();

        CommandScheduler.getInstance().run();
    }
}