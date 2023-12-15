package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDrive extends CommandBase {
    private Drive_Subsystem drive;

    private GamepadEx gamepad;


    public JogDrive(Drive_Subsystem drive, GamepadEx gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
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

        if (!drive.drive.fieldCentric) {

            double y = this.gamepad.getLeftY();
            double x = this.gamepad.getLeftX();
            double rx = this.gamepad.getRightX();

            drive.drive.jog(y, x, rx);

        }

        if (drive.drive.fieldCentric) {

            double strafe = -this.gamepad.getLeftY(); /* Invert stick Y axis */
            double forward = this.gamepad.getLeftX();
            double rcw = this.gamepad.getRightX();

            /* Adjust Joystick X/Y inputs by navX MXP yaw angle */

            double gyro_radians = drive.drive.startRadians - drive.drive.getRawExternalHeading();

            //     new drive  = strafe * sin(heading) + drive * cos(heading)
            //    new strafe = strafe * cos(heading) - drive * sin(heading)


            double temp = strafe * Math.sin(gyro_radians) + forward * (float) Math.cos(gyro_radians);

            strafe = strafe * Math.cos(gyro_radians) - forward * Math.sin(gyro_radians);

            forward = temp;

            drive.drive.jog(forward, strafe, rcw);
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
