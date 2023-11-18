package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;


public class ShowAutoValues extends CommandBase {

    private CommandOpMode opMode;

    private Vision_Subsystem vss;

    public ShowAutoValues(CommandOpMode opMode, Vision_Subsystem vss) {
        this.opMode = opMode;
        this.vss = vss;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        if (vss.getCameraOpened()) {
            opMode.telemetry.addData("Streaming", vss.getWebcam().getFps());
            opMode.telemetry.addData("RRAIsempty", vss.getSptop().rrAreas.isEmpty());

            opMode.telemetry.addData("Red", vss.getSptop().getRedPipeline());
            opMode.telemetry.addData("BlTh", vss.getSptop().blueThreshold);

            opMode.telemetry.addData("LCR", vss.getSptop().lcr);
            opMode.telemetry.addData("NumCon", vss.getSptop().numContoursFound);
            opMode.telemetry.addData("ValCon", vss.getSptop().usableContours);
            if (!vss.getSptop().rr.isEmpty())
                opMode.telemetry.addData("RRSize", vss.getSptop().rr.size());
            if (!vss.getSptop().rrxval.isEmpty())
                opMode.telemetry.addData("XVALSize", vss.getSptop().rrxval.size());
            if (!vss.getSptop().rrAreas.isEmpty())
                opMode.telemetry.addData("AreasSize", vss.getSptop().rrAreas.size());
            if (!vss.getSptop().changing)
                opMode.telemetry.addData("area0", vss.getSptop().getArea(0));
            if (!vss.getSptop().changing)
                opMode.telemetry.addData("X0", vss.getSptop().getX(0));
            if (!vss.getSptop().changing)
                opMode.telemetry.addData("area1", vss.getSptop().getArea(1));
            if (!vss.getSptop().changing)
                opMode.telemetry.addData("X1", vss.getSptop().getX(1));
            if (!vss.getSptop().changing)
                opMode.telemetry.addData("area2", vss.getSptop().getArea(2));
            if (!vss.getSptop().changing)
                opMode.telemetry.addData("X2", vss.getSptop().getX(2));


            if (vss.getSptop().getUsableContours() > 1) {

                opMode.telemetry.addData("Cont Found", vss.getSptop().getNumberContours());
                opMode.telemetry.addData("Usable", vss.getSptop().getUsableContours());

            }

        }
        opMode.telemetry.update();

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
