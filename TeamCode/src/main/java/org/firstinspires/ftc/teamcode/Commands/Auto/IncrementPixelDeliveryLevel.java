package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;


public class IncrementPixelDeliveryLevel extends CommandBase {

    public IncrementPixelDeliveryLevel() {

    }

    @Override
    public void initialize() {
        int level = ActiveMotionValues.getBackboardLevel();
        level++;
        if (level >= Constants.ArmConstants.armExtensions.values().length)
            level = 1;
        ActiveMotionValues.setBackboardLevel(level);
        Constants.ArmConstants.armExtensions entry = Constants.ArmConstants.armExtensions.values()[ActiveMotionValues.getBackboardLevel()];



    }


    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
