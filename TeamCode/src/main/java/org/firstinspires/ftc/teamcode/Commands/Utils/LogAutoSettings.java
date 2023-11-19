package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.W_Datalogger_v05;


public class LogAutoSettings extends CommandBase {

    W_Datalogger_v05 avDL;     // edit name to Datalogger, or the name you used
    String datalogFilename = "myDatalog_AV1";   // modify name for each run

    private final CommandOpMode opMode;


    ElapsedTime dataTimer;              // timer object
    int logInterval = 50;               // target interval in milliseconds

    boolean logged = false;

    int lpctr;

    public LogAutoSettings(CommandOpMode opMode) {
        this.opMode = opMode;

    }

    @Override
    public void initialize() {
        logged = false;
        lpctr = 0;
        avDL = new W_Datalogger_v05(datalogFilename);
        // Instantiate datalog timer.
        dataTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        avDL.addField("Red Alliance");
        avDL.addField("LCR");
        avDL.addField("Start Position");
        avDL.addField("Start Pose X");
        avDL.addField("Start Pose Y");
        avDL.addField("Start Pose DEG");


        avDL.addField("Advance Pose");
        avDL.addField("Drop Pose");

        avDL.addField("Retract Pose");
        avDL.addField("Pre Tag Pose");
        avDL.addField("Act Tag");
        avDL.firstLine();
        dataTimer.reset();
    }

    @Override
    public void execute() {
        boolean a = ActiveMotionValues.getRedAlliance();
        int b = ActiveMotionValues.getLcrpos();
        boolean c = ActiveMotionValues.getBBStart();
        String d = ActiveMotionValues.getStartPose().toString();
        String e = ActiveMotionValues.getAdvancePose().toString();
        String f = ActiveMotionValues.getDropOffPose().toString();
        lpctr++;
opMode.telemetry.addData("STRPOSE",ActiveMotionValues.getStartPose().toString());
opMode.telemetry.update();
        if (lpctr == 5) {

            avDL.addField(a);
            avDL.addField(b);
            avDL.addField(c);
            avDL.addField(d);
            avDL.addField(e);
            avDL.addField(f);
//            avDL.addField(ActiveMotionValues.getRetractPose().toString());
//            avDL.addField(ActiveMotionValues.getPreTagPose().toString());
//            avDL.addField(ActiveMotionValues.getActTag());
            avDL.newLine();


        }
        if (lpctr == 8) logged = true;
    }

    @Override
    public void end(boolean interrupted) {
        avDL.closeDataLogger();
    }

    @Override
    public boolean isFinished() {
        return logged;
    }


}

