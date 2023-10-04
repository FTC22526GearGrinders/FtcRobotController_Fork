package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;

public class SelectMotionValuesBlue extends CommandBase {

private final boolean bbstart;

private int lcr;

    public SelectMotionValuesBlue(boolean bbStart, int lcr) {

        this.bbstart = bbStart;
        this.lcr = lcr;

    }


    @Override
    public void initialize() {

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            case 1://left tape Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;

                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsBlue.XPYP.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsBlue.XPYP.LeftTapeMid.getY() + (FieldConstantsBlue.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsBlue.XPYP.LeftTapeMid.getX();


                ActiveMotionValues.finalPose = FieldConstantsBlue.AprilTagConstants.atag1.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose);


                break;

            case 2://center Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 1;

                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsBlue.XPYP.StartPos;//start pose;

                ActiveMotionValues.yFirstPoint = FieldConstantsBlue.XPYP.CenterTapeMid.getY();

                ActiveMotionValues.xFirstPoint = FieldConstantsBlue.XPYP.CenterTapeMid.getX();

                ActiveMotionValues.finalPose = FieldConstantsBlue.AprilTagConstants.atag2.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose);


                break;


            case 3://right tape Red

                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;

                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsBlue.XPYP.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsBlue.XPYP.RightTapeMid.getY() + (FieldConstantsBlue.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsBlue.XPYP.RightTapeMid.getX();

                ActiveMotionValues.finalPose = FieldConstantsBlue.AprilTagConstants.atag3.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose);


                break;

            //RED
            case 11://left tape Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;

                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsBlue.XMYP.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsBlue.XMYP.LeftTapeMid.getY() + (FieldConstantsBlue.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsBlue.XMYP.LeftTapeMid.getX();

                       ActiveMotionValues.finalPose = FieldConstantsBlue.wingPose;


                break;

            case 12://center Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 6;

                ActiveMotionValues.strafeDistance = -10;

                ActiveMotionValues.startPose = FieldConstantsBlue.XMYP.StartPos;//start pose;

                ActiveMotionValues.yFirstPoint = FieldConstantsBlue.XMYP.CenterTapeMid.getY();

                ActiveMotionValues.xFirstPoint = FieldConstantsBlue.XMYP.CenterTapeMid.getX();

                ActiveMotionValues.finalPose = FieldConstantsBlue.wingPose;

                break;


            case 13://right tape Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;

                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsBlue.XMYP.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsBlue.XMYP.RightTapeMid.getY() - (FieldConstantsBlue.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsBlue.XMYP.RightTapeMid.getX();

                ActiveMotionValues.atag = 2;

                ActiveMotionValues.finalPose = FieldConstantsBlue.wingPose;

                break;


        }


    }
}
