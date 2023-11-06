package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;

public class SelectMotionValuesBlue extends CommandBase {

    /*
     *
     *
     * See SelectMotionsBlue for explanations of these values
     *
     *
     *
     * */

    public SelectMotionValuesBlue() {
        boolean bbstart = ActiveMotionValues.getBBStart();
        int lcr = ActiveMotionValues.getLcrpos();
        ActiveMotionValues.setStrafeDistance(0);
        ActiveMotionValues.setAdvancePose(new Pose2d());

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            //******************************************************************************************
            //******************************************************************************************


            case 1://left tape


                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.leftDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.leftRetractPose);

                ActiveMotionValues.setActTag(1);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPose);

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.centerDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.centerRetractPose);

                ActiveMotionValues.setActTag(2);


                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPose);

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://right ta
                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.rightDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.rightRetractPose);

                ActiveMotionValues.setStrafeDistance(-11.5);

                ActiveMotionValues.setActTag(3);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPose);

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape

                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.leftDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.leftRetractPose);


                ActiveMotionValues.setActTag(1);

                setOptions();


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center


                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.centerDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.centerRetractPose);


                ActiveMotionValues.setActTag(2);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

                ActiveMotionValues.setYOffsetPose(new Pose2d());

                ActiveMotionValues.setXOffsetPose(new Pose2d());

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XMYP.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.rightDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.rightRetractPose);


                ActiveMotionValues.setActTag(3);

                setOptions();

                break;

        }
    }

    private void setOptions() {

        int lcr = ActiveMotionValues.getLcrpos() + 10;
        boolean useStageDoor = ActiveMotionValues.getUseStageDoor();
        boolean secondPixel = ActiveMotionValues.getSecondPixel();
        ActiveMotionValues.setStrafeDistance(0);
        ActiveMotionValues.setAdvancePose(new Pose2d());
        ActiveMotionValues.setParkPose(new Pose2d());

        boolean useTruss = !useStageDoor;

        if (ActiveMotionValues.getCenterPark())

            ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose);
        else
            ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose);


        if (useTruss) {

            if (lcr == 11) ActiveMotionValues.setStrafeDistance(11.5);

            if (lcr == 13) ActiveMotionValues.setStrafeDistance(-11.5);


            ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsBlue.nearTrussLineUpPose);

            ActiveMotionValues.setOptionStopPose(FieldConstantsBlue.nearOptionPose);

            if (!secondPixel)

                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.nearParkPose);


            else {
                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));

            }
        }

        if (useStageDoor) {

            if (lcr == 11) {
                ActiveMotionValues.setStrafeDistance(-11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setStrafeDistance(11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose2));
            }

            if (lcr == 13) {
                ActiveMotionValues.setStrafeDistance(11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }

            ActiveMotionValues.setOptionStopPose(FieldConstantsBlue.centerOptionPose);

            if (!secondPixel)
                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.centerParkPose);
            else {
                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));

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
