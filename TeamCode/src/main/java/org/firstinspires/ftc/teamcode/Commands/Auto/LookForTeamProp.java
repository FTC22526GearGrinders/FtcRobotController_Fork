package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.CV.SpikeTapePipelineBlue;
import org.firstinspires.ftc.teamcode.CV.SpikeTapePipelineRed;
import org.firstinspires.ftc.teamcode.CV.SpikeTapePipelineRed2;
import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;
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



    // List<Rect> lr;


    public LookForTeamProp(CommandOpMode opMode, OpenCvWebcam webcam) {
        myOpMode = opMode;
        this.webcam = webcam;

    }

    @Override
    public void initialize() {


        //sptopB = new SpikeTapePipelineBlue();

        sptopR = new SpikeTapePipelineRed();

        //if (ActiveMotionValues.getRedAlliance())
            webcam.setPipeline(sptopR);

//        else
//            webcam.setPipeline(sptopB);


    }

    @Override
    public void execute() {
//        List<Rect> r = new ArrayList<>(10);
//     //   if (ActiveMotionValues.getRedAlliance()) {
//            r = sptopR.getRects();

//        } else {
//            r = sptopB.getRects();
//        }
//
//        lcr = calcLCR(r);
//
//        ActiveMotionValues.setLcrpos(lcr);


//        myOpMode.telemetry.addData("NumContours", sptopR.getNumberContours());
//
//        myOpMode.telemetry.addData("ValidContours", sptopR.getValidContours());


        myOpMode.telemetry.addData("W1", sptopR.getW1());
        myOpMode.telemetry.addData("W2", sptopR.getW2());
        myOpMode.telemetry.addData("W3", sptopR.getW3());
        myOpMode.telemetry.addData("Height", sptopR.getImgHeight());
        myOpMode.telemetry.addData("Width", sptopR.getImgWidth());




        myOpMode.telemetry.update();


    }

    @Override
    public void end(boolean interrupted) {
        webcam.closeCameraDevice();
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
