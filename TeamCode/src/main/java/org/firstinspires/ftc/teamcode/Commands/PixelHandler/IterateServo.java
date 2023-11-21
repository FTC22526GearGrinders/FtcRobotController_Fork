package org.firstinspires.ftc.teamcode.Commands.PixelHandler;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class IterateServo extends CommandBase {

    double INCREMENT = 0.01;     // amount to slew servo each CYCLE_MS cycle
    final int CYCLE_MS = 50;     // period of each cycle
    double MAX_POS = 1.0;     // Maximum rotational position
    double MIN_POS = 0.0;     // Minimum rotational position
    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;
    boolean rampDown = false;

    private final CommandOpMode opmode;

    private final PixelHandlerSubsystem phss;

    private final Servo servo;

    private double target;
    private boolean atMaxPosn;
    private boolean atMinPosn;


    public IterateServo(PixelHandlerSubsystem phss, Servo servo, double target, CommandOpMode opMode) {
        this.phss = phss;
        this.servo = servo;
        this.opmode = opMode;
        this.target = target;
    }

    @Override
    public void initialize() {
//        if (servo == phss.leftGripper) {
//            MAX_POS = Constants.PixelHandlerConstants.LEFT_GRIPPER_OPEN_POSITION;
//            MIN_POS = Constants.PixelHandlerConstants.LEFT_GRIPPER_CLOSED_POSITION;
//            INCREMENT=.01;
//        }
//        if (servo == phss.rightGripper) {
//            MAX_POS = Constants.PixelHandlerConstants.RIGHT_GRIPPER_OPEN_POSITION;
//            MIN_POS = Constants.PixelHandlerConstants.RIGHT_GRIPPER_CLOSED_POSITION;
//            INCREMENT=.01;
//        }
//
//        if (servo == phss.turnGrippers) {
//            MAX_POS = Constants.PixelHandlerConstants.TURN_DELIVER_POSITION;
//            MIN_POS = Constants.PixelHandlerConstants.TURN_PICKUP_POSITION;
//            INCREMENT=.01;
//        }

        double pulsesInTravel = Math.abs(MAX_POS-MIN_POS);
        double cyclesToTravel = pulsesInTravel/INCREMENT;
    }

    @Override
    public void execute() {
        rampUp = (target == MAX_POS);
        rampDown = (target == MIN_POS);

        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position += INCREMENT;
            if (position >= MAX_POS) {
                position = MAX_POS;
                atMaxPosn = true;
            }
        }
        if (rampDown) {
            // Keep stepping down until we hit the min value.
            position -= INCREMENT;
            if (position <= MIN_POS) {
                position = MIN_POS;
                atMinPosn = true;
            }
        }

        // Set the servo to the new position and pause;
        phss.leftGripper.setPosition(position);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return atMaxPosn && !rampDown || atMinPosn && !rampUp;
    }
}
