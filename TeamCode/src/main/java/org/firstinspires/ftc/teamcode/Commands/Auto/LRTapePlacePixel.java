package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class LRTapePlacePixel extends CommandBase {

    private Drive_Subsystem drive;

    private Claw_Subsystem clawSubsystem;

    TrajectorySequence traj1;

    Timing.Timer trajTimer = new Timing.Timer(10);


    public LRTapePlacePixel(Drive_Subsystem drive, Claw_Subsystem clawSubsystem) {
        this.drive = drive;
        this.clawSubsystem = clawSubsystem;

    }

    @Override
    public void initialize() {

        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        traj1 =
                drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineTo(new Vector2d((ActiveMotionValues.getxFirstPoint()),

                                ActiveMotionValues.getyFirstPoint()))


                        .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(clawSubsystem).schedule())

                        .waitSeconds(3)

                        .lineTo(new Vector2d((ActiveMotionValues.getxSecondPoint()),

                                ActiveMotionValues.getySecondPoint()))


                        .lineToLinearHeading(ActiveMotionValues.getTagLookAheadPose())

                        .build();


    }

    @Override
    public void execute() {

        drive.drive.followTrajectorySequence(traj1);

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return trajTimer.elapsedTime() > traj1.duration() && drive.drive.getDriveStopped() || trajTimer.done();
    }
}
