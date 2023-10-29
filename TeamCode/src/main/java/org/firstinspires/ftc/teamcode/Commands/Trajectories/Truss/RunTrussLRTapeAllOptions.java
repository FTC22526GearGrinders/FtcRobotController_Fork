package org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunTrussLRTapeAllOptions extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;

    private TrajectorySequence trussNear;


    public RunTrussLRTapeAllOptions(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;

    }

    @Override
    public void initialize() {

        boolean secondPixel=ActiveMotionValues.getSecondPixel();

        boolean trussSideTape = ActiveMotionValues.getRedAlliance() &&

                (ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 1

                        || !ActiveMotionValues.getBBStart() && ActiveMotionValues.getLcrpos() == 3);


        if (!secondPixel && !trussSideTape) {
            /**
             * Use th 5 step center for stage door selection
             * <p>
             * It has the pixel delivery after the first step
             */
            trussNear = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .waitSeconds(.5)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getParkPose())


                    .build();

        }

        if (!secondPixel && trussSideTape) {

            trussNear = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .waitSeconds(.5)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getStrafePose())


                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getStrafeToAprilTagPose())


                    .build();

        }

        if (secondPixel && !trussSideTape) {
            /**
             * Use th 5 step center for stage door selection
             * <p>
             * It has the pixel delivery after the first step
             */
            trussNear = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .waitSeconds(.5)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getStrafeToAprilTagPose())


                    .build();

        }

        if (secondPixel && trussSideTape) {

            trussNear = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .waitSeconds(.5)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getStrafeToAprilTagPose())


                    .build();

        }


        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(trussNear);
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
