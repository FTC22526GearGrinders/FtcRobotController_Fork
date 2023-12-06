package org.firstinspires.ftc.teamcode.Commands.Climber;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ClimberSubsystem;


public class JogClimber extends CommandBase {

    private ClimberSubsystem climber;

    private final GamepadEx gamepad;


    public JogClimber(ClimberSubsystem climber, GamepadEx gamepad) {
        this.climber = climber;
        this.gamepad = gamepad;
        addRequirements(this.climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        climber.power = -gamepad.getRightY() / 4;

        if (climber.power > 0 && climber.getPositionInches() < Constants.ClimberConstants.UPPER_POSITION_LIMIT

                || climber.power < 0 && climber.getPositionInches() > Constants.ClimberConstants.LOWER_POSITION_LIMIT) {

        } else
            climber.power = 0;

        climber.climberMotor.set(climber.power);
    }

    @Override
    public void end(boolean interrupted) {
        climber.climberMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
