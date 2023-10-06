package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


public class Drive_Subsystem extends SubsystemBase {
    public boolean fieldCentric;


    public int test;

    private ElapsedTime runtime = new ElapsedTime();


    public SampleMecanumDrive drive;

    CommandOpMode myOpmode;



    public Drive_Subsystem(CommandOpMode opMode) {
        myOpmode = opMode;
        runtime.reset();


        drive = new SampleMecanumDrive(myOpmode.hardwareMap);


        runtime.reset();


    }

    public void periodic() {


    }



}




