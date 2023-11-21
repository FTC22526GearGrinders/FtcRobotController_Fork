package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class TurnGrippersIncrementalCommand extends CommandBase {

    private final PixelHandlerSubsystem phss;

    private final Constants.TurnGripperJogSet val;
    private final boolean plus;

    public TurnGrippersIncrementalCommand(PixelHandlerSubsystem phss, boolean plus, Constants.TurnGripperJogSet val) {
        this.phss = phss;
        this.val = val;
        this.plus = plus;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double temp = val.increment;
        if (!plus) temp = -temp;
        double nextPosition = phss.turnGrippersPosition + temp;
        if (nextPosition > Constants.PixelHandlerConstants.TurnGripperSet.DELIVER.position)
            nextPosition = Constants.PixelHandlerConstants.TurnGripperSet.DELIVER.position;
        if (nextPosition < Constants.PixelHandlerConstants.TurnGripperSet.PICKUP.position)
            nextPosition = Constants.PixelHandlerConstants.TurnGripperSet.PICKUP.position;

        phss.positionTurnGripper(nextPosition);
        phss.turnGrippersPosition = nextPosition;

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
