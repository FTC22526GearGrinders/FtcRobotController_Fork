package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

public class AutoFactory extends CommandBase {
    Drive_Subsystem drive;
    PixelHandlerSubsystem phss;

    ArmSubsystem arm;

    CommandOpMode opMode;


    Vision_Subsystem vss;


    public AutoFactory(CommandOpMode opmode, Drive_Subsystem drive,
                       PixelHandlerSubsystem phss, ArmSubsystem arm, Vision_Subsystem vss) {
        this.opMode = opmode;
        this.drive = drive;
        this.phss = phss;
        this.arm = arm;
        this.vss = vss;

    }

    public Command getAASRed() {
        return new AutoActionsSequencesRed(opMode, drive, phss, arm, vss);
    }


    public Command getAASBlue() {
        return new AutoActionsSequencesBlue(opMode, drive, phss, arm,vss);
    }


}