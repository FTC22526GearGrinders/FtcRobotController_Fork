package org.firstinspires.ftc.teamcode.OpCodes_Auto;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.Auto.CenterTapePlacePixel;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectMotionValuesBlue;
import org.firstinspires.ftc.teamcode.Commands.Auto.SelectMotionValuesRed;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;

@Config
@Autonomous(name = "Test Run Trajs", group = "Test")

public class TestTrajMode extends CommandOpMode {
    private IO_Subsystem ioss;

    private Drive_Subsystem drive;

    @Override
    public void initialize() {


        drive = new Drive_Subsystem(this);

        ioss = new IO_Subsystem(this);

        boolean redAlliance = !ioss.dc0.getState();

        boolean bbStart = !ioss.dc1.getState();


        ActiveMotionValues.setRedAlliance(redAlliance);

        ActiveMotionValues.setBBStart(bbStart);

        ActiveMotionValues.setLcrpos(2);

//        new SequentialCommandGroup(
//
//                new ConditionalCommand(new SelectMotionValuesRed(),
//
//                        new SelectMotionValuesBlue(), () -> redAlliance),//takes alliance and LCR and sets up data


                new CenterTapePlacePixel(drive).schedule();


    }

    // Put run blocks here.
    public void run() {

        CommandScheduler.getInstance().run();

    }
}
