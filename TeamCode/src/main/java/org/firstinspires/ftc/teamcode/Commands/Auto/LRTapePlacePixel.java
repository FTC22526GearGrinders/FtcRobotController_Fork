package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class LRTapePlacePixel extends CommandBase {

    private Drive_Subsystem drive;

    TrajectorySequence traj1;

    private boolean trajectoryStarted;

    public LRTapePlacePixel(Drive_Subsystem drive) {
        this.drive = drive;

    }

    @Override
    public void initialize() {


        traj1 =
                drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineTo(new Vector2d((ActiveMotionValues.getxFirstPoint() + ActiveMotionValues.getxOffset()), ActiveMotionValues.getyFirstPoint() + ActiveMotionValues.getyOffset()))

                        .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand().schedule())

                        .waitSeconds(3)

                        .lineTo(new Vector2d((ActiveMotionValues.getxFirstPoint() + ActiveMotionValues.getxOffset()), ActiveMotionValues.getyFirstPoint() + ActiveMotionValues.getyOffset()))

                        .back(ActiveMotionValues.getRetractDistance())

                        .lineToLinearHeading(ActiveMotionValues.getTagLookAheadPose())

                        .build();


    }

    @Override
    public void execute() {

        drive.drive.followTrajectorySequence(traj1);

        if (!trajectoryStarted && drive.drive.getTrajectoryRunning()) {
            trajectoryStarted = true;
        }

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return trajectoryStarted && !drive.drive.getTrajectoryRunning() && drive.drive.getDriveStopped();
    }
}
