package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class MoveToPark extends CommandBase {

    private Drive_Subsystem drive;

    TrajectorySequence traj1;

    Timing.Timer trajTimer = new Timing.Timer(10);



    public MoveToPark(Drive_Subsystem drive) {
        this.drive = drive;

    }

    @Override
    public void initialize() {



        /**
         * This is only used for non backboard starts
         * The trajectory may use the stage door route in which case center park will be used
         * If the "newr" truss path is ussed the parking will be in the near locations
         * Near means near the drivers in each alliance
         *
         * *
         * */





        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        traj1 = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())


                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//drive to drop off poinr

                        ActiveMotionValues.getyPoint(4)))


                .lineTo(new Vector2d((ActiveMotionValues.getxPoint(5)),//move left or right on to middle of tape

                        ActiveMotionValues.getyPoint(5)))


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
