package org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class BuildTrussSDLRTape extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;


    public BuildTrussSDLRTape(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;

    }

    @Override
    public void initialize() {

        boolean stageDoor = ActiveMotionValues.getUseStageDoor();

        boolean trussSideTapeRed = ActiveMotionValues.getRedAlliance() &&
                ActiveMotionValues.getLcrpos() == 3;

        boolean trussSideTapeBlue = !ActiveMotionValues.getRedAlliance() &&
                ActiveMotionValues.getLcrpos() == 1;

        boolean trussSideTape = trussSideTapeRed || trussSideTapeBlue;

        if (!stageDoor) {


            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getClearPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                    .waitSeconds(.5)

                    .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())


                    .build();

        }


        if (stageDoor) {

            if (!trussSideTape) {

                drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                        .waitSeconds(1)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .strafeLeft(ActiveMotionValues.getStrafeDistance())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .waitSeconds(ActiveMotionValues.getStopSecs())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())


                        .build();

            }

            if (trussSideTape) {

                drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                        .waitSeconds(1)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .strafeLeft(ActiveMotionValues.getStrafeDistance())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                        .build();


                drive.trajName = "TSLDCenter";

            }


            drive.trajectoryBuilt = drive.currentTrajSeq != null;
        }


    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        drive.trajectoryBuilding = false;
    }

    @Override
    public boolean isFinished() {
        return drive.trajectoryBuilt;
    }

}
