package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;


public class TelemetryCommand extends CommandBase {

    CommandOpMode myOpMode;

    public TelemetryCommand(CommandOpMode opMode) {
        myOpMode = myOpMode;
    }

    @Override
    public void initialize() {


        myOpMode.telemetry.addData("Hello", 911);
        myOpMode.telemetry.update();

    }

    @Override
    public void execute() {
//        myOpMode.telemetry.addData("LCRPOS", ActiveMotionValues.getLcrpos());
//        myOpMode.telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
