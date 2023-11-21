package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.W_Datalogger_v05;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LogAutoSettings extends CommandBase {

    W_Datalogger_v05 avDL;     // edit name to Datalogger, or the name you used
    String datalogFilename = "myDatalog_AV1";   // modify name for each run

    private final CommandOpMode opMode;

    boolean headersWritten = false;

    boolean dataWritten = false;
    ElapsedTime dataTimer;              // timer object
    int logInterval = 50;               // target interval in milliseconds

    boolean logged = false;

    boolean valuesSet = false;

    int lpctr;

    String a;
    String b;
    String c;
    String d;

    String e;

    String f;

    String g;

    public LogAutoSettings(CommandOpMode opMode) {
        this.opMode = opMode;

    }

    @Override
    public void initialize() {
        logged = false;
        lpctr = 0;
        String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        datalogFilename += out + ".txt";
        avDL = new W_Datalogger_v05(datalogFilename);
        // Instantiate datalog timer.
        dataTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        dataTimer.reset();
        opMode.telemetry.addData("LogAutoInit", "");
        opMode.telemetry.update();

    }

    @Override
    public void execute() {


        if (headersWritten && valuesSet && !dataWritten) {

            avDL.addField(a);
            avDL.addField(b);
            avDL.addField(c);
            avDL.addField(d);
            avDL.addField(e);
            avDL.addField(f);
            avDL.addField(g);

            avDL.newLine();
            dataTimer.reset();
            dataWritten = true;
        }

        if (!headersWritten && !valuesSet) {
            a = ActiveMotionValues.getRedAlliance() ? "true" : "false";
            b = ActiveMotionValues.getBBStart() ? "true" : "false";
            c = ActiveMotionValues.getUseStageDoor() ? "true" : "false";
            d = ActiveMotionValues.getSecondPixel() ? "true" : "false";
            e = ActiveMotionValues.getNearPark() ? "true" : "false";
            f = ActiveMotionValues.getCenterPark() ? "true" : "false";
            g = ActiveMotionValues.getStartPose().toString();
            g=g.replace(",","_");


            valuesSet = true;
            dataTimer.reset();
        }

        if (!headersWritten) {

            avDL.addField("Red Alliance");
            avDL.addField("BBStart");
            avDL.addField("StageDoor");
            avDL.addField("2nd Pixel");
            avDL.addField("NearPark");
            avDL.addField("CenterPark");
            avDL.addField("StartPose");
            headersWritten = true;
            avDL.firstLine();
            dataTimer.reset();
        }

    }

    @Override
    public void end(boolean interrupted) {
        avDL.closeDataLogger();
        opMode.telemetry.addData("LogAutoEnd", "");
        opMode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return dataWritten && dataTimer.time() > logInterval;
    }


}

