package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ClimberSubsystem extends SubsystemBase {


    public MotorEx climberMotor;


    private final CommandOpMode myOpMode;


    double maxPower = .5;

    public ClimberSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        climberMotor = new MotorEx(myOpMode.hardwareMap, "climber motor");

        climberMotor.setInverted(true);

        climberMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

    }


    @Override

    public void periodic() {

    }

    public void runClimberMoor(double power){
        climberMotor.set(power);
    }


    public void showTelemetry(Telemetry telemetry) {


        telemetry.update();

    }
}
