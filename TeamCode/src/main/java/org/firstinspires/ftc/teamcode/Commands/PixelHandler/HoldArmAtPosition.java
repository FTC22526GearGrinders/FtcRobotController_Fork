package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class HoldArmAtPosition extends CommandBase {
    private final ArmSubsystem arm;

    private double power;


    public HoldArmAtPosition(ArmSubsystem arm) {
        this.arm = arm;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {

        arm.holdInches = arm.getPositionInches();
        arm.holdCountTimer = 0;
    }

    @Override
    public void execute() {
        arm.holdCountTimer++;
        double output = arm.controller.calculate(
                arm.getPositionInches(), arm.holdInches);

        arm.armMotor.set(output + Constants.ArmConstants.POSITION_Kg);


    }


    @Override
    public void end(boolean interrupted) {
        arm.armMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
