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

                        af.closeGrippers(),

                        af.raiseArmOffFloor(),

                        af.getTeamProp(),

                        af.getAllianceData(opMode, red),

                        af.buildAndRunTrajectory(),


                        new ConditionalCommand(

                                new SequentialCommandGroup(


                                        af.raiseArmToPosition(),


                                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),

                                        new InstantCommand(() -> phss.flipGrippersToDeliver()),

                                        new WaitCommand(500),
                                        new InstantCommand(() -> phss.flipGrippersToLeftDown()),
                                        af.trajToBackboardSimple(),



                                        new WaitCommand(500),

                                        new InstantCommand(phss::openLeftGripper),

                                        new WaitCommand(500),

                                        new InstantCommand(() -> phss.flipGrippersToPickup()),
                                        new WaitCommand(500),

                                        new InstantCommand(() -> phss.lowerGrippersToPickup()),

                                        new WaitCommand(500),

                                        af.positionArmHome()),


                                new DoNothing(),

                                () -> ActiveMotionValues.getBBStart() || !ActiveMotionValues.getBBStart() && ActiveMotionValues.getSecondPixel())));


    }
}
