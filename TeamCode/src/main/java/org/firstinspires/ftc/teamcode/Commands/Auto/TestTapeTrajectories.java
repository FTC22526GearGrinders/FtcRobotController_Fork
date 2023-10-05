package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class TestTapeTrajectories extends SequentialCommandGroup {
    private CommandOpMode myOpMode;

    public TestTapeTrajectories(CommandOpMode
                                        opMode, Drive_Subsystem drive) {
        myOpMode = opMode;

        boolean redAlliance = ActiveMotionValues.getRedAlliance();

        boolean bbStart = ActiveMotionValues.getBBStart();

        ActiveMotionValues.setLcrpos(2);



        addCommands(


                new ConditionalCommand(new SelectMotionValuesRed(),

                        new SelectMotionValuesBlue(), () -> redAlliance),//takes alliance and LCR and sets up data


                new PickAndRunTrajectories(drive)//takes alliance and lcr picks and runs trajectory


        );
        addRequirements(drive);
    }
}

