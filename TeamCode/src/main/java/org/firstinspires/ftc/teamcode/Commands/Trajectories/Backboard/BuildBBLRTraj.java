package org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class BuildBBLRTraj extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;


    private int numberPoints;

    public BuildBBLRTraj(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;
    }

    @Override
    public void initialize() {

        boolean trussSideTapeRed = ActiveMotionValues.getRedAlliance() &&

                ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 1;

        boolean trussSideTapeBlue = !ActiveMotionValues.getRedAlliance() &&

                ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 3;


        boolean trussSideTape = trussSideTapeRed || trussSideTapeBlue;

        /**
         * Use th 5 step center for stage door selection
         * <p>
         * It has the pixel delivery after the first step
         */

        if (!trussSideTape) {

            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getPreTagPose())


                    .build();

        } else {

            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1.)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .strafeLeft(ActiveMotionValues.getStrafeDistance())

                    .lineToLinearHeading(ActiveMotionValues.getPreTagPose())

                    .build();

            drive.trajName = "BBLeftRight";
        }

        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(drive.currentTrajSeq);
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
