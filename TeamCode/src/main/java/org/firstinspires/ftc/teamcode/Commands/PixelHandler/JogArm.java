package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class JogArm extends CommandBase {

    private PixelHandlerSubsystem phss;
    private double power;

    public JogArm(PixelHandlerSubsystem phss, double power) {
        this.phss = phss;
        this.power = power;
        addRequirements(this.phss);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        phss.jog(power);
    }

    @Override
    public void end(boolean interrupted) {
        phss.armMotor.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
