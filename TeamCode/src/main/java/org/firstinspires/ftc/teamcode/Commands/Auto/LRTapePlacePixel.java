package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class LRTapePlacePixel extends CommandBase {

    private Drive_Subsystem drive;

    TrajectorySequence traj1;

    boolean trajend;


    public LRTapePlacePixel(Drive_Subsystem drive) {
        this.drive = drive;

    }

    @Override
    public void initialize() {


        traj1 =
                drive.drive.trajectorySequenceBuilder(ActiveMotionValues.startPose)

                        .lineTo(new Vector2d((ActiveMotionValues.xFirstPoint + ActiveMotionValues.xOffset), ActiveMotionValues.yFirstPoint + ActiveMotionValues.yOffset))

                        .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand().schedule())

                        .waitSeconds(3)

                        .lineTo(new Vector2d((ActiveMotionValues.xFirstPoint + ActiveMotionValues.xOffset), ActiveMotionValues.yFirstPoint + ActiveMotionValues.yOffset))

                        .back(ActiveMotionValues.retractDistance)

                        .lineToLinearHeading(ActiveMotionValues.tagLookAheadPose)

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
        return trajend;
    }
}
