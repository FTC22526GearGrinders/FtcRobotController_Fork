package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class PickAndRunTrajectories extends CommandBase {

    private Drive_Subsystem drive;
    boolean runLR;
    boolean runC;

    public PickAndRunTrajectories(Drive_Subsystem drive) {
        this.drive = drive;
    }

    @Override
    public void initialize() {

        drive.test=1;


        int lcr = ActiveMotionValues.getLcrpos();



        switch (lcr) {

            case 1:
            case 3:
            case 11:
            case 13:
                new LRTapePlacePixel(drive).schedule();//
                break;
            default:
                break;


        }

        switch (lcr) {

            case 2:
            case 12:
                new CenterTapePlacePixel(drive).schedule();//
                break;
            default:
                break;


        }


    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
