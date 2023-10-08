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
 * other values as required. Motions are in the Y direction. Red Y values are negative, Blue Y values are positive.
 * So a red start position will look like -Field length/ 2 + robot length /2. Blue valus would be +Field length /2 - robot length /2
 *
 * The Y drop off point is called the yfirstposition. For the center tape it is compensated by robot length /2 taking sign into account.
 * To tweak this value there is a y offset that can be used - keep the sign in mind!
 *
 *The robot will be retracted after placing the pixel on the tape; The robot will move back the value in retractDistance in the Y axis
 * only for the center tape. Retract distance must have a value other that 0 or the code will crash;
 * The robot will retract along the same line it came in on for the left and right tapes. The retract distance will be along that line;
 *
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

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            case 1://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XPYP.LeftTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2)

                        + Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XPYP.LeftTapeMid.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsBlue.AprilTagConstants.atag1.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(1);

                break;

            case 2://center


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);//MUST MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XPYP.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XPYP.StartPos.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(FieldConstantsBlue.XPYP.StartPos.getX());

                ActiveMotionValues.setFinalPose(FieldConstantsBlue.AprilTagConstants.atag2.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(2);

                break;


            case 3://right tape

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XPYP.RightTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2)

                        + Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XPYP.RightTapeMid.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsBlue.AprilTagConstants.atag3.plus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActTag(3);

                break;

            //RED
            case 11://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XMYP.LeftTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2)

                        + Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XMYP.LeftTapeMid.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsBlue.wingPose);


                break;

            case 12://center


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);//MUST MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose;

                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XMYP.CenterTapeMid.getY() - Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XMYP.StartPos.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setFinalPose(FieldConstantsBlue.wingPose);

                break;


            case 13://right tape Red


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);

                ActiveMotionValues.setStrafeDistance(1);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setyFirstPoint(FieldConstantsBlue.XMYP.RightTapeMid.getY() + (Constants.TapeConstants.tapeLength / 2)

                        + Constants.RobotConstants.length / 2 + ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxFirstPoint(FieldConstantsBlue.XMYP.RightTapeMid.getX());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());


                ActiveMotionValues.setFinalPose(FieldConstantsBlue.wingPose);

                break;


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
