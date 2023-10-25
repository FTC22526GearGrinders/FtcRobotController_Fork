package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.RunBBCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.RunBBLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor.RunSDCenter2PixTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor.RunSDCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor.RunSDLR2PixTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor.RunSDLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss.RunTrussCenter2PixTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss.RunTrussCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss.RunTrussLR2PixTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss.RunTrussLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class SelectAndRunTrajectory extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;
    boolean bbstart = false;
    boolean useSD = false;

    boolean secondPixel = false;
    int lcr = 0;

    public SelectAndRunTrajectory(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;

    }

    @Override
    public void initialize() {
        bbstart = ActiveMotionValues.getBBStart();
        useSD = ActiveMotionValues.getUseStageDoor();
        lcr = ActiveMotionValues.getLcrpos();
        secondPixel = ActiveMotionValues.getSecondPixel();
    }

    @Override
    public void execute() {

        if (bbstart) {
            if (lcr == 2) new RunBBCenterTraj(drive, phss).schedule();

            if (lcr == 1 || lcr == 3)
                new RunBBLRTraj(drive, phss).schedule();
        }

        if (!bbstart && !useSD) {
            if (!secondPixel) {
                if (lcr == 1 || lcr == 3) new RunTrussLRTraj(drive, phss).schedule();
                if (lcr == 2) new RunTrussCenterTraj(drive, phss).schedule();
            } else {
                if (lcr == 1 || lcr == 3) new RunTrussLR2PixTraj(drive, phss).schedule();
                if (lcr == 2) new RunTrussCenter2PixTraj(drive, phss).schedule();
            }
        }

        if (!bbstart && useSD) {
            if (!secondPixel) {
                if (lcr == 1 || lcr == 3) new RunSDLRTraj(drive, phss).schedule();
                if (lcr == 2) new RunSDCenterTraj(drive, phss).schedule();
            } else {
                if (lcr == 1 || lcr == 3) new RunSDLR2PixTraj(drive, phss).schedule();
                if (lcr == 2) new RunSDCenter2PixTraj(drive, phss).schedule();
            }
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
