package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.trajectorysequence.TrajectorySequence;


public class Drive_Subsystem extends SubsystemBase {

    public TrajectorySequence currentTrajSeq = null;
    public String trajName ="";
    public String runningTrajName="";
    public boolean trajectoryBuilt;
    public boolean trajectoryBuilding;

    private ElapsedTime runtime = new ElapsedTime();


    public SampleMecanumDrive drive;

    CommandOpMode myOpmode;

    public boolean running = false;

    public boolean started = false;

    public boolean stopped = false;

    private DistanceSensor sensorDistance;

    Rev2mDistanceSensor sensorTimeOfFlight;


    public Drive_Subsystem(CommandOpMode opMode) {
        myOpmode = opMode;
        runtime.reset();


        drive = new SampleMecanumDrive(myOpmode.hardwareMap);

        // you can use this as a regular DistanceSensor.
        sensorDistance = myOpmode.hardwareMap.get(DistanceSensor.class, "sensor_distance");

       sensorTimeOfFlight = (Rev2mDistanceSensor) sensorDistance;

        runtime.reset();
    }

    public void periodic() {


    }

    public void showtelemetry(Telemetry telemetry) {

        telemetry.addData("range", String.format("%.01f in", sensorDistance.getDistance(DistanceUnit.INCH)));

        // Rev2mDistanceSensor specific methods.
        telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
        telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));

        telemetry.update();

    }

}




