package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;
import org.openftc.easyopencv.OpenCvWebcam;


public class RunAuto extends SequentialCommandGroup {

    public RunAuto(CommandOpMode opMode, Drive_Subsystem drive, OpenCvWebcam webcam) {


        boolean redAlliance = ActiveMotionValues.getRedAlliance();
        boolean bbStart = ActiveMotionValues.getBBStart();

        addCommands(


                new LookForTeamProp(opMode, webcam),//reads team element position and sets LCR value. Red blue choice made internally

                new ConditionalCommand(new SelectMotionValuesRed(bbStart, ActiveMotionValues.getLcrpos()),

                        new SelectMotionValuesBlue(bbStart, ActiveMotionValues.getLcrpos()), () -> redAlliance),//takes alliance and LCR and sets up data

                new PickAndRunTrajectories(drive),//takes alliance and lcr picks and runs trajectory

                //  new GetTargetAprilTagPose(opMode, vss),

                new CalculateMoveToBackboard(),

                new DeliverPixelCommand()


        );
        addRequirements(drive);
    }
}

