package org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard;

import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.MAX_ACCEL;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.MAX_ANG_VEL;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.MAX_VEL;

import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunBBCenterTraj extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;

    private TrajectorySequence backboardCenter;


    public RunBBCenterTraj(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;
    }

    @Override
    public void initialize() {



        /**
         * Use th 5 step center for stage door selection
         * <p>
         * It has the pixel delivery after the first step
         */
        backboardCenter = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width



                .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                .UNSTABLE_addTemporalMarkerOffset(.5,()-> phss.dropPixel())

                .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                .lineToLinearHeading(ActiveMotionValues.getPreTagPose())


                .build();




        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(backboardCenter);
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
    public boolean isFinished() {
        return Thread.currentThread().isInterrupted() || !drive.drive.isBusy();
    }

}
