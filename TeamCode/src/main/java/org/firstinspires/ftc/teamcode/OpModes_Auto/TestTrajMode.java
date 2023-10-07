package org.firstinspires.ftc.teamcode.OpModes_Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Auto.PickAndRunTrajectories;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IO_Subsystem;

@Config
@Autonomous(name = "Test Run Trajs", group = "Test")

public class TestTrajMode extends CommandOpMode {
    private IO_Subsystem ioss;

    private Drive_Subsystem drive;

    FtcDashboard dashboard;


    public static int lcr = 1;

    public static boolean redAlliance;

    public static boolean bbStart;

    @Override
    public void initialize() {


        drive = new Drive_Subsystem(this);

        ioss = new IO_Subsystem(this);

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());


//        boolean redAlliance = !ioss.dc0.getState();
//
//        boolean bbStart = !ioss.dc1.getState();




        //   new SequentialCommandGroup(



                new PickAndRunTrajectories(drive).schedule();


    }

    // Put run blocks here.
    public void run() {


        telemetry.addData("RedAlliance", ActiveMotionValues.getRedAlliance());
        telemetry.addData("BackboardStart", ActiveMotionValues.getBBStart());

        telemetry.addData("AprilTag", ActiveMotionValues.getActTag());
        telemetry.addData("Yfirst", ActiveMotionValues.getyFirstPoint());

        telemetry.addData("DRTEST", drive.test);


        telemetry.update();

        //  drive.drive.showTelemetry(telemetry);

        CommandScheduler.getInstance().run();

    }
}
