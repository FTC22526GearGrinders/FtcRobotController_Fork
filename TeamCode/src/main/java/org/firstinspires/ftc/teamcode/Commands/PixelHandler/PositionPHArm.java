package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class PositionPHArm extends CommandBase {

    private ArmSubsystem arm;

    private double power;

    private double distance;

    private int counts;

    public PositionPHArm(ArmSubsystem arm, double distance, double power) {
        this.arm=arm;
        this.power = power;
        this.distance = distance;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        counts = arm.getCountsfrominches(this.distance);
        arm.armMotor.setTargetPosition(counts);
        arm.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.armMotor.setPower(.2);
    }

    @Override
    public void execute() {


    }


    @Override
    public void end(boolean interrupted) {
        arm.armMotor.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return arm.inPosition();
    }
}
