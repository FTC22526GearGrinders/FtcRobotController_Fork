package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class MoveToPark extends CommandBase {
    private Drive_Subsystem drive;

    private Trajectory parkTraj;

    public MoveToPark(Drive_Subsystem drive) {
        this.drive = drive;
    }

    @Override
    public void initialize() {
        parkTraj = drive.drive.trajectoryBuilder(ActiveMotionValues.getFinalTagPose())
                .lineToLinearHeading(ActiveMotionValues.getParkPose())
                .build();

    }

    @Override
    public void execute() {


        drive.drive.setPoseEstimate(ActiveMotionValues.getFinalTagPose());

        drive.drive.followTrajectory(parkTraj);

    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.drive.stop();
        }
    }

    @Override
    public boolean isFinished() {

        return Thread.currentThread().isInterrupted() || !drive.drive.isBusy() || ActiveMotionValues.getParkPose() == new Pose2d();

    }
}
