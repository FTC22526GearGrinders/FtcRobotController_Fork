package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class ShowTrajectoryInfo extends CommandBase {

    private final Drive_Subsystem drive;

    private final CommandOpMode opMode;

    private ElapsedTime et;

    int size;

    public ShowTrajectoryInfo(Drive_Subsystem drive, CommandOpMode opMode) {
        this.drive = drive;
        this.opMode = opMode;
    }

    @Override
    public void initialize() {
        et = new ElapsedTime();
    }

    @Override
    public void execute() {

        boolean nullTraj = drive.currentTrajSeq == null;

        if (!nullTraj) {
            size = drive.currentTrajSeq.size();
            double d = drive.currentTrajSeq.duration();
        }

        opMode.telemetry.addData("NullTraj", nullTraj);
        opMode.telemetry.addData("TName", drive.runningTrajName);
        opMode.telemetry.addData("TBuilding", drive.trajectoryBuilding);
        opMode.telemetry.addData("TBuilt", drive.trajectoryBuilt);
        opMode.telemetry.addData("LEftRightCenter", ActiveMotionValues.getLcrpos());



        opMode.telemetry.addData("Size", size);
        opMode.telemetry.addData("ETime", et.seconds());


    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return et.seconds() > 1;
    }
}
