package org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunBBLRTraj extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;

    private TrajectorySequence backboardLeftRight;

    private int numberPoints;

    public RunBBLRTraj(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
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

            backboardLeftRight = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getPreTagPose())


                    .build();

        } else {

            backboardLeftRight = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1.)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .strafeLeft(ActiveMotionValues.getStrafeDistance())

                    .lineToLinearHeading(ActiveMotionValues.getPreTagPose())


                    .build();


        }

        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(backboardLeftRight);
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
