package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IO_Subsystem extends SubsystemBase {


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


        dc1 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_1");
        dc2 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_2");
        dc3 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_3");
        dc4 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_4");
        dc5 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_5");
        dc6 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_6");
        dc7 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_7");
        dc8 = myOpMode.hardwareMap.get(DigitalChannel.class, "input_8");

        dc1.setMode(DigitalChannel.Mode.INPUT);
        dc2.setMode(DigitalChannel.Mode.INPUT);
        dc3.setMode(DigitalChannel.Mode.INPUT);
        dc4.setMode(DigitalChannel.Mode.INPUT);
        dc5.setMode(DigitalChannel.Mode.INPUT);
        dc6.setMode(DigitalChannel.Mode.INPUT);
        dc7.setMode(DigitalChannel.Mode.INPUT);
        dc8.setMode(DigitalChannel.Mode.INPUT);


    }

    @Override

    public void periodic() {

    }


}
