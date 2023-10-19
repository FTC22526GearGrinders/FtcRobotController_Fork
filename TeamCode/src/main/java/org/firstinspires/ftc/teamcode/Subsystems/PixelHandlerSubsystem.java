package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class PixelHandlerSubsystem extends SubsystemBase {


    Servo claw;

    Servo pixelDrop;

    private DcMotorEx armMotor;

    private Telemetry telemetry;

    private CommandOpMode myOpMode;


    public PixelHandlerSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        claw = myOpMode.hardwareMap.get(Servo.class, "claw");

        armMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "arm motor");
        armMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override

    public void periodic() {

    }

    public void setClawPosition(double position) {
        claw.setPosition(position);
    }

    public void closeClaw() {
        setClawPosition(Constants.PixelHandlerConstants.CLAW_CLOSE_POSITION);
    }


    public void openClaw() {
        setClawPosition(Constants.PixelHandlerConstants.CLAW_OPEN_POSITION);

    }

    public void setPixelDropPosition(double position) {
        pixelDrop.setPosition(position);
    }

    public void holdPixel() {
        setPixelDropPosition(Constants.PixelHandlerConstants.DROP_CLOSED_POSITION);
    }


    public void dropPixel() {
        setPixelDropPosition(Constants.PixelHandlerConstants.DROP_CLOSED_POSITION);

    }

    public void setClawDirection(boolean fwd) {
        if (fwd)
            claw.setDirection(Servo.Direction.FORWARD);
        else
            claw.setDirection(Servo.Direction.REVERSE);
    }

    public void setPixelDropDirection(boolean fwd) {
        if (fwd)
            pixelDrop.setDirection(Servo.Direction.FORWARD);
        else
            pixelDrop.setDirection(Servo.Direction.REVERSE);
    }

    public void positionArm(double height) {

    }

    public double getPositionInches(double ticks) {
        return Constants.PixelHandlerConstants.encoderTicksToInches(ticks);
    }

}
