package org.firstinspires.ftc.teamcode.Subsystems;


import android.util.Size;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;


public class Vision_Subsystem extends SubsystemBase {

    AprilTagLibrary myAprilTagLibrary;
    public AprilTagProcessor myAprilTagProcessor;
// Create the AprilTag processor and assign it to a variable.

    VisionPortal.Builder myVisionPortalBuilder;
    public VisionPortal myVisionPortal;


    public CommandOpMode myOpMode;

    private boolean closeCamera = false;

    public int lpctr;


    public Vision_Subsystem(CommandOpMode opMode) {
        myOpMode = opMode;
        setCloseCamera(false);
// Get the AprilTagLibrary for the current season.
        myAprilTagLibrary = AprilTagGameDatabase.getCurrentGameTagLibrary();


        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        myAprilTagProcessor = new AprilTagProcessor.Builder()
                .setTagLibrary(myAprilTagLibrary)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setLensIntrinsics(634.549, 634.549, 303.319, 235.933) //C920HD
                //  .setLensIntrinsics( 634.549, 634.549, 303.319, 235.933)//ms2
                //   .setLensIntrinsics( 1156.14, 1156.14, 643.32, 371.34)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .build();


        myVisionPortalBuilder = new VisionPortal.Builder();

// Specify the camera to be used for this VisionPortal.
        myVisionPortalBuilder.setCamera(myOpMode.hardwareMap.get(WebcamName.class, "Webcam 1"));      // Other choices are: RC phone camera and "switchable camera name".

// Add the AprilTag Processor to the VisionPortal Builder.
        myVisionPortalBuilder.addProcessor(myAprilTagProcessor);
        // An added Processor is enabled by default.

// Optional: set other custom features of the VisionPortal (4 are shown here).
        myVisionPortalBuilder.setCameraResolution(new Size(640, 480));  // Each resolution, for each camera model, needs calibration values for good pose estimation.
        myVisionPortalBuilder.setStreamFormat(VisionPortal.StreamFormat.YUY2);  // MJPEG format uses less bandwidth than the default YUY2.

        myVisionPortalBuilder.setAutoStopLiveView(true);     // Automatically stop LiveView (RC preview) when all vision processors are disabled.

// Create a VisionPortal by calling build()
        myVisionPortal = myVisionPortalBuilder.build();

    }

    public void initialize() {

        lpctr=0;

        enableAprilTagProcessor(false);


//        myOpMode.telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
//        myOpMode.telemetry.addData("CamNane", visionPortal.getCameraState());
//        myOpMode.telemetry.update();

    }

    @Override

    public void periodic() {
//        myOpMode.telemetry.addData("Image", object);
//        myOpMode.telemetry.addData("result", result);
//        myOpMode.telemetry.addData("bolt0", xy_Bolt[0]);
//        myOpMode.telemetry.addData("bolt1", xy_Bolt[1]);
//        myOpMode.telemetry.addData("targ0", xyTarget[0]);

    }

    public void enableAprilTagProcessor(boolean on) {
        myVisionPortal.setProcessorEnabled(myAprilTagProcessor, on);
    }


    public void setAprilTagDecimation(int val) {
        // Adjust Image Decimation to trade-off detection-range for detection-rate.
        // eg: Some typical detection data using a Logitech C920 WebCam
        // Decimation = 1 ..  Detect 2" Tag from 10 feet away at 10 Frames per second
        // Decimation = 2 ..  Detect 2" Tag from 6  feet away at 22 Frames per second
        // Decimation = 3 ..  Detect 2" Tag from 4  feet away at 30 Frames Per Second
        // Decimation = 3 ..  Detect 5" Tag from 10 feet away at 30 Frames Per Second
        // Note: Decimation can be changed on-the-fly to adapt during a match.
        myAprilTagProcessor.setDecimation(val);

    }

    public void setCloseCamera(boolean val) {
        closeCamera = val;
    }

    public boolean getCloseCamera() {
        return closeCamera;
    }
}





