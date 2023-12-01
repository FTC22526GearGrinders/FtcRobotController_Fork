package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDrive extends CommandBase {
    private Drive_Subsystem drive;


    private GamepadEx gamepad;
    private boolean fieldOriented;

    public JogDrive(Drive_Subsystem drive, GamepadEx gamepad, boolean fieldOriented) {
        this.drive = drive;
        this.gamepad = gamepad;
        this.fieldOriented = fieldOriented;
        addRequirements(this.drive);

    }


    @Override
    public void initialize() {

    }

    //    public void driveFieldCentric(double yaxis, double turn, double xaxis, double speed) {
//        double angle = startHeading - getHeading();
//        double forward = Math.cos(angle) * yaxis + Math.sin(angle) * xaxis;
//        double strafe = -Math.sin(angle) * yaxis + Math.cos(angle) * xaxis;
//        driveSimple(forward, turn, strafe, speed);
//    }
    @Override
    public void execute() {

        if (drive.drive.fieldCentric) {


            double forward = this.gamepad.getLeftY(); /* Invert stick Y axis */
            double strafe = this.gamepad.getLeftX();
            double rcw = this.gamepad.getRightX();

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
            double y = -this.gamepad.getLeftY();
            double x = this.gamepad.getLeftX();
            double rx = this.gamepad.getRightX();

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
