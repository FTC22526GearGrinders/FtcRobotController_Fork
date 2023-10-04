package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDrive extends CommandBase {
    private Drive_Subsystem drive;

    private GamepadEx gamepad;

    private boolean fieldOriented;

    public JogDrive(Drive_Subsystem drive, GamepadEx gamepad, boolean fieldoOriented) {
        this.drive = drive;
        this.gamepad = gamepad;
        this.fieldOriented = fieldoOriented;
        addRequirements(this.drive);

    }


    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        if (fieldOriented) {


            double forwrd = this.gamepad.getLeftY(); /* Invert stick Y axis */
            double strafe = this.gamepad.getLeftX();
            double rcw = this.gamepad.getRightX();

            /* Adjust Joystick X/Y inputs by navX MXP yaw angle */

            double gyro_degrees = drive.getGyroHeading().getDegrees();

            double gyro_radians = gyro_degrees * Math.PI / 180;
            double temp = forwrd * (float) Math.cos(gyro_radians) +
                    strafe * Math.sin(gyro_radians);
            strafe = -forwrd * Math.sin(gyro_radians) +
                    strafe * Math.cos(gyro_radians);
            forwrd = temp;

            drive.drive.jog(strafe, forwrd, rcw);


            /* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
            /* rotated by the gyro angle, and can be sent to drive system */
        } else {
            double x = this.gamepad.getLeftX();
            double y = this.gamepad.getLeftY();
            double rx = this.gamepad.getRightX();

            drive.drive.jog(x, y, rx);
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
