package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Commands.PixelHandler.JogArm;
import org.firstinspires.ftc.teamcode.Commands.PixelHandler.PositionPHArm;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;

   // protected Vision_Subsystem visionSubsystem;

    protected PixelHandlerSubsystem phss;


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


        ///register(drive, phss, visionSubsystem);

        drive.setDefaultCommand(new JogDrive(this.drive, gamepad1, false));

//        gamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(
//
//                new DriveToAprilTagTeleop(drive, visionSubsystem, this, gamepad1));


        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(phss::openClaw);

        driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(phss::closeClaw);



        driver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(phss::dropPixel);

        driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(phss::holdPixel);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(drive.drive::toggleFieldCentric);


        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new JogArm(phss,.25));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new JogArm(phss,.25));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(new PositionPHArm(phss,500,.1));

        driver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON).whenPressed(new PositionPHArm(phss,1000,.1));

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON).whenPressed(new PositionPHArm(phss,0,.1));



    }


    public void run() {


        telemetry.update();

        CommandScheduler.getInstance().run();


         // drive.drive.showTelemetry(telemetry);

       // drive.showtelemetry(telemetry);

        phss.showTelemetry(telemetry);
    }


}