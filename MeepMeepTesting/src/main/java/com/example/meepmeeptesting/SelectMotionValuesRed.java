package com.example.meepmeeptesting;




/*
 *Values are taken fron the FieldConstantsColor files to build values for the auto trajectories to use.
 *
 *
 * Motions are selected based on alliance, backdrop (or not ) start and a value called lcr from the vision system.
 * 1=left
 * 2=center
 * 3=right
 * 10 is added on for non backboard starts
 * Alliance is selected by digital input 0. Backboard start by digital input 2
 * Digital inputs 2 and 3 can be used to set lcr for testing. Inputs work in binary fashion.
 *
 * Values are grouped by quadrants using the signs of the x and y values P and M are plus and minus
 *
 * XPYP is the blue backboard start
 * XMYP is the blue non backboard start
 * XPYM is the red backboard start
 * XMYM is the red non backboard start
 *
 *
 *
 * Motions are based on the center of the robot. To compensate, 1/2 robot length is used for Y start positions amd for
 * other values as required. Motions are in the Y direction. Red Y values are negative, Blue Y values are positive.
 * So a red start position will look like -Field length/ 2 + robot length /2. Blue valus would be +Field length /2 - robot length /2
 *
 * The Y drop off point is called the yfirstposition. For the center tape it is compensated by robot length /2 taking sign into account.
 * To tweak this value there is a y offset that can be used - keep the sign in mind!
 *
 *The robot will be retracted after placing the pixel on the tape; The robot will move back the value in retractDistance in the Y axis
 * only for the center tape. Retract distance must have a value other that 0 or the code will crash;
 *
 *For left and right tapes, the robot heading does not change. I will reach the tape parallel to it.
 * Retract will then take if back along the Y axis.
 *
 *
 *
 *
 *
 *
 * */


import com.acmerobotics.roadrunner.geometry.Pose2d;

public class SelectMotionValuesRed {

    private final boolean bbstart;

    private int lcr;


    public SelectMotionValuesRed() {
        bbstart = ActiveMotionValues.getBBStart();
        lcr = ActiveMotionValues.getLcrpos();
    }


    public void initialize() {


        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            case 1://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(2);

                ActiveMotionValues.setRetractDistance(-6);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsRed.XPYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsRed.XPYM.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsRed.XPYM.LeftTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2) + ActiveMotionValues.getRetractDistance());



                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setActiveTagPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2, 0, 0)));

                ActiveMotionValues.setParkPose(FieldConstantsRed.slideToearBBSideParkPose);


                break;

            case 2://center straight motion to midddle of center tape


                ActiveMotionValues.setyOffset(0);// adds to forward motion to control position where pixel is  dropped

                ActiveMotionValues.setxOffset(0);//can be used to offset x motion so pixel is place off the x center

                ActiveMotionValues.setRetractDistance(-12);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsRed.XPYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsRed.XPYM.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 -
                        ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsRed.XPYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, FieldConstantsRed.XPYM.CenterTapeMid.getY() -
                        Constants.TapeConstants.tapeLength / 2 -
                        ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2) + ActiveMotionValues.getRetractDistance());


                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setActiveTagPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2 + 3, 0, 0)));


                ActiveMotionValues.setParkPose(FieldConstantsRed.slideToearBBSideParkPose);


                break;


            case 3://right tape

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-10);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsRed.XPYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsRed.XPYM.RightTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsRed.XPYM.RightTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));

                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2) + ActiveMotionValues.getRetractDistance());


                ActiveMotionValues.setActTag(6);


                ActiveMotionValues.setActiveTagPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2 + 3, 0, 0)));

                ActiveMotionValues.setParkPose(FieldConstantsRed.slideToearBBSideParkPose);


                break;

            //RED
            case 11://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-10);


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsRed.XMYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsRed.XMYM.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsRed.XMYM.LeftTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));

                setCommonMotion(4);

                break;

            case 12://center


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsRed.XMYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsRed.XMYM.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 -
                        ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsRed.XMYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, FieldConstantsRed.XMYM.CenterTapeMid.getY() -
                        Constants.TapeConstants.tapeLength / 2 -
                        ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));

                setCommonMotion(5);


                break;


            case 13://right tape Red


                //robot moves in Y

                ActiveMotionValues.setyOffset(-6);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-6);

                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsRed.XMYM.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsRed.XMYM.RightTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsRed.XMYM.RightTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));

                setCommonMotion(6);


                break;

        }
    }

    public boolean setCommonMotion(int tagNum) {

        ActiveMotionValues.setxPoint(4, FieldConstantsRed.nearBackstageTrussLineUp.getX());

        ActiveMotionValues.setyPoint(4, FieldConstantsRed.nearBackstageTrussLineUp.getY());

        ActiveMotionValues.setxPoint(5, FieldConstantsRed.nearLookForAprilTagPose.getX());

        ActiveMotionValues.setyPoint(5, FieldConstantsRed.nearLookForAprilTagPose.getY());


        ActiveMotionValues.setActTag(tagNum);


        ActiveMotionValues.setActiveTagPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                .minus(new Pose2d(Constants.RobotConstants.length / 2, 0, 0)));


        ActiveMotionValues.setParkPose(FieldConstantsRed.slideToearBBSideParkPose);

        return true;


    }


}
