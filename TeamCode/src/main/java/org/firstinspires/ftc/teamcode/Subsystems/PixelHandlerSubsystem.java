package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants;

public class PixelHandlerSubsystem extends SubsystemBase {


    Servo claw;

    Servo pixelDrop;

    Servo clawExtend;

    NormalizedColorSensor colorSensor;

    private CommandOpMode myOpMode = null;


    private double currentClawArmPosition;


    public PixelHandlerSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        claw = myOpMode.hardwareMap.get(Servo.class, "claw");

        pixelDrop = myOpMode.hardwareMap.get(Servo.class, "pixel drop");

        pixelDrop.setDirection(Servo.Direction.FORWARD);

        clawExtend = myOpMode.hardwareMap.get(Servo.class, "claw extend");

        claw.setDirection(Servo.Direction.FORWARD);

        pixelDrop.setDirection(Servo.Direction.FORWARD);

        clawExtend.setDirection(Servo.Direction.FORWARD);

        retractClawArml();

        currentClawArmPosition = Constants.PixelHandlerConstants.CLAW_ARM_RETRACT_POSITION;

        // Get a reference to our sensor object. It's recommended to use NormalizedColorSensor over
        // ColorSensor, because NormalizedColorSensor consistently gives values between 0 and 1, while
        // the values you get from ColorSensor are dependent on the specific sensor you're using.
        colorSensor = myOpMode.hardwareMap.get(NormalizedColorSensor.class, "sensor_color");

        float gain = 1;
        colorSensor.setGain(gain);

        // If possible, turn the light on in the beginning (it might already be on anyway,
        // we just make sure it is if we can).
        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight) colorSensor).enableLight(true);

        }


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

    public void setClawArmExtendPosition(double position) {
        clawExtend.setPosition(position);
    }

    public double getLastPosition() {
        return currentClawArmPosition;
    }

    public void extendClawArm() {
        setClawArmExtendPosition(Constants.PixelHandlerConstants.CLAW_ARM_EXTEND_POSITION);
    }

    public void retractClawArml() {
        setClawArmExtendPosition(Constants.PixelHandlerConstants.CLAW_ARM_RETRACT_POSITION);
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
            clawExtend.setDirection(Servo.Direction.FORWARD);
        else
            clawExtend.setDirection(Servo.Direction.REVERSE);
    }

    public double getCurrentClawArmPosition() {
        return currentClawArmPosition;
    }

    public void setCurrentClawArmPosition(double position) {
        this.currentClawArmPosition = position;
    }

    public void iterateExtendClawArm(double increment) {
        currentClawArmPosition += increment;
        if (currentClawArmPosition > Constants.PixelHandlerConstants.CLAW_ARM_EXTEND_POSITION)
            currentClawArmPosition = Constants.PixelHandlerConstants.CLAW_ARM_EXTEND_POSITION;
        setClawArmExtendPosition(currentClawArmPosition);
    }

    public void iterateRetractArm(double increment) {
        currentClawArmPosition -= increment;
        if (currentClawArmPosition < Constants.PixelHandlerConstants.CLAW_ARM_RETRACT_POSITION)
            currentClawArmPosition = Constants.PixelHandlerConstants.CLAW_ARM_RETRACT_POSITION;
        setClawArmExtendPosition(currentClawArmPosition);
    }

    public void retractClawArm() {
        clawExtend.setPosition(Constants.PixelHandlerConstants.CLAW_ARM_RETRACT_POSITION);
    }

    public double getSensorDistanceInches() {
        return ((DistanceSensor) colorSensor).getDistance(DistanceUnit.INCH);
    }

    public NormalizedRGBA getColors() {
        return colorSensor.getNormalizedColors();
    }

    public float getRed() {
        return colorSensor.getNormalizedColors().red;
    }

    public float getGreen() {
        return colorSensor.getNormalizedColors().green;
    }

    public float getBlue() {
        return colorSensor.getNormalizedColors().blue;
    }


    public void showTelemetry(Telemetry telemetry) {
        telemetry.addData("Sensor Inches", getSensorDistanceInches());
        telemetry.addData("CurrentExtPosition", getCurrentClawArmPosition());
        telemetry.update();
    }

}
