package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.openftc.easyopencv.OpenCvWebcam;

public class AutoFactory extends CommandBase {
    Drive_Subsystem drive;
    PixelHandlerSubsystem phss;

    ArmSubsystem arm;

    CommandOpMode opMode;

    OpenCvWebcam webcam;

    StageSwitchingPipeline sptop;

    Vision_Subsystem vss;
    boolean redAlliance = ActiveMotionValues.getRedAlliance();

    public AutoFactory(CommandOpMode opmode, OpenCvWebcam webcam, StageSwitchingPipeline sptop, Drive_Subsystem drive,
                       PixelHandlerSubsystem phss, ArmSubsystem arm, Vision_Subsystem vss) {
        this.opMode = opmode;
        this.drive = drive;
        this.phss = phss;
        this.arm = arm;
        this.webcam = webcam;
        this.sptop = sptop;
        this.vss = vss;


    }

    public Command getAASRed() {

        return new AutoActionsSequencesRed(opMode, drive, phss, arm, vss, sptop, webcam);
    }
    public Command getAASBlue() {

        return new AutoActionsSequencesBlue(opMode, drive, phss, arm, vss, sptop, webcam);
    }

}