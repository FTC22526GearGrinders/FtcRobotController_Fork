package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class CenterTapeTest extends CommandBase {

    private Drive_Subsystem drive;

    TrajectorySequence traj1;

    Timing.Timer trajTimer = new Timing.Timer(10);

    long dropOffTime = 1L;
    private boolean trajectoryStarted;

    public CenterTapeTest(Drive_Subsystem drive) {
        this.drive = drive;
        trajTimer.start();
        ;
    }

    @Override
    public void initialize() {


        trajectoryStarted = false;

        drive.started = false;

        drive.drive.setPoseEstimate(new Pose2d());

        traj1 = drive.drive.trajectorySequenceBuilder(new Pose2d())

                .lineTo(new Vector2d(50, -10))

                //  .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand())

                .waitSeconds(3)

                //  .back(ActiveMotionValues.getRetractDistance())
//
                //CenterTapePlacePixel    .lineToLinearHeading(ActiveMotionValues.getTagLookAheadPose())

                .build();

    }

    @Override
    public void execute() {

        drive.drive.followTrajectorySequence(traj1);

    }


    @Override
    public void end(boolean interrupted) {
        drive.drive.stop();
    }

    @Override
    public boolean isFinished() {
        return trajTimer.elapsedTime() > traj1.duration() && drive.drive.getDriveStopped() || trajTimer.done();
    }
}
