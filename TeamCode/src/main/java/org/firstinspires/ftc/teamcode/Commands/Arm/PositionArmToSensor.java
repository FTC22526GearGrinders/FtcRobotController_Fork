package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class PositionArmToSensor extends CommandBase {
    private final ArmSubsystem arm;

    private final PixelHandlerSubsystem phss;

    private final double targetInches;


    public PositionArmToSensor(ArmSubsystem arm, PixelHandlerSubsystem phss, double targetInches) {
        this.arm = arm;
        this.phss = phss;
        this.targetInches = targetInches;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        double sensorTargetValue = 2.5;

        double sensorMinValid = 1;

        double sensorMaxValid = 3.5;

        double sensorReading = phss.getSensorDistanceInches();

        boolean sensorValid = sensorReading < sensorMaxValid && sensorReading > sensorMinValid;

        if (sensorValid) {

            double remainingDistance = arm.targetInches - arm.getPositionInches();

            double sensorRemainingDistance = sensorTargetValue - sensorReading;

            double positionCorrection = remainingDistance - sensorRemainingDistance; //+ means need to go further than initial target

            arm.targetInches += positionCorrection;

            arm.profController.setGoal(arm.targetInches);

        }


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
