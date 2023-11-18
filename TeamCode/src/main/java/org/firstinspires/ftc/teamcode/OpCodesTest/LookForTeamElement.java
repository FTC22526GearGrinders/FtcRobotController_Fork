package org.firstinspires.ftc.teamcode.OpCodesTest;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Auto.LookForTeamProp;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
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

    private StageSwitchingPipeline sptop;

    public static int redThreshold = 170;
    public static int blueThreshold = 150;

    public static int left = 146;

    public static int right = 231;

    int lastleft;

    int lastRight;
    int lastBlue;

    int lastRed;

    private Vision_Subsystem vss;


    public void initialize() {


        dashboard = FtcDashboard.getInstance();

        boolean redAlliance = ActiveMotionValues.getRedAlliance();


        sptop = new StageSwitchingPipeline(redAlliance);

        vss = new Vision_Subsystem(this);


        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        FtcDashboard.getInstance().startCameraStream(vss.getWebcam(), 5);

    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        if (!vss.getCameraOpened()) vss.openCamera(false);


//        //if you are using dashboard, update dashboard camera view
//        if (vss.getCameraOpened())
//
//            FtcDashboard.getInstance().startCameraStream(vss.getWebcam(), 5);

        CommandScheduler.getInstance().schedule(new LookForTeamProp(this,false,vss));


        while (!isStopRequested() && opModeIsActive()) {

            run();

            telemetry.update();

        }
        reset();

    }



    // Put run blocks here.
    public void run() {

        if (lastBlue != blueThreshold) {
            sptop.setBlueThreshold(blueThreshold);
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

        telemetry.addData("Streaming", vss.getWebcam().getFps());
        telemetry.addData("RRAIsempty", sptop.rrAreas.isEmpty());

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
        if (!sptop.changing)
            telemetry.addData("area0", sptop.getArea(0));
        if (!sptop.changing)
            telemetry.addData("X0", sptop.getX(0));
        if (!sptop.changing)
            telemetry.addData("area1", sptop.getArea(1));
        if (!sptop.changing)
            telemetry.addData("X1", sptop.getX(1));
        if (!sptop.changing)
            telemetry.addData("area2", sptop.getArea(2));
        if (!sptop.changing)
            telemetry.addData("X2", sptop.getX(2));


        if (sptop.getUsableContours() > 1) {

            telemetry.addData("Cont Found", sptop.getNumberContours());
            telemetry.addData("Usable", sptop.getUsableContours());

        }
        telemetry.update();

        CommandScheduler.getInstance().run();

    }


}

