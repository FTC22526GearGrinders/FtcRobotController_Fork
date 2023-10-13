package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;


public class DeliverPixelSpikeTapeCommand extends CommandBase {

    private Claw_Subsystem clawSubsystem;

    int cnt;

    public DeliverPixelSpikeTapeCommand(Claw_Subsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
    }

    @Override
    public void initialize() {

        clawSubsystem.open();
        cnt = 0;

    }

    @Override
    public void execute() {
        clawSubsystem.open();
        cnt++;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return cnt > 100;
    }
}
