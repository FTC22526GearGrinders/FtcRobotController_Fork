package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class RunTrajSequence extends CommandBase {

    private Drive_Subsystem drive;
    private CommandOpMode opMode;

    ElapsedTime et;

    public RunTrajSequence(Drive_Subsystem drive, CommandOpMode opMode) {
        this.drive = drive;
        this.opMode = opMode;
    }

    @Override
    public void initialize() {
        et = new ElapsedTime();

        drive.drive.setPoseEstimate(ActiveMotionValues.getStartPose());

        drive.drive.followTrajectorySequence(drive.currentTrajSeq);
    }

    @Override
    public void execute() {
        drive.drive.update();
        opMode.telemetry.addData("Running","");
        opMode.telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.drive.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return Thread.currentThread().isInterrupted() || !drive.drive.isBusy() || et.seconds() > 4;
    }

}