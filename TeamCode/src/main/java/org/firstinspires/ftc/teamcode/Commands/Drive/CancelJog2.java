package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class CancelJog2 extends CommandBase {

    private final Drive_Subsystem drive;

    public CancelJog2(Drive_Subsystem drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
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
