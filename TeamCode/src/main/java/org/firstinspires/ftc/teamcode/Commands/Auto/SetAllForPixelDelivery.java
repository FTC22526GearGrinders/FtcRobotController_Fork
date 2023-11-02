package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;

public class SetAllForPixelDelivery extends SequentialCommandGroup {


    public SetAllForPixelDelivery(Drive_Subsystem drive, ArmSubsystem arm, PixelHandlerSubsystem phss, int level) {

        addCommands(

//                new RetractClawArm(phss),
//                new SetTagDistance(level),
//                new PositionPHArm(arm,level),
//                new DriveToAprilTagTeleop(drive,level),
//


        );


    }


}
