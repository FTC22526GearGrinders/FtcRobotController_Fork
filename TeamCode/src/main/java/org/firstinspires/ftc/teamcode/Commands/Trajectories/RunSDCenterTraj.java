package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.PixelHandler.DropPixelCommand;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunSDCenterTraj extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;

    private TrajectorySequence stageDoorCenter;

    public RunSDCenterTraj(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss=phss;
    }

    @Override
    public void initialize() {


        /**
         * Use th 5 step center for stage door selection
         * <p>
         * It has the pixel delivery after the first step
         */
        stageDoorCenter = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                        ActiveMotionValues.getyPoint(1)))

                .addTemporalMarker(() -> new DropPixelCommand(phss))

                .waitSeconds(3)//pixel drop off time

                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                        ActiveMotionValues.getyPoint(2)))

                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                        ActiveMotionValues.getyPoint(3)))

                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//move left or right on to middle of tape

                        ActiveMotionValues.getyPoint(4)))



                .build();


        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(stageDoorCenter);
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
