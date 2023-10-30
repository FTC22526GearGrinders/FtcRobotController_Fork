package org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunTrussSDCenterTape extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;

    private TrajectorySequence trussSDTraj;


    public RunTrussSDCenterTape(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
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
            trussSDTraj = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                  .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .waitSeconds(.5)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                    .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                    .build();



        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(trussSDTraj);
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
