package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class DroneCatapultSubsystem extends SubsystemBase {


   public Servo catapult;



    private final CommandOpMode myOpMode;



    public DroneCatapultSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        catapult = myOpMode.hardwareMap.get(Servo.class, "catapult");

        catapult.setDirection(Servo.Direction.FORWARD);
    }

    @Override

    public void periodic() {

    }


    public void setCatapultPosition(double position) {
        catapult.setPosition(position);
    }

    public void lockCatapult() {
        setCatapultPosition(Constants.CatapultConstants.CATAPULT_LOCK_POSITION);
    }


    public void releaseCatapult() {
        setCatapultPosition(Constants.CatapultConstants.CATAPULT_RELEASE_POSITION);

    }




}
