package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Arm.JogArm;
import org.firstinspires.ftc.teamcode.Commands.Arm.PositionArm;
import org.firstinspires.ftc.teamcode.Commands.Climber.JogClimber;
import org.firstinspires.ftc.teamcode.Commands.Climber.PositionHoldClimber;
import org.firstinspires.ftc.teamcode.Commands.Drive.CancelJog2;
import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive2;
import org.firstinspires.ftc.teamcode.Commands.Drive.PositionToBackboard;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimberSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DroneCatapultSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;

    protected PixelHandlerSubsystem phss;

    protected ArmSubsystem arm;

    protected ClimberSubsystem climber;
    Pose2d poseEstimate;
    GamepadEx driver;
    GamepadEx coDriver;
    private DroneCatapultSubsystem dcatss;


    int ctr;

    private int teleSwitch;

    TriggerReader drlt;
    TriggerReader drrt;

    TriggerReader cdlt;
    TriggerReader cdrt;


    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);

        drive = new Drive_Subsystem(this);

        phss = new PixelHandlerSubsystem(this);

        arm = new ArmSubsystem(this);

        climber = new ClimberSubsystem(this);


        dcatss = new DroneCatapultSubsystem(this);

        //DEFAULT COMMANDS

        drive.setDefaultCommand(new JogDrive(this.drive, driver));

        climber.setDefaultCommand(new PositionHoldClimber(climber));


/**
 * Driver gamepad assignmnents
 * */

        drlt = new TriggerReader(
                driver, GamepadKeys.Trigger.LEFT_TRIGGER);

        drrt = new TriggerReader(
                driver, GamepadKeys.Trigger.RIGHT_TRIGGER);


        driver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setTargetInches(15)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));



        driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setTargetInches(8)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));


        driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> arm.setArmDeliverLevel(0)));


        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> phss.flipGrippersToPickup()),
                        new WaitCommand(100),
                        new InstantCommand(() -> phss.lowerGrippersToPickup()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> arm.setTargetInches(0.8))));


        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(() -> phss.openLeftGripper()));

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new InstantCommand(() -> phss.openRightGripper()));

        driver.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new InstantCommand(() -> drive.drive.toggleFieldCentric()));

        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whileHeld(new PositionToBackboard(drive));


        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(new InstantCommand(() -> phss.flipGrippersToDeliver()));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(new InstantCommand(() -> climber.climberToLiftPosition()));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new JogDrive2(drive,driver))
                        .whenPressed(new InstantCommand(()->phss.toWideOpen()));

        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(() -> dcatss.releaseCatapult()));


        driver.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(new CancelJog2(drive));


        // driver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
        //
        // driver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)


/**
 * co driver gamepad assignmnents
 * */
        cdlt = new TriggerReader(
                coDriver, GamepadKeys.Trigger.LEFT_TRIGGER);

        cdrt = new TriggerReader(
                coDriver, GamepadKeys.Trigger.RIGHT_TRIGGER);


        coDriver.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setArmDeliverLevel(10)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));


        coDriver.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> arm.setArmDeliverLevel(0)));


        coDriver.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setArmDeliverLevel(0)),
                        new InstantCommand(() -> phss.flipGrippersToPickup()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.lowerGrippersToPickup())));

        coDriver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setArmDeliverLevel(20)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));

        // coDriver.getGamepadButton(GamepadKeys.Button.A).whenPressed(


//        coDriver.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> climber.climberToClearBar()));
//
//        coDriver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(() -> climber.climberToLiftPosition()));


        // coDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(


        coDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new JogDrive2(drive, coDriver));


        coDriver.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(new CancelJog2(drive));


        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(new InstantCommand(() -> teleSwitch++));

        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whileHeld(new PositionToBackboard(drive));


        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new JogClimber(climber, coDriver))
                .whenPressed(new CancelJog2(drive));

        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new JogArm(arm, coDriver))
                .whenPressed(new CancelJog2(drive));

        coDriver.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new InstantCommand(() -> drive.drive.toggleFieldCentric()));

        coDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new InstantCommand(() -> dcatss.releaseCatapult()));
        // coDriver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
        //
        // coDriver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)

    }


    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

            run();

            checkTriggers();

            showTelemetry();


        }
        reset();

    }

    void checkTriggers() {

        drlt.readValue();
        drrt.readValue();
        cdlt.readValue();
        cdrt.readValue();

        if (drlt.wasJustPressed()) new InstantCommand(() -> phss.closeLeftGripper()).schedule();

        if (drrt.wasJustPressed())  new InstantCommand(() -> phss.closeRightGripper()).schedule();

       if(drlt.isDown()&&drrt.isDown())new SequentialCommandGroup(new CancelJog2(drive),
               new WaitCommand(1000),
               new InstantCommand(()->arm.setTargetInches(.8))).schedule();


        telemetry.update();
    }


    public void showTelemetry() {

        poseEstimate = drive.drive.getPoseEstimate();
        if (teleSwitch > 4) teleSwitch = 0;
        if (teleSwitch < 0) teleSwitch = 4;


        switch (teleSwitch) {

            case 0:
                arm.showTelemetry(telemetry);
                break;
            case 1:
                drive.showtelemetry(telemetry);
                break;
            case 2:
                drive.drive.showTelemetry(telemetry);
                break;
            case 3:
                phss.showTelemetry(telemetry);
                break;
            case 4:
                climber.showTelemetry(telemetry);
                break;
            default:
                break;

        }
    }
}

