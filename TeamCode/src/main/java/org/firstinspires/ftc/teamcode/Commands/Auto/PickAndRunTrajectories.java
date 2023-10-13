package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class PickAndRunTrajectories extends CommandBase {

    private Drive_Subsystem drive;

    private Claw_Subsystem clawSubsystem;
    boolean runLR;
    boolean runC;

    public PickAndRunTrajectories(Drive_Subsystem drive, Claw_Subsystem clawSubsystem) {
        this.drive = drive;
        this.clawSubsystem = clawSubsystem;
    }

    @Override
    public void initialize() {

        drive.test = 1;


        int lcr = ActiveMotionValues.getLcrpos();


        switch (lcr) {

            case 1:
            case 3:
            case 11:
            case 13:
                  new LRTapePlacePixel(drive,clawSubsystem).schedule();//

                break;
            default:
                break;


        }

        switch (lcr) {

            case 2:
            case 12:
                new CenterTapePlacePixel(drive, clawSubsystem).schedule();//
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
