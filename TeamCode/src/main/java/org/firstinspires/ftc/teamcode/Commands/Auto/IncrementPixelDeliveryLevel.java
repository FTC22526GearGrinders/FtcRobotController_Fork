package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;


public class IncrementPixelDeliveryLevel extends CommandBase {

    boolean reset;

    public IncrementPixelDeliveryLevel(boolean reset) {
        this.reset = reset;
    }

    @Override
    public void initialize() {
        if (reset) {
            ActiveMotionValues.setBackboardLevel(1);
        } else {
            int level = ActiveMotionValues.getBackboardLevel();
            level++;
            if (level > Constants.ArmConstants.NUMBER_LEVELS)
                level = 1;
            ActiveMotionValues.setBackboardLevel(level);
        }
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
