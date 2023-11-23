package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class ArmSubsystem extends SubsystemBase {


    public Motor armMotor;

    public Motor.Encoder armEncoder;
    private final CommandOpMode myOpMode;
    public double targetInches;

    public int armPositionIndex;
    public double holdInches;
    public int holdCountTimer;

    public double power;

    double maxPower = .5;

    public PIDController controller = new PIDController(Constants.ArmConstants.kP, Constants.ArmConstants.kI, Constants.ArmConstants.kD);

    public ArmSubsystem(CommandOpMode opMode) {

        myOpMode = opMode;

        armMotor = new Motor(myOpMode.hardwareMap, "arm motor", Motor.GoBILDA.RPM_435);

        armMotor.setInverted(true);

        armMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        armEncoder = armMotor.encoder;

        armEncoder.setDirection(Motor.Direction.FORWARD);


        armEncoder.setDistancePerPulse(1 / Constants.ArmConstants.ENCODER_COUNTS_PER_INCH);

        controller.setTolerance(Constants.ArmConstants.POSITION_TOLERANCE_INCHES);

        resetEncoder();

    }


    @Override

    public void periodic() {

    }

    public void resetEncoder() {
        armEncoder.reset();
    }

    public double getPositionInches() {
        return armEncoder.getDistance();
    }


    public boolean inPosition() {
        return controller.atSetPoint();
    }

    public void setPositionKp(double kp) {
        controller.setP(kp);
    }

    public double getPositionKp() {
        return controller.getP();
    }

    public double getPositionKd() {
        return controller.getD();
    }

    public void setPower(double power){
        armMotor.set(power);
    }

    public double getPower() {
        return armMotor.get();
    }


    public void showTelemetry(Telemetry telemetry) {

        telemetry.addData("EncCtsPerInch", Constants.ArmConstants.ENCODER_COUNTS_PER_INCH);
        telemetry.addData("MaxIPS", Constants.ArmConstants.MAX_INCHES_PER_SECOND);
        telemetry.addData("ArmPowerCmd", power);
        telemetry.addData("ArmInches", getPositionInches());
        telemetry.addData("TargetInches", targetInches);
        telemetry.addData("HoldInches", holdInches);


        telemetry.addData("ArmInches", getPositionInches());
        telemetry.addData("ArmVelocity", armEncoder.getRawVelocity());
        telemetry.addData("ArmPower", armMotor.get());
        telemetry.addData("HoldCtr", holdCountTimer);


        telemetry.update();

    }
}
