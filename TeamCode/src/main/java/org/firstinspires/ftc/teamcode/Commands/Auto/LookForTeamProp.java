package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

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

    StageSwitchingPipeline sptop = null;

    ElapsedTime endTimer;


    CommandOpMode myOpMode;

    FtcDashboard dashboard;

    private boolean noEnd;
    private Vision_Subsystem vss;
    int currentLCR = 0;

    int lastlcr;
    private int lcrCheckCount;

    private boolean waitingForCamera;

    private int checklimit = 2;

    private float fps;

    public LookForTeamProp(CommandOpMode opMode, boolean noEnd, Vision_Subsystem vss) {
        myOpMode = opMode;
        this.sptop = sptop;
        this.noEnd = noEnd;
        this.vss = vss;
    }

    @Override
    public void initialize() {
        sptop = new StageSwitchingPipeline(ActiveMotionValues.getRedAlliance());

        endTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        waitingForCamera = true;

        currentLCR = 0;

        boolean red = ActiveMotionValues.getRedAlliance();

        ActiveMotionValues.setLcrpos(0);

        myOpMode.telemetry.clearAll();


    }


    @Override
    public void execute() {

        if (!vss.getCameraOpened()) endTimer.reset();

        fps = vss.getWebcam().getFps();

        if (fps > 1) {
            currentLCR = vss.sptop.getLCR();

            if (lastlcr != 0 && currentLCR == lastlcr) {
                lcrCheckCount++;
            }


            if (currentLCR != 0 && lastlcr == 0) {
                lastlcr = currentLCR;
            }


            if (currentLCR != lastlcr) {
                lcrCheckCount = 0;
                lastlcr = currentLCR;
            }
        }
        myOpMode.telemetry.addData("LCR ", currentLCR);
        myOpMode.telemetry.addData("LCRChkCnt ", lcrCheckCount);
        myOpMode.telemetry.addData("LookForProp Secs", endTimer.seconds());
        myOpMode.telemetry.addData("Streaming", fps);
        myOpMode.telemetry.addData("UsableContour", vss.sptop.getUsableContours());
//        myOpMode.telemetry.addData("LargestArea A", vss.sptop.getArea(1));
        myOpMode.telemetry.addData("LargestArea X", vss.sptop.getX(1));
        myOpMode.telemetry.addData("Red", vss.sptop.getRedPipeline());


        myOpMode.telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {

        myOpMode.telemetry.addData("LFTPEnding", "");
        myOpMode.telemetry.addData("TimeElapsed", endTimer.seconds());
        myOpMode.telemetry.update();
        if (currentLCR < 1 || currentLCR > 3) {
            currentLCR = 2;
        }
        ActiveMotionValues.setLcrpos(currentLCR);

    }

    @Override
    public boolean isFinished() {
        return !noEnd && (lcrCheckCount >= checklimit && endTimer.seconds() > 2 || endTimer.seconds() > 10);
    }


}
