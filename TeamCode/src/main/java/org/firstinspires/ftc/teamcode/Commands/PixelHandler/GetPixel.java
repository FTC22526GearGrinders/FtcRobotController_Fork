package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;


public class GetPixel extends CommandBase {

    private boolean leftGripper;

    public GetPixel(boolean leftGripper) {
        this.leftGripper = leftGripper;
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
