package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class PixelTapePlacerCommand extends CommandBase {

    private PixelHandlerSubsystem phsss;

    int cnt;

    public PixelTapePlacerCommand(PixelHandlerSubsystem phss) {
        this.phsss = phss;
    }

    @Override
    public void initialize() {

        phsss.holdPixel();
        cnt = 0;

    }

    @Override
    public void execute() {
        phsss.dropPixel();
        cnt++;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return cnt > 100;
    }
}
