package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.CV.SpikeTapePipeline;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.Timer;

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

    SpikeTapePipeline sptop = null;


    CommandOpMode myOpMode;

    FtcDashboard dashboard;


    private OpenCvWebcam webcam;

    int lastlcr;
    double lpctr;

    Timer timer = new Timer();

    private OpenCvPipeline pl;

    private boolean noEnd;


    public LookForTeamProp(CommandOpMode opMode, OpenCvWebcam webcam, boolean noEnd) {
        myOpMode = opMode;
        this.webcam = webcam;
        this.noEnd=noEnd;
    }

    @Override
    public void initialize() {

        lastlcr = 0;


    }

    @Override
    public void execute() {

        lpctr++;

        int currentLCR = ActiveMotionValues.getLcrpos();

        if (currentLCR != 0 && lastlcr == 0)
            lastlcr = currentLCR;

        if (lastlcr != 0 && lastlcr == currentLCR)
            lpctr++;
        else {
            lastlcr = 0;
            lpctr = 0;
        }

    }

    @Override
    public void end(boolean interrupted) {
        webcam.closeCameraDeviceAsync(() -> {
            myOpMode.telemetry.addData("Camera Closed ", "");
            myOpMode.telemetry.update();
        });

    }


    @Override
    public boolean isFinished() {
        return !noEnd &&(lpctr > 5 && ActiveMotionValues.getLcrpos() != 0);
    }


}
