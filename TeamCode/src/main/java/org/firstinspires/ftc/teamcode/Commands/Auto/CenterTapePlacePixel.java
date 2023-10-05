package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class CenterTapePlacePixel extends CommandBase {

    private Drive_Subsystem drive;

   TrajectorySequence traj1;

    boolean trajend;

    long dropOffTime = 1L;

    public CenterTapePlacePixel(Drive_Subsystem drive) {
        this.drive = drive;

    }

    @Override
    public void initialize() {

        traj1 = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

              .lineTo(new Vector2d(ActiveMotionValues.getxFirstPoint(), ActiveMotionValues.getStartPose().getY() + ActiveMotionValues.getyOffset()))

                .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand())

                .waitSeconds(3)

                .back(ActiveMotionValues.getRetractDistance())
//
                .lineToLinearHeading(ActiveMotionValues.getTagLookAheadPose())

                .build();


        trajend = false;

    }

    @Override
    public void execute() {

        drive.drive.followTrajectorySequence(traj1);

        trajend = true;

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
