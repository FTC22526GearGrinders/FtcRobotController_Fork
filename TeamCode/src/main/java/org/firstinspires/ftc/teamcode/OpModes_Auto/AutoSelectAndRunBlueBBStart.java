package org.firstinspires.ftc.teamcode.OpModes_Auto;


/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Auto.AutoFactory;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;


@Autonomous(name = "Auto: Select-BLUE BBStart", group = "Auto")
//@Disabled
public class AutoSelectAndRunBlueBBStart extends CommandOpMode {

    private Drive_Subsystem drive;

    private PixelHandlerSubsystem phss;

    private ArmSubsystem arm;

    private Vision_Subsystem vss;

    FtcDashboard dashboard;


    boolean buttonLocked = false;

    boolean centerPark = false;

    boolean nearPark = false;
    private AutoFactory af;

    @Override
    public void initialize() {


        boolean currentX = false;
        boolean currentY = false;
        boolean currentA = false;
        boolean currentB = false;
        boolean currentLB = false;
        boolean currentRB = false;
        boolean currentStart = false;

        while (!currentLB && opModeInInit() && !isStopRequested()) {

            currentX = gamepad1.x;
            currentY = gamepad1.y;
            currentA = gamepad1.a;
            currentB = gamepad1.b;
            currentLB = gamepad1.left_bumper;
            currentRB = gamepad1.right_bumper;
            currentStart = gamepad1.start;

            if (buttonLocked) {

                if (currentB) {
                    centerPark = !centerPark;
                }

                if (currentA) {
                    nearPark = !nearPark;
                }

                if (currentB && centerPark) nearPark = false;

                if (currentA && nearPark) centerPark = false;

            }

            boolean xReleased = !currentX;
            boolean yReleased = !currentY;

            boolean aReleased = !currentA;
            boolean bReleased = !currentB;

            boolean lbReleased = !currentLB;
            boolean rbReleased = !currentRB;
            boolean startReleased = !currentStart;

            buttonLocked = xReleased && yReleased && aReleased && bReleased && lbReleased && rbReleased && startReleased;

            telemetry.addData("You Have Chosen BLUE Alliance BB Start", "");
            telemetry.addLine();
            telemetry.addData("Choose Neither -  or Only One Park", "");
            telemetry.addData("Center Park Selected B to Change", centerPark);
            telemetry.addLine();
            telemetry.addData("Near Park Selected - A to Change", nearPark);
            telemetry.addLine();

            telemetry.addData("Press Left Bumper To Continue", "");
            telemetry.addLine();
            telemetry.addData("DO NOT PRESS PLAY AT THIS TIME", "");

            telemetry.update();

        }

        telemetry.addData("You Have Chosen BLUE Alliance BB Start", "");
        telemetry.addLine();
        if (centerPark)
            telemetry.addData("You Have Chosen Center Park", "");

        if (nearPark)
            telemetry.addData("You Have Chosen Near Park", "");

        if (!centerPark && !nearPark)
            telemetry.addData("You Have Chosen Park In Place", "");
        telemetry.addLine();
        telemetry.addData("Reselect Opmode to Change", "");
        telemetry.addLine();
        telemetry.addData(" Press Play When Told to Start Match", "");

        telemetry.update();


        ActiveMotionValues.setRedAlliance(false);
        ActiveMotionValues.setBBStart(true);
        ActiveMotionValues.setUseStageDoor(false);
        ActiveMotionValues.setSecondPixel(false);

        ActiveMotionValues.setCenterPark(centerPark);
        ActiveMotionValues.setNearPark(nearPark);


        drive = new Drive_Subsystem(this);

        phss = new PixelHandlerSubsystem(this);

        arm = new ArmSubsystem(this);

        vss = new Vision_Subsystem(this);

        af = new AutoFactory(this, drive, phss, arm, vss);

        dashboard = FtcDashboard.getInstance();

    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        if (!vss.getCameraOpened()) vss.openCamera(false);
        waitForStart();



        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        FtcDashboard.getInstance().startCameraStream(vss.getWebcam(), 5);

        CommandScheduler.getInstance().schedule(af.getAAS());

        while (!isStopRequested() && opModeIsActive()) {

            run();

            telemetry.update();

        }
        reset();

    }

}
