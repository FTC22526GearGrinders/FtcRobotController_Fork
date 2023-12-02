package org.firstinspires.ftc.teamcode.Commands.Climber;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ClimberSubsystem;


public class PositionHoldClimber extends CommandBase {
    private final ClimberSubsystem climber;

    private double power;


    public PositionHoldClimber(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(this.climber);
    }

    @Override
    public void initialize() {
        double temp = climber.getPositionInches();
        climber.profController.setGoal(temp);

    }

    @Override
    public void execute() {

        double output = climber.profController.calculate(
                climber.getPositionInches());

        climber.climberMotor.set(output + Constants.ClimberConstants.POSITION_Kg);

    }


    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
