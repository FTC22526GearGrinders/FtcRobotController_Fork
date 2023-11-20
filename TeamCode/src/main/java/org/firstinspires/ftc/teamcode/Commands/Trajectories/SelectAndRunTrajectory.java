package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.BuildBBCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.BuildBBLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor.BuildTrussSDCenterTape;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor.BuildTrussSDLRTape;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.DoNothing;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class SelectAndRunTrajectory extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;

    private final CommandOpMode opmode;
    boolean bbstart = false;
    boolean useSD = false;

    boolean secondPixel = false;
    int lcr = 0;

    boolean logTrajectory = true;

    public SelectAndRunTrajectory(CommandOpMode opMode, Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;
        this.opmode = opMode;

    }

    @Override
    public void initialize() {
        bbstart = ActiveMotionValues.getBBStart();
        useSD = ActiveMotionValues.getUseStageDoor();
        lcr = ActiveMotionValues.getLcrpos();
        secondPixel = ActiveMotionValues.getSecondPixel();
        opmode.telemetry.addData("SARTinit", "");
        opmode.telemetry.update();
    }

    @Override
    public void execute() {


        if (bbstart) {


            if (lcr == 2)
                new SequentialCommandGroup(
                        new BuildBBCenterTraj(drive, phss, opmode),
                        new ParallelCommandGroup(
                                new RunTrajSequence(drive, opmode),
                                new ConditionalCommand(
                                        new LogTrajectory(drive, opmode),
                                        new DoNothing(),
                                        () -> logTrajectory)));


            if (lcr == 1 || lcr == 3)
                new SequentialCommandGroup(
                        new BuildBBLRTraj(drive, phss),
                        new ParallelCommandGroup(
                                new RunTrajSequence(drive, opmode),
                                new ConditionalCommand(
                                        new LogTrajectory(drive, opmode),
                                        new DoNothing(),
                                        () -> logTrajectory)));
        }

        if (!bbstart) {
            if (lcr == 1 || lcr == 3)
                new SequentialCommandGroup(
                        new BuildTrussSDLRTape(drive, phss),
                        new ParallelCommandGroup(
                                new RunTrajSequence(drive, opmode),
                                new ConditionalCommand(
                                        new LogTrajectory(drive, opmode),
                                        new DoNothing(),
                                        () -> logTrajectory)));

            if (lcr == 2)
                new SequentialCommandGroup(
                        new BuildTrussSDCenterTape(drive, phss),
                        new ParallelCommandGroup(
                                new RunTrajSequence(drive, opmode),
                                new ConditionalCommand(
                                        new LogTrajectory(drive, opmode),
                                        new DoNothing(),
                                        () -> logTrajectory)));
        }

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
