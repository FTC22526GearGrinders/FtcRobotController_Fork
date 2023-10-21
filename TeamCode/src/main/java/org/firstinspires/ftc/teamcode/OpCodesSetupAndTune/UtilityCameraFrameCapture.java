/*
 * Copyright (c) 2023 FIRST
 *
 * All rights reserved.
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
 * Neither the name of FIRST nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior
 * written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.OpCodesSetupAndTune;

import android.graphics.Bitmap;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.CameraControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.FocusControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.PtzControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.WhiteBalanceControl;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibrationIdentity;
import org.firstinspires.ftc.vision.VisionPortal;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.PipelineRecordingParameters;

import java.util.Locale;

/*
 * This OpMode helps calibrate a webcam or RC phone camera, useful for AprilTag pose estimation
 * with the FTC VisionPortal.   It captures a camera frame (image) and stores it on the Robot Controller
 * (Control Hub or RC phone), with each press of the gamepad button X (or Square).
 * Full calibration instructions are here:
 *
 *  https://ftc-docs.firstinspires.org/camera-calibration
 *
 * In Android Studio, copy this class into your "teamcode" folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 *
 * In OnBot Java, use "Add File" to add this OpMode from the list of Samples.
 */

@TeleOp(name = "Utility: Camera Frame Capture", group = "Utility")
@Disabled
public class UtilityCameraFrameCapture extends LinearOpMode
{
    /*
     * EDIT THESE PARAMETERS AS NEEDED
     */
    final boolean USING_WEBCAM = true;
    final BuiltinCameraDirection INTERNAL_CAM_DIR = BuiltinCameraDirection.BACK;
    final int RESOLUTION_WIDTH = 640;
    final int RESOLUTION_HEIGHT = 480;

    // Internal state
    boolean lastX;
    int frameCount;
    long capReqTime;

    @Override
    public void runOpMode()
    {
        VisionPortal portal;
        OpenCvWebcam cam = new OpenCvWebcam() {
            @Override
            public void setMillisecondsPermissionTimeout(int ms) {

            }

            @Override
            public void startStreaming(int width, int height, OpenCvCameraRotation rotation, StreamFormat streamFormat) {

            }

            @Override
            public ExposureControl getExposureControl() {
                return null;
            }

            @Override
            public FocusControl getFocusControl() {
                return null;
            }

            @Override
            public PtzControl getPtzControl() {
                return null;
            }

            @Override
            public GainControl getGainControl() {
                return null;
            }

            @Override
            public WhiteBalanceControl getWhiteBalanceControl() {
                return null;
            }

            @Override
            public <T extends CameraControl> T getControl(Class<T> controlType) {
                return null;
            }

            @Override
            public CameraCalibrationIdentity getCalibrationIdentity() {
                return null;
            }

            @Override
            public int openCameraDevice() {
                return 0;
            }

            @Override
            public void openCameraDeviceAsync(AsyncCameraOpenListener cameraOpenListener) {

            }

            @Override
            public void closeCameraDevice() {

            }

            @Override
            public void closeCameraDeviceAsync(AsyncCameraCloseListener cameraCloseListener) {

            }

            @Override
            public void showFpsMeterOnViewport(boolean show) {

            }

            @Override
            public void pauseViewport() {

            }

            @Override
            public void resumeViewport() {

            }

            @Override
            public void setViewportRenderingPolicy(ViewportRenderingPolicy policy) {

            }

            @Override
            public void setViewportRenderer(ViewportRenderer renderer) {

            }

            @Override
            public void startStreaming(int width, int height) {

            }

            @Override
            public void startStreaming(int width, int height, OpenCvCameraRotation rotation) {

            }

            @Override
            public void stopStreaming() {

            }

            @Override
            public void setPipeline(OpenCvPipeline pipeline) {

            }

            @Override
            public int getFrameCount() {
                return 0;
            }

            @Override
            public float getFps() {
                return 0;
            }

            @Override
            public int getPipelineTimeMs() {
                return 0;
            }

            @Override
            public int getOverheadTimeMs() {
                return 0;
            }

            @Override
            public int getTotalFrameTimeMs() {
                return 0;
            }

            @Override
            public int getCurrentPipelineMaxFps() {
                return 0;
            }

            @Override
            public void startRecordingPipeline(PipelineRecordingParameters parameters) {

            }

            @Override
            public void stopRecordingPipeline() {

            }

            @Override
            public void getFrameBitmap(Continuation<? extends Consumer<Bitmap>> continuation) {

            }
        };

        if (USING_WEBCAM)
        {
            portal = new VisionPortal.Builder()
                    .setCamera(hardwareMap.get(WebcamName.class, "Webcam 2"))
                    .setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT))
                    .setCamera((CameraName) cam)
                    .build();
        }
        else
        {
            portal = new VisionPortal.Builder()
                    .setCamera(INTERNAL_CAM_DIR)
                    .setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT))
                    .build();
        }

        while (!isStopRequested())
        {
            boolean x = gamepad1.x;



            if (x && !lastX)
            {
                portal.saveNextFrameRaw(String.format(Locale.US, "CameraFrameCapture-%06d", frameCount++));
                capReqTime = System.currentTimeMillis();
            }

            lastX = x;

            telemetry.addLine("######## Camera Capture Utility ########");
            telemetry.addLine(String.format(Locale.US, " > Resolution: %dx%d", RESOLUTION_WIDTH, RESOLUTION_HEIGHT));
            telemetry.addLine(" > Press X (or Square) to capture a frame");
            telemetry.addData(" > Camera Status", portal.getCameraState());

            if (capReqTime != 0)
            {
                telemetry.addLine("\nCaptured Frame!");
            }

            if (capReqTime != 0 && System.currentTimeMillis() - capReqTime > 1000)
            {
                capReqTime = 0;
            }

            telemetry.update();
        }
    }
}
