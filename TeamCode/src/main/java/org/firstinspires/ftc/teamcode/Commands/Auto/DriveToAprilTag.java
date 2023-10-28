package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class DriveToAprilTag extends CommandBase {
    private Drive_Subsystem drive;


    private Trajectory tagTraj;

    private Pose2d robotPose;

    Pose2d tagDistancePose = new Pose2d();

    Pose2d tagPose = new Pose2d();

    Pose2d finalPose = new Pose2d();


    public DriveToAprilTag(Drive_Subsystem drive) {
        this.drive = drive;

    }

    @Override
    public void initialize() {

        robotPose = ActiveMotionValues.getRobotPose();


        tagDistancePose = new Pose2d(-6 - Constants.RobotConstants.length / 2, 0);

        tagPose = FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag());

        finalPose = tagPose.plus(tagDistancePose);


        tagTraj = drive.drive.trajectoryBuilder(robotPose)
                .lineToLinearHeading(finalPose)
                .build();


        drive.drive.setPoseEstimate(robotPose);

        drive.drive.followTrajectory(tagTraj);
    }

    @Override
    public void execute() {

         drive.drive.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.drive.stop();
        }
    }

    @Override
    public boolean isFinished() {//return false;
        return Thread.currentThread().isInterrupted() || !drive.drive.isBusy();
    }

}
