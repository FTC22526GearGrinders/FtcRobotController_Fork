package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;

public class SelectValues extends SequentialCommandGroup {


    public SelectValues() {

        int lcr = ActiveMotionValues.getLcrpos();
        if (lcr > 3 || lcr <= 0) lcr = 2;
        ActiveMotionValues.setLcrpos(lcr);
        boolean redAlliance = ActiveMotionValues.getRedAlliance();

        addCommands(new SequentialCommandGroup(


                new ConditionalCommand(

                        new SelectMotionValuesRed(), new SelectMotionValuesBlue(), () -> redAlliance)));

    }


}