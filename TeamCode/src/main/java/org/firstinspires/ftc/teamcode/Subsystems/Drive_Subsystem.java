package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


public class Drive_Subsystem extends SubsystemBase {
    public boolean fieldCentric;


    private int test;

    private ElapsedTime runtime = new ElapsedTime();


    public SampleMecanumDrive drive;

    CommandOpMode myOpmode;


    // This is the built-in IMU in the REV hub.
    // We're initializing it by its default parameters
    // and name in the config ('imu'). The orientation
    // of the hub is important. Below is a model
    // of the REV Hub and the orientation axes for the IMU.
    //
    //                           | Z axis
    //                           |
    //     (Motor Port Side)     |   / X axis
    //                       ____|__/____
    //          Y axis     / *   | /    /|   (IO Side)
    //          _________ /______|/    //      I2C
    //                   /___________ //     Digital
    //                  |____________|/      Analog
    //
    //                 (Servo Port Side)
    //
    // (unapologetically stolen from the road-runner-quickstart)

    public Drive_Subsystem(CommandOpMode opMode) {
        myOpmode = opMode;
        runtime.reset();


        drive = new SampleMecanumDrive(myOpmode.hardwareMap);


        runtime.reset();


    }

    public void periodic() {
        test++;
        ;
    }


}




