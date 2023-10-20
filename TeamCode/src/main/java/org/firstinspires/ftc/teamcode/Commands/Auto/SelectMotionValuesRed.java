package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;

public class SelectMotionValuesRed extends CommandBase {

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
    }


    @Override
    public void initialize() {

        if (lcr < 1 || lcr > 3) lcr = 1;

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
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);



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
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);



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
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);



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

                setCommonMotion(4, ActiveMotionValues.getUseStageDoor(), ActiveMotionValues.getLcrpos() == 2, ActiveMotionValues.getCenterPark());

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

                ActiveMotionValues.setyPoint(2, FieldConstantsRed.XMYM.CenterTapeMid.getY() + Constants.RobotConstants.length+ +
                        Constants.TapeConstants.tapeLength / 2);


                ActiveMotionValues.setxPoint(3, FieldConstantsRed.XMYM.RightTapeMid.getX());

                ActiveMotionValues.setyPoint(3, ActiveMotionValues.getyPoint(2));



                setCommonMotion(5, ActiveMotionValues.getUseStageDoor(), ActiveMotionValues.getLcrpos() == 2, ActiveMotionValues.getCenterPark());


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

                setCommonMotion(6, ActiveMotionValues.getUseStageDoor(), ActiveMotionValues.getLcrpos() == 2, ActiveMotionValues.getCenterPark());


                break;

        }
    }

    public boolean setCommonMotion(int tagNum, boolean useStageDoor, boolean centerTape, boolean centerPark) {


        if (useStageDoor) {


//
            if (centerTape) {


                ActiveMotionValues.setxPoint(4, FieldConstantsRed.stageDoorLineUpPose2.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsRed.stageDoorLineUpPose2.getY());

            } else {

                ActiveMotionValues.setxPoint(4, FieldConstantsRed.stageDoorLineUpPose13.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsRed.stageDoorLineUpPose13.getY());
            }


            ActiveMotionValues.setxPoint(5, FieldConstantsRed.centerBBSideParkPose.getX());

            ActiveMotionValues.setyPoint(5, FieldConstantsRed.centerBBSideParkPose.getY());

        }
        if (!useStageDoor) {

            if(centerTape) {

                ActiveMotionValues.setxPoint(3, FieldConstantsRed.nearBackstageTrussLineUp.getX());

                ActiveMotionValues.setyPoint(3, FieldConstantsRed.nearBackstageTrussLineUp.getY());

                ActiveMotionValues.setxPoint(4, FieldConstantsRed.slideToCenterBBSideParkPose.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsRed.slideToNearBBSideParkPose.getY());
            }
            else {

                ActiveMotionValues.setxPoint(4, FieldConstantsRed.nearBackstageTrussLineUp.getX());

                ActiveMotionValues.setyPoint(4, FieldConstantsRed.nearBackstageTrussLineUp.getY());

                ActiveMotionValues.setxPoint(5, FieldConstantsRed.slideToCenterBBSideParkPose.getX());

                ActiveMotionValues.setyPoint(5, FieldConstantsRed.slideToNearBBSideParkPose.getY());




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
