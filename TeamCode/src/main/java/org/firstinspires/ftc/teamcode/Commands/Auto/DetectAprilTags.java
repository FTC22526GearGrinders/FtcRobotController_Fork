package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


public class DetectAprilTags extends CommandBase {

    private final CommandOpMode myOpMode;


    private int n;
    private boolean tagsSeen;

    private final Vision_Subsystem vss;

    public DetectAprilTags(CommandOpMode opMode, Vision_Subsystem vss) {
        this.vss = vss;

        myOpMode = opMode;
    }

    @Override
    public void initialize() {
        n = ActiveMotionValues.getActTag();
    }

    @Override
    public void execute() {

        List<AprilTagDetection> currentDetections = vss.myAprilTagProcessor.getDetections();
        myOpMode.telemetry.addData("# AprilTags Detected", currentDetections.size());
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                tagsSeen = true;
                ActiveMotionValues.setAprilTagSeen(false);
                if (detection.id == n) {
                    ActiveMotionValues.setAprilTagSeen(true);

                    Pose2d camPose = new Pose2d(detection.ftcPose.y, detection.ftcPose.x, Math.toRadians(detection.ftcPose.yaw));

                    Pose2d tagPose = FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag());

                    Pose2d camFieldPose = tagPose.minus(camPose);

                    Pose2d currentRobotPose = camFieldPose.minus(Constants.RobotConstants.kCameraToRobot);

                    Pose2d robotPoseAtTag = tagPose.minus(Constants.RobotConstants.kCameraToRobot);

                    Pose2d tagOffsetPose = Constants.DriveConstants.tagOffsetPose;

                    Pose2d finalTagPose = robotPoseAtTag.minus(tagOffsetPose);

                    ActiveMotionValues.setFinalTagPose(finalTagPose);

                    ActiveMotionValues.setCurrentRobotPose(currentRobotPose);


                    myOpMode.telemetry.addData("Tag ID", detection.id);
                    myOpMode.telemetry.addLine();
                    myOpMode.telemetry.addData("TagPose", tagPose.toString());
                    myOpMode.telemetry.addLine();
                    myOpMode.telemetry.addData("CamPose", camPose.toString());
                    myOpMode.telemetry.addLine();
                    myOpMode.telemetry.addData("CamFieldPose", camFieldPose.toString());
                    myOpMode.telemetry.addLine();

                    myOpMode.telemetry.addData("CurrRobotPose", currentRobotPose.toString());
                    myOpMode.telemetry.addLine();
                    myOpMode.telemetry.addData("RobotPoseAtTag", robotPoseAtTag.toString());
                    myOpMode.telemetry.addLine();
                    myOpMode.telemetry.addData("TagDistPose", tagOffsetPose.toString());
                    myOpMode.telemetry.addLine();
                    myOpMode.telemetry.addData("FinalPose", finalTagPose.toString());
                    myOpMode.telemetry.addLine();

                    myOpMode.telemetry.update();


                }

            } else {
                tagsSeen = false;
            }

            int numTagsseen = currentDetections.size();

        }

    }

    @Override

    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return ActiveMotionValues.getAprilTagSeen();
    }
}
