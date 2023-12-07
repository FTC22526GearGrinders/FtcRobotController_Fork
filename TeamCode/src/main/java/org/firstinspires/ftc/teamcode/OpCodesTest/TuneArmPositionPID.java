package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.Constants;
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
@Disabled
public class TuneArmPositionPID extends CommandOpMode {

    // our DC motor
    ArmSubsystem arm = null;

    public static PIDCoefficients armTunePID = new PIDCoefficients(0.06, 0, 0);

    public static double maxAccel = 2;

    public static double maxVel = 4;

    double lastAccel;

    double lastVel;

    FtcDashboard dashboard;

    public static double TARGET_INCHES = 0;

    double lastTargetInches;

    public void initialize() {

        arm = new ArmSubsystem(this);

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

            run();

            if (armTunePID.p != arm.getPositionKp())
                arm.setPositionKp(armTunePID.p);

            if (armTunePID.i != arm.getPositionKi())
                arm.setPositionKi(armTunePID.i);

            if (armTunePID.d != arm.getPositionKd())
                arm.setPositionKp(armTunePID.d);

            if (maxAccel != lastAccel || maxVel != lastVel) {
                arm.setTrapConstraints(maxVel, maxVel);
            }


            if (TARGET_INCHES != lastTargetInches) {
                arm.profController.setGoal(TARGET_INCHES);
                lastTargetInches = TARGET_INCHES;
            }

            double output = arm.profController.calculate(
                    arm.getPositionInches());

            double temp = output + Constants.ArmConstants.POSITION_Kg;


            arm.power = temp;

            arm.armMotor.set(arm.power);


            telemetry.addData("trapout", output);
            telemetry.addData("poserr", arm.profController.getPositionError());
            telemetry.addData("velerr", arm.profController.getVelocityError());

            telemetry.addData("commandpower", arm.power);
            telemetry.addData("armtargetinches", TARGET_INCHES);
            telemetry.addData("arm current inches", arm.getPositionInches());
            telemetry.addData("motorpower", arm.getPower());

            telemetry.update();


        }
        reset();

    }
}

