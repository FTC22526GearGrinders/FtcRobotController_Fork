package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;

/**
 * Iterate servo position by setting position on a timed basis.
 * <p>
 * Servo range is 0 to 1. Increment time is 50 ms
 * .01 increment per 50 ms is .2 per second so full range in 5 seconds
 */


public class IterateClawExtendServo extends CommandBase {

    private PixelHandlerSubsystem phss;

    private boolean extend;

    private double lastPosition;


    public IterateClawExtendServo(PixelHandlerSubsystem phss, boolean extend) {
        this.phss = phss;
        this.extend = extend;
    }

    @Override
    public void initialize() {
        lastPosition = phss.getLastPosition();
    }

    @Override
    public void execute() {
        double increment = .005;
        if (extend) phss.iterateExtendClawArm(increment);
        else phss.iterateRetractArm(increment);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
