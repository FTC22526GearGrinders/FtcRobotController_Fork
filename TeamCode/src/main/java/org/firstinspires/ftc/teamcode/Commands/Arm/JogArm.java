package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class JogArm extends CommandBase {

    private ArmSubsystem arm;

    private boolean up;


    public JogArm(ArmSubsystem arm, boolean up) {
        this.arm = arm;
        this.up = up;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double power = 0;
        if (up) {

            power = Constants.ArmConstants.JOG_UP_POWER;

           // arm.targetInches = Constants.ArmConstants.UPPER_POSITION_LIMIT;

        }

        if (!up) {

            power = Constants.ArmConstants.JOG_DOWN_POWER;
           // arm.targetInches = Constants.ArmConstants.LOWER_POSITION_LIMIT;

        }

        if (power > 0 && arm.getPositionInches() < Constants.ArmConstants.UPPER_POSITION_LIMIT

                || power < 0 && arm.getPositionInches() > Constants.ArmConstants.LOWER_POSITION_LIMIT) {

            arm.armMotor.set(power);

        } else arm.armMotor.set(0);
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
