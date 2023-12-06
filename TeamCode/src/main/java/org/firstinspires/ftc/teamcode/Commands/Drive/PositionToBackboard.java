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
   
    public static double MAX_AUTO_TURN = Constants.DriveConstants.MAX_AUTO_TURN;
    static double STRAFE_GAIN = Constants.DriveConstants.STRAFE_GAIN;
    static double TURN_GAIN = Constants.DriveConstants.TURN_GAIN;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)
    static double MAX_AUTO_SPEED = Constants.DriveConstants.MAX_AUTO_SPEED;  //  Clip the approach speed to this max value (adjust for your robot)
    static double MAX_AUTO_STRAFE = Constants.DriveConstants.MAX_AUTO_STRAFE;   //  Clip the approach speed to this max value (adjust for your robot)

    double forward = 0;        // Desired forward power/speed (-1 to +1)
    double strafe = 0;        // Desired strafe power/speed (-1 to +1)
    double turn = 0;

    double headingError;

    double yawError;

    public PositionToBackboard(Drive_Subsystem drive) {
        this.drive = drive;
    }

    @Override
    public void initialize() {
        drive.drive.resetEncoders();
        drive.drive.setPoseEstimate(new Pose2d());
        detection = ActiveMotionValues.getDetection();

        double sensorOffset = 2;//??????????????

        double sensorDistance = drive.getAveSensorInches();

        double angleDistance = sensorDistance - Constants.DriveConstants.AT_BACKBOARD_ANGLE_DISTANCE;

        double robotDistance = angleDistance * Math.sin(Math.toRadians(60)) - sensorOffset;

        drive.profController.setGoal(robotDistance);


        // Determine heading and Yaw (tag image rotation) error so we can use them to control the robot automatically.

        headingError = detection.ftcPose.bearing;

        yawError = detection.ftcPose.yaw;

    }

    @Override
    public void execute() {


        // Use the speed and turn "gains" to calculate how we want the robot to move.
        forward = drive.profController.calculate(drive.drive.getPoseEstimate().getX());

        turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);

        strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);


        moveRobot(forward, strafe, turn);

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
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
