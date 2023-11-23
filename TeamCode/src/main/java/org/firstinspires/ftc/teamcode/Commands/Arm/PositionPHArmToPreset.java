package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class PositionPHArmToPreset extends CommandBase {
    private final ArmSubsystem arm;
    private int index;
    private double power;
    private double target;

    public PositionPHArmToPreset(ArmSubsystem arm, int index, double power) {
        this.arm = arm;
        this.index = index;
        this.power = power;

        addRequirements(arm);
    }

    @Override
    public void initialize() {


    }

    @Override
    public void execute() {

        if (arm.armPositionIndex > Constants.ArmConstants.armPositionInches.length - 1)
            arm.armPositionIndex = Constants.ArmConstants.armPositionInches.length - 1;

        if (arm.armPositionIndex < 0)
            arm.armPositionIndex = 0;


        arm.targetInches = Constants.ArmConstants.armPositionInches[index];


        double output = arm.controller.calculate(
                arm.getPositionInches(), arm.targetInches);

        double temp = output + Constants.ArmConstants.POSITION_Kg;

        boolean minus = temp < 0;

        if (Math.abs(temp) > power) temp = power;

        if (minus) temp = -temp;

        arm.armMotor.set(temp);


    }


    @Override
    public void end(boolean interrupted) {
        arm.armMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return  arm.inPosition();
    }
}
