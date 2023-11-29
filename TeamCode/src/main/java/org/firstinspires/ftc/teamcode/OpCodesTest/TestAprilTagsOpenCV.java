/* Copyright (c) 2023 FIRST. All rights reserved.
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

package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.OpenCVAprilTag;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

/*
 * This OpMode illustrates the basics of AprilTag recognition and pose estimation, using
 * the easy way.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 */
@Config
@TeleOp(name = "Auto: Test AprilTag OPENCV ", group = "Test")
//@Disabled
public class TestAprilTagsOpenCV extends CommandOpMode {

    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    /**
     * The variable to store our instance of the AprilTag processor.
     */

    public OpenCvCamera webcam;
    private FtcDashboard dashboard;


    public static int reqdTagID =3;

    int cameraMonitorViewId;

    @Override
    public void initialize() {
        cameraMonitorViewId = this.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(this.hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        telemetry.addData("CMVID", cameraMonitorViewId);

        dashboard = FtcDashboard.getInstance();

        // Wait for the DS start button to be touched.

        telemetry.update();

        waitForStart();


    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        FtcDashboard.getInstance().startCameraStream(webcam, 5);

        CommandScheduler.getInstance().schedule(new OpenCVAprilTag(this, webcam, 30));

        while (!isStopRequested() && opModeIsActive()) {
            run();

            if (ActiveMotionValues.getActTag() != reqdTagID)
                ActiveMotionValues.setActTag(reqdTagID);
            telemetry.update();
        }
        reset();

    }
}
