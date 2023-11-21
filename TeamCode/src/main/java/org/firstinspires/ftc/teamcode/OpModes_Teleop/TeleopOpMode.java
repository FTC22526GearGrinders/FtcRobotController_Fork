package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Arm.JogArm;
import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Commands.PixelHandler.TurnGrippersIncrementalCommand;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
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

    private int tst;

    @Override
    public void initialize() {


        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);

        drive = new Drive_Subsystem(this);


        phss = new PixelHandlerSubsystem(this);

        arm = new ArmSubsystem(this);

        vss = new Vision_Subsystem(this);

        dcatss = new DroneCatapultSubsystem(this);

        //DEFAULT COMMANDS

        drive.setDefaultCommand(new JogDrive(this.drive, gamepad1, false));

        // arm.setDefaultCommand(new HoldArmAtPosition(this.arm));

        if (ActiveMotionValues.getRedAlliance())
            ActiveMotionValues.setBaseTag(4);
        else ActiveMotionValues.setBaseTag(1);


/**
 * Driver gamepad assignmnents
 * */

        TriggerReader drlt = new TriggerReader(
                driver, GamepadKeys.Trigger.LEFT_TRIGGER);

        TriggerReader drrt = new TriggerReader(
                driver, GamepadKeys.Trigger.RIGHT_TRIGGER);

        // example usage if(drrt.wasJustPressed())new IncrementPixelDeliveryLevel().schedule();

        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new InstantCommand(() -> drive.drive.toggleFieldCentric()));

        //       driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(

        //       driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(

        //      driver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(


        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileHeld(
                new JogArm(arm, gamepad2));

        //driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(


        //driver.getGamepadButton(GamepadKeys.Button.START).whenPressed(


        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new TurnGrippersIncrementalCommand(phss, true, Constants.TurnGripperJogSet.HI));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new TurnGrippersIncrementalCommand(phss, false, Constants.TurnGripperJogSet.HI));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new InstantCommand(() -> phss.turnGrippersToDeliver()));


        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(() -> phss.turnGrippersToPickup()));


        // driver.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(


        // driver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
        //
        // driver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)


/**
 * co driver gamepad assignmnents
 * */
        TriggerReader cdlt = new TriggerReader(
                coDriver, GamepadKeys.Trigger.LEFT_TRIGGER);

        TriggerReader cdrt = new TriggerReader(
                coDriver, GamepadKeys.Trigger.RIGHT_TRIGGER);

        // example usage if(drrt.wasJustPressed())new IncrementPixelDeliveryLevel().schedule();

        //codriver.getGamepadButton(GamepadKeys.Button.A).whenPressed(

        //       codriver.getGamepadButton(GamepadKeys.Button.B).whenPressed(

        //       codriver.getGamepadButton(GamepadKeys.Button.X).whenPressed(

        //      codriver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(


        //       codriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(

        //codriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(


        //codriver.getGamepadButton(GamepadKeys.Button.START).whenPressed(


        //codriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(

        // codriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(


//        codriver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new JogArm(arm, true));

//        codriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new JogArm(arm, false));

//        codriver.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(


        // codriver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
        //
        // codriver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)

    }


    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

//        CommandScheduler.getInstance().run();

        while (!isStopRequested() && opModeIsActive()) {

            run();

            showTelemetry();

        }
        reset();

    }


    public void showTelemetry() {
//
//telemetry.addData("TST",tst++);
//telemetry.update();
        poseEstimate = drive.drive.getPoseEstimate();

        // drive.drive.showTelemetry(telemetry);

        //   drive.showtelemetry(telemetry);

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