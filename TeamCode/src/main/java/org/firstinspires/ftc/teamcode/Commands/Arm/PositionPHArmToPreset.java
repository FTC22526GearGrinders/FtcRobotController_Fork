package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class PositionPHArmToPreset extends CommandBase {

    private ArmSubsystem arm;

    private double power;



    public PositionPHArmToPreset(ArmSubsystem arm, double power) {
        this.arm = arm;
        this.power = power;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.loopCountTimer = 0;
        Constants.ArmConstants.armExtensions entry = Constants.ArmConstants.armExtensions.values()[ActiveMotionValues.getBackboardLevel()];

        double distance = entry.extension;
    }

    @Override
    public void execute() {

        arm.loopCountTimer++;

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
        return arm.loopCountTimer > 10 && arm.inPosition() || arm.loopCountTimer > 5000;
    }
}
