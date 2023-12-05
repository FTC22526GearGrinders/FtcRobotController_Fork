package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class ClimberSubsystem extends SubsystemBase {


    public MotorEx climberMotor;

    public Motor.Encoder climberEncoder;

    private final CommandOpMode myOpMode;
    private FtcDashboard dashboard;
    private Telemetry telemetry;
    public ProfiledPIDController profController = null;
    public double power;
    public double targetInches;
    double maxPower = .5;

    public ClimberSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        climberMotor = new MotorEx(myOpMode.hardwareMap, "climber motor", Motor.GoBILDA.RPM_312);
        
        climberEncoder = climberMotor.encoder;

        climberMotor.setInverted(false);

        climberMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        climberEncoder.setDistancePerPulse(1 / Constants.ClimberConstants.ENCODER_COUNTS_PER_INCH);

        profController = new ProfiledPIDController(
                Constants.ClimberConstants.kP, Constants.ClimberConstants.kI, Constants.ClimberConstants.kD,
                new TrapezoidProfile.Constraints(Constants.ClimberConstants.MAX_VEL, Constants.ClimberConstants.MAX_ACCEL));

        profController.setTolerance(Constants.ClimberConstants.POSITION_TOLERANCE_INCHES);

        profController.reset(0);

        resetEncoder();

        dashboard = FtcDashboard.getInstance();

        opMode.telemetry = new MultipleTelemetry(opMode.telemetry, dashboard.getTelemetry());
    }


    @Override

    public void periodic() {

    }

    public void climberToClearBar(){
        profController.setGoal(Constants.ClimberConstants.CLEAR_BAR_HEIGHT);
    }

    public void climberToLiftPosition(){
        profController.setGoal(Constants.ClimberConstants.RAISe_ROBOT_HEIGHT);
    }


    public void runClimberMoor(double power){
        climberMotor.set(power);
    }

    public void resetEncoder() {
        climberEncoder.reset();
    }

    public double getPositionInches() {
        return climberEncoder.getDistance();
    }

    public void setPower(double power) {
        climberMotor.set(power);
    }

    public double getPower() {
        return climberMotor.get();
    }


    public boolean inPosition() {
        return profController.atSetpoint();
    }

    public double getGoalPosition(){
        return profController.getGoal().position;
    }

    public void setPositionKp(double kp) {
        profController.setP(kp);
    }

    public double getPositionKp() {
        return profController.getP();
    }

    public double getPositionKi() {
        return profController.getD();
    }

    public void setPositionKi(double ki) {
        profController.setI(ki);
    }

    public double getPositionKd() {
        return profController.getD();
    }

    public void setPositionKd(double kd) {
        profController.setD(kd);
    }

    public void setTrapConstraints(double vel, double acc) {
        profController.setConstraints(new TrapezoidProfile.Constraints(vel, acc));
    }


    public void showTelemetry(Telemetry telemetry) {

        telemetry.addData("EncCtsPerInch", Constants.ClimberConstants.ENCODER_COUNTS_PER_INCH);
        telemetry.addData("MaxIPS", Constants.ClimberConstants.MAX_INCHES_PER_SECOND);
        telemetry.addData("ClimberPowerCmd", power);
        telemetry.addData("ClimberInches", getPositionInches());
        telemetry.addData("TargetInches", targetInches);
        telemetry.addData("ClimberInches", getPositionInches());
        telemetry.addData("ClimberVelocity", climberEncoder.getRawVelocity());
        telemetry.addData("ClimberPower", climberMotor.get());

        telemetry.update();

    }
}
