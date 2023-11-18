package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.RunBBCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.RunBBLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor.RunTrussSDCenterTape;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor.RunTrussSDLRTape;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class SelectAndRunTrajectory extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;

    private CommandOpMode opmode;
    boolean bbstart = false;
    boolean useSD = false;

    boolean secondPixel = false;
    int lcr = 0;

    public SelectAndRunTrajectory(CommandOpMode opMode,Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;
        this.opmode=opMode;

    }

    @Override
    public void initialize() {
        bbstart = ActiveMotionValues.getBBStart();
        useSD = ActiveMotionValues.getUseStageDoor();
        lcr = ActiveMotionValues.getLcrpos();
        secondPixel = ActiveMotionValues.getSecondPixel();
        opmode.telemetry.addData("SARTinit","");
        opmode.telemetry.update();
    }

    @Override
    public void execute() {


        if (bbstart) {


            if (lcr == 2) new RunBBCenterTraj(drive, phss).withTimeout(5000).schedule();

            if (lcr == 1 || lcr == 3)

                new RunBBLRTraj(drive, phss).withTimeout(5000).schedule();


        }

        if (!bbstart) {
            if (lcr == 1 || lcr == 3)
                new RunTrussSDLRTape(drive, phss).withTimeout(5000).schedule();
            if(lcr==2)
                new RunTrussSDCenterTape(drive,phss).withTimeout(5000).schedule();
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
