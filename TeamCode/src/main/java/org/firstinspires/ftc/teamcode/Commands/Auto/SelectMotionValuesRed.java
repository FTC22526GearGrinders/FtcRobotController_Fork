package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;

public class SelectMotionValuesRed extends CommandBase {


    private final boolean bbstart;

    private int lcr;

    public SelectMotionValuesRed(boolean bbStart, int lcr) {

        this.bbstart = bbStart;
        this.lcr = lcr;

    }


    @Override
    public void initialize() {

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {


            //RED
            case 1://left tape Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;

                ActiveMotionValues.strafeDistance = 0;


                ActiveMotionValues.startPose = FieldConstantsRed.XPYM.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsRed.XPYM.LeftTapeMid.getY() - (FieldConstantsRed.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsRed.XPYM.LeftTapeMid.getX();


                ActiveMotionValues.finalPose = FieldConstantsRed.AprilTagConstants.atag4.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose);


                break;

            case 2://center Red


                ActiveMotionValues.yOffset = -4;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = -6;

                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsRed.XPYM.StartPos;//start pose;

                ActiveMotionValues.yFirstPoint = FieldConstantsRed.XPYM.CenterTapeMid.getY() - FieldConstantsRed.ROBOT.length;

                ActiveMotionValues.xFirstPoint = FieldConstantsRed.XPYM.CenterTapeMid.getX();

                ActiveMotionValues.finalPose = FieldConstantsRed.AprilTagConstants.atag5.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose);


                break;


            case 3://right tape Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;

                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsRed.XPYM.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsRed.XPYM.rightTapeMid.getY() - (FieldConstantsRed.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsRed.XPYM.rightTapeMid.getX();

                ActiveMotionValues.finalPose = FieldConstantsRed.AprilTagConstants.atag6.plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose);


                break;


            case 11://left tape Red


                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;
                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsRed.XMYM.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsRed.XMYM.LeftTapeMid.getY() - (FieldConstantsRed.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsRed.XMYM.LeftTapeMid.getX();


//
                ActiveMotionValues.finalPose = FieldConstantsRed.wingPose;
//


                break;

            case 12://center Red

                ;
                ActiveMotionValues.yOffset = 12;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 1;
                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsRed.XMYM.StartPos;//start pose;

                ActiveMotionValues.yFirstPoint = FieldConstantsRed.XMYM.CenterTapeMid.getY();

                ActiveMotionValues.xFirstPoint = FieldConstantsRed.XMYM.CenterTapeMid.getX();


                ActiveMotionValues.finalPose = FieldConstantsRed.wingPose;

                break;


            case 13://right tape Red

                ActiveMotionValues.yOffset = 0;

                ActiveMotionValues.xOffset = 0;

                ActiveMotionValues.retractDistance = 0;
                ActiveMotionValues.strafeDistance = 0;

                ActiveMotionValues.startPose = FieldConstantsRed.XMYM.StartPos;//start pose

                ActiveMotionValues.yFirstPoint = FieldConstantsRed.XMYM.RightTapeMid.getY() + (FieldConstantsRed.TAPE.tapeLength / 2);


                ActiveMotionValues.xFirstPoint = FieldConstantsRed.XMYM.RightTapeMid.getX();

                ActiveMotionValues.finalPose = FieldConstantsRed.wingPose;

                break;


        }
    }
}
