package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.openftc.easyopencv.OpenCvWebcam;


public class RunAuto extends SequentialCommandGroup {

    public RunAuto(CommandOpMode opMode, Drive_Subsystem drive, Claw_Subsystem clawSubsystem, OpenCvWebcam webcam, Vision_Subsystem visionSubsystem) {


        addCommands(


                new LookForTeamProp(opMode, webcam),//reads team element position and sets LCR value. Red blue choice made internally

                new SelectValues(),

                new PickAndRunTrajectories(drive,clawSubsystem),//takes alliance and lcr picks and runs trajectory

                new DriveToAprilTagAuto(drive, visionSubsystem),

                new InstantCommand(clawSubsystem::open, clawSubsystem),

                new TrajToPark(drive)



        );
        addRequirements(drive,clawSubsystem);
    }
}

