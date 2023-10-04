package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;


public class GetSwitchSettings extends CommandBase {

    private IO_Subsystem ioss;

    private CommandOpMode myOpMode;

    public GetSwitchSettings(CommandOpMode opMode,IO_Subsystem ioss) {
        ioss = ioss;
        myOpMode=opMode;
    }

    @Override
    public void initialize() {
        ActiveMotionValues.setRedAlliance(ioss.dc0.getState());
        ActiveMotionValues.setBBStart(ioss.dc1.getState());

    }

    @Override
    public void execute() {
        ActiveMotionValues.setRedAlliance(ioss.dc0.getState());
        ActiveMotionValues.setBBStart(ioss.dc1.getState());
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
