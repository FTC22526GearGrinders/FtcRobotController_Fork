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

    public Servo flipGrippers;

    ColorSensor colorSensor;

    private CommandOpMode myOpMode = null;

    public double turnGrippersPosition;


    public PixelHandlerSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        leftGripper = myOpMode.hardwareMap.get(Servo.class, "left gripper");
        rightGripper = myOpMode.hardwareMap.get(Servo.class, "right gripper");
        turnGrippers = myOpMode.hardwareMap.get(Servo.class, "turn gripper");
        flipGrippers = myOpMode.hardwareMap.get(Servo.class, "flip gripper");


        pixelDrop = myOpMode.hardwareMap.get(Servo.class, "pixel drop");


        pixelDrop.setDirection(Servo.Direction.FORWARD);

        leftGripper.setDirection(Servo.Direction.FORWARD);

        rightGripper.setDirection(Servo.Direction.FORWARD);

        turnGrippers.setDirection(Servo.Direction.FORWARD);

        flipGrippers.setDirection(Servo.Direction.FORWARD);

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

    public void positionLeftGripper(double position) {
        leftGripper.setPosition(position);
    }

    public void positionRightGripper(double position) {
        rightGripper.setPosition(position);
    }

    public void positionTurnGripper(double position) {
        turnGrippers.setPosition(position);
    }

    public void positionFlipGripper(double position) {
        flipGrippers.setPosition(position);
    }


    public void openLeftGripper() {
        leftGripper.setPosition(Constants.PixelHandlerConstants.LeftGripperSet.OPEN.position);
    }

    public void midLeftGripper() {
        leftGripper.setPosition(Constants.PixelHandlerConstants.LeftGripperSet.MID.position);
    }

    public void closeLeftGripper() {
        leftGripper.setPosition(Constants.PixelHandlerConstants.LeftGripperSet.CLOSED.position);
    }

    public void openRightGripper() {
        rightGripper.setPosition(Constants.PixelHandlerConstants.LeftGripperSet.OPEN.position);
    }

    public void midRightGripper() {
        rightGripper.setPosition(Constants.PixelHandlerConstants.LeftGripperSet.MID.position);
    }

    public void closeRightGripper() {
        rightGripper.setPosition(Constants.PixelHandlerConstants.LeftGripperSet.CLOSED.position);
    }

    public void openBothGrippers() {
        openLeftGripper();
        openRightGripper();
    }

    public void closeBothGrippers() {
        closeLeftGripper();
        closeRightGripper();
    }

    public void raiseGrippersToDeliver() {
        turnGrippers.setPosition(Constants.PixelHandlerConstants.TurnGripperSet.DELIVER.position);
        turnGrippersPosition = Constants.PixelHandlerConstants.TurnGripperSet.DELIVER.position;
    }
    public void turnGrippersToMid() {
        turnGrippers.setPosition(Constants.PixelHandlerConstants.TurnGripperSet.MID.position);
        turnGrippersPosition = Constants.PixelHandlerConstants.TurnGripperSet.MID.position;
    }
    public void lowerGrippersToPickup() {
        turnGrippers.setPosition(Constants.PixelHandlerConstants.TurnGripperSet.PICKUP.position);
        turnGrippersPosition = Constants.PixelHandlerConstants.TurnGripperSet.PICKUP.position;
    }

    public void flipGrippersToPickup() {
        flipGrippers.setPosition(Constants.PixelHandlerConstants.FlipGripperSet.PICKUP.position);
    }

    public void flipGrippersToDeliver() {
        flipGrippers.setPosition(Constants.PixelHandlerConstants.FlipGripperSet.DELIVER.position);
    }
    public void flipGrippersToLeftDown() {
        flipGrippers.setPosition(Constants.PixelHandlerConstants.FlipGripperSet.LEFT_DOWN.position);
    }
    public void flipGrippersToRightDown() {
        flipGrippers.setPosition(Constants.PixelHandlerConstants.FlipGripperSet.RIGHT_DOWN.position);
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
