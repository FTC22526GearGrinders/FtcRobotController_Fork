package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;


public class DeliverPixelSpikeTapeCommand extends CommandBase {

    int cnt;

    public DeliverPixelSpikeTapeCommand() {

    }

    @Override
    public void initialize() {
        cnt = 0;

    }

    @Override
    public void execute() {
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
