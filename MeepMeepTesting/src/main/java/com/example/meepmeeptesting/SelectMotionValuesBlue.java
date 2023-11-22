package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;


public class SelectMotionValuesBlue {


    public SelectMotionValuesBlue() {

        boolean bbstart = ActiveMotionValues.getBBStart();
        int lcr = ActiveMotionValues.getLcrpos();
        ActiveMotionValues.setStrafeDistance(0);
        ActiveMotionValues.setAdvancePose(new Pose2d());
        ActiveMotionValues.setClearPose(new Pose2d());
        ActiveMotionValues.setParkPose(new Pose2d());


        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            //******************************************************************************************
            //******************************************************************************************


            case 1://left tape


                Pose2d xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.leftRetractPose);

                ActiveMotionValues.setActTag(1);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setParkPose(new Pose2d());

                Pose2d parkPoseOffset = new Pose2d(0, 0, Math.toRadians(-90));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose.plus(parkPoseOffset));

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose.plus(parkPoseOffset));



                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                xyOffsetPose = new Pose2d();

                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.centerDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.centerRetractPose);

                ActiveMotionValues.setActTag(2);


                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setParkPose(new Pose2d());

                parkPoseOffset = new Pose2d(0, 0, Math.toRadians(-90));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose.plus(parkPoseOffset));

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose.plus(parkPoseOffset));


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://right ta

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.rightDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsBlue.XPYP.clearPose);

                ActiveMotionValues.setActTag(3);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                parkPoseOffset = new Pose2d(0, 0, Math.toRadians(-90));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose.plus(parkPoseOffset));

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose.plus(parkPoseOffset));


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XMYP.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsBlue.XMYP.clearPose);


                ActiveMotionValues.setActTag(1);

                setOptions();


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.centerDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.centerRetractPose);


                ActiveMotionValues.setActTag(2);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Blue


                //robot moves in Y

                xyOffsetPose = new Pose2d();


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XMYP.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.rightDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsBlue.XMYP.clearPose);


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
        ActiveMotionValues.setParkPose(new Pose2d());

        double strafeDistance = -11.5;


        boolean useTruss = !useStageDoor;

        if (ActiveMotionValues.getCenterPark())

            ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose);
        else
            ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose);


        if (useTruss) {

            if (lcr == 11) ActiveMotionValues.setStrafeDistance(strafeDistance);

            if (lcr == 13) ActiveMotionValues.setStrafeDistance(-strafeDistance);


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
                ActiveMotionValues.setStrafeDistance(-strafeDistance);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setStrafeDistance(strafeDistance);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose2));
            }

            if (lcr == 13) {
                ActiveMotionValues.setStrafeDistance(strafeDistance);
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


}