package org.firstinspires.ftc.teamcode.OpCodes_Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Commands.Elevator.HoldElevatorAtPosition;
import org.firstinspires.ftc.teamcode.Commands.Elevator.JogElevatorMinus;
import org.firstinspires.ftc.teamcode.Commands.Elevator.JogElevatorPlus;
import org.firstinspires.ftc.teamcode.Commands.Elevator.PositionElevator;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Elevator_Subsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;

    protected Elevator_Subsystem elevator;
    protected Claw_Subsystem claw;


    protected GamepadEx driverGamepad;
    protected GamepadEx secondaryGamepad;


    @Override
    public void initialize() {
        drive = new Drive_Subsystem(this);


        elevator = new Elevator_Subsystem(this);

        claw = new Claw_Subsystem(this);

//new TensorFlowVision(vision_subsystems).schedule();
        // register(drive, elevator, claw);

        driverGamepad = new GamepadEx(gamepad1);

        secondaryGamepad = new GamepadEx(gamepad2);


//        drive.setDefaultCommand(new JogDrive(this.drive, driverGamepad));
//
//        elevator.setDefaultCommand(new HoldElevatorAtPosition(this.elevator));


        driverGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                // .whenPressed(new CloseClaw(this));
                .whenPressed(new InstantCommand(claw::close, claw));

        driverGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(claw::open, claw));

        driverGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whileHeld(new JogElevatorPlus(this.elevator));

        driverGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whileHeld(new JogElevatorMinus(this.elevator));

        driverGamepad.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new PositionElevator(this.elevator, Constants.ElevatorConstants.LOW_JUNCTION).withTimeout(10000));

        driverGamepad.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new PositionElevator(this.elevator, Constants.ElevatorConstants.MID_JUNCTION).withTimeout(10000));

        driverGamepad.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new PositionElevator(this.elevator, Constants.ElevatorConstants.HIGH_JUNCTION).withTimeout(10000));

        driverGamepad.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new PositionElevator(this.elevator, Constants.ElevatorConstants.GROWND_JUNCTION).withTimeout(10000));

  //      driverGamepad.getGamepadButton(GamepadKeys.Button.BACK)
    //            .whenPressed(new InstantCommand(drive::toggleFieldOrient));

   //     secondaryGamepad.getGamepadButton(GamepadKeys.Button.A)


     //   secondaryGamepad.getGamepadButton(GamepadKeys.Button.B)


        //secondaryGamepad.getGamepadButton(GamepadKeys.Button.X)


      //  secondaryGamepad.getGamepadButton(GamepadKeys.Button.Y)

       // secondaryGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)

     //   secondaryGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)

        //secondaryGamepad.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)


        //secondaryGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)

       // secondaryGamepad.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)

        //secondaryGamepad.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)


       // secondaryGamepad.getGamepadButton(GamepadKeys.Button.BACK)


        register(drive, elevator, claw);

       drive.setDefaultCommand(new JogDrive(this.drive, driverGamepad));

        elevator.setDefaultCommand(new HoldElevatorAtPosition(this.elevator));
    }


    public void run() {

        CommandScheduler.getInstance().run();

        //elevator.showTelemetry();
       // drive.showTelemetry();
    }


}