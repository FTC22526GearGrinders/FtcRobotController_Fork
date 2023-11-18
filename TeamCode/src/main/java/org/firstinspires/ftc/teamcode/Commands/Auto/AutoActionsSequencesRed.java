package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.CV.StageSwitchingPipeline;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.openftc.easyopencv.OpenCvWebcam;

public class AutoActionsSequencesRed extends SequentialCommandGroup {

    public AutoActionsSequencesRed(CommandOpMode opMode, Drive_Subsystem drive, PixelHandlerSubsystem phss,
                                   ArmSubsystem arm, Vision_Subsystem vss, StageSwitchingPipeline sptop, OpenCvWebcam webcam) {
        ActiveMotionValues.setBackboardLevel(1);
        addCommands(

                new SequentialCommandGroup(

                        new LookForTeamProp(opMode, webcam, sptop, false, vss),

                        new SelectMotionValuesRed(),

                     //  new InstantCommand(() -> opMode.telemetry.addData("BB", ActiveMotionValues.getBBStart())),

                      //  new InstantCommand(() -> opMode.telemetry.addData("RA", ActiveMotionValues.getRedAlliance())),


                        new InstantCommand(() -> opMode.telemetry.addData("SP",String.valueOf(ActiveMotionValues.getLcrpos())+ ActiveMotionValues.getStartPose().toString())),

                        new InstantCommand(() -> opMode.telemetry.update())));


        //     new SelectAndRunTrajectory(opMode, drive, phss).withTimeout(10000)

//                        new ConditionalCommand(
//
//                                new SequentialCommandGroup(
//
//                                        new DetectAprilTags(opMode, vss, false),
//
//                                        new RunToAprilTag(drive, opMode),
//
//                                        new PositionPHArm(arm, Constants.ArmConstants.armExtensions.LOW.extension, .5),
//
//                                        new InstantCommand(() -> phss.openClaw()),
//
//                                        new TimeDelay(.5),
//
//                                        new ParallelCommandGroup(
//
//                                                new InstantCommand(() -> phss.closeClaw()),
//
//                                                new InstantCommand(() -> phss.retractClawArm())),
//
//                                        new PositionPHArmToPreset(arm, Constants.ArmConstants.armExtensions.HOME.extension),
//
//                                        new ConditionalCommand(new MoveToPark(drive), new DoNothing(),
//                                                () -> (ActiveMotionValues.getNearPark() || ActiveMotionValues.getCenterPark()))),
//
//
//                                new DoNothing(), () -> ActiveMotionValues.getBBStart()
//
//                                || !ActiveMotionValues.getBBStart() && ActiveMotionValues.getSecondPixel())))
        ;


    }
}
