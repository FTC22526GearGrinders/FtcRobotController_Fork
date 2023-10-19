package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class PlacePixelOnBB extends CommandBase {

    private PixelHandlerSubsystem phss;

    public PlacePixelOnBB(PixelHandlerSubsystem phss) {
        this.phss = phss;
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
