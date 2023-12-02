package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.W_Datalogger_v05;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LogTrajectory extends CommandBase {

    W_Datalogger_v05 tsDL;     // edit name to Datalogger, or the name you used
    String datalogFilename = "myDatalog_Traj";   // modify name for each run
    Drive_Subsystem drive;

    private final CommandOpMode opMode;

    ElapsedTime dataTimer;              // timer object

    ElapsedTime logTimer;// timer object

    int logInterval = 100;               // target interval in milliseconds

    boolean logged = false;

    boolean headersWritten = false;

    int lpctr;

    boolean writeRunning = false;

    public LogTrajectory(Drive_Subsystem drive, CommandOpMode opMode) {
        this.opMode = opMode;
        this.drive = drive;
    }

    @Override
    public void initialize() {

        opMode.telemetry.addData("Log Started", "");
        opMode.telemetry.update();

        String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        datalogFilename = drive.trajName + out + ".txt";
        tsDL = new W_Datalogger_v05(datalogFilename);

        logged = false;
        lpctr = 0;


        // Instantiate datalog timer.
        dataTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        logTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);


        tsDL.addField("Total Time");
        tsDL.addField("Num Segments");
        tsDL.addField("StartPose");
        tsDL.addField("EndPose");
        tsDL.addField("Running");
        tsDL.addField("Robot X");
        tsDL.addField("Robot Y");
        tsDL.addField("Robot Heading");
        tsDL.addField("Pose Velocity");

        tsDL.firstLine();                        // end first line (row)

        dataTimer.reset();

        logTimer.reset();


    }

    @Override
    public void execute() {

        if (headersWritten && dataTimer.time() > logInterval) {
            writeRunning = true;
            tsDL.addField("," + "," + "," + ",");
            tsDL.addField(drive.drive.getTrajectoryRunning());
            tsDL.addField(drive.drive.getPoseEstimate().getX());
            tsDL.addField(drive.drive.getPoseEstimate().getY());
            tsDL.addField(drive.drive.getPoseEstimate().getHeading());
            tsDL.addField(drive.drive.getExternalHeadingVelocity());
            tsDL.newLine();
            dataTimer.reset();
            writeRunning = false;
            lpctr += 1;
        }

        if (!headersWritten) {
            writeRunning = true;
            double d = drive.currentTrajSeq.duration();
            int s = drive.currentTrajSeq.size();
            String startPose = drive.currentTrajSeq.start().toString();
            startPose = startPose.replace(",", "_");
            String endPose = drive.currentTrajSeq.end().toString();
            endPose = endPose.replace(",", "_");
            tsDL.addField(d);
            tsDL.addField(s);
            tsDL.addField(startPose);
            tsDL.addField(endPose);
            tsDL.newLine();
            headersWritten = true;
            writeRunning = false;
            dataTimer.reset();
        }

    }

    @Override
    public void end(boolean interrupted) {
        tsDL.closeDataLogger();
        opMode.telemetry.addData("Log Ended", "");
        opMode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}

