package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;

public class SelectMotionValuesBlue extends CommandBase {

    private final boolean bbstart;

    private int lcr;

    public SelectMotionValuesBlue(boolean bbStart, int lcr) {

        this.bbstart = bbStart;
        this.lcr = lcr;

    }

    public SelectMotionValuesBlue() {
        bbstart = ActiveMotionValues.getBBStart();
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

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XPYP.LeftTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2));


                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XPYP.LeftTapeMid.getX());


                ActiveMotionValues.setFinalPose(FieldConstantsBlue.AprilTagConstants.atag1.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(1);

                break;

            case 2://center Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XPYP.CenterTapeMid.getY());

                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XPYP.CenterTapeMid.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsBlue.AprilTagConstants.atag2.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(2);

                break;


            case 3://right tape Red

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XPYP.RightTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2));

                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XPYP.RightTapeMid.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsBlue.AprilTagConstants.atag3.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(3);

                break;

            //RED
            case 11://left tape Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XMYP.LeftTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2));


                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XMYP.LeftTapeMid.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsBlue.wingPose);


                break;

            case 12://center Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(6);

                ActiveMotionValues.setStrafeDistance(-10);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XMYP.CenterTapeMid.getY());

                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XMYP.CenterTapeMid.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsBlue.wingPose);

                break;


            case 13://right tape Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);
                ;
                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XMYP.RightTapeMid.getY() - (Constants.TapeConstants.tapeLength / 2));


                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XMYP.RightTapeMid.getX());

                ActiveMotionValues.setActTag(2);

                ActiveMotionValues.setFinalPose(FieldConstantsBlue.wingPose);

                break;


        }


    }
}
