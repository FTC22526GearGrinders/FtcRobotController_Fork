package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class MoveToPark extends CommandBase {
    private Drive_Subsystem drive;

    private Trajectory parkTraj;

    public MoveToPark(Drive_Subsystem drivw) {
        this.drive = drive;
    }

    @Override
    public void initialize() {

        //parkTraj =
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
