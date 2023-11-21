package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class LeftGripperCommand extends CommandBase {

    private final PixelHandlerSubsystem phss;

    private final Constants.PixelHandlerConstants.LeftGripperSet value;


    private int lpctr;

    public LeftGripperCommand(PixelHandlerSubsystem phss, Constants.PixelHandlerConstants.LeftGripperSet value) {
        this.phss = phss;
        this.value = value;
    }

    @Override
    public void initialize() {
        lpctr = 0;

    }

    @Override
    public void execute() {

        lpctr++;

        phss.positionLeftGripper(value.position);

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return lpctr >= 2;
    }
}
