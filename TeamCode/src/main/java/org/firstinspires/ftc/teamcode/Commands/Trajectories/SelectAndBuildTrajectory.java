package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.BuildBBCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.BuildBBLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor.BuildTrussSDCenterTape;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss_StageDoor.BuildTrussSDLRTape;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class SelectAndBuildTrajectory extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;

    private ElapsedTime runTime;

    private final CommandOpMode opmode;
    boolean bbstart = false;
    boolean useSD = false;

    boolean found = false;

    boolean secondPixel = false;
    int lcr = 0;

    boolean logTrajectory = false;

    public SelectAndBuildTrajectory(CommandOpMode opMode, Drive_Subsystem drive, PixelHandlerSubsystem phss) {
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
        drive.trajectoryBuilding = false;
        runTime = new ElapsedTime();
        opmode.telemetry.addData("SABTinit", "");
        opmode.telemetry.update();
    }

    @Override
    public void execute() {

        found = false;
        if (bbstart && lcr == 2) {
            drive.runningTrajName = "BB Center";
            new BuildBBCenterTraj(drive, phss, opmode).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }


        if (bbstart && (lcr == 1 || lcr == 3)) {
            drive.runningTrajName = "BB LR";
            new BuildBBLRTraj(drive, phss,opmode).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }


        if (!bbstart && (lcr == 1 || lcr == 3)) {
            drive.runningTrajName = "Not BB Center";
            new BuildTrussSDLRTape(drive, phss).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }

        if (!bbstart && lcr == 2) {
            drive.runningTrajName = "Not BB LR";
            new BuildTrussSDCenterTape(drive, phss).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }

    }

    @Override
    public void end(boolean interrupted) {
        opmode.telemetry.addData("Selected", drive.runningTrajName);
        opmode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return found;
    }
}
