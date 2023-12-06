package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


public class PositionToBackboard extends CommandBase {
    private final Drive_Subsystem drive;
    private AprilTagDetection detection;

    public static double max_auto_turn = Constants.DriveConstants.MAX_AUTO_TURN;

    double max_auto_speed = Constants.DriveConstants.MAX_AUTO_SPEED;  //  Clip the approach speed to this max value (adjust for your robot)
    double max_auto_strafe = Constants.DriveConstants.MAX_AUTO_STRAFE;   //  Clip the approach speed to this max value (adjust for your robot)

    double forward = 0;        // Desired forward power/speed (-1 to +1)
    double strafe = 0.5;        // Desired strafe power/speed (-1 to +1)
    double turn = 0.5;

    double headingError;

    double yawError;


    public PositionToBackboard(Drive_Subsystem drive) {
        this.drive = drive;
    }

    @Override
    public void initialize() {
        drive.drive.resetEncoders();
        drive.drive.setPoseEstimate(new Pose2d());
    }

    @Override
    public void execute() {

        if (ActiveMotionValues.getAprilTagSeen())

            detection = ActiveMotionValues.getDetection();

        headingError = detection.ftcPose.bearing;

        yawError = detection.ftcPose.yaw;

        drive.profController.setGoal(detection.ftcPose.range - drive.stopDistanceFromTag);

        forward = drive.profController.calculate(drive.drive.getPoseEstimate().getX());

        turn = Range.clip(headingError * drive.turn_gain, -max_auto_turn, max_auto_turn);

        strafe = Range.clip(-yawError * drive.strafe_gain, -max_auto_strafe, max_auto_strafe);

        moveRobot(forward, strafe, turn);

        //  drive.drive.jog(forward,strafe,turn);

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return drive.profController.atGoal();
    }

    public void moveRobot(double x, double y, double yaw) {
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

        // Send powers to the wheels.
        drive.drive.setMotorPowers(leftFrontPower, leftBackPower, rightBackPower, rightFrontPower);

    }
}
