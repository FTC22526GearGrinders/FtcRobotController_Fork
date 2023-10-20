package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class PositionPHArm extends CommandBase {

    private PixelHandlerSubsystem phss;

    private double power;

    private double distance;

    private double counts;

    public PositionPHArm(PixelHandlerSubsystem phss, double distance, double power) {
        this.phss=phss;
        this.power = power;
        this.distance = distance;
        addRequirements(phss);
    }

    @Override
    public void initialize() {
        counts = phss.getCountsfrominches(this.distance);
    }

    @Override
    public void execute() {
        double output = phss.controller.calculate(
                phss.getPositionInches(), counts);  // the measured value

        double motorOutput = output;

        if (output > 0 && output > power) motorOutput = power;

        if (output < 0 && output < power) motorOutput = -power;

        phss.armMotor.setPower(motorOutput);

    }


    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return phss.inPosition();
    }
}
