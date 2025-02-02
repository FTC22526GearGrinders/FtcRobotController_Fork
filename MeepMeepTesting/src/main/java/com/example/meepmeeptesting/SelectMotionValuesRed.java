package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;

public class SelectMotionValuesRed {

    public SelectMotionValuesRed() {

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


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.leftDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XPYM.clearPose);


                ActiveMotionValues.setActTag(4);


                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setParkPose(new Pose2d());

                Pose2d parkPoseOffset = new Pose2d(0, 0, 90);

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPose.plus(parkPoseOffset));

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose.plus(parkPoseOffset));


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                xyOffsetPose = new Pose2d();


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.centerDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.centerRetractPose);

                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                parkPoseOffset = new Pose2d(0, 0, 90);

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPose.plus(parkPoseOffset));

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose.plus(parkPoseOffset));


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://right ta

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.rightDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XPYM.clearPose);


                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                parkPoseOffset = new Pose2d(0, 0, 90);

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPose.plus(parkPoseOffset));

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose.plus(parkPoseOffset));


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XMYM.clearPose);


                ActiveMotionValues.setActTag(4);

                setOptions();


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center


                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.centerDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.centerRetractPose);


                ActiveMotionValues.setActTag(5);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.rightDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XMYM.clearPose);

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

        double strafeDistance = 11.5;

        if (useTruss) {

            if (lcr == 11) ActiveMotionValues.setStrafeDistance(strafeDistance);

            if (lcr == 13) ActiveMotionValues.setStrafeDistance(-strafeDistance);


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
                ActiveMotionValues.setStrafeDistance(strafeDistance);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setStrafeDistance(-strafeDistance);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose2));
            }
            if (lcr == 13) {
                ActiveMotionValues.setStrafeDistance(-strafeDistance);
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
}
