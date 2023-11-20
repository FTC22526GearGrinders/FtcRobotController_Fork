package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants;

public class PixelHandlerSubsystem extends SubsystemBase {


    public Servo leftGripper;

    public Servo rightGripper;

    Servo pixelDrop;

    public Servo turnGrippers;

    ColorSensor colorSensor;

    private CommandOpMode myOpMode = null;


    public PixelHandlerSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        leftGripper = myOpMode.hardwareMap.get(Servo.class, "left gripper");
        rightGripper = myOpMode.hardwareMap.get(Servo.class, "right gripper");
        turnGrippers = myOpMode.hardwareMap.get(Servo.class, "turn gripper");


        pixelDrop = myOpMode.hardwareMap.get(Servo.class, "pixel drop");


        pixelDrop.setDirection(Servo.Direction.FORWARD);

        leftGripper.setDirection(Servo.Direction.FORWARD);

        rightGripper.setDirection(Servo.Direction.FORWARD);

        pixelDrop.setDirection(Servo.Direction.FORWARD);

        turnGrippers.setDirection(Servo.Direction.FORWARD);

    }

    @Override

    public void periodic() {
    }


    public void holdPixel() {
        pixelDrop.setPosition(Constants.PixelHandlerConstants.DROP_CLOSED_POSITION);
    }
    public void dropPixel() {
        pixelDrop.setPosition(Constants.PixelHandlerConstants.DROP_OPEN_POSITION);
    }
    public void openLeftGripper() {
        leftGripper.setPosition(Constants.PixelHandlerConstants.LEFT_GRIPPER_OPEN_POSITION);
    }
    public void closeLeftGripper() {
        leftGripper.setPosition(Constants.PixelHandlerConstants.LEFT_GRIPPER_CLOSED_POSITION);
    }
    public void openRightGripper() {
        rightGripper.setPosition(Constants.PixelHandlerConstants.RIGHT_GRIPPER_OPEN_POSITION);
    }
    public void closeRightGripper() {
        rightGripper.setPosition(Constants.PixelHandlerConstants.RIGHT_GRIPPER_CLOSED_POSITION);
    }
    public void openBothGrippers(){
        openLeftGripper();
        openRightGripper();
    }
    public void closeBothGrippers(){
        closeLeftGripper();
        closeRightGripper();
    }
    public void turnGrippersToDeliver() {
        turnGrippers.setPosition(Constants.PixelHandlerConstants.TURN_DELIVER_POSITION);
    }
    public void turnGrippersToPickup() {
        turnGrippers.setPosition(Constants.PixelHandlerConstants.TURN_PICKUP_POSITION);
    }


    public double getSensorDistanceInches() {
        return ((DistanceSensor) colorSensor).getDistance(DistanceUnit.INCH);
    }

    public float getRed() {
        return colorSensor.red();
    }

    public float getGreen() {
        return colorSensor.green();
    }

    public float getBlue() {
        return colorSensor.blue();
    }


    public void showTelemetry(Telemetry telemetry) {
        telemetry.addData("Sensor Inches", getSensorDistanceInches());
        telemetry.update();
    }

}
