package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class PositionPHArm extends CommandBase {

    private PixelHandlerSubsystem phss;

    private double power;

    private double distance;

    private int counts;

    public PositionPHArm(PixelHandlerSubsystem phss, double distance, double power) {
        this.phss = phss;
        this.power = power;
        this.distance = distance;
        addRequirements(phss);
    }

    @Override
    public void initialize() {
        counts = phss.getCountsfrominches(this.distance);
        phss.armMotor.setTargetPosition(counts);
        phss.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        phss.armMotor.setPower(.2);
    }

    @Override
    public void execute() {


    }


    @Override
    public void end(boolean interrupted) {
        phss.armMotor.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return phss.inPosition();
    }
}
