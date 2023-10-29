package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDrive extends CommandBase {
    private Drive_Subsystem drive;


    private Gamepad gamepad;
    private boolean fieldOriented;

    public JogDrive(Drive_Subsystem drive, Gamepad gamepad, boolean fieldOriented) {
        this.drive = drive;
        this.gamepad = gamepad;
        this.fieldOriented = fieldOriented;
        addRequirements(this.drive);

    }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        if (drive.drive.fieldCentric) {


            double forward = -this.gamepad.left_stick_y; /* Invert stick Y axis */
            double strafe = this.gamepad.left_stick_x;
            double rcw = this.gamepad.right_stick_x;

            /* Adjust Joystick X/Y inputs by navX MXP yaw angle */

            double gyro_degrees = drive.drive.getRawExternalHeading();

            double gyro_radians = gyro_degrees * Math.PI / 180;
            double temp = forward * (float) Math.cos(gyro_radians) +
                    strafe * Math.sin(gyro_radians);
            strafe = -forward * Math.sin(gyro_radians) +
                    strafe * Math.cos(gyro_radians);
            forward = temp;

            drive.drive.jog(strafe, forward, rcw);


            /* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
            /* rotated by the gyro angle, and can be sent to drive system */
        } else {
            double y = -this.gamepad.left_stick_y;
            double x = this.gamepad.left_stick_x;
            double rx = this.gamepad.right_stick_x;

            drive.drive.jog(y, x, rx);

        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
