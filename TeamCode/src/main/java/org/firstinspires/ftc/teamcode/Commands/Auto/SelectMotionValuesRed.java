package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;

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
        ActiveMotionValues.setStrafeDistance(0);
        ActiveMotionValues.setAdvancePose(new Pose2d());
        ActiveMotionValues.setParkPose(new Pose2d());

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;

        switch (motionSelected) {

            //******************************************************************************************
            //******************************************************************************************


            case 1://left tape


                Pose2d xyOffsetPose=new Pose2d();





                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYM.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYM.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYM.leftDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYM.leftTrussRetractPose);

                ActiveMotionValues.setStrafeDistance(11.5);


                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose);

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                xyOffsetPose=new Pose2d();




                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYM.centerDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYM.centerRetractPose);

                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose);

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose);


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://right ta

                 xyOffsetPose=new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYM.rightDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYM.rightRetractPose);


                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose);

                if (ActiveMotionValues.getNearPark())

                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose);

                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape

                xyOffsetPose=new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYM.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYM.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYM.leftRetractPose);

                ActiveMotionValues.setActTag(4);

                setOptions();


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center


                xyOffsetPose=new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYM.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYM.centerDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYM.centerRetractPose);


                ActiveMotionValues.setActTag(5);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

               xyOffsetPose=new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XMYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYM.rightDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYM.rightRetractPose);

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

        if (useTruss) {

            if (lcr == 11) ActiveMotionValues.setStrafeDistance(11.5);

            if (lcr == 13) ActiveMotionValues.setStrafeDistance(-11.5);


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
                ActiveMotionValues.setStrafeDistance(11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setStrafeDistance(-11.5);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose2));
            }
            if (lcr == 13) {
                ActiveMotionValues.setStrafeDistance(-11.5);
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

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
