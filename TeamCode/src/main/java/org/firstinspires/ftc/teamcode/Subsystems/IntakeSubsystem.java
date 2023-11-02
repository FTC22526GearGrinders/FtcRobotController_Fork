package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {


    public MotorEx intakeMotor;


    private final CommandOpMode myOpMode;


    double maxPower = .5;

    public IntakeSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        intakeMotor = new MotorEx(myOpMode.hardwareMap, "intake motor");

        intakeMotor.setInverted(true);

        intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);


        ;
    }


    @Override

    public void periodic() {

    }

    public void runIntakeMotor(double power){
        intakeMotor.set(power);
    }
    public void showTelemetry(Telemetry telemetry) {


        telemetry.update();

    }
}
