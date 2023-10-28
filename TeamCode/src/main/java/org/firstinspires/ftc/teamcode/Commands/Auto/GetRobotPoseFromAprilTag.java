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

package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


public class GetRobotPoseFromAprilTag extends CommandBase {

    private Drive_Subsystem drive;

    private Vision_Subsystem vss;
    private boolean targetFound;

    private int desiredTagID = 1;
    private AprilTagDetection desiredTag;

    public GetRobotPoseFromAprilTag(Drive_Subsystem drive, Vision_Subsystem vss) {
        this.drive = drive;

        this.vss = vss;

        addRequirements(this.drive);
    }

    @Override
    public void initialize() {
        vss.enableAprilTagProcessor(true);
        // Initialize the Apriltag Detection process
        targetFound = false;    // Set to true when an AprilTag target is detected
        desiredTagID = ActiveMotionValues.getActTag();
    }

    @Override
    public void execute() {
        targetFound = false;
        desiredTagID = 0;

        // Step through the list of detected tags and look for a matching tag
        List<AprilTagDetection> currentDetections = vss.myAprilTagProcessor.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            // Look to see if we have size info on this tag.

            if (detection.metadata != null) {
                //  Check to see if we want to track towards this tag.
                if (detection.id == desiredTagID) {
                    // Yes, we want to use this tag.
                    targetFound = true;
                    desiredTag = detection;
                    break;  // don't look any further.

                }
            }
        }

        if (targetFound) {

            Pose2d camPose = new Pose2d(desiredTag.ftcPose.y, desiredTag.ftcPose.x);

            Pose2d tagPose = FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag());

            Pose2d fieldCamPose = tagPose.minus(camPose);

            Pose2d robotPose = fieldCamPose.minus(Constants.RobotConstants.kCameraToRobot);

            ActiveMotionValues.setRobotPose(robotPose);

        }


    }

    @Override
    public void end(boolean interrupted) {
        vss.enableAprilTagProcessor(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
