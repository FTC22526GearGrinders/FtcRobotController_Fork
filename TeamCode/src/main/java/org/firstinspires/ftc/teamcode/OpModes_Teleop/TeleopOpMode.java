package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;

    protected Vision_Subsystem visionSubsystem;

    protected Claw_Subsystem claw;


    GamepadEx gamepad;


    @Override
    public void initialize() {

        gamepad = new GamepadEx(gamepad1);

        //  FtcDashboard.getInstance().getTelemetry();

        drive = new Drive_Subsystem(this);

        visionSubsystem = new Vision_Subsystem(this);

        claw = new Claw_Subsystem(this);


        register(drive, claw, visionSubsystem);

        drive.setDefaultCommand(new JogDrive(this.drive, gamepad1, false));

        gamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(

                new DriveToAprilTagTeleop(drive, visionSubsystem, this, gamepad1));


        gamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(claw::open);

        gamepad.getGamepadButton(GamepadKeys.Button.B).whenPressed(claw::close);

       // waitForStart();
    }


    public void run() {


        CommandScheduler.getInstance().run();

        //elevator.showTelemetry();
        //  drive.drive.showTelemetry(telemetry);
    }


}