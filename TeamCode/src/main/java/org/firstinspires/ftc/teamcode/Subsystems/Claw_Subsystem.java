package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Claw_Subsystem extends SubsystemBase {


    Servo claw;

    private Telemetry telemetry;

    private CommandOpMode myOpMode;


    public Claw_Subsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        claw = myOpMode.hardwareMap.get(Servo.class, "claw");

    }

    @Override

    public void periodic() {

    }

    public void setClawPosition(double position) {
        claw.setPosition(position);
    }

    public void close() {
        setClawPosition(Constants.ClawConstants.CLAW_CLOSE_POSITION);
    }


    public void open() {
        setClawPosition(Constants.ClawConstants.CLAW_OPEN_POSITION);

    }

    public void setDirection(boolean fwd) {
        if (fwd)
            claw.setDirection(Servo.Direction.FORWARD);
        else
            claw.setDirection(Servo.Direction.REVERSE);
    }



}
