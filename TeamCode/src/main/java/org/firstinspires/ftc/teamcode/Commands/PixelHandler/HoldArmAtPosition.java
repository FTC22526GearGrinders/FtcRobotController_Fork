package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class HoldArmAtPosition extends CommandBase {
    private final ArmSubsystem arm;

    private double power;

    private double distanceInches;

    private int counts;


    public HoldArmAtPosition(ArmSubsystem arm) {
        this.arm = arm;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
        distanceInches = arm.getPositionInches();
    }

    @Override
    public void execute() {

        double output = arm.controller.calculate(
                arm.getPositionInches(), this.distanceInches);  // the measured value

        double motorOutput = output;

        arm.armMotor.setPower(motorOutput + Constants.PixelHandlerConstants.POSITION_Kg);


    }


    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
