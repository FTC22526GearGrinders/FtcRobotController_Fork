package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ClimberSubsystem;


/**
 * Created by Tom on 9/26/17.  Updated 9/24/2021 for PIDF.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This OpMode uses the extended/enhanced
 * PIDF-related functions of the DcMotorEx class.  The REV Robotics Expansion Hub
 * supports the extended motor functions, but other controllers (such as the
 * deprecated Modern Robotics and Hitechnic DC Motor Controllers) do not.
 */
@Config
@TeleOp(name = "Climber: Tune PID", group = "Tune")
@Disabled
public class TuneClimberPositionPID extends CommandOpMode {

    // our DC motor
    ClimberSubsystem climber = null;

    public static PIDCoefficients climberTunePID = new PIDCoefficients(0.06, 0, 0);

    public static double maxAccel = 2;

    public static double maxVel = 4;

    double lastAccel;

    double lastVel;

    FtcDashboard dashboard;

    public static double TARGET_INCHES = 0;

    double lastTargetInches;

    public void initialize() {

        climber = new ClimberSubsystem(this);

        dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

            run();

            if (climberTunePID.p != climber.getPositionKp())
                climber.setPositionKp(climberTunePID.p);

            if (climberTunePID.i != climber.getPositionKi())
                climber.setPositionKi(climberTunePID.i);

            if (climberTunePID.d != climber.getPositionKd())
                climber.setPositionKp(climberTunePID.d);

            if (maxAccel != lastAccel || maxVel != lastVel) {
                climber.setTrapConstraints(maxVel, maxVel);
            }


            if (TARGET_INCHES != lastTargetInches) {
                climber.profController.setGoal(TARGET_INCHES);
                lastTargetInches = TARGET_INCHES;
            }

            double output = climber.profController.calculate(
                    climber.getPositionInches());

            double temp = output + Constants.ClimberConstants.POSITION_Kg;


            climber.power = temp;

            climber.climberMotor.set(climber.power);


            telemetry.addData("trapout", output);
            telemetry.addData("poserr", climber.profController.getPositionError());
            telemetry.addData("velerr", climber.profController.getVelocityError());

            telemetry.addData("commandpower", climber.power);
            telemetry.addData("climbertargetinches", TARGET_INCHES);
            telemetry.addData("climber current inches", climber.getPositionInches());
            telemetry.addData("motorpower", climber.getPower());

            telemetry.update();


        }
        reset();

    }
}

