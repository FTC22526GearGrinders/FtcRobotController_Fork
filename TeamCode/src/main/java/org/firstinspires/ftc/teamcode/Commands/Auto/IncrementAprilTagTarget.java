package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;


public class IncrementAprilTagTarget extends CommandBase {

    public int number;
    boolean redAlliance;

    public IncrementAprilTagTarget() {

    }

    @Override
    public void initialize() {

        redAlliance = ActiveMotionValues.getRedAlliance();

        number = ActiveMotionValues.getActTag();
        number++;
        if (redAlliance) {
            if (number > 6) number = 4;
        } else {
            if (number > 3) number = 1;
        }
        ActiveMotionValues.setActTag(number);
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
