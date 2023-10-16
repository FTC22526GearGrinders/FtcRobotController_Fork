package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.teamcode.Commands.Auto.LookForTeamProp;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

/**
 * Created by Tom on 9/26/17.  Updated 9/24/2021 for PIDF.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This OpMode uses the extended/enhanced
 * PIDF-related functions of the DcMotorEx class.  The REV Robotics Expansion Hub
 * supports the extended motor functions, but other controllers (such as the
 * deprecated Modern Robotics and Hitechnic DC Motor Controllers) do not.
 */
@Config
@Autonomous(name = "Vision: Team Element", group = "Auto")

public class LookForTeamElement extends CommandOpMode {

    // our DC motor


    FtcDashboard dashboard;

    public static int DISPSWITCH = 0;


    public OpenCvWebcam webcam;//

    private int myExposure;
    private int minExposure;
    private int maxExposure;
    private int myGain;
    private int minGain;
    private int maxGain;

    boolean thisExpUp = false;
    boolean thisExpDn = false;
    boolean thisGainUp = false;
    boolean thisGainDn = false;

    boolean lastExpUp = false;
    boolean lastExpDn = false;
    boolean lastGainUp = false;
    boolean lastGainDn = false;
    private boolean exposureSupported;
    private boolean cameraOpened;


    public void initialize() {


        webcam = OpenCvCameraFactory.getInstance().createWebcam(this.hardwareMap.get(WebcamName.class, "Webcam 1"));

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());


        //if you are using dashboard, update dashboard camera view
        FtcDashboard.getInstance().startCameraStream(webcam, 5);



        cameraOpened = false;
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {


            @Override

            public void onOpened() {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
                 * supports streaming from the webcam in the uncompressed YUV image format. This means
                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
                 *
                 * Also, we specify the rotation that the webcam is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */

                //    webcam.setPipeline(stpb);
                // webcam.setPipeline(stpb);
                //start streaming the camera
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);

                //if you are using dashboard, update dashboard camera view



                cameraOpened = true;

            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });


        // new WaitCommand(10000000).schedule();
        new LookForTeamProp(this, webcam).schedule();
    }

    // Put run blocks here.
    public void run() {

        if (!exposureSupported) {

            exposureSupported = webcam.getExposureControl().isExposureSupported();

            webcam.getExposureControl().setMode(ExposureControl.Mode.Auto);

        }


//        webcam.getExposureControl().setMode(ExposureControl.Mode.Auto);
//
//        thisExpUp = gamepad1.left_bumper;
//        thisExpDn = gamepad1.left_trigger > 0.25;
//        thisGainUp = gamepad1.right_bumper;
//        thisGainDn = gamepad1.right_trigger > 0.25;
//
//        // look for clicks to change exposure
//        if (thisExpUp && !lastExpUp) {
//
//            myExposure = Range.clip(myExposure + 1, minExposure, maxExposure);
//            webcam.getExposureControl().setExposure(myExposure, TimeUnit.MILLISECONDS);
//        } else if (thisExpDn && !lastExpDn) {
//            minExposure = (int) webcam.getExposureControl().getMinExposure(TimeUnit.MILLISECONDS);
//            maxExposure = (int) webcam.getExposureControl().getMaxExposure(TimeUnit.MILLISECONDS);
//
//
//            myExposure = Range.clip(myExposure - 1, minExposure, maxExposure);
//            webcam.getExposureControl().setExposure(myExposure, TimeUnit.MILLISECONDS);
//        }
//
//        long eposure = webcam.getExposureControl().getExposure(TimeUnit.MILLISECONDS);


//      telemetry.addData("ExpSupp",exposureSupported);
//      telemetry.addData("Exp millis",eposure);
//      telemetry.update();
        CommandScheduler.getInstance().run();

    }


}

