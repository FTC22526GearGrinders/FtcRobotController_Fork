package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


public class Drive_Subsystem extends SubsystemBase {
    public boolean fieldCentric;


    private ElapsedTime runtime = new ElapsedTime();


    public SampleMecanumDrive drive;

    CommandOpMode myOpmode;

    public boolean running = false;

    public boolean started = false;

    public boolean stopped = false;


    public Drive_Subsystem(CommandOpMode opMode) {
        myOpmode = opMode;
        runtime.reset();


        drive = new SampleMecanumDrive(myOpmode.hardwareMap);


        runtime.reset();


    }

    public void periodic() {


    }

    public void showtelemetry(Telemetry telemetry) {

    }

}




