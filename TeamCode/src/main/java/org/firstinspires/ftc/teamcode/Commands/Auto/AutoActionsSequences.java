package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.Arm.PositionArm;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.DoNothing;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

public class AutoActionsSequences extends SequentialCommandGroup {

    public AutoActionsSequences(CommandOpMode opMode, Drive_Subsystem drive, PixelHandlerSubsystem phss,
                                ArmSubsystem arm, Vision_Subsystem vss, AutoFactory af) {

        boolean red = ActiveMotionValues.getRedAlliance();

        addCommands(

                new SequentialCommandGroup(

                        new LookForTeamProp(opMode, false, vss),

                        af.getAllianceData(opMode, red),

                        af.buildAndRunTrajectory(),

                        new ConditionalCommand(

                                new SequentialCommandGroup(

                                        af.detectAndMoveToAprilTag(),

                                        new ParallelCommandGroup(

                                                new PositionArm(arm, Constants.ArmConstants.DELIVER_POSITION),

                                                new InstantCommand(() -> phss.turnGrippersToDeliver())),

                                        new WaitCommand(500),

                                        new InstantCommand(() -> phss.openBothGrippers()),

                                        new WaitCommand(500),

                                        new ParallelCommandGroup(

                                                new InstantCommand(() -> phss.closeBothGrippers()),

                                                new PositionArm(arm, Constants.ArmConstants.HOME_POSITION),

                                                af.getMoveToPark())),

                                new DoNothing(),

                                () -> ActiveMotionValues.getBBStart() || !ActiveMotionValues.getBBStart() && ActiveMotionValues.getSecondPixel())))
        ;


    }
}
