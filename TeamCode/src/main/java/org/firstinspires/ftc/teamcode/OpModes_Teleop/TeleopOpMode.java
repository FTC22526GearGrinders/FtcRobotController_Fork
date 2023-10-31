package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Commands.PixelHandler.HoldArmAtPosition;
import org.firstinspires.ftc.teamcode.Commands.PixelHandler.IterateExtendArnServo;
import org.firstinspires.ftc.teamcode.Commands.PixelHandler.JogArm;
import org.firstinspires.ftc.teamcode.Commands.PixelHandler.PositionPHArm;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;

    // protected Vision_Subsystem visionSubsystem;

    protected PixelHandlerSubsystem phss;

    protected ArmSubsystem arm;

    //   protected StandardTrackingWheelLocalizer teleLocalizer;

    GamepadEx driver;
    GamepadEx coDriver;


    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);


        //  FtcDashboard.getInstance().getTelemetry();

        drive = new Drive_Subsystem(this);

        //  visionSubsystem = new Vision_Subsystem(this);

        phss = new PixelHandlerSubsystem(this);

        arm = new ArmSubsystem(this);

//        teleLocalizer= new StandardTrackingWheelLocalizer(this.hardwareMap);
//
//        teleLocalizer.setPoseEstimate(ActiveMotionValues.getAutoRobotPose());

        drive.setDefaultCommand(new JogDrive(this.drive, gamepad1, false));

        arm.setDefaultCommand(new HoldArmAtPosition(this.arm));


//        gamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(
//
//                new DriveToAprilTagTeleop(drive, visionSubsystem, this, gamepad1));


        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(phss::openClaw);

        driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(phss::closeClaw);


        driver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(phss::dropPixel);

        driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(phss::holdPixel);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(drive.drive::toggleFieldCentric);


        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new JogArm(arm, true));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new JogArm(arm, false));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(new PositionPHArm(arm, Constants.ArmConstants.armHeights.HOME.height, .1));

        driver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON).whenPressed(new PositionPHArm(arm, Constants.ArmConstants.armHeights.MID.height, .1));


        driver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON).whenPressed(new PositionPHArm(arm, Constants.ArmConstants.armHeights.HIGH.height, .1));

        //Codriver buttons

        coDriver.getGamepadButton((GamepadKeys.Button.A)).whenHeld(new IterateExtendArnServo(phss, true));
        coDriver.getGamepadButton((GamepadKeys.Button.B)).whenHeld(new IterateExtendArnServo(phss, false));


    }


    public void run() {


        telemetry.update();

        CommandScheduler.getInstance().run();

//        teleLocalizer.update();
//
//        Pose2d currentPose = teleLocalizer.getPoseEstimate();


       // drive.drive.showTelemetry(telemetry);

        // drive.showtelemetry(telemetry);

//        arm.showTelemetry(telemetry);

        phss.showTelemetry(telemetry);
    }


}