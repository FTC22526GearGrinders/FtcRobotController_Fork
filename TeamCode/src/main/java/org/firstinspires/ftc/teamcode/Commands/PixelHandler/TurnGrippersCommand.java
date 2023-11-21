package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class TurnGrippersCommand extends CommandBase {

    private final PixelHandlerSubsystem phss;

    private final Constants.PixelHandlerConstants.TurnGripperSet value;


    private int lpctr;

    public TurnGrippersCommand(PixelHandlerSubsystem phss, Constants.PixelHandlerConstants.TurnGripperSet value) {
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

        phss.positionTurnGripper(value.position);
        phss.turnGrippersPosition=value.position;

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return lpctr >= 2;
    }
}
