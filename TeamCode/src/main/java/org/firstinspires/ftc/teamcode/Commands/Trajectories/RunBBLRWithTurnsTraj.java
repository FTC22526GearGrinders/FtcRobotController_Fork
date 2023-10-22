package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunBBLRWithTurnsTraj extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;

    private TrajectorySequence backboardLeftRightTurn1;
    private TrajectorySequence backboardLeftRightTurn2;

    private int numberPoints;

    public RunBBLRWithTurnsTraj(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;
    }

    @Override
    public void initialize() {

        numberPoints = ActiveMotionValues.getPointsUsed();
        /**
         * Use th 5 step center for stage door selection
         * <p>
         * It has the pixel delivery after the first step
         */
        backboardLeftRightTurn1= drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                        ActiveMotionValues.getyPoint(1)))


                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                        ActiveMotionValues.getyPoint(2)))

                .build();


        backboardLeftRightTurn2= drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())


                .UNSTABLE_addTemporalMarkerOffset(.25, () -> phss.dropPixel())

                .waitSeconds(2)//pixel drop off time

                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                        ActiveMotionValues.getyPoint(3)))


                .lineToLinearHeading(ActiveMotionValues.getTagLookAheadPose())


                .build();


        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(backboardLeftRightTurn1);


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
