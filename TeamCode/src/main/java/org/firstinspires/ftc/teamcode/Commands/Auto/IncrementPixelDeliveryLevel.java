package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;public class IncrementPixelDeliveryLevel extends CommandBase {
    private final ArmSubsystem arm;
    private final boolean up;
    public IncrementPixelDeliveryLevel(ArmSubsystem arm, boolean up) {
        this.arm = arm;
        this.up = up;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (up)
            arm.armPositionIndex += 1;
        else
            arm.armPositionIndex -= 1;

        if (arm.armPositionIndex > Constants.ArmConstants.armPositionInches.length - 1)
            arm.armPositionIndex = Constants.ArmConstants.armPositionInches.length - 1;

        if (arm.armPositionIndex < 0)
            arm.armPositionIndex = 0;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
