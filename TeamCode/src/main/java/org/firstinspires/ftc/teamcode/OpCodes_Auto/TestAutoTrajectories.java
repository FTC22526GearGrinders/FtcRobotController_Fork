package org.firstinspires.ftc.teamcode.OpCodes_Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Auto.CenterTapePlacePixel;
import org.firstinspires.ftc.teamcode.Commands.Auto.LRTapePlacePixel;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


/**
 * Created by Tom on 9/26/17.  Updated 9/24/2021 for PIDF.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This OpMode uses the extended/enhanced
 * PIDF-related functions of the DcMotorEx class.  The REV Robotics Expansion Hub
 * supports the extended motor functions, but other controllers (such as the
 * deprecated Modern Robotics and Hitechnic DC Motor Controllers) do not.
 */
@Config
@Autonomous(name = "Drive: Tune PID", group = "Test")

public class TestAutoTrajectories extends CommandOpMode {

    private Drive_Subsystem drive;
    FtcDashboard dashboard;

    public static int TRAJNUM = 0;


    public void initialize() {

        drive = new Drive_Subsystem(this);

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        //new SelectMotionValues(TRAJNUM).schedule();

        switch (TRAJNUM) {

            case 0:
            case 2:
            case 10:
            case 12:
                new LRTapePlacePixel(drive).schedule();
                break;
            default:
                break;
        }

        switch (TRAJNUM) {
            case 1:
                case11:
                new CenterTapePlacePixel(drive).schedule();
                break;
            default:
                break;
        }


    }


    // Put run blocks here.
    public void run() {

        telemetry.addData("StartPoseX", ActiveMotionValues.startPose.getX());
        telemetry.addData("XFirst", ActiveMotionValues.xFirstPoint);
        telemetry.addData("Y Sednd", ActiveMotionValues.ySecondPoint);
        telemetry.addData("RagLAPoseX", ActiveMotionValues.tagLookAheadPose.getX());

        telemetry.addData("RetctDist", ActiveMotionValues.retractDistance);

        telemetry.addData("XOffset", ActiveMotionValues.xOffset);
        telemetry.addData("YOffset", ActiveMotionValues.yOffset);


        telemetry.update();


    }
}