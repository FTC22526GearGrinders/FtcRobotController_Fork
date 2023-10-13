package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class TrajToParkUnderStageDoor extends CommandBase {

    private Drive_Subsystem drive;
    TrajectorySequence traj1;

    Timing.Timer trajTimer = new Timing.Timer(10);

    int lcr;

    boolean redAlliiance;

    Pose2d seoondPose=new Pose2d();

    public TrajToParkUnderStageDoor(Drive_Subsystem drive) {
        this.drive = drive;


    }

    @Override
    public void initialize() {

        lcr = ActiveMotionValues.getLcrpos();

        redAlliiance = ActiveMotionValues.getRedAlliance();

        Pose2d startPos = new Pose2d();

        Pose2d seoondPose = new Pose2d();

        Pose2d thirdPose = new Pose2d();

        Pose2d parkPose = new Pose2d();


        switch (lcr) {
            case 1:
                if (redAlliiance) {
                    startPos = FieldConstantsRed.stageDoorLineUpPose2;
                } else {
                    startPos = FieldConstantsBlue.stageDoorLineUpPose1;
                }
                break;
            case 2:
                if (redAlliiance) {
                    startPos = FieldConstantsRed.stageDoorLineUpPose;

                } else
                    startPos = FieldConstantsBlue.stageDoorLineUpPose;
                break;
            case 3:
                if (redAlliiance)
                    startPos = FieldConstantsRed.stageDoorLineUpPose1;
                else
                    startPos = FieldConstantsBlue.stageDoorLineUpPose2;
                break;

            default:
                break;

        }
        if (redAlliiance) {
            seoondPose = FieldConstantsRed.slowToStageDoorPose;
            thirdPose = FieldConstantsRed.clearStageDoorPose;
            parkPose = FieldConstantsRed.farBackstagePark;

        } else {
            seoondPose = FieldConstantsBlue.slowToStageDoorPose;
            thirdPose = FieldConstantsBlue.clearStageDoorPose;
            parkPose = FieldConstantsBlue.farBackstagePark;
        }


        drive.drive.setPoseEstimate(startPos);

        traj1 =
                drive.drive.trajectorySequenceBuilder(startPos)

                        .setConstraints(DriveConstants.trajVel, DriveConstants.trajAccel)

                        .lineTo(new Vector2d(seoondPose.getX(), seoondPose.getY()))

                        .setConstraints(DriveConstants.liftDoorVel, DriveConstants.liftDoorAccel)

                        .lineTo(new Vector2d(thirdPose.getX(), thirdPose.getY()))

                        .setConstraints(DriveConstants.trajVel, DriveConstants.trajAccel)


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
