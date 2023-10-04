package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;


public class DetectAprilTags extends CommandBase {

    private CommandOpMode myOpMode;

    private AprilTagProcessor aprilTag;

    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    private VisionPortal visionPortal;

    private OpenCvWebcam webcam;

    private int n;
    private boolean tagsSeen;

    public DetectAprilTags(CommandOpMode opMode, AprilTagProcessor aprilTag, int n) {
        myOpMode = opMode;
        this.aprilTag = aprilTag;
        this.n = n;
    }

    @Override
    public void initialize() {

//
    }

    @Override
    public void execute() {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        myOpMode.telemetry.addData("# AprilTags Detected", currentDetections.size());
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                tagsSeen = true;
                if (detection.id == n) {
                    ActiveMotionValues.setPoseFromTag(detection.ftcPose);
                }


                myOpMode.telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                myOpMode.telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                myOpMode.telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                myOpMode.telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                tagsSeen = false;
                myOpMode.telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                myOpMode.telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }

            int numTagsseen = currentDetections.size();


            myOpMode.telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
            myOpMode.telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
            myOpMode.telemetry.addLine("RBE = Range, Bearing & Elevation");


        }
        myOpMode.telemetry.update();
    }

    @Override

    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
