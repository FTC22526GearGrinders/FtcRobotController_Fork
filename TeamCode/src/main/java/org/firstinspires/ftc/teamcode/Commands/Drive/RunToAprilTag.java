package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class RunToAprilTag extends CommandBase {
    private Drive_Subsystem drive;


    private Trajectory tagTraj;

    private Pose2d currentRobotPose;

    private CommandOpMode myOpMode;

    Pose2d tagDistancePose = new Pose2d();

    Pose2d tagPose = new Pose2d();

    Pose2d finalPose = new Pose2d();

    public RunToAprilTag(Drive_Subsystem drive, CommandOpMode opMode) {
        this.drive = drive;
        myOpMode = opMode;
        addRequirements(drive);
    }

    @Override
    public void initialize() {



        currentRobotPose = ActiveMotionValues.getCurrentRobotPose();

        finalPose = ActiveMotionValues.getFinalTagPose();

        tagTraj = drive.drive.trajectoryBuilder(currentRobotPose)
                .lineToLinearHeading(finalPose)
                .build();


        drive.drive.setPoseEstimate(currentRobotPose);

        drive.drive.followTrajectory(tagTraj);
    }

    @Override
    public void execute() {

        myOpMode.telemetry.addData("RobotPose", currentRobotPose.toString());
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
