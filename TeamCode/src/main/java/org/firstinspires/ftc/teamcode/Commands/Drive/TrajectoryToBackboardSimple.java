package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


public class TrajectoryToBackboardSimple extends CommandBase {
    private Drive_Subsystem drive;


    private Trajectory tagTraj;

    private Pose2d currentRobotPose;

    private CommandOpMode myOpMode;

    private ElapsedTime et;

    ;

    public TrajectoryToBackboardSimple(Drive_Subsystem drive, CommandOpMode opMode) {
        this.drive = drive;
        myOpMode = opMode;
        addRequirements(drive);
    }

    @Override
    public void initialize() {

        myOpMode.telemetry.clearAll();
        myOpMode.telemetry.addData("RunToTagInit", "");
        myOpMode.telemetry.update();
        et = new ElapsedTime();


        Pose2d currentRobotPose = new Pose2d();

        Pose2d finalPose = new Pose2d(5, 0);

        ActiveMotionValues.setFinalTagPose(tagTraj.end());


        tagTraj = drive.drive.trajectoryBuilder(currentRobotPose)
                .lineToLinearHeading(finalPose)
                .build();

        drive.drive.setPoseEstimate(currentRobotPose);

        drive.drive.followTrajectory(tagTraj);
    }

    @Override
    public void execute() {

//        myOpMode.telemetry.addData("RobotPose", currentRobotPose.toString());
//        myOpMode.telemetry.addLine();
//        myOpMode.telemetry.addData("DistPose", tagDistancePose.toString());
//        myOpMode.telemetry.addLine();
//        myOpMode.telemetry.addData("TagPose", tagPose.toString());
//        myOpMode.telemetry.addLine();
//        myOpMode.telemetry.addData("FinalPose", finalPose.toString());
//        myOpMode.telemetry.addLine();
//        myOpMode.telemetry.addData("PresetPose", drive.drive.getPoseEstimate().toString());
//        myOpMode.telemetry.update();


        drive.drive.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.drive.stop();
        }
        myOpMode.telemetry.clearAll();
        myOpMode.telemetry.addData("RunToTagEnd", "");
        myOpMode.telemetry.update();

    }

    @Override
    public boolean isFinished() {//return false;
        return et.seconds() > 5 && Thread.currentThread().isInterrupted() || !drive.drive.isBusy();
    }

}
