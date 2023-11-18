package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Arm.JogArm;
import org.firstinspires.ftc.teamcode.Commands.Arm.PositionPHArm;
import org.firstinspires.ftc.teamcode.Commands.Arm.PositionPHArmToPreset;
import org.firstinspires.ftc.teamcode.Commands.Auto.DetectAprilTags;
import org.firstinspires.ftc.teamcode.Commands.Auto.IncrementAprilTagTarget;
import org.firstinspires.ftc.teamcode.Commands.Auto.IncrementPixelDeliveryLevel;
import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Commands.Drive.RunToAprilTag;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Commands.Utils.TimeDelay;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DroneCatapultSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;


    protected PixelHandlerSubsystem phss;

    protected ArmSubsystem arm;
    Pose2d poseEstimate;
    GamepadEx driver;
    GamepadEx coDriver;
    private DroneCatapultSubsystem dcatss;

    private Vision_Subsystem vss;

    @Override
    public void initialize() {


        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);


        //  FtcDashboard.getInstance().getTelemetry();

        drive = new Drive_Subsystem(this);


        phss = new PixelHandlerSubsystem(this);

        arm = new ArmSubsystem(this);

        vss= new Vision_Subsystem(this);

        dcatss = new DroneCatapultSubsystem(this);

        drive.setDefaultCommand(new JogDrive(this.drive, gamepad1, false));

        // arm.setDefaultCommand(new HoldArmAtPosition(this.arm));

        if (ActiveMotionValues.getRedAlliance())
            ActiveMotionValues.setBaseTag(4);
        else ActiveMotionValues.setBaseTag(1);


//        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)


        TriggerReader drlt = new TriggerReader(
                driver, GamepadKeys.Trigger.LEFT_TRIGGER);

        TriggerReader drrt = new TriggerReader(
                driver, GamepadKeys.Trigger.RIGHT_TRIGGER);

        // example usage if(drrt.wasJustPressed())new IncrementPixelDeliveryLevel().schedule();


        driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(new IncrementPixelDeliveryLevel());

        driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(new IncrementAprilTagTarget());

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(
                new SequentialCommandGroup(
                        new DetectAprilTags(this, vss,false),
                        new ParallelCommandGroup(
                                new RunToAprilTag(drive, this),
                                new PositionPHArmToPreset(arm, .5)),

                        new InstantCommand(() -> phss.openClaw()),
                        new TimeDelay(.5),
                        new InstantCommand(() -> phss.retractClawArm()),
                        new PositionPHArmToPreset(arm, Constants.ArmConstants.armExtensions.HOME.extension)));

        driver.getGamepadButton(GamepadKeys.Button.START).whenPressed(() -> ActiveMotionValues.setBackboardLevel(1))
                .whenPressed(() -> ActiveMotionValues.setActTag(ActiveMotionValues.getBaseTag()));


        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(drive.drive::toggleFieldCentric);

        //driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new JogArm(arm, true));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new JogArm(arm, false));

        driver.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(
                new PositionPHArm(arm, Constants.ArmConstants.armExtensions.HOME.extension, .5));

        // driver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
        //
        // driver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)


        //Codriver buttons

        TriggerReader cdlt = new TriggerReader(
                coDriver, GamepadKeys.Trigger.LEFT_TRIGGER);

        TriggerReader cdrt = new TriggerReader(
                coDriver, GamepadKeys.Trigger.RIGHT_TRIGGER);


        //  coDriver.getGamepadButton((GamepadKeys.Button.A))

        //  coDriver.getGamepadButton((GamepadKeys.Button.B))

        coDriver.getGamepadButton((GamepadKeys.Button.DPAD_LEFT)).whenPressed(phss::extendClawArm);

        coDriver.getGamepadButton((GamepadKeys.Button.DPAD_RIGHT)).whenPressed(phss::retractClawArml);


        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(phss::openClaw);


//ph
        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(phss::closeClaw);

        coDriver.getGamepadButton((GamepadKeys.Button.X)).whenPressed(new RunToAprilTag(drive, this));


    }


    public void run() {


        telemetry.update();

        CommandScheduler.getInstance().run();


        poseEstimate = drive.drive.getPoseEstimate();

        drive.drive.showTelemetry(telemetry);

        // drive.showtelemetry(telemetry);

        // arm.showTelemetry(telemetry);

        // phss.showTelemetry(telemetry);

//        telemetry.addData("Atag Num", ActiveMotionValues.getActTag());
//        telemetry.addLine();
//        telemetry.addData("Atag Base", ActiveMotionValues.getBackboardLevel());
//        Constants.ArmConstants.armExtensions entry = Constants.ArmConstants.armExtensions.values()[ActiveMotionValues.getBackboardLevel()];
//
//        double extension = entry.extension;
//        telemetry.addData("ArmTgtExten", extension);
//        telemetry.addLine();
//
//        telemetry.addData("TagDistance", Constants.DriveConstants.tagOffsetPose.toString());
//        telemetry.addLine();
//        telemetry.addData("SensorDistance", phss.getSensorDistanceInches());
//        telemetry.update();

    }

}