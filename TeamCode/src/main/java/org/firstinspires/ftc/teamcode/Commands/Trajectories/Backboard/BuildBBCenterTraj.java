package org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class BuildBBCenterTraj extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;

    private CommandOpMode opMode;

    private ElapsedTime et;

    public BuildBBCenterTraj(Drive_Subsystem drive, PixelHandlerSubsystem phss, CommandOpMode opMode) {
        this.drive = drive;
        this.phss = phss;
        this.opMode = opMode;
    }

    @Override
    public void initialize() {
        et = new ElapsedTime();

        drive.trajectoryBuilt = false;
    }

    @Override
    public void execute() {

        /**
         * Use th 5 step center for stage door selection
         * <p>
         * It has the pixel delivery after the first step
         *
         */
        drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width


                .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                .UNSTABLE_addTemporalMarkerOffset(1, () -> phss.dropPixel())

                .waitSeconds(1)

                .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                .lineToLinearHeading(ActiveMotionValues.getPreTagPose())


                .build();

        drive.trajName = "BBCenter";

        drive.trajectoryBuilt = drive.currentTrajSeq != null;

        opMode.telemetry.addData("Building BBCenter", "");
        opMode.telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        drive.trajectoryBuilding = false;
        opMode.telemetry.addData("End BBCenter Build", "");
        opMode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return drive.trajectoryBuilt;
    }

}
