package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.W_Datalogger_v05;


public class LogTrajectory extends CommandBase {

    W_Datalogger_v05 tsDL;     // edit name to Datalogger, or the name you used
    String datalogFilename = "myDatalog_Traj";   // modify name for each run
    Drive_Subsystem drive;

    private final CommandOpMode opMode;

    ElapsedTime dataTimer;              // timer object

    ElapsedTime logTimer;// timer object

    int logInterval = 50;               // target interval in milliseconds

    boolean logged = false;

    int lpctr;

    int s;
    double d;
    String startPose;

    String endPose;




    public LogTrajectory(Drive_Subsystem drive,CommandOpMode opMode) {
        this.opMode = opMode;
        this.drive=drive;
    }

    @Override
    public void initialize() {

        opMode.telemetry.addData("Log Started", "");
        opMode.telemetry.update();
        datalogFilename = drive.trajName;

        logged = false;
        lpctr = 0;
        tsDL = new W_Datalogger_v05(datalogFilename);
        // Instantiate datalog timer.
        dataTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        logTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        drive = new Drive_Subsystem(opMode);

        tsDL.addField("Total Time");
        tsDL.addField("Num Segments");
        tsDL.addField("StartPose");
        tsDL.addField("EndPose");
        tsDL.addField("Running");
        tsDL.addField("Robot X");
        tsDL.addField("Robot Y");
        tsDL.addField("Robot Heading");

        tsDL.firstLine();                        // end first line (row)

        dataTimer.reset();
        logTimer.reset();

        d = drive.trajDuration;
        s = drive.trajSize;
        startPose = drive.trsjStartPose.toString();
        startPose = startPose.replace(",", "_");
        endPose = drive.trajEndPose.toString();
        endPose = endPose.replace(",", "_");

    }

    @Override
    public void execute() {

        opMode.telemetry.addData("Time", logTimer.seconds());
        opMode.telemetry.update();

        // dataTimer.reset();

        if (lpctr == 0 && dataTimer.time() > logInterval) {
            tsDL.addField(d);
            tsDL.addField(s);
            tsDL.addField(startPose);
            tsDL.addField(endPose);
            lpctr = 1;
            dataTimer.reset();
        }

        if (lpctr == 0 && dataTimer.time() > logInterval) {
            tsDL.addField(drive.drive.getTrajectoryRunning());
            tsDL.addField(drive.drive.getPoseEstimate().getX());
            tsDL.addField(drive.drive.getPoseEstimate().getY());
            tsDL.addField(drive.drive.getPoseEstimate().getHeading());
            tsDL.newLine();
            dataTimer.reset();
        }

        logged = logTimer.seconds() > drive.trajDuration / 4 && !drive.drive.getTrajectoryRunning();
    }

    @Override
    public void end(boolean interrupted) {
        tsDL.closeDataLogger();
    }

    @Override
    public boolean isFinished() {
        return logged;
    }


}

