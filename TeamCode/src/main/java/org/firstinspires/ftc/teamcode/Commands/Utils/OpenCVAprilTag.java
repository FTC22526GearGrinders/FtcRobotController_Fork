package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.CV.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;

public class OpenCVAprilTag extends CommandBase {

    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    public final OpenCvCamera webcam;

    double m_timeOut;
    CommandOpMode m_opMode;
    ElapsedTime m_elapsedTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    AprilTagDetection tagOfInterest = null;

    public static final double FEET_PER_METER = 3.28084;
    public static final double fx = 634.549;//933.6;//1420.7594;
    public static final double fy = 634.549;//933.6;//1420.9965;
    public static final double cx = 303.319;//659;//630.8343;
    public static final double cy = 235.933;//411;///381.3086;
    public static final double tagsize = 0.166;
    public static int numFramesWithoutDetection = 0;

    public static final float DECIMATION_HIGH = 3;
    public static final float DECIMATION_LOW = 2;
    public static final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    public static final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;
    public static  int ID_TAG_OF_INTEREST = 5;


    public OpenCVAprilTag(CommandOpMode _opMode, OpenCvCamera webcam,double _timeOut) {
        m_timeOut = _timeOut;
        m_opMode = _opMode;
        this.webcam = webcam;

    }

    @Override
    public void initialize() {
             aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

       webcam .setPipeline(aprilTagDetectionPipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
        m_elapsedTimer.reset();
        m_opMode.telemetry.setMsTransmissionInterval(50);


        m_elapsedTimer.reset();
        m_opMode.telemetry.setMsTransmissionInterval(50);
    }

    @Override
    public void execute() {

        ID_TAG_OF_INTEREST=ActiveMotionValues.getActTag();

        // Calling getDetectionsUpdate() will only return an object if there was a new frame
        // processed since the last time we called it. Otherwise, it will return null. This
        // enables us to only run logic when there has been a new frame, as opposed to the
        // getLatestDetections() method which will always return an object.

        ArrayList<AprilTagDetection> detections = aprilTagDetectionPipeline.getDetectionsUpdate();

        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
        m_opMode.telemetry.addData("Num Tags", currentDetections.size());
        m_opMode.telemetry.addData("TagOfInterest", ID_TAG_OF_INTEREST);

        if (currentDetections.size() != 0) {
            boolean tagFound = false;

            for (AprilTagDetection tag : currentDetections) {
                if (tag.id == ID_TAG_OF_INTEREST) {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }

            if (tagFound) {
                m_opMode.telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                tagToTelemetry(tagOfInterest);
            } else {
                m_opMode.telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
                    m_opMode.telemetry.addLine("(The tag has never been seen)");
                } else {
                    m_opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }

        } else {
            m_opMode.telemetry.addLine("Don't see tag of interest :(");

            if (tagOfInterest == null) {
                m_opMode.telemetry.addLine("(The tag has never been seen)");
            } else {
                m_opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                tagToTelemetry(tagOfInterest);
            }

        }

        m_opMode.telemetry.update();
        m_opMode.sleep(20);


        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if (tagOfInterest != null) {
            m_opMode.telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            m_opMode.telemetry.update();
        } else {
              m_opMode.telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            m_opMode.telemetry.update();
        }

        /* Actually do something useful */
        if (tagOfInterest == null) {
            /*
             * Insert your autonomous code here, presumably running some default configuration
             * since the tag was never sighted during INIT
             */
        } else {
            /*
             * Insert your autonomous code here, probably using the tag pose to decide your configuration.
             */

            // e.g.
            if (tagOfInterest.pose.x <= 20) {
                // do something
            } else if (tagOfInterest.pose.x >= 20 && tagOfInterest.pose.x <= 50) {
                // do something else
            } else if (tagOfInterest.pose.x >= 50) {
                // do something else
            }
        }
    }

    @Override
    public boolean isFinished() {
        if (m_elapsedTimer.seconds() > m_timeOut) {
            return true;
        }
        return false;
    }

    void tagToTelemetry(AprilTagDetection detection) {
        Orientation rot = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);

        m_opMode.telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        m_opMode.telemetry.addLine(String.format("Translation X: %.2f m", detection.pose.x));
        m_opMode.telemetry.addLine(String.format("Translation Y: %.2f m", detection.pose.y));
        m_opMode.telemetry.addLine(String.format("Translation Z: %.2f m", detection.pose.z));
        m_opMode.telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rot.firstAngle));
        m_opMode.telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rot.secondAngle));
        m_opMode.telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rot.thirdAngle));
    }
}
