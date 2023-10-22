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

package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


public class DriveToAprilTagTeleop extends CommandBase {


    // Adjust these numbers to suit your robot.
    final double DESIRED_DISTANCE = 12.0; //  this is how close the camera should get to the target (inches)

    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN = 0.02;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN = 0.015;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    final double TURN_GAIN = 0.01;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)

    int desiredTagID = 0;     // Choose the tag you want to approach or set to -1 for ANY tag.
    // Used for managing the AprilTag detection process.
    private AprilTagDetection desiredTag = null;     // Used to hold the data for a detected AprilTag

    private Drive_Subsystem drive;

    private Vision_Subsystem vss;

    private CommandOpMode myOpMode;

    private Gamepad gamepad;

    private boolean targetFound;


    double forward = 0;        // Desired forward power/speed (-1 to +1)
    double strafe = 0;        // Desired strafe power/speed (-1 to +1)
    double turn = 0;        // Desired turning power/speed (-1 to +1)

    int lpctr;

    public DriveToAprilTagTeleop(Drive_Subsystem drive, Vision_Subsystem vss, CommandOpMode opMode, Gamepad gamepad) {
        this.drive = drive;
        this.vss = vss;
        myOpMode = opMode;
        this.gamepad = gamepad;
        addRequirements(this.drive);

    }

    @Override
    public void initialize() {
        vss.enableAprilTagProcessor(true);
        // Initialize the Apriltag Detection process
        
        targetFound = false;    // Set to true when an AprilTag target is detected

           desiredTagID = ActiveMotionValues.getActTag();


        vss.setAprilTagDecimation(2);
    }

    @Override
    public void execute() {

        lpctr++;
        targetFound = false;
        desiredTag = null;

        // Step through the list of detected tags and look for a matching tag
        List<AprilTagDetection> currentDetections = vss.myAprilTagProcessor.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            // Look to see if we have size info on this tag.
            if (detection.metadata != null) {
                //  Check to see if we want to track towards this tag.
                if ((desiredTagID < 0) || (detection.id == desiredTagID)) {
                    // Yes, we want to use this tag.
                    targetFound = true;
                    desiredTag = detection;
                    break;  // don't look any further.
                }
            }
        }
        if (targetFound) {
            myOpMode.telemetry.addData("\n>", "HOLD Left-Bumper to Drive to Target\n");
            myOpMode.telemetry.addData("Found", "ID %d (%s)", desiredTag.id, desiredTag.metadata.name);
            myOpMode.telemetry.addData("Range", "%5.1f inches", desiredTag.ftcPose.range);
            myOpMode.telemetry.addData("Bearing", "%3.0f degrees", desiredTag.ftcPose.bearing);
            myOpMode.telemetry.addData("Yaw", "%3.0f degrees", desiredTag.ftcPose.yaw);
        } else {
            myOpMode.telemetry.addData("\n>", "Drive using joysticks to find valid target\n");
        }
        if (targetFound) {
            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
            double headingError = desiredTag.ftcPose.bearing;
            double yawError = desiredTag.ftcPose.yaw;

            // Use the speed and turn "gains" to calculate how we want the robot to move.
            forward = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
            strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

        } else {
            double y = -this.gamepad.left_stick_y / 2;
            double x = this.gamepad.left_stick_x / 2;
            double rx = this.gamepad.right_stick_x / 3;

            drive.drive.jog(y, x, rx);

        }


        myOpMode.telemetry.update();
    }


    @Override
    public void end(boolean interrupted) {
        vss.enableAprilTagProcessor(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


    /**
     * Move robot according to desired axes motions
     * <p>
     * Positive X is forward
     * <p>
     * Positive Y is strafe left
     * <p>
     * Positive Yaw is counter-clockwise
     */
    void moveRobot(double x, double y, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower = x - y - yaw;
        double rightFrontPower = x + y + yaw;
        double leftBackPower = x + y - yaw;
        double rightBackPower = x - y + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }
        drive.drive.setMotorPowers(leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
    }

    


}
