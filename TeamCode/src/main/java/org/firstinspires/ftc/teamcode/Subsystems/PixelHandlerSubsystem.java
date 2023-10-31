package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class PixelHandlerSubsystem extends SubsystemBase {


    Servo claw;

    Servo pixelDrop;

    Servo armExtend;


    private final CommandOpMode myOpMode;

    private double currentArmPosition;


    public PixelHandlerSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        claw = myOpMode.hardwareMap.get(Servo.class, "claw");

        pixelDrop = myOpMode.hardwareMap.get(Servo.class, "pixel drop");

        armExtend = myOpMode.hardwareMap.get(Servo.class, "arm extend");

        retractArml();

        currentArmPosition = Constants.PixelHandlerConstants.ARM_RETRACT_POSITION;


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
        setPixelDropPosition(Constants.PixelHandlerConstants.DROP_OPEN_POSITION);

    }

    public void setArmExtendPosition(double position) {
        armExtend.setPosition(position);
    }

    public void extendArm(double increment) {
        currentArmPosition += increment;
        if (currentArmPosition > Constants.PixelHandlerConstants.ARM_RETRACT_POSITION)
            currentArmPosition = Constants.PixelHandlerConstants.ARM_RETRACT_POSITION;
        setArmExtendPosition(currentArmPosition);
    }

    public void extendArm() {
        setArmExtendPosition(Constants.PixelHandlerConstants.ARM_EXTEND_POSITION);
    }

    public void retractArml() {
        setArmExtendPosition(Constants.PixelHandlerConstants.ARM_RETRACT_POSITION);
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

    public void setArmExtendDirection(boolean fwd) {
        if (fwd)
            armExtend.setDirection(Servo.Direction.FORWARD);
        else
            armExtend.setDirection(Servo.Direction.REVERSE);
    }


}
