package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
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


    public SelectMotionValuesRed() {
        bbstart = ActiveMotionValues.getBBStart();
        lcr = ActiveMotionValues.getLcrpos();

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            //******************************************************************************************
            //******************************************************************************************


            case 1://left tape


                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.leftDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.leftRetractPose);


                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.centerDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.centerRetractPose);

                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://right ta
                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.rightDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.rightRetractPose);


                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape

                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.leftDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.leftRetractPose);

                ActiveMotionValues.setActTag(4);

                setOptions();


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center


                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.centerDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.centerRetractPose);


                ActiveMotionValues.setActTag(5);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.rightDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.rightRetractPose);


                ActiveMotionValues.setActTag(6);

                setOptions();

                break;

        }
    }

    private void setOptions() {

        int lcr = ActiveMotionValues.getLcrpos() + 10;
        boolean centerTape = lcr == 12;
        boolean useStageDoor = ActiveMotionValues.getUseStageDoor();
        boolean secondPixel = ActiveMotionValues.getSecondPixel();

        boolean useTruss = !useStageDoor;

        if (useTruss) {

            if (lcr == 11) ActiveMotionValues.setStrafeDistance(11.5 / 2);

            if (lcr == 13) ActiveMotionValues.setStrafeDistance(11.5 / 2);


            ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsRed.nearTrussLineUpPose);

            ActiveMotionValues.setOptionStopPose(FieldConstantsRed.nearOptionPose);

            if (!secondPixel)

                ActiveMotionValues.setOptionTargetPose(FieldConstantsRed.nearParkPose);

            else {

                ActiveMotionValues.setOptionTargetPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPose));

            }
        }

        if (useStageDoor) {

            if (lcr == 11) {
                ActiveMotionValues.setStrafeDistance(-11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setStrafeDistance(11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose2));
            }
            if (lcr == 13) {
                ActiveMotionValues.setStrafeDistance(11.5 / 2);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose13));
            }

            ActiveMotionValues.setOptionStopPose(FieldConstantsRed.centerOptionPose);

            if (!secondPixel)
                ActiveMotionValues.setOptionTargetPose(FieldConstantsRed.centerParkPose);
            else {
                ActiveMotionValues.setOptionTargetPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPose));
            }
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
