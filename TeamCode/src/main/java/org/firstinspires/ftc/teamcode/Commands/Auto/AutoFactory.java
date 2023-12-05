package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Drive.MoveToPark;
import org.firstinspires.ftc.teamcode.Commands.Drive.RunToBackboard;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.LogTrajectory;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunTrajSequence;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.SelectAndBuildTrajectory;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.ShowTrajectoryInfo;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.DoNothing;
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

    boolean doLogging = true;


    public AutoFactory(CommandOpMode opmode, Drive_Subsystem drive,
                       PixelHandlerSubsystem phss, ArmSubsystem arm, Vision_Subsystem vss) {
        this.opMode = opmode;
        this.drive = drive;
        this.phss = phss;
        this.arm = arm;
        this.vss = vss;

    }

    public Command getAAS() {
        return new AutoActionsSequences(opMode, drive, phss, arm, vss, this);
    }

    public Command getAllianceData(CommandOpMode opmode, boolean red) {
        return new ConditionalCommand(new SelectMotionValuesRed(opMode), new SelectMotionValuesBlue(opmode), () -> red);
    }

    public Command getMoveToPark() {
        return new ConditionalCommand(new MoveToPark(drive),
                new DoNothing(),
                () -> (ActiveMotionValues.getNearPark()
                        || ActiveMotionValues.getCenterPark()));
    }

    public Command buildAndRunTrajectory() {

        return new SequentialCommandGroup(
                new SelectAndBuildTrajectory(opMode, drive, phss),
                new ShowTrajectoryInfo(drive, opMode),
                new ParallelRaceGroup(
                new RunTrajSequence(drive, opMode),
                        new LogTrajectory(drive,opMode)));
    }

    public Command detectAndMoveToAprilTag() {
        return new SequentialCommandGroup(
                new DetectAprilTags(opMode, vss, false),
                new RunToBackboard(drive, opMode));
    }

    public Command getTeamProp() {
        return new LookForTeamProp(opMode, false, vss);
    }

}