package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

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
        opMode.telemetry.addData(drive.runningTrajName, " running");
        opMode.telemetry.update();

        drive.drive.setPoseEstimate(drive.currentTrajSeq.start());

        drive.drive.followTrajectorySequence(drive.currentTrajSeq);

    }

    @Override
    public void execute() {


        drive.drive.update();

        opMode.telemetry.addData("Running ", drive.trajName);
        opMode.telemetry.addData("Duration", drive.currentTrajSeq.duration());
        opMode.telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.drive.stop();
        }

        opMode.telemetry.addData(drive.runningTrajName," ended");
        opMode.telemetry.update();

    }

    @Override
    public boolean isFinished() {
        return et.seconds() > 2 && (Thread.currentThread().isInterrupted() || !drive.drive.isBusy()) || et.seconds() > 4;
    }

}