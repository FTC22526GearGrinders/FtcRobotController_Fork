package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class LRTapePlacePixel extends CommandBase {

    private final Drive_Subsystem drive;

    private final Claw_Subsystem clawSubsystem;

    TrajectorySequence traj1;

    Timing.Timer trajTimer = new Timing.Timer(10);

    /**
     * This routine takes care of moving from the start position to the tape, dropping off the pixel and returning to center
     * Moves are noe L shaped since one of the tapes is always close to the tusses and access needs to be from the side
     * All side tapes have the same motion.
     * <p>
     * Follow up motions are dependent on start position and other selections such as park and use the stage door
     */


    public LRTapePlacePixel(Drive_Subsystem drive, Claw_Subsystem clawSubsystem) {
        this.drive = drive;
        this.clawSubsystem = clawSubsystem;

    }

    @Override
    public void initialize() {

        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        traj1 =
                drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to point inside l and r tapes

                                ActiveMotionValues.getyPoint(1)))

                        .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                                ActiveMotionValues.getyPoint(2)))


                        .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(clawSubsystem).schedule())//drop pixel on tape

                        .waitSeconds(3)

                        .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move back to center

                                ActiveMotionValues.getyPoint(3)))


                        .build();


    }

    @Override
    public void execute() {

        drive.drive.followTrajectorySequence(traj1);

    }

    @Override
    public void end(boolean interrupted) {
        drive.drive.setPoseEstimate(ActiveMotionValues.getActiveTagPose());

    }

    @Override
    public boolean isFinished() {
        return trajTimer.elapsedTime() > traj1.duration() && drive.drive.getDriveStopped() || trajTimer.done();
    }
}
