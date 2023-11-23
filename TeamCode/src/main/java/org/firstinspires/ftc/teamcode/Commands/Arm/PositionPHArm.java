package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class PositionPHArm extends CommandBase {

    private ArmSubsystem arm;

    private double power;

    private double extension;

    ElapsedTime et;

    public PositionPHArm(ArmSubsystem arm, double extension, double power) {
        this.arm = arm;
        this.power = power;
        this.extension = extension;

        addRequirements(arm);
    }

    @Override
    public void initialize() {

        et = new ElapsedTime();

        arm.targetInches = extension;
    }

    @Override
    public void execute() {


        double output = arm.controller.calculate(
                arm.getPositionInches(), arm.targetInches);

        double temp = output + Constants.ArmConstants.POSITION_Kg;

        boolean minus = temp < 0;

        if (Math.abs(temp) > power) temp = power;

        if (minus) temp = -temp;

        arm.power = temp;

        // arm.armMotor.set(arm.power);

    }


    @Override
    public void end(boolean interrupted) {
        arm.armMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return et.seconds() > .1 && arm.inPosition() || et.seconds() > 10;
    }

    public void setTargetInches(double inches){
        ;
    }
}
