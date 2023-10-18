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


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.CV.SpikeTapePipelineBlue;
import org.firstinspires.ftc.teamcode.CV.SpikeTapePipelineRed;
import org.firstinspires.ftc.teamcode.Commands.Auto.DriveToAprilTagAuto;
import org.firstinspires.ftc.teamcode.Commands.Auto.LookForTeamProp;
import org.firstinspires.ftc.teamcode.Commands.Auto.PickAndRunTrajectories;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;


@Config
@Autonomous(name = "Auto: Select-Run Auto", group = "Auto")
//@Disabled
public class AutoSelectAndRun extends CommandOpMode {

    private Drive_Subsystem drive;

    private OpenCvWebcam webcam;//

    private Claw_Subsystem clawSubsystem;


    boolean buttonLocked = false;


    boolean redAlliance = false;

    boolean leftStart = false;

    boolean centerPark = false;

    SpikeTapePipelineRed sptopR = null;

    SpikeTapePipelineBlue sptopB = null;

    @Override
    public void initialize() {
        webcam = OpenCvCameraFactory.getInstance().createWebcam(this.hardwareMap.get(WebcamName.class, "Webcam 1"));


        boolean currentX = false;
        boolean currentY = false;
        boolean currentA = false;
        boolean currentB = false;
        boolean currentLB = false;
        boolean currentRB = false;


        while (!currentLB && opModeInInit() && !isStopRequested()) {

            currentX = gamepad1.x;
            currentY = gamepad1.y;
            currentA = gamepad1.a;
            currentB = gamepad1.b;
            currentLB = gamepad1.left_bumper;
            currentRB = gamepad1.right_bumper;


            if (buttonLocked) {

                if (currentX) {
                    redAlliance = !redAlliance;
                }
                if (currentY) {
                    leftStart = !leftStart;
                }

                if (currentA) {
                    centerPark = !centerPark;
                }

            }

            boolean xReleased = !currentX;
            boolean yReleased = !currentY;

            boolean aReleased = !currentA;
            boolean bReleased = !currentB;

            boolean lbReleased = !currentLB;
            boolean rbReleased = !currentRB;


            buttonLocked = xReleased && yReleased && aReleased && bReleased && lbReleased && rbReleased;

            telemetry.addData("Red Alliance Selected X to Change", redAlliance);
            telemetry.addData("Left Start Selected Y to Change", leftStart);
            telemetry.addData("Left Park Selected A to Change", centerPark);
            telemetry.addData("Press Left Bumper To Continue", "");


            telemetry.update();

        }

        if (redAlliance)

            telemetry.addData("You Have Chosen Red Alliance", "");
        else
            telemetry.addData("You Have Chosen Blue Alliance", "");

        if (leftStart)

            telemetry.addData("You Have Chosen Left Start", "");
        else
            telemetry.addData("You Have Chosen Right Start", "");

        if (centerPark)

            telemetry.addData("You Have Chosen Center Park", "");
        else
            telemetry.addData("You Have Chosen Near Park", "");

        telemetry.addData("Reselect Opmode to Change", "");

        telemetry.addData(" Press Play When Told to Start Match", "");

        telemetry.update();


        ActiveMotionValues.setRedAlliance(redAlliance);
        boolean bbStart = redAlliance && !leftStart || !redAlliance && leftStart;
        ActiveMotionValues.setBBStart(bbStart);
        ActiveMotionValues.setCenterPark(centerPark);



        drive = new Drive_Subsystem(this);

        clawSubsystem = new Claw_Subsystem(this);

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

                //    webcam.setPipeline(stpb);
                // webcam.setPipeline(stpb);
                //start streaming the camera
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);

                sptopB = new SpikeTapePipelineBlue();

                sptopR = new SpikeTapePipelineRed();

                if (ActiveMotionValues.getRedAlliance())
                    webcam.setPipeline(sptopR);

                else
                    webcam.setPipeline(sptopB);


                //if you are using dashboard, update dashboard camera view
                //     FtcDashboard.getInstance().startCameraStream(webcam, 5);


            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
        waitForStart();

        new SequentialCommandGroup(

                new LookForTeamProp(this, webcam).withTimeout(5000),

                new SelectValues(),

                new PickAndRunTrajectories(drive, clawSubsystem).withTimeout(5000),

                new DriveToAprilTagAuto(this,drive).withTimeout(5000)


        ).schedule();

    }

    public void run() {


        CommandScheduler.getInstance().run();


    }
}






