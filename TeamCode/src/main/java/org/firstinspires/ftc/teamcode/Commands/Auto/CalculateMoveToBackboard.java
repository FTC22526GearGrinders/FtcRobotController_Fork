package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;


public class CalculateMoveToBackboard extends CommandBase {

    private AprilTagPoseFtc aprilTagPoseFtc;
    private Pose2d tagCalcPose;

    private Pose2d robotLastPose;

    public CalculateMoveToBackboard() {

    }

    @Override
    public void initialize() {
        aprilTagPoseFtc = ActiveMotionValues.getPoseFromTag();
        robotLastPose = ActiveMotionValues.getTagLookAheadPose();
    }


    @Override
    public void execute() {
        //need to extract info from apriltagposeftc and create a roadrunner Pose2d
        //need to add in robot length and camera offsets

        double x = aprilTagPoseFtc.x;

        double y = aprilTagPoseFtc.y;

        double yaw = aprilTagPoseFtc.yaw;

        double bearing = aprilTagPoseFtc.bearing;

        double range = aprilTagPoseFtc.range;

        Pose2d tagCalcPose = new Pose2d(-x, -y, -bearing);

        ActiveMotionValues.setPoseFromTag(aprilTagPoseFtc);


    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
