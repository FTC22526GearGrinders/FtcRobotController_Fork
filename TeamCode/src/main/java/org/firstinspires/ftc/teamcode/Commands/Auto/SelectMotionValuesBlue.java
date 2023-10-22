package org.firstinspires.ftc.teamcode.Commands.Auto;


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
 * other values as required. Motions are in the Y direction. Blue Y values are negative, Blue Y values are positive.
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


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getX());

//with zero offset robot stiops with Y center on the tape. The Y offset can take it either way from there.
                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getY()
                        - ActiveMotionValues.getyOffset());


//robot x centered is on the left tape center. The x offset can move the robot either way.
                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XPYP.LeftTapeMid.getX()
                        - ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2)
                        - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setPointsUsed(4);

                ActiveMotionValues.setActTag(1);

                ActiveMotionValues.setTagLookAheadPose(FieldConstantsBlue.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearBBSideParkPose);


                break;

            case 2://center straight motion to midddle of center tape

//To move robot toward tape y value is less positive

                ActiveMotionValues.setyOffset(0);// controls stopping position of robot

                ActiveMotionValues.setxOffset(0);//can be used to offset x motion so pixel is place off the x center

                ActiveMotionValues.setRetractDistance(12);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! sets amount of final retract


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getX());

                //with zero offset robot y center is 6" short of center tape mid. Y offset is uded to stop robot with the pixel over the tape

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getY() - Constants.TapeConstants.tapeLength / 2
                        - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XPYP.CenterTapeMid.getX()
                        + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1)
                        - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setPointsUsed(2);
                ActiveMotionValues.setActTag(2);

                ActiveMotionValues.setTagLookAheadPose(FieldConstantsBlue.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.slideToNearBBSideParkPose);


                break;


            case 3://right tape

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose

                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getX());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XPYP.CenterTapeMid.getY()

                        - ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XPYP.RightTapeMid.getX()
                        + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));

                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));

                ActiveMotionValues.setxPoint(4, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(2)
                        + ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setPointsUsed(4);

                ActiveMotionValues.setActTag(3);


                ActiveMotionValues.setTagLookAheadPose(FieldConstantsBlue.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));


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

                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getX());


                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getY()
                        + Constants.FieldConstants.trussBaseClearance + Constants.RobotConstants.length / 2
                        + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XMYP.LeftTapeMid.getX()
                        + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(1));


                setCommonMotion();


                break;

            case 12://center


                ActiveMotionValues.setyOffset(-5);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getX());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getY()
                        - ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XMYP.CenterTapeMid.getX()
                        + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, FieldConstantsBlue.XMYP.CenterTapeMid.getY()
                        + Constants.RobotConstants.length + Constants.TapeConstants.tapeLength / 2);


                setCommonMotion();


                break;


            case 13://right tape Blue


                //robot moves in Y

                ActiveMotionValues.setyOffset(-12);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);

                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setxPoint(1, FieldConstantsBlue.XMYP.CenterTapeMid.getX()
                        + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(1, FieldConstantsBlue.XMYP.RightTapeMid.getY()
                        - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.XMYP.RightTapeMid.getX()
                        + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyPoint(2, ActiveMotionValues.getyPoint(1));


                ActiveMotionValues.setxPoint(3, ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));


                setCommonMotion();


                break;

        }
    }

    public boolean setCommonMotion() {
        int lcr = ActiveMotionValues.getLcrpos() + 10;
        boolean centerTape = lcr == 12;
        boolean useStageDoor = ActiveMotionValues.getUseStageDoor();
        boolean secondPixel = ActiveMotionValues.getSecondpixel();

        boolean useTruss = !useStageDoor;

        //if using the truss center tape has one less move than left or right
        if (useTruss && !secondPixel) {

            if (lcr == 12) {
//write over earlier retract value point 3 so robot retracts in one motion

                ActiveMotionValues.setxPoint(2, FieldConstantsBlue.nearTrussLineUp.getX());

                ActiveMotionValues.setyPoint(2, FieldConstantsBlue.nearTrussLineUp.getY());

                ActiveMotionValues.setxPoint(3, FieldConstantsBlue.clearForSecondPixel.getX());

                ActiveMotionValues.setyPoint(3, FieldConstantsBlue.clearForSecondPixel.getY());

                ActiveMotionValues.setParkPose(FieldConstantsBlue.nearBBSideParkPose);

                ActiveMotionValues.setPointsUsed(3);

                //write over earlier retract value point 4
            } else {

                ActiveMotionValues.setxPoint(4, FieldConstantsBlue.nearTrussLineUp.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsBlue.nearTrussLineUp.getY());

                ActiveMotionValues.setxPoint(5, FieldConstantsBlue.clearForSecondPixel.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsBlue.clearForSecondPixel.getY());


                ActiveMotionValues.setParkPose(FieldConstantsBlue.nearBBSideParkPose);

                ActiveMotionValues.setPointsUsed(5);
            }
        }


        if (useStageDoor && !secondPixel) {

            if (lcr == 12) {
//center tape moves across to right tape x center


                ActiveMotionValues.setxPoint(3, FieldConstantsBlue.XMYP.LeftTapeMid.getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));

                ActiveMotionValues.setxPoint(4, FieldConstantsBlue.stageDoorLineUpPose2.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsBlue.stageDoorLineUpPose2.getY());

                ActiveMotionValues.setxPoint(5, FieldConstantsBlue.nearBBSDLineUp.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsBlue.nearBBSDLineUp.getY());

                ActiveMotionValues.setParkPose(FieldConstantsBlue.centerBBSideParkPose);

                ActiveMotionValues.setPointsUsed(5);

            } else {

                ActiveMotionValues.setxPoint(4, FieldConstantsBlue.XMYP.CenterTapeMid.getX());

                ActiveMotionValues.setyPoint(4, ActiveMotionValues.getyPoint(3));

                ActiveMotionValues.setxPoint(5, FieldConstantsBlue.stageDoorLineUpPose13.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsBlue.stageDoorLineUpPose13.getY());

                ActiveMotionValues.setxPoint(6, FieldConstantsBlue.nearBBSDLineUp.getX());

                ActiveMotionValues.setyPoint(6, FieldConstantsBlue.nearBBSDLineUp.getY());

                ActiveMotionValues.setParkPose(FieldConstantsBlue.centerBBSideParkPose);

                ActiveMotionValues.setPointsUsed(6);
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
