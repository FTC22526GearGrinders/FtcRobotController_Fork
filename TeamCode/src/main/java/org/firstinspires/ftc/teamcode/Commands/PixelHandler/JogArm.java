package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class JogArm extends CommandBase {

    private ArmSubsystem arm;
    private double power;

    public JogArm(ArmSubsystem arm, double power) {
        this.arm=arm;
        this.power = power;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {arm.jog(power);
    }

    @Override
    public void end(boolean interrupted) {
        arm.armMotor.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
