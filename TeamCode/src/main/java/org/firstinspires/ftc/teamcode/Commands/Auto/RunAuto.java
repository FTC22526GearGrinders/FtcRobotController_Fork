package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;
import org.openftc.easyopencv.OpenCvWebcam;


public class RunAuto extends SequentialCommandGroup {

    public RunAuto(CommandOpMode opMode, Drive_Subsystem drive, OpenCvWebcam webcam) {


        addCommands(



                new LookForTeamProp(opMode, webcam),//reads team element position and sets LCR value. Red blue choice made internally

                new SelectValues(),

                new PickAndRunTrajectories(drive),//takes alliance and lcr picks and runs trajectory

                new CalculateMoveToBackboard(),

                new DeliverPixelCommand()


        );
        addRequirements(drive);
    }
}

