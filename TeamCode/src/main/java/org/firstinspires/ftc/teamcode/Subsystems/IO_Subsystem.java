package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IO_Subsystem extends SubsystemBase {

    public DigitalChannel dc0;  // Hardware Device Object
    public DigitalChannel dc1;  // Hardware Device Object
    public DigitalChannel dc2;  // Hardware Device Object
    public DigitalChannel dc3;  // Hardware Device Object
    public DigitalChannel dc4;  // Hardware Device Object
    public DigitalChannel dc5;  // Hardware Device Object
    public DigitalChannel dc6;  // Hardware Device Object\
    public DigitalChannel dc7;  // Hardware Device Object
    public DigitalChannel dc8;  // Hardware Device Object
    private Telemetry telemetry;
    private final CommandOpMode myOpMode;


    public IO_Subsystem(CommandOpMode opMode) {
        myOpMode = opMode;


        dc0 = myOpMode.hardwareMap.get(DigitalChannel.class, "red alliance");
        dc1 = myOpMode.hardwareMap.get(DigitalChannel.class, "backboard start");
        dc3 = myOpMode.hardwareMap.get(DigitalChannel.class, ("lcr one bit"));
        dc2 = myOpMode.hardwareMap.get(DigitalChannel.class, "lcr two bit");
        dc4 = myOpMode.hardwareMap.get(DigitalChannel.class, "use stage door");
        dc5 = myOpMode.hardwareMap.get(DigitalChannel.class, "center park");


        //        dc4 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_5");
        //        dc5 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_5");
//        dc6 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_6");
//        dc7 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_7");
//        dc8 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_8");

        dc0.setMode(DigitalChannel.Mode.INPUT);
        dc1.setMode(DigitalChannel.Mode.INPUT);
        dc2.setMode(DigitalChannel.Mode.INPUT);
        dc3.setMode(DigitalChannel.Mode.INPUT);
        dc4.setMode(DigitalChannel.Mode.INPUT);
        dc5.setMode(DigitalChannel.Mode.INPUT);

//        dc4.setMode(DigitalChannel.Mode.INPUT);
//        dc5.setMode(DigitalChannel.Mode.INPUT);
//        dc6.setMode(DigitalChannel.Mode.INPUT);
//        dc7.setMode(DigitalChannel.Mode.INPUT);
//        dc8.setMode(DigitalChannel.Mode.INPUT);


    }

    @Override

    public void periodic() {

    }

    public void showTelemetry(Telemetry telemetry) {
        telemetry.addData("RedAlliance", dc0.getState());
        telemetry.addData("BackBoardStart", dc1.getState());
        telemetry.addData("One Bit", dc3.getState());
        telemetry.addData("Two Bit", dc2.getState());
        telemetry.addData("Use Stage Door", dc4.getState());
        telemetry.addData("Center Park", dc5.getState());




        telemetry.update();


    }


}
