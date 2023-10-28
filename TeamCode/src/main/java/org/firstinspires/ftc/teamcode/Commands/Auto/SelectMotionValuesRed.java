package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
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

        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            //******************************************************************************************
            //******************************************************************************************


            case 1://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.leftDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.leftRetractPose);


                ActiveMotionValues.setStrafePose(FieldConstantsRed.XPYM.lrStrafePose);

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



                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.centerDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.centerRetractPose);

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


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.rightDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.rightRetractPose);


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


                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.leftDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.leftRetractPose);

                ActiveMotionValues.setStrafePose(FieldConstantsRed.XMYM.lrStrafePose);


                ActiveMotionValues.setActTag(4);


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center


                ActiveMotionValues.setyOffset(0);// adds to forward motion to control position where pixel is  dropped

                ActiveMotionValues.setxOffset(0);//can be used to offset x motion so pixel is place off the x center


                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPos);//start pose


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.centerDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.centerRetractPose);


                ActiveMotionValues.setActTag(5);




//set up options depending on selections

                //     setCommonMotion();


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.rightDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.rightRetractPose);

                ActiveMotionValues.setStrafePose(FieldConstantsRed.XMYM.lrStrafePose);

                ActiveMotionValues.setActTag(6);


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
