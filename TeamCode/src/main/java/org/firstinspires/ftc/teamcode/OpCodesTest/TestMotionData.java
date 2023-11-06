package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Auto.SelectMotionValuesBlue;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectMotionValuesRed;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;

@Config
@TeleOp(name = "Auto: Values", group = "Test")

public class TestMotionData extends CommandOpMode {

    FtcDashboard dashboard;


    boolean buttonLocked = false;


    boolean redAlliance = false;

    boolean bbStart = true;

    boolean useStageDoor = false;

    boolean centerPark = false;

    boolean secondPixel = false;

    int lcr = 1;

    public void initialize() {

        CommandScheduler.getInstance().cancelAll();

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        boolean currentX = false;
        boolean currentY = false;
        boolean currentA = false;
        boolean currentB = false;
        boolean currentLB = false;
        boolean currentRB = false;
        boolean currentStart = false;


        while (opModeInInit() && !isStopRequested() && !currentStart) {

            currentX = gamepad1.x;
            currentY = gamepad1.y;
            currentA = gamepad1.a;
            currentB = gamepad1.b;
            currentLB = gamepad1.left_bumper;
            currentRB = gamepad1.right_bumper;
            currentStart = gamepad1.start;


            if (buttonLocked) {

                if (currentX) {
                    redAlliance = !redAlliance;
                }


                if (currentA) {
                    bbStart = !bbStart;
                }

                if (currentY) {
                    useStageDoor = !useStageDoor;
                }


                if (currentB) {
                    centerPark = !centerPark;
                }

                if (currentRB) {
                    secondPixel = !secondPixel;
                }

                if (currentLB) {
                    lcr++;
                    if (lcr > 3) lcr = 1;
                }


            }

            boolean xReleased = !currentX;
            boolean yReleased = !currentY;

            boolean aReleased = !currentA;
            boolean bReleased = !currentB;

            boolean lbReleased = !currentLB;
            boolean rbReleased = !currentRB;


            buttonLocked = xReleased && yReleased && aReleased && bReleased && lbReleased && rbReleased;


            telemetry.addData("Red Alliance Selected- X to Change", redAlliance);
            telemetry.addLine();
            telemetry.addData("Backboard Start Selected - A to Change", bbStart);
            telemetry.addLine();
            telemetry.addData("LCR Value - Left Bumper Increments", lcr);
            telemetry.addLine();
            telemetry.addData("Center Park Selected - B to Change", centerPark);
            telemetry.addLine();
            telemetry.addData("Stage Door Selected - Y to Change", useStageDoor);
            telemetry.addLine();

            telemetry.addData("Second Pixel Selected - RB to Change", secondPixel);
            telemetry.addLine();
            telemetry.addData("Press Play to Continue", "");


            telemetry.update();

        }

        ActiveMotionValues.setRedAlliance(redAlliance);
        ActiveMotionValues.setLcrpos(lcr);
        ActiveMotionValues.setBBStart(bbStart);

        ActiveMotionValues.setUseStageDoor(useStageDoor);
        ActiveMotionValues.setCenterPark(centerPark);
        ActiveMotionValues.setSecondPixel(secondPixel);

        waitForStart();

        if (redAlliance) new SelectMotionValuesRed().schedule();

        else new SelectMotionValuesBlue().schedule();


    }


    // Put run blocks here.
    public void run() {


        telemetry.addLine();
        telemetry.addData("RedAlliance", ActiveMotionValues.getRedAlliance());
        telemetry.addData("BB Start", ActiveMotionValues.getBBStart());
        telemetry.addData("CenterPark", ActiveMotionValues.getCenterPark());
        telemetry.addData("LCR", ActiveMotionValues.getLcrpos());


        telemetry.addData("Use SD", ActiveMotionValues.getUseStageDoor());
        telemetry.addData("SecondPixel", ActiveMotionValues.getSecondPixel());

        telemetry.addLine();


        telemetry.addData("StartPose", ActiveMotionValues.getStartPose().toString());
        telemetry.addData("AdvancePose", ActiveMotionValues.getAdvancePose().toString());
        telemetry.addLine();
        telemetry.addData("DropOffPose", ActiveMotionValues.getDropOffPose().toString());
        telemetry.addData("RetractPose", ActiveMotionValues.getRetractPose().toString());
        telemetry.addData("StrafeDistance", ActiveMotionValues.getStrafeDistance());
        telemetry.addLine();
        if (ActiveMotionValues.getBBStart())
            telemetry.addData("PreTagPose", ActiveMotionValues.getPreTagPose().toString());

        telemetry.addLine();

        if (!ActiveMotionValues.getBBStart()) {
            telemetry.addData("TrussSDPose", ActiveMotionValues.getTrussSDLineUpPose().toString());

            telemetry.addData("OptStopPose", ActiveMotionValues.getOptionStopPose().toString());
            telemetry.addLine();
            if (ActiveMotionValues.getSecondPixel())
                telemetry.addData("OptTagPose",
                        FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                                .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));

            telemetry.addData("OptTgtPose", ActiveMotionValues.getOptionTargetPose().toString());

            telemetry.addData("Atag", ActiveMotionValues.getActTag());
            telemetry.addLine();


        }


        telemetry.update();

        CommandScheduler.getInstance().run();
    }
}