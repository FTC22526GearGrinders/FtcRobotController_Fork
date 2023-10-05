package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDrive extends CommandBase {
    private Drive_Subsystem drive;


private Gamepad gamepad;
    private boolean fieldOriented;

    public JogDrive(Drive_Subsystem drive, Gamepad gamepad, boolean fieldoOriented) {
        this.drive = drive;
        this.gamepad = gamepad;
        this.fieldOriented = fieldoOriented;
        addRequirements(this.drive);

    }


    @Override
    public void initialize() {
        fieldOriented=false;
    }

    @Override
    public void execute() {

        if (fieldOriented) {


            double forwrd = this.gamepad.left_stick_x; /* Invert stick Y axis */
            double strafe = this.gamepad.left_stick_y;
            double rcw = this.gamepad.right_stick_x;

            /* Adjust Joystick X/Y inputs by navX MXP yaw angle */

            double gyro_degrees = drive.drive.getRawExternalHeading();

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
            double y = this.gamepad.left_stick_y;
            double x = this.gamepad.left_stick_x;
            double rx = this.gamepad.right_stick_x;

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
