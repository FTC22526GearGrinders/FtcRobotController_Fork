package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Arm.PositionPHArm;
import org.firstinspires.ftc.teamcode.Commands.Drive.MoveToPark;
import org.firstinspires.ftc.teamcode.Commands.Drive.RunToAprilTag;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.RunTrajSequence;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.SelectAndBuildTrajectory;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.ShowTrajectoryInfo;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.DoNothing;
import org.firstinspires.ftc.teamcode.Commands.Utils.LogAutoSettings;
import org.firstinspires.ftc.teamcode.Commands.Utils.TimeDelay;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

public class AutoActionsSequencesBlue extends SequentialCommandGroup {

    public AutoActionsSequencesBlue(CommandOpMode opMode, Drive_Subsystem drive, PixelHandlerSubsystem phss,
                                    ArmSubsystem arm, Vision_Subsystem vss) {


        addCommands(

                new SequentialCommandGroup(

                        new LookForTeamProp(opMode, false, vss),

                        new SelectMotionValuesBlue(opMode),

                        new LogAutoSettings(opMode),

                        new SelectAndBuildTrajectory(opMode, drive, phss),

                        new ShowTrajectoryInfo(drive,opMode),

                        new RunTrajSequence(drive,opMode),

                        new ConditionalCommand(

                                new SequentialCommandGroup(

                                        new DetectAprilTags(opMode, vss, false),

                                        new RunToAprilTag(drive, opMode),

                                        new ParallelCommandGroup(

                                                new PositionPHArm(arm, Constants.ArmConstants.armPositionInches[1], .5),

                                                new InstantCommand(() -> phss.turnGrippersToDeliver())),

                                        new WaitCommand(500),

                                        new InstantCommand(() -> phss.openBothGrippers()),

                                        new TimeDelay(.5),

                                        new ParallelCommandGroup(

                                                new InstantCommand(() -> phss.closeBothGrippers()),

                                                new PositionPHArm(arm, Constants.ArmConstants.armPositionInches[0], .5),

                                                new ConditionalCommand(new MoveToPark(drive),
                                                        new DoNothing(),
                                                        () -> (ActiveMotionValues.getNearPark()
                                                                || ActiveMotionValues.getCenterPark())))),


                                new DoNothing(),

                                () -> ActiveMotionValues.getBBStart() || !ActiveMotionValues.getBBStart() && ActiveMotionValues.getSecondPixel())));


    }
}
