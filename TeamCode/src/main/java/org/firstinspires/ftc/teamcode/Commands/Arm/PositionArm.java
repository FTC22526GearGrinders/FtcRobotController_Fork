package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class PositionArm extends CommandBase {
    private final ArmSubsystem arm;

    private final double targetInches;


    public PositionArm(ArmSubsystem arm, double targetInches) {
        this.arm = arm;
        this.targetInches = targetInches;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
        arm.targetInches = targetInches;
        arm.profController.setGoal(arm.targetInches);
    }

    @Override
    public void execute() {

        double output = arm.profController.calculate(
                arm.getPositionInches());

        arm.armMotor.set(output + Constants.ArmConstants.POSITION_Kg);

    }


    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return arm.profController.atSetpoint();
    }
}
