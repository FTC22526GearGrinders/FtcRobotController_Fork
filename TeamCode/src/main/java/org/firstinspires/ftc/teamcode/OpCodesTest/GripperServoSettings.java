package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.DroneCatapultSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;

/*
 * Set direction of all 3 gripper servos
 * Let and right grippers use higher numbers to close.
 * Turn grippers use higher number to the deliver position
 * Servos have separate entries.
 *
 */
@Config
@TeleOp(group = "grippers")
@Disabled
public class GripperServoSettings extends CommandOpMode {

    public static boolean LEFT_DIRECTION = true;
    boolean lastLeftDirection;
    public static boolean RIGHT_DIRECTION = true;
    boolean lastRightDirection;
    public static boolean TURN_DIRECTION = true;
    boolean lastTurnDirection;
    public static boolean FLIP_DIRECTION = true;
    boolean lastFlipDirection;

    public static boolean CATAPULT_DIRECTION = true;
    boolean lastCatapultDirection;

    public static double LEFT_POSITION = .1;
    public static double RIGHT_POSITION = .1;
    public static double TURN_POSITION = .1;
    public static double FLIP_POSITION = .1;

    public static double CATAPULT_POSITION = .1;

    public static boolean LEFT_GRIPPER = true;
    public static boolean RIGHT_GRIPPER = true;
    public static boolean TURN_GRIPPER = true;
    public static boolean FLIP_GRIPPER = true;

    public static boolean CATAPULT = true;

    FtcDashboard dashboard;
    private PixelHandlerSubsystem phss;

    private DroneCatapultSubsystem drcatss;

    public void initialize() {
        dashboard = FtcDashboard.getInstance();

        phss = new PixelHandlerSubsystem(this);

        drcatss = new DroneCatapultSubsystem(this);

        telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());


        telemetry.addData("Controls catapult, left, right and turn servos", "");
        telemetry.addData("Switch each on or off,change Direction", "");
        telemetry.addData("Set positions from 0.0 to 1", "");
        telemetry.addData("Record open, closed and mid for left and right", "");
        telemetry.addData("Record home, pickup and deliver for turn", "");
        telemetry.addData("Record home, pickup and deliver for flip", "");

        telemetry.addData("Edit Constants file for these values and test", "");

        telemetry.update();
        //  telemetry.clearAll();
    }

    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {
            run();

            if (LEFT_DIRECTION != lastLeftDirection) {
                if (LEFT_DIRECTION)
                    phss.leftGripper.setDirection(Servo.Direction.FORWARD);
                else
                    phss.leftGripper.setDirection(Servo.Direction.REVERSE);
                lastLeftDirection = LEFT_DIRECTION;
            }
            if (RIGHT_DIRECTION != lastRightDirection) {
                if (RIGHT_DIRECTION)
                    phss.rightGripper.setDirection(Servo.Direction.FORWARD);
                else
                    phss.rightGripper.setDirection(Servo.Direction.REVERSE);
                lastRightDirection = RIGHT_DIRECTION;
            }

            if (TURN_DIRECTION != lastTurnDirection) {
                if (TURN_DIRECTION)
                    phss.turnGrippers.setDirection(Servo.Direction.FORWARD);
                else
                    phss.turnGrippers.setDirection(Servo.Direction.REVERSE);
                lastTurnDirection = TURN_DIRECTION;
            }

            if (FLIP_DIRECTION != lastFlipDirection) {
                if (FLIP_DIRECTION)
                    phss.flipGrippers.setDirection(Servo.Direction.FORWARD);
                else
                    phss.flipGrippers.setDirection(Servo.Direction.REVERSE);
                lastFlipDirection = FLIP_DIRECTION;
            }

            if (CATAPULT_DIRECTION != lastCatapultDirection) {
                if (CATAPULT_DIRECTION)
                    drcatss.catapult.setDirection(Servo.Direction.FORWARD);
                else
                    drcatss.catapult.setDirection(Servo.Direction.REVERSE);
                lastCatapultDirection = CATAPULT_DIRECTION;
            }


            if (LEFT_GRIPPER) {
                phss.positionLeftGripper(LEFT_POSITION);
            }
            if (RIGHT_GRIPPER) {
                phss.positionRightGripper(RIGHT_POSITION);
            }

            if (TURN_GRIPPER)
                phss.positionTurnGripper(TURN_POSITION);

            if (FLIP_GRIPPER)
                phss.positionFlipGripper(FLIP_POSITION);

            if (CATAPULT)
                drcatss.setCatapultPosition(CATAPULT_POSITION);


        }
        reset();
    }
}