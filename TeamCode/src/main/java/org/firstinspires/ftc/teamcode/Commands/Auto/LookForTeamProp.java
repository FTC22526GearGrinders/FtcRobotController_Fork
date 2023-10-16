package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.CV.SpikeTapePipelineBlue;
import org.firstinspires.ftc.teamcode.CV.SpikeTapePipelineRed;
import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;

/*Camera needs to see all 3 spike tapes with enough space to the left, top and right for
 *the area of the prop
 * Tape is 1" wide x 13" long while prop is 3" x 3" x 3"
 * Tiles are nominal 24" square.
 *  Distance between left and right tape centers is nominal 22"
 * Gap left and right at top tap approx. 5"
 * Allow 6" left, right and center means camera view is 34" wide x 18"high
 * Camera resolution is 640 px wide  x320 px high px count starts top left corner x left y down
 *Px per inch = 640/34 approx 20 width and 320/18 approx 18 high
 *Spike tape pixels = 20 * 12 + 18 *1 = 258
 *
 * */
public class LookForTeamProp extends CommandBase {

    SpikeTapePipelineRed sptopR = null;

    SpikeTapePipelineBlue sptopB = null;

    CommandOpMode myOpMode;

    FtcDashboard dashboard;


    private OpenCvWebcam webcam;

    private int lcr;
    private int xLeft;
    private int xRight;

    private int yTop;

    double lpctr;


    public LookForTeamProp(CommandOpMode opMode, OpenCvWebcam webcam) {
        myOpMode = opMode;
        this.webcam = webcam;

    }

    @Override
    public void initialize() {


//        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//
//
//            @Override
//
//            public void onOpened() {
//                /*
//                 * Tell the webcam to start streaming images to us! Note that you must make sure
//                 * the resolution you specify is supported by the camera. If it is not, an exception
//                 * will be thrown.
//                 *
//                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
//                 * supports streaming from the webcam in the uncompressed YUV image format. This means
//                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
//                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
//                 *
//                 * Also, we specify the rotation that the webcam is used in. This is so that the image
//                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
//                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
//                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
//                 * away from the user.
//                 */
//
//                //    webcam.setPipeline(stpb);
//                // webcam.setPipeline(stpb);
//                //start streaming the camera
//                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
//
//
//                //if you are using dashboard, update dashboard camera view
//           //     FtcDashboard.getInstance().startCameraStream(webcam, 5);
//
//
//            }
//
//            @Override
//            public void onError(int errorCode) {
//                /*
//                 * This will be called if the camera could not be opened
//                 */
//            }
//        });


//        lpctr = 0;
//
//        sptopB = new SpikeTapePipelineBlue();
//
//        sptopR = new SpikeTapePipelineRed();
//
//        if (ActiveMotionValues.getRedAlliance())
//            webcam.setPipeline(sptopR);
//
//        else
//            webcam.setPipeline(sptopB);
//
    }

    @Override
    public void execute() {
        lpctr++;

//
//        myOpMode.telemetry.addData("NumContours", sptopB.getNumberContours());
//
//        myOpMode.telemetry.addData("ValidContours", sptopB.getValidContours());
//
//        myOpMode.telemetry.addData("Streaming", webcam.getFps());
//


//        myOpMode.telemetry.addData("W1", sptopR.getW1());
//        myOpMode.telemetry.addData("W2", sptopR.getW2());
//        myOpMode.telemetry.addData("W3", sptopR.getW3());
//        myOpMode.telemetry.addData("LCR", sptopR.getLCR());
//
//
//        myOpMode.telemetry.addData("Cols", sptopR.getImgCols());
//        myOpMode.telemetry.addData("Rows", sptopR.getImgRows());
//
//
//
//
        myOpMode.telemetry.update();


    }

    @Override
    public void end(boolean interrupted) {
        webcam.closeCameraDeviceAsync(new OpenCvCamera.AsyncCameraCloseListener() {
            @Override
            public void onClose() {
                myOpMode.telemetry.addData("Closing ", "");
                myOpMode.telemetry.update();
            }
        });

    }


    @Override
    public boolean isFinished() {
        return false;
    }


    private int calcLCR(List<Rect> r) {


        xLeft = 0;
        xRight = 0;
        yTop = 0;


        int lcr = 0;

        xLeft = Math.min(r.get(0).x, Math.min(r.get(0).x, r.get(2).x));

        xRight = Math.max(r.get(0).x, Math.max(r.get(1).x, r.get(2).x));

        yTop = Math.min(r.get(0).y, Math.min(r.get(1).y, r.get(2).y));

        if (r.get(0).x == xLeft)
            lcr = 1;

        if (r.get(0).y == yTop) lcr = 2;


        if (r.get(0).x == xRight)
            lcr = 3;

        return lcr;
    }

}
