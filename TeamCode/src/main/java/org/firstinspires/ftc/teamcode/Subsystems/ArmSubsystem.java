package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class ArmSubsystem extends SubsystemBase {


    public final DcMotorEx armMotor;


    private final CommandOpMode myOpMode;

    double maxPower = .5;

    double kP = 0.005;
    double kI = 0;
    double kD = 0;

    public PIDController controller = new PIDController(kP, kI, kD);

    public ArmSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        armMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "armMotor");
        armMotor.setDirection(DcMotor.Direction.REVERSE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        controller.setTolerance(Constants.PixelHandlerConstants.POSITION_TOLERANCE);


    }

    public static double encoderTicksToInches(double ticks) {
        return 100 * ticks;
    }


    @Override

    public void periodic() {

    }

    public void resetEncoder() {
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setTargetPosition(0);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void jog(double power) {

//        if (power > 0 && getPositionInches() < Constants.PixelHandlerConstants.UPPER_POSITION_LIMIT
//                || power < 0 && getPositionInches() > Constants.PixelHandlerConstants.LOWER_POSITION_LIMIT) {
        armMotor.setPower(power);
        // }
    }

    public void position(double target) {
        double output = controller.calculate(
                armMotor.getCurrentPosition(), target);  // the measured value

        double motorOutput = output;

        if (output > 0 && output > maxPower) motorOutput = maxPower;

        if (output < 0 && output < -maxPower) motorOutput = -maxPower;

        armMotor.setVelocity(motorOutput);

    }


    public void positionArm(double height) {

    }

    public double getPositionInches() {
        return encoderTicksToInches(getEncoderTicks());
    }

    public double getEncoderTicks() {
        return 10;//armMotor.getCurrentPosition();

    }

    public int getCountsfrominches(double inches) {
        return (int) (inches * Constants.PixelHandlerConstants.ENCODER_COUNTS_PER_INCH);

    }

    public boolean inPosition() {
        return controller.atSetPoint();
    }

    public DcMotorEx getArmMotor() {
        return armMotor;
    }

    public void showTelemetry(Telemetry telemetry) {
        telemetry.addData("ArmPosition", armMotor.getCurrentPosition());
        telemetry.addData("ArmInches", encoderTicksToInches(armMotor.getCurrentPosition()));
        telemetry.addData("ArmVelocity", armMotor.getVelocity());
        telemetry.addData("ArmPower", armMotor.getPower());


        telemetry.update();

    }
}
