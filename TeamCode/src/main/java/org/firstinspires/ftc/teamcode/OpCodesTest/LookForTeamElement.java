package org.firstinspires.ftc.teamcode.OpCodesTest;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Auto.LookForTeamProp;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
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
@TeleOp(name = "Vision: Team Element", group = "Auto")

public class LookForTeamElement extends CommandOpMode {

    // our DC motor


    FtcDashboard dashboard;

    public OpenCvWebcam webcam;//
    private boolean cameraOpened;
    private StageSwitchingPipeline sptop;

    public static int redThreshold = 170;
    public static int blueThreshold = 150;

    public static int left = 146;

    public static int right = 231;

    int lastleft;

    int lastRight;
    int lastBlue;

    int lastRed;


    public void initialize() {


        webcam = OpenCvCameraFactory.getInstance().createWebcam(this.hardwareMap.get(WebcamName.class, "Webcam 2"));

        dashboard = FtcDashboard.getInstance();

        boolean redAlliance = ActiveMotionValues.getRedAlliance();


        sptop = new StageSwitchingPipeline(redAlliance);


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

                webcam.setPipeline(sptop);
                // webcam.setPipeline(stpb);
                //start streaming the camera
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

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
        new LookForTeamProp(this, webcam, true).schedule();
    }

    // Put run blocks here.
    public void run() {

        if (lastBlue != blueThreshold) {
            sptop.setBlueThresholed(blueThreshold);
            lastBlue = blueThreshold;
        }

        if (lastRed != redThreshold) {
            sptop.setRedThreshold(redThreshold);
            lastRed = redThreshold;
        }

        if (lastleft != left) {
            sptop.left = left;
            lastleft = left;
        }
        if (lastRight != right) {
            sptop.right = right;
            lastRight = right;
        }

        telemetry.addData("Streaming", webcam.getFps());
        telemetry.addData("RRAIsempty", sptop.rrAreas.isEmpty());

        telemetry.addData("LCR", ActiveMotionValues.getLcrpos());
        telemetry.addData("Red", sptop.getRedPipeline());
        telemetry.addData("BlTh", sptop.blueThreshold);

        telemetry.addData("LCR", sptop.lcr);
        telemetry.addData("NumCon", sptop.numContoursFound);
        telemetry.addData("ValCon", sptop.usableContours);
        if (!sptop.rr.isEmpty())
            telemetry.addData("RRSize", sptop.rr.size());
        if (!sptop.rrxval.isEmpty())
            telemetry.addData("XVALSize", sptop.rrxval.size());
        if (!sptop.rrAreas.isEmpty())
            telemetry.addData("AreasSize", sptop.rrAreas.size());

        telemetry.addData("area0", sptop.getArea(0));
        telemetry.addData("X0", sptop.getX(0));
        telemetry.addData("area1", sptop.getArea(1));
        telemetry.addData("X1", sptop.getX(1));

        telemetry.addData("area2", sptop.getArea(2));
        telemetry.addData("X2", sptop.getX(2));


        if (sptop.getUsableContours() > 1) {

            telemetry.addData("Cont Found", sptop.getNumberContours());
            telemetry.addData("Usable", sptop.getUsableContours());

        }
        telemetry.update();

        CommandScheduler.getInstance().run();

    }


}

