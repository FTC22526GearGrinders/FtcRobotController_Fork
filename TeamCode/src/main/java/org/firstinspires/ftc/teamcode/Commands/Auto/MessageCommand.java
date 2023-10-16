package org.firstinspires.ftc.teamcode.Commands.Auto;

import android.graphics.Path;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.apache.commons.math3.util.IterationListener;
import org.openftc.easyopencv.OpenCvWebcam;


public class MessageCommand extends CommandBase {

private CommandOpMode myOpmode;
private OpenCvWebcam webcam;
    public MessageCommand(CommandOpMode opMode, OpenCvWebcam webcam) {
myOpmode=opMode;
this.webcam=webcam;
    }

    @Override
    public void initialize() {
        myOpmode.telemetry.addData("Help",911);
        myOpmode.telemetry.update();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
