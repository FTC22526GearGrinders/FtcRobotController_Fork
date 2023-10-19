package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class RunTrajectorySequence extends CommandBase {
    private Drive_Subsystem drive;
    private Claw_Subsystem claw;

    private TrajectorySequence testSeq;

    public RunTrajectorySequence(Drive_Subsystem drive) {
       this.drive=drive;


    }

    @Override
    public void initialize() {


       testSeq = drive.drive.trajectorySequenceBuilder(new Pose2d())

//                .splineTo(new Vector2d(10, 10), 0)
//                .turn(Math.toRadians(90))
//                .splineTo(new Vector2d(25, -15), 0)
//                .waitSeconds(3)
//                .turn(Math.toRadians(45))

               .strafeRight(72)
               //forward(72)
               .waitSeconds(5)
               .strafeLeft(72)
             //   .turn(Math.toRadians(180))



              //  .strafeLeft(5)
               // .splineToLinearHeading(new Pose2d(-10, -10, Math.toRadians(45)), 0)
                .build();



       drive.drive .setPoseEstimate(new Pose2d());

       drive.drive.followTrajectorySequence(testSeq);
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
