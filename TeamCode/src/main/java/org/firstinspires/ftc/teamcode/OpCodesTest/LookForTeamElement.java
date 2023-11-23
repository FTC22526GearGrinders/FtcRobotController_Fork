package org.firstinspires.ftc.teamcode.OpCodesTest;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Auto.LookForTeamProp;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

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

    public static int redThreshold = 170;
    public static int blueThreshold = 150;

    public static int left = 146;

    public static int right = 231;
    public static int changeLinesInt = 0;

    public static int blueRed_0_1 = 0;
    boolean changeLines;
    boolean lastChangeLines;

    int lastleft;

    int lastRight;
    int lastBlue;

    int lastRed;

    private Vision_Subsystem vss;


    public void initialize() {


        dashboard = FtcDashboard.getInstance();


        vss = new Vision_Subsystem(this);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        if (!vss.getCameraOpened()) vss.openCamera(false);

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        FtcDashboard.getInstance().startCameraStream(vss.getWebcam(), 5);

        vss.setUseDashboard(true);

        CommandScheduler.getInstance().schedule(new LookForTeamProp(this, true, vss));


        while (!isStopRequested() && opModeIsActive()) {

            run();

            telemetry.update();

        }
        reset();

    }


    // Put run blocks here.
    public void run() {

        if (vss.getCameraOpened() && isStopRequested()) vss.closeCamera();

        changeLines = changeLinesInt != 0;

        if (blueRed_0_1 == 1)
            vss.sptop.red = true;
        else vss.sptop.red = false;

        if (changeLines != lastChangeLines) {
            vss.sptop.allowLineChange = changeLines;
            lastChangeLines = changeLines;
        }

        if (lastBlue != blueThreshold) {
            vss.sptop.setBlueThreshold(blueThreshold);
            lastBlue = blueThreshold;
        }

        if (lastRed != redThreshold) {
            vss.sptop.setRedThreshold(redThreshold);
            lastRed = redThreshold;
        }

        if (lastleft != left) {
            vss.sptop.left = left;
            lastleft = left;
        }
        if (lastRight != right) {
            vss.sptop.right = right;
            lastRight = right;
        }

        telemetry.addData("Streaming", vss.getWebcam().getFps());
        telemetry.addData("LCR", vss.sptop.lcr);
        telemetry.addData("NumCon", vss.sptop.numContoursFound);
        telemetry.addData("ValCon", vss.sptop.usableContours);
        telemetry.addData("Red", vss.sptop.getRedPipeline());
        if (!vss.sptop.rr.isEmpty())
            telemetry.addData("RRSize", vss.sptop.rr.size());
        if (!vss.sptop.rrxval.isEmpty())
            telemetry.addData("XVALSize", vss.sptop.rrxval.size());
        if (!vss.sptop.rrAreas.isEmpty())
            telemetry.addData("AreasSize", vss.sptop.rrAreas.size());
        if (!vss.sptop.changing)
            telemetry.addData("area0", vss.sptop.getArea(0));
        if (!vss.sptop.changing)
            telemetry.addData("X0", vss.sptop.getX(0));
        if (!vss.sptop.changing)
            telemetry.addData("area1", vss.sptop.getArea(1));
        if (!vss.sptop.changing)
            telemetry.addData("X1", vss.sptop.getX(1));
        if (!vss.sptop.changing)
            telemetry.addData("area2", vss.sptop.getArea(2));
        if (!vss.sptop.changing)
            telemetry.addData("X2", vss.sptop.getX(2));


        if (vss.sptop.getUsableContours() > 1) {

            telemetry.addData("Cont Found", vss.sptop.getNumberContours());
            telemetry.addData("Usable", vss.sptop.getUsableContours());

        }
        telemetry.update();

        CommandScheduler.getInstance().run();

    }


}

