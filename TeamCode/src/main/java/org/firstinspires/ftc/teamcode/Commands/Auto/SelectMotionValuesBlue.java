package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;

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


public class SelectMotionValuesBlue extends CommandBase {

    private final boolean bbstart;

    private int lcr;


    public SelectMotionValuesBlue() {
        bbstart = ActiveMotionValues.getBBStart();
        lcr = ActiveMotionValues.getLcrpos();
    }


    @Override
    public void initialize() {

        if (lcr < 1 || lcr > 3) lcr = 2;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            case 1://left tape


                ActiveMotionValues.setyOffset(4);

                ActiveMotionValues.setxOffset(2);

                ActiveMotionValues.setRetractDistance(12);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getY() + Constants.RobotConstants.length / 2 +
                        Constants.TapeConstants.tapeLength / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XPYP.LeftTapeMid.getX() - ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2) + ActiveMotionValues.getRetractDistance());


                ActiveMotionValues.setActTag(1);

                ActiveMotionValues.setActiveTagPose(FieldConstantsBlue.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2, 0, 0)));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearBBSideParkPose);


                break;

            case 2://center straight motion to midddle of center tape


                ActiveMotionValues.setyOffset(0);// adds to forward motion to control position where pixel is  dropped

                ActiveMotionValues.setxOffset(0);//can be used to offset x motion so pixel is place off the x center

                ActiveMotionValues.setRetractDistance(12);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getY() + Constants.RobotConstants.length / 2 +
                        Constants.TapeConstants.tapeLength / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XPYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, FieldConstantsBlue.XPYP.CenterTapeMid.getY() +
                        Constants.TapeConstants.tapeLength / 2 +
                        ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2) + ActiveMotionValues.getRetractDistance());


                ActiveMotionValues.setActTag(2);


                ActiveMotionValues.setActiveTagPose(FieldConstantsBlue.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2 + 3, 0, 0)));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearBBSideParkPose);


                break;


            case 3://right tape

                ActiveMotionValues.setyOffset(-4);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XPYP.RightTapeMid.getY() + Constants.RobotConstants.length / 2 +
                        Constants.TapeConstants.tapeLength / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XPYP.RightTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));

                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2) + ActiveMotionValues.getRetractDistance());


                ActiveMotionValues.setActTag(3);


                ActiveMotionValues.setActiveTagPose(FieldConstantsBlue.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2 + 3, 0, 0)));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearBBSideParkPose);


                break;

            //RED
            case 11://left tape


                ActiveMotionValues.setyOffset(1);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(10);


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getY() + Constants.RobotConstants.length / 2 +
                        Constants.TapeConstants.tapeLength / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XMYP.LeftTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));


                setCommonMotion(4, ActiveMotionValues.getUseStageDoor(), ActiveMotionValues.getLcrpos() == 2, ActiveMotionValues.getCenterPark());


                break;

            case 12://center


                ActiveMotionValues.setyOffset(-5);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getY() + Constants.RobotConstants.length / 2 +
                        Constants.TapeConstants.tapeLength / 2 +
                        ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XMYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, FieldConstantsBlue.XMYP.CenterTapeMid.getY() + Constants.RobotConstants.length+ +
                        Constants.TapeConstants.tapeLength / 2);


                ActiveMotionValues.setxPoint(3, FieldConstantsBlue.XMYP.RightTapeMid.getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));


                setCommonMotion(4, ActiveMotionValues.getUseStageDoor(), ActiveMotionValues.getLcrpos() == 2, ActiveMotionValues.getCenterPark());


                break;


            case 13://right tape Blue


                //robot moves in Y

                ActiveMotionValues.setyOffset(-12);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-6);

                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XMYP.RightTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XMYP.RightTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));


                setCommonMotion(4, ActiveMotionValues.getUseStageDoor(), ActiveMotionValues.getLcrpos() == 2, ActiveMotionValues.getCenterPark());


                break;

        }
    }

    public boolean setCommonMotion(int tagNum, boolean useStageDoor, boolean centerTape, boolean centerPark) {


        if (useStageDoor) {

            if (centerTape) {

                ActiveMotionValues.setxPoint(4, FieldConstantsBlue.stageDoorLineUpPose2.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsBlue.stageDoorLineUpPose2.getY());

            } else {

                ActiveMotionValues.setxPoint(4, FieldConstantsBlue.stageDoorLineUpPose13.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsBlue.stageDoorLineUpPose13.getY());
            }


            ActiveMotionValues.setxPoint(5, FieldConstantsBlue.centerBBSideParkPose.getX());

            ActiveMotionValues.setyPoint(5, FieldConstantsBlue.centerBBSideParkPose.getY());

        }
        if (!useStageDoor) {

            if(centerTape) {

                ActiveMotionValues.setxPoint(3, FieldConstantsBlue.nearBackstageTrussLineUp.getX());

                ActiveMotionValues.setyPoint(3, FieldConstantsBlue.nearBackstageTrussLineUp.getY());

                ActiveMotionValues.setxPoint(4, FieldConstantsBlue.slideToCenterBBSideParkPose.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsBlue.slideToNearBBSideParkPose.getY());
            }
            else {

                ActiveMotionValues.setxPoint(4, FieldConstantsBlue.nearBackstageTrussLineUp.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsBlue.nearBackstageTrussLineUp.getY());

                ActiveMotionValues.setxPoint(5, FieldConstantsBlue.slideToCenterBBSideParkPose.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsBlue.slideToNearBBSideParkPose.getY());




            }

        }


        return true;


    }



    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
