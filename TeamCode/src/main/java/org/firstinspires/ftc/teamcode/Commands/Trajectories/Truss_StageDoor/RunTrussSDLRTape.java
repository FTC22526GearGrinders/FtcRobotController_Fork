package org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunTrussSDLRTape extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;

    private TrajectorySequence trussSDTraj;


    public RunTrussSDLRTape(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;

    }

    @Override
    public void initialize() {

        boolean stageDoor = ActiveMotionValues.getUseStageDoor();

        boolean trussSideTapeRed = ActiveMotionValues.getRedAlliance() &&

                (ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 1

                        || !ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 13);

        boolean trussSideTapeBlue = !ActiveMotionValues.getRedAlliance() &&

                (ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 3

                        || !ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 11);

        boolean trussSideTape = trussSideTapeRed || trussSideTapeBlue;

        if (!stageDoor) {

            if (!trussSideTape) {

                trussSDTraj = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5,()-> phss.dropPixel())

                        .waitSeconds(.5)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())


                        .build();

            }

            if (trussSideTape) {

                trussSDTraj = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5,()-> phss.dropPixel())

                        .waitSeconds(.5)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                        .build();

            }
        }

        if (stageDoor) {

            if (!trussSideTape) {

                trussSDTraj = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5,()-> phss.dropPixel())

                        .waitSeconds(.5)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .strafeLeft(ActiveMotionValues.getStrafeDistance())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())


                        .build();

            }

            if (trussSideTape) {

                trussSDTraj = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5,()-> phss.dropPixel())

                        .waitSeconds(.5)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .strafeLeft(ActiveMotionValues.getStrafeDistance())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                        .build();

            }
        }


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
