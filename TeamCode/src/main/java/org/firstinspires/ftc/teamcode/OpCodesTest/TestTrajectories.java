package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Auto.DetectAprilTags;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectMotionValuesRed;
import org.firstinspires.ftc.teamcode.Commands.Drive.TrajectoryToBackboard;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunTrajSequence;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.SelectAndBuildTrajectory;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.ShowTrajectoryInfo;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.DoNothing;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

@Config
@TeleOp(name = "Teleop Main Test Traj Red", group = "Test")
@Disabled
public class TestTrajectories extends CommandOpMode {
    boolean redAlliance = true;
    boolean backBoardStart = true;
    boolean useStageDoor;
    boolean centerPark;
    boolean secondPixel;
    int lcr = 2;
    Telemetry telemetry;
    Drive_Subsystem drive;
    PixelHandlerSubsystem phss;
    Vision_Subsystem vss;

    @Override
    public void initialize() {

        drive = new Drive_Subsystem(this);

        phss = new PixelHandlerSubsystem(this);

        vss = new Vision_Subsystem(this);

        redAlliance = ActiveMotionValues.getRedAlliance();

        backBoardStart = ActiveMotionValues.getBBStart();

        useStageDoor = ActiveMotionValues.getUseStageDoor();

        centerPark = ActiveMotionValues.getCenterPark();

        secondPixel = ActiveMotionValues.getSecondPixel();

        lcr = ActiveMotionValues.getLcrpos();

        new SequentialCommandGroup(
                new SelectMotionValuesRed(this),
                new SelectAndBuildTrajectory(this, drive, phss),
                new ShowTrajectoryInfo(drive, this),
                new RunTrajSequence(drive, this),
                new DetectAprilTags(this, vss, false),
                new ConditionalCommand(
                        new TrajectoryToBackboard(drive, this),
                        new DoNothing(),
                        () -> ActiveMotionValues.getAprilTagSeen())).schedule();
    }

    // Put run blocks here.
    public void run() {

        CommandScheduler.getInstance().run();

        drive.showtelemetry(this.telemetry);
    }
}
