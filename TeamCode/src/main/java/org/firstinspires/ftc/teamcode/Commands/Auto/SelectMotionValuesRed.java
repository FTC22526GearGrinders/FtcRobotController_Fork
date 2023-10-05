package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.arcrobotics.ftclib.command.CommandBase;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;

public class SelectMotionValuesRed extends CommandBase {


    private final boolean bbstart;

    private int lcr;

    public SelectMotionValuesRed(boolean bbStart, int lcr) {

        this.bbstart = bbStart;
        this.lcr = lcr;

    }

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


            //RED
            case 1://left tape Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(0);

                ActiveMotionValues.setStrafeDistance(0);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.LeftTapeMid.getY() - (Constants.TapeConstants.tapeLength / 2));


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XPYM.LeftTapeMid.getX());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.AprilTagConstants.atag4.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(4);

                break;

            case 2://center Red


                ActiveMotionValues.setyOffset(-4);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-6);

                ActiveMotionValues.setStrafeDistance( 0);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.CenterTapeMid.getY() - Constants.RobotConstants.length);

                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XPYM.CenterTapeMid.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsRed.AprilTagConstants.atag5.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(5);

                break;


            case 3://right tape Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(0);

                ActiveMotionValues.setStrafeDistance(0);



                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.rightTapeMid.getY() - (Constants.TapeConstants.tapeLength / 2));


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XPYM.rightTapeMid.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsRed.AprilTagConstants.atag6.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(6);
                break;


            case 11://left tape Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(0);

                ActiveMotionValues.setStrafeDistance(0);



                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.LeftTapeMid.getY() - (Constants.TapeConstants.tapeLength / 2));


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XMYM.LeftTapeMid.getX());


//
                ActiveMotionValues.setFinalPose(FieldConstantsRed.wingPose);
//


                break;

            case 12://center Red


                ActiveMotionValues.setyOffset(12);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(0);



                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.CenterTapeMid.getY());

                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XMYM.CenterTapeMid.getX());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.wingPose);

                break;


            case 13://right tape Red

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(0);

                ActiveMotionValues.setStrafeDistance(0);



                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.RightTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2));


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XMYM.RightTapeMid.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsRed.wingPose);

                break;


        }
    }
}
