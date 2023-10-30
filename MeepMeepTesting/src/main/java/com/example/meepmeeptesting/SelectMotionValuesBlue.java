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


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.leftDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.leftRetractPose);


//                ActiveMotionValues.setStrafePose(FieldConstantsBlue.XPYP.lrStrafePose);

                ActiveMotionValues.setActTag(1);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearParkPose);


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


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearParkPose);


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


                ActiveMotionValues.setActTag(3);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearParkPose);


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
        boolean centerTape = lcr == 12;
        boolean useStageDoor = ActiveMotionValues.getUseStageDoor();
        boolean secondPixel = ActiveMotionValues.getSecondPixel();

        boolean useTruss = !useStageDoor;

        if (ActiveMotionValues.getCenterPark())

            ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterParkPose);
        else
            ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearParkPose);


        if (useTruss) {

            if (lcr == 11) ActiveMotionValues.setStrafeDistance(-11.5);

            if (lcr == 13) ActiveMotionValues.setStrafeDistance(11.5);


            ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsBlue.nearTrussLineUPose);

            ActiveMotionValues.setStrafeToAprilTagPose(FieldConstantsBlue.nearLookForAprilTagPose);

            if (!secondPixel)

                ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose);

            else {

                ActiveMotionValues.setSecondAprilTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));

            }
        }

        if (useStageDoor) {

            if (lcr == 11) {
                ActiveMotionValues.setStrafeDistance(11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setStrafeDistance(11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose2));
            }


            if (lcr == 13) {
                ActiveMotionValues.setStrafeDistance(-11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }
            ActiveMotionValues.setStrafeToAprilTagPose(FieldConstantsBlue.centerLookForAprilTagPose);

            if (!secondPixel)

                ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose);

            else {

                ActiveMotionValues.setSecondAprilTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));

            }

        }
    }

}
