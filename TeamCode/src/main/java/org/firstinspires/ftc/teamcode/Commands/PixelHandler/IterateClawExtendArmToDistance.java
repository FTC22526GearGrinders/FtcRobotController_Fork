package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;

/**
 * Iterate servo position by setting position on a timed basis.
 * <p>
 * Servo range is 0 to 1. Increment time is 50 ms
 * .01 increment per 50 ms is .2 per second so full range in 5 seconds
 */


public class IterateClawExtendArmToDistance extends CommandBase {

    private final PixelHandlerSubsystem phss;


    public IterateClawExtendArmToDistance(PixelHandlerSubsystem phss) {
        this.phss = phss;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double increment = .005;
        phss.iterateExtendClawArm(increment);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return phss.getSensorDistanceInches() <= Constants.PixelHandlerConstants.CLAW_ARM_DROP_DISTANCE;
    }
}
