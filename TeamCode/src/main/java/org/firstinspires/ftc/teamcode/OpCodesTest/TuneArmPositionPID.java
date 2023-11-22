package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.Commands.Arm.PositionPHArm;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


/**
 * Created by Tom on 9/26/17.  Updated 9/24/2021 for PIDF.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This OpMode uses the extended/enhanced
 * PIDF-related functions of the DcMotorEx class.  The REV Robotics Expansion Hub
 * supports the extended motor functions, but other controllers (such as the
 * deprecated Modern Robotics and Hitechnic DC Motor Controllers) do not.
 */
@Config
@TeleOp(name = "Arm: Tune PID", group = "Tune")

public class TuneArmPositionPID extends CommandOpMode {

    // our DC motor
    ArmSubsystem arm = null;

    public static PIDCoefficients armTunePID = new PIDCoefficients(0, 0, 0);


    FtcDashboard dashboard;

    public static double TARGET_POSITION = 10;

    double integral = 0;

    public void initialize() {

        arm = new ArmSubsystem(this);

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }

    // Put run blocks here.
    public void run() {


        if (TARGET_POSITION != arm.targetInches)

            new PositionPHArm(arm, TARGET_POSITION, .5).schedule();

        if (armTunePID.p != arm.getPositionKp())
            arm.setPositionKp(armTunePID.p);

        if (armTunePID.d != arm.getPositionKd())
            arm.setPositionKp(armTunePID.d);


        telemetry.addData("power", arm.getPower());
        telemetry.addData("pos curr", arm.getPositionInches());
        telemetry.addData("armtargetinches", arm.targetInches);
        telemetry.addData("p", armTunePID.p);
        telemetry.addData("d", armTunePID.d);
        telemetry.addData("TP", TARGET_POSITION);

        telemetry.update();


    }
}
