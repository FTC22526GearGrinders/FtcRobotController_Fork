package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class JogArm extends CommandBase {

    private ArmSubsystem arm;

    private final Gamepad gamepad;


    public JogArm(ArmSubsystem arm, Gamepad gamepad) {
        this.arm = arm;
        this.gamepad = gamepad;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        double power = -gamepad.left_stick_y / 4;

        if (power > 0 && arm.getPositionInches() < Constants.ArmConstants.UPPER_POSITION_LIMIT

                || power < 0 && arm.getPositionInches() > Constants.ArmConstants.LOWER_POSITION_LIMIT) {

        } else
            power = 0;

        arm.armMotor.set(power);
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
