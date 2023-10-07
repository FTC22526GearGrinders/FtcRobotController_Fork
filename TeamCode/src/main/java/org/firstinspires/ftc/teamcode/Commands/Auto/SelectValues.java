package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;

public class SelectValues extends SequentialCommandGroup {
    public SelectValues() {

        int lcr = ActiveMotionValues.getLcrpos();
        boolean redAlliance= ActiveMotionValues.getRedAlliance();
        boolean bbStart= ActiveMotionValues.getBBStart();

     addCommands(   new SequentialCommandGroup(


                new ConditionalCommand(

                        new SelectMotionValuesRed(bbStart, lcr), new SelectMotionValuesBlue(bbStart, lcr), () -> redAlliance)));

    }


}