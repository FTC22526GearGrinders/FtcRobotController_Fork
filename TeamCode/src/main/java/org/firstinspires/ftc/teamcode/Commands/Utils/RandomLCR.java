package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.Random;


public class RandomLCR extends CommandBase {
    Random rand;
    int max = 3;
    int min = 1;

    int boolmax = 1;

    int boolMin = 0;

    public RandomLCR() {

    }

    @Override
    public void initialize() {
        rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        ActiveMotionValues.setLcrpos(randomNum);

        randomNum = rand.nextInt((boolmax - boolMin) + 1) + min;

        ActiveMotionValues.setBBStart(false);

        if (randomNum == 1) ActiveMotionValues.setBBStart(true);
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
