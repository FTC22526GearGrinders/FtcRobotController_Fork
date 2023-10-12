package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.arcrobotics.ftclib.command.CommandBase;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;

public class SelectMotionValuesRed extends CommandBase {

    /*
     *
     *
     * See SelectMotionsBlue for explanations of these values
     *
     *
     *
     * */


    private final boolean bbstart;

    private int lcr;
    

    public SelectMotionValuesRed(){
        bbstart=ActiveMotionValues.getBBStart();
        lcr = ActiveMotionValues.getLcrpos();
    }


    @Override
    public void initialize() {

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;

        switch (motionSelected) {

            case 1://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose

                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XPYM.LeftTapeMid.getX()- Constants.RobotConstants.length/2);

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.LeftTapeMid.getY()+ Constants.TapeConstants.tapeLength/2);

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() );

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.AprilTagConstants.atag4.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(4);

                break;

            case 2://center


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);//MUST MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XPYM.StartPos.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(FieldConstantsRed.XPYM.StartPos.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsRed.AprilTagConstants.atag5.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(5);

                break;


            case 3://right tape 

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose


                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.RightTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2)

                        + Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XPYM.RightTapeMid.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.AprilTagConstants.atag6.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(6);

                break;

            //RED
            case 11://left tape 


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.LeftTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2)

                        + Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XMYM.LeftTapeMid.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.wingPose);


                break;

            case 12://center 


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);//MUST MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XMYM.StartPos.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setFinalPose(FieldConstantsRed.wingPose);

                break;


            case 13://right tape Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.RightTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2)

                        + Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XMYM.RightTapeMid.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.wingPose);

                break;


        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
