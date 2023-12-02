package org.firstinspires.ftc.teamcode.Commands.Climber;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ClimberSubsystem;


public class PositionClimber extends CommandBase {
    private final ClimberSubsystem climber;

    private final double targetInches;


    public PositionClimber(ClimberSubsystem climber, double targetInches) {
        this.climber = climber;
        this.targetInches = targetInches;
        addRequirements(this.climber);
    }

    @Override
    public void initialize() {
        climber.targetInches = targetInches;
        climber.profController.setGoal(climber.targetInches);
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
        return climber.profController.atSetpoint();
    }
}
