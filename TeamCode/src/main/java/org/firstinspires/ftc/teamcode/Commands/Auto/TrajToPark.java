package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class TrajToPark extends CommandBase {

    private Drive_Subsystem drive;
    TrajectorySequence traj1;

    Timing.Timer trajTimer = new Timing.Timer(10);


    public TrajToPark(Drive_Subsystem drive) {
        this.drive = drive;


    }

    @Override
    public void initialize() {

        drive.drive.setPoseEstimate(ActiveMotionValues.getActiveTagPose());

        traj1 =
                drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getActiveTagPose())

                        .lineTo(new Vector2d(ActiveMotionValues.getFinalPose().getX(),

                                ActiveMotionValues.getFinalPose().getX()))


                        .waitSeconds(3)

                        .lineToLinearHeading(ActiveMotionValues.getParkPose())


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
