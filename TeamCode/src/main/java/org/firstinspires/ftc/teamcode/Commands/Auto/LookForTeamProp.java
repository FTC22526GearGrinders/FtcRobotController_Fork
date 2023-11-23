package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

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

    StageSwitchingPipeline sptop = null;

    ElapsedTime endTimer;


    CommandOpMode myOpMode;

    FtcDashboard dashboard;

    Timer timer = new Timer();

    private boolean noEnd;

    private boolean oneShot = false;

    private Vision_Subsystem vss;
    int currentLCR = 0;

    int lastlcr;
    private int lcrCheckCount;

    private boolean waitingForCamera;

    private int checklimit = 3;

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

    }


    @Override
    public void execute() {

        if (vss.getCameraOpened()) {

            if (vss.getWebcam().getFps() > 1) {
                waitingForCamera = false;
            }

            currentLCR = vss.sptop.getLCR();

            if (currentLCR != 0 && currentLCR == lastlcr) {
                lcrCheckCount++;
            }

            if (currentLCR != lastlcr) {
                lcrCheckCount = 0;
                currentLCR = lastlcr;
            }

            if (lcrCheckCount >= checklimit)
                ActiveMotionValues.setLcrpos(currentLCR);


            myOpMode.telemetry.addData("LookForProp Secs", endTimer.seconds());
            myOpMode.telemetry.addData("Streaming", vss.getWebcam().getFps());
            myOpMode.telemetry.addData("RRAIsempty", vss.sptop.rrAreas.isEmpty());
            myOpMode.telemetry.addData("LCR current", currentLCR);
            myOpMode.telemetry.addData("Red", vss.sptop.getRedPipeline());

        }
        myOpMode.telemetry.addData("WaitingForCamera", "");

        myOpMode.telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {

        myOpMode.telemetry.addData("LFTPEnding", "");
        myOpMode.telemetry.addData("TimeElapsed", endTimer.seconds());
        myOpMode.telemetry.update();
        if (currentLCR < 1 || currentLCR > 2) {
            currentLCR = 2;
            ActiveMotionValues.setLcrpos(currentLCR);
        }
    }

    @Override
    public boolean isFinished() {
        return !noEnd && (endTimer.seconds() > 2 && lcrCheckCount >= checklimit || endTimer.seconds() > 5);
    }


}
