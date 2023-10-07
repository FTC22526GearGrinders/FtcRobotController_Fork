package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;

    @Override
    public void initialize() {
        drive = new Drive_Subsystem(this);


        register(drive);

        drive.setDefaultCommand(new JogDrive(this.drive, gamepad1, false));


    }


    public void run() {

        CommandScheduler.getInstance().run();

        //elevator.showTelemetry();
         drive.drive.showTelemetry(telemetry);
    }


}