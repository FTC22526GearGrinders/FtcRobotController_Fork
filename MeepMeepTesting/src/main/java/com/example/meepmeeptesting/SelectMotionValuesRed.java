package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;

public class SelectMotionValuesRed {

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


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(3);

                ActiveMotionValues.setStrafeDistance(12);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);

                double y = FieldConstantsRed.XPYM.LeftTapeMid.getY()
                        - Constants.TapeConstants.tapeLength * 2 / 3
                        // + Constants.TapeConstants.tapeWidth / 2
                        - Constants.RobotConstants.pixelDropTranslation.getY()
                        - ActiveMotionValues.getyOffset();


                double x = FieldConstantsRed.XPYM.LeftTapeMid.getX()
                        + Constants.RobotConstants.pixelDropTranslation.getX()
                        + ActiveMotionValues.getxOffset();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));

                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setLastPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                ActiveMotionValues.setyOffset(0);// adds to forward motion to control position where pixel is  dropped

                ActiveMotionValues.setxOffset(0);//can be used to offset x motion so pixel is place off the x center

                ActiveMotionValues.setRetractDistance(24);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose


                x = FieldConstantsRed.XPYM.CenterTapeMid.getX();

                y = FieldConstantsRed.XPYM.CenterTapeMid.getY()

                        + Constants.RobotConstants.pixelDropTranslation.getY()
                        - ActiveMotionValues.getyOffset();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));

                double x1 = x;

                double y1 = y - ActiveMotionValues.getRetractDistance();

                ActiveMotionValues.setRetractPose(new Pose2d(x1, y1, Math.toRadians(-90)));

                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setLastPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://right tape

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);

                ActiveMotionValues.setStrafeDistance(-1);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);//start pose




                x = FieldConstantsRed.XPYM.RightTapeMid.getX()
                        + Constants.RobotConstants.pixelDropTranslation.getX()
                        + ActiveMotionValues.getxOffset();

                y = FieldConstantsRed.XPYM.RightTapeMid.getY() - Constants.TapeConstants.tapeLength / 2;



                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));


                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setLastPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(4);
                ActiveMotionValues.setStrafeDistance(12);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                x = FieldConstantsRed.XMYM.LeftTapeMid.getX() + Constants.TapeConstants.tapeWidth / 2
                        - Constants.RobotConstants.pixelDropTranslation.getX()
                        + ActiveMotionValues.getxOffset();

                y = FieldConstantsRed.XMYM.LeftTapeMid.getY()
                        - Constants.TapeConstants.tapeLength - Constants.RobotConstants.pixelDropTranslation.getY();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));




                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setLastPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);


//set up options depending on selections

                // setCommonMotion();

                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center


                ActiveMotionValues.setyOffset(0);// adds to forward motion to control position where pixel is  dropped

                ActiveMotionValues.setxOffset(0);//can be used to offset x motion so pixel is place off the x center

                ActiveMotionValues.setRetractDistance(24);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative

                ActiveMotionValues.setStrafeDistance(12);

                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                x = FieldConstantsRed.XMYM.CenterTapeMid.getX();

                y = FieldConstantsRed.XMYM.CenterTapeMid.getY()
                        + Constants.RobotConstants.pixelDropTranslation.getY()
                        - ActiveMotionValues.getyOffset();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));



                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setLastPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);


//set up options depending on selections

                //     setCommonMotion();


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(3);

                ActiveMotionValues.setStrafeDistance(-12);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.advancePose);

                y = FieldConstantsRed.XMYM.RightTapeMid.getY()
                        - Constants.TapeConstants.tapeLength * 2 / 3
                        // + Constants.TapeConstants.tapeWidth / 2
                        - Constants.RobotConstants.pixelDropTranslation.getY()
                        - ActiveMotionValues.getyOffset();


                x = FieldConstantsRed.XMYM.RightTapeMid.getX()
                        + Constants.RobotConstants.pixelDropTranslation.getX()
                        + ActiveMotionValues.getxOffset();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));




                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setLastPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);


                break;

        }
    }

    //set up options depending on selections
    public boolean setCommonMotion() {

        int lcr = ActiveMotionValues.getLcrpos() + 10;
        boolean centerTape = lcr == 12;
        boolean useStageDoor = ActiveMotionValues.getUseStageDoor();
        boolean secondPixel = ActiveMotionValues.getSecondPixel();

        boolean useTruss = !useStageDoor;

        //if using the truss center tape has one less move than left or right
        if (useTruss) {

            if (lcr == 12) {
//write over earlier retract value point 3 so robot retracts in one motion

                ActiveMotionValues.setxPoint(2, FieldConstantsRed.nearTrussLineUp.getX());

                ActiveMotionValues.setyPoint(2, FieldConstantsRed.nearTrussLineUp.getY());

                ActiveMotionValues.setxPoint(3, FieldConstantsRed.clearForSecondPixelTruss.getX());

                ActiveMotionValues.setyPoint(3, FieldConstantsRed.clearForSecondPixelTruss.getY());

                ActiveMotionValues.setLastPose(FieldConstantsRed.nearBBSideParkPose);

                ActiveMotionValues.setPointsUsed(3);

                //write over earlier retract value point 4
            } else {

                ActiveMotionValues.setxPoint(4, FieldConstantsRed.nearTrussLineUp.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsRed.nearTrussLineUp.getY());

                ActiveMotionValues.setxPoint(5, FieldConstantsRed.clearForSecondPixelTruss.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsRed.clearForSecondPixelTruss.getY());

                ActiveMotionValues.setLastPose(FieldConstantsRed.nearBBSideParkPose);

                ActiveMotionValues.setPointsUsed(5);


            }
        }


        if (useStageDoor) {

            if (lcr == 12) {
//center tape moves across to right tape x center


                ActiveMotionValues.setxPoint(3, FieldConstantsRed.XMYM.LeftTapeMid.getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));

                ActiveMotionValues.setxPoint(4, FieldConstantsRed.stageDoorLineUpPose2.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsRed.stageDoorLineUpPose2.getY());

                ActiveMotionValues.setxPoint(5, FieldConstantsRed.nearBBSDLineUp.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsRed.nearBBSDLineUp.getY());

                ActiveMotionValues.setLastPose(FieldConstantsRed.centerBBSideParkPose);

                ActiveMotionValues.setPointsUsed(5);

            } else {

                ActiveMotionValues.setxPoint(4, FieldConstantsRed.XMYM.CenterTapeMid.getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(3));

                ActiveMotionValues.setxPoint(5, FieldConstantsRed.stageDoorLineUpPose13.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsRed.stageDoorLineUpPose13.getY());

                ActiveMotionValues.setxPoint(6, FieldConstantsRed.nearBBSDLineUp.getX());

                ActiveMotionValues.setyPoint(6, FieldConstantsRed.nearBBSDLineUp.getY());

                ActiveMotionValues.setLastPose(FieldConstantsRed.centerBBSideParkPose);

                ActiveMotionValues.setPointsUsed(6);
            }

            if (secondPixel) {

                int tag = lcr - 10;

                ActiveMotionValues.setActTag(tag);

                ActiveMotionValues.setLastPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

            }
        }


        return true;

    }
}
