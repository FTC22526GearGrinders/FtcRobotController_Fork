package org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class RunToAprilTag extends CommandBase {
    private Drive_Subsystem drive;


    private Trajectory tagTraj;

    private Pose2d robotPose;

    private CommandOpMode myOpMode;

    Pose2d tagDistancePose = new Pose2d();

    Pose2d tagPose = new Pose2d();

    Pose2d finalPose = new Pose2d();


    public RunToAprilTag(Drive_Subsystem drive, CommandOpMode opMode) {
        this.drive = drive;
        myOpMode = opMode;
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

        myOpMode.telemetry.addData("RobotPose", robotPose.toString());
        myOpMode.telemetry.addLine();
        myOpMode.telemetry.addData("DistPose", tagDistancePose.toString());
        myOpMode.telemetry.addLine();
        myOpMode.telemetry.addData("TagPose", tagPose.toString());
        myOpMode.telemetry.addLine();
        myOpMode.telemetry.addData("FinalPose", finalPose.toString());
        myOpMode.telemetry.addLine();
        myOpMode.telemetry.addData("PresetPose", drive.drive.getPoseEstimate().toString());
        myOpMode.telemetry.update();



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
