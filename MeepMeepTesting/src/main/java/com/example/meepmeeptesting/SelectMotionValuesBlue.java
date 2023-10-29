package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;


public class SelectMotionValuesBlue {

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


    public SelectMotionValuesBlue() {
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


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.leftDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.leftRetractPose);


                ActiveMotionValues.setStrafePose(FieldConstantsBlue.XPYP.lrStrafePose);

                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setLastPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPose));


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

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.centerDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.centerRetractPose);

                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setLastPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
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


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.rightDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.rightRetractPose);


                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setLastPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
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


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.leftDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.leftRetractPose);


                ActiveMotionValues.setActTag(2);

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


                ActiveMotionValues.setActTag(5);

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

        if (ActiveMotionValues.getCenterPark())

            ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterParkPose);
        else
            ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearParkPose);


        if (useTruss) {

            if (lcr == 11) ActiveMotionValues.setStrafeDistance(11.75 / 2);

            if (lcr == 13) ActiveMotionValues.setStrafeDistance(11.75 / 2);


            ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsRed.nearTrussLineUpPose);

            ActiveMotionValues.setStrafeToAprilTagPose(FieldConstantsRed.nearLookForAprilTagPose);

            if (!secondPixel)

                ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose);

            else {

                ActiveMotionValues.setStrafeToAprilTagPose(ActiveMotionValues.getActiveTagPose()
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPose));

            }
        }

        if (useStageDoor) {

            if (lcr == 11) {
                ActiveMotionValues.setStrafeDistance(11.75 / 2);
                ActiveMotionValues.setClearStageDoorPose((FieldConstantsRed.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setStrafeDistance(-11.75 / 2);
                ActiveMotionValues.setClearStageDoorPose((FieldConstantsRed.stageDoorLineUpPose2));
            }


            if (lcr == 13) {
                ActiveMotionValues.setStrafeDistance(11.75 / 2);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose13));
            }

            ActiveMotionValues.setStrafeToAprilTagPose(FieldConstantsRed.centerLookForAprilTagPose);

            if (!secondPixel)

                ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose);

            else {

                ActiveMotionValues.setStrafeToAprilTagPose(ActiveMotionValues.getActiveTagPose()
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPose));

            }

        }
    }

}
