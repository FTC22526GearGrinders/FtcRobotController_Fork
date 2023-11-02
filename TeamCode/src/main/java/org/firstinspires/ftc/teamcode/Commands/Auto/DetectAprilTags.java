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

    private CommandOpMode myOpMode;


    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    private int n;
    private boolean tagsSeen;

    private Vision_Subsystem vss;

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
                if (detection.id == n) {


                    Pose2d camPose = new Pose2d(detection.ftcPose.y, detection.ftcPose.x, Math.toRadians(detection.ftcPose.yaw));

                    Pose2d tagPose = FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag());

                    Pose2d camFieldPose = tagPose.minus(camPose);

                    Pose2d currentRobotPose = camFieldPose.minus(Constants.RobotConstants.kCameraToRobot);

                    Pose2d robotPoseAtTag = tagPose.minus(Constants.RobotConstants.kCameraToRobot);

                    Constants.ArmConstants.armExtensions entry = Constants.ArmConstants.armExtensions.values()[ActiveMotionValues.getBackboardLevel()];

                    double distance = entry.tagDistance;

                    Pose2d tagDistancePose = new Pose2d(distance, 0);

                    Pose2d finalTagPose = robotPoseAtTag.minus(tagDistancePose);

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
                    myOpMode.telemetry.addData("TagDistPose", tagDistancePose.toString());
                    myOpMode.telemetry.addLine();
                    myOpMode.telemetry.addData("FinalPose", finalTagPose.toString());
                    myOpMode.telemetry.addLine();

                    myOpMode.telemetry.update();


                }

            } else {
                tagsSeen = false;
            }

            int numTagsseen = currentDetections.size();


//            myOpMode.telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
//            myOpMode.telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
//            myOpMode.telemetry.addLine("RBE = Range, Bearing & Elevation");


        }
        //  myOpMode.telemetry.update();
    }

    @Override

    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
