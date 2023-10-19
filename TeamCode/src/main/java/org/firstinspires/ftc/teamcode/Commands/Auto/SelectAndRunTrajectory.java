package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunBBCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunBBLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunSDCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunSDLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunTrussCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunTrussLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class SelectAndRunTrajectory extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem claw;
    boolean bbstart = false;
    boolean useSD = false;
    int lcr = 0;

    public SelectAndRunTrajectory(Drive_Subsystem drive, PixelHandlerSubsystem claw) {
        this.drive = drive;
        this.claw = claw;

    }

    @Override
    public void initialize() {
        bbstart = ActiveMotionValues.getBBStart();
        useSD = ActiveMotionValues.getUseStageDoor();
        lcr = ActiveMotionValues.getLcrpos();
    }

    @Override
    public void execute() {

        if (bbstart) {
            if (lcr == 2) new RunBBCenterTraj(drive, claw).schedule();
            if (lcr == 1 || lcr == 3) new RunBBLRTraj(drive, claw).schedule();
        }

        if (!bbstart && !useSD) {
            if (lcr == 2) new RunTrussCenterTraj(drive, claw).schedule();
            if (lcr == 1 || lcr == 3) new RunTrussLRTraj(drive, claw).schedule();
        }


        if (!bbstart && useSD) {
            if (lcr == 2) new RunSDCenterTraj(drive, claw).schedule();
            if (lcr == 1 || lcr == 3) new RunSDLRTraj(drive, claw).schedule();
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
