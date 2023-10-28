package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;

public class SelectMotionValuesBlue extends CommandBase {

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


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(3);

                ActiveMotionValues.setStrafeDistance(11.5);

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);

                double y = FieldConstantsBlue.XPYP.LeftTapeMid.getY()
                        - Constants.TapeConstants.tapeLength * 2 / 3
                        // + Constants.TapeConstants.tapeWidth / 2
                        - Constants.RobotConstants.pixelDropPose.getY()
                        - ActiveMotionValues.getyOffset();


                double x = FieldConstantsBlue.XPYP.LeftTapeMid.getX()
                        + Constants.RobotConstants.pixelDropPose.getX()
                        + ActiveMotionValues.getxOffset();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));

                double tempy = 0;

                tempy += ActiveMotionValues.getRetractDistance();

                double tempX = ActiveMotionValues.getDropOffPose().getX();

                ActiveMotionValues.setRetractPose(new Pose2d(tempX, tempy));

                tempX = ActiveMotionValues.getRetractPose().getX();

                tempX += ActiveMotionValues.getStrafeDistance();

                tempy = ActiveMotionValues.getRetractPose().getY();

                ActiveMotionValues.setStrafePose(new Pose2d(tempX, tempy));

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

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose


                x = FieldConstantsBlue.XPYP.CenterTapeMid.getX();

                y = FieldConstantsBlue.XPYP.CenterTapeMid.getY()

                        + Constants.RobotConstants.pixelDropPose.getY()
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

                ActiveMotionValues.setStrafeDistance(-9.5);


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.StartPos);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);//start pose


                x = FieldConstantsBlue.XPYP.RightTapeMid.getX()
                        + Constants.RobotConstants.pixelDropPose.getX()
                        + ActiveMotionValues.getxOffset();

                y = FieldConstantsBlue.XPYP.RightTapeMid.getY() - Constants.TapeConstants.tapeLength / 2;


                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));

                tempy = 0;

                tempy += ActiveMotionValues.getRetractDistance();

                tempX = ActiveMotionValues.getDropOffPose().getX();

                ActiveMotionValues.setRetractPose(new Pose2d(tempX, tempy));

                tempX = ActiveMotionValues.getRetractPose().getX();


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


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                x = FieldConstantsBlue.XMYP.LeftTapeMid.getX() + Constants.TapeConstants.tapeWidth / 2
                        - Constants.RobotConstants.pixelDropPose.getX()
                        + ActiveMotionValues.getxOffset();

                y = FieldConstantsBlue.XMYP.LeftTapeMid.getY()
                        - Constants.TapeConstants.tapeLength - Constants.RobotConstants.pixelDropPose.getY();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));

                tempy = ActiveMotionValues.getDropOffPose().getY();

                tempy += ActiveMotionValues.getRetractDistance();

                tempX = ActiveMotionValues.getDropOffPose().getX();

                ActiveMotionValues.setRetractPose(new Pose2d(tempX, tempy));


                ActiveMotionValues.setActTag(4);


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

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                x = FieldConstantsBlue.XMYP.CenterTapeMid.getX();

                y = FieldConstantsBlue.XMYP.CenterTapeMid.getY()
                        + Constants.RobotConstants.pixelDropPose.getY()
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

                ActiveMotionValues.setStrafeDistance(-11.5);


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.StartPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XMYP.advancePose);


                y = FieldConstantsBlue.XMYP.RightTapeMid.getY()
                        - Constants.TapeConstants.tapeLength * 2 / 3
                        // + Constants.TapeConstants.tapeWidth / 2
                        - Constants.RobotConstants.pixelDropPose.getY()
                        - ActiveMotionValues.getyOffset();


                x = FieldConstantsBlue.XMYP.RightTapeMid.getX()
                        + Constants.RobotConstants.pixelDropPose.getX()
                        + ActiveMotionValues.getxOffset();

                ActiveMotionValues.setDropOffPose(new Pose2d(x, y, Math.toRadians(-90)));

                tempy = 0;

                tempy += ActiveMotionValues.getRetractDistance();

                tempX = ActiveMotionValues.getDropOffPose().getX();

                ActiveMotionValues.setRetractPose(new Pose2d(tempX, tempy));

                tempX = ActiveMotionValues.getRetractPose().getX();

                tempX += ActiveMotionValues.getStrafeDistance();

                tempy = ActiveMotionValues.getRetractPose().getY();

                ActiveMotionValues.setStrafePose(new Pose2d(tempX, tempy));


                ActiveMotionValues.setActTag(6);


                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);

                else

                    ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);


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

            ActiveMotionValues.setParkPose(FieldConstantsRed.slideToCenterBBSideParkPose);
        else
            ActiveMotionValues.setParkPose(FieldConstantsRed.slideToNearBBSideParkPose);

        if (!secondPixel && useTruss) {


            ActiveMotionValues.setTrussLineUpPose(FieldConstantsRed.nearTrussLineUpPose);

        }

        if (secondPixel && useTruss) {
            ActiveMotionValues.setTrussLineUpPose(FieldConstantsRed.nearTrussLineUpPose);

            ActiveMotionValues.setLookForAprilTagPose(FieldConstantsRed.nearLookForAprilTagPose);
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
