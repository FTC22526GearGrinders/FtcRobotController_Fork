package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class PositionPHArm extends CommandBase {

    private PixelHandlerSubsystem phss;

    private double targetInches;

    public PositionPHArm(PixelHandlerSubsystem phss, double targetInches) {
        this.phss = phss;
        this.targetInches = targetInches;
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
        return false;
    }
}
