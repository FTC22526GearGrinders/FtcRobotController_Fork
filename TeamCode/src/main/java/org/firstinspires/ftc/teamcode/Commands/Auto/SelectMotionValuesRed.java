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

                //robot moves in Y

                ActiveMotionValues.setxFirstPoint(
                        FieldConstantsRed.XPYM.LeftTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.LeftTapeMid.getY() - Constants.TapeConstants.tapeLength / 2 -
                        Constants.RobotConstants.length / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() + ActiveMotionValues.getRetractDistance());

                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setFinalPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag()).

                        plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setActiveTagPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2 + 3, 0, 0)));

                ActiveMotionValues.setParkPose(FieldConstantsRed.nearBackstagePark);


                break;

            case 2://center straight motion to midddle of center tape


                ActiveMotionValues.setyOffset(0);// adds to forward motion to control position where pixel is  dropped

                ActiveMotionValues.setxOffset(0);//can be used to offset x motion so pixel is place off the x center

                ActiveMotionValues.setRetractDistance(6);//MUST MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose;

                //robot moves in Y

                ActiveMotionValues.setxFirstPoint(ActiveMotionValues.getStartPose().getX() - ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.CenterTapeMid.getY() -
                        Constants.RobotConstants.length / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() - ActiveMotionValues.getRetractDistance());


                ActiveMotionValues.setActTag(5);

                int temp = ActiveMotionValues.getActTag();

                ActiveMotionValues.setFinalPose(FieldConstantsRed.setActiveTagPose(temp).

                        plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setActiveTagPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2 + 3, 0, 0)));

                ActiveMotionValues.setParkPose(FieldConstantsRed.nearBackstagePark);

                break;


            case 3://right tape

                ActiveMotionValues.setyOffset(4);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(1);


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose

                //robot moves in Y

                ActiveMotionValues.setyOffset(1);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-6);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.StartPos);//start pose

                //robot moves in Y

                ActiveMotionValues.setxFirstPoint(
                        FieldConstantsRed.XPYM.RightTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XPYM.RightTapeMid.getY() - Constants.TapeConstants.tapeLength / 2 -
                        Constants.RobotConstants.length / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxSecondPoint(ActiveMotionValues.getxFirstPoint());

                ActiveMotionValues.setySecondPoint(ActiveMotionValues.getyFirstPoint() + ActiveMotionValues.getRetractDistance());


                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setFinalPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag()).

                        plus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));


                ActiveMotionValues.setActiveTagPose(FieldConstantsRed.setActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(new Pose2d(Constants.RobotConstants.length / 2 + 3, 0, 0)));

                ActiveMotionValues.setParkPose(FieldConstantsRed.nearBackstagePark);

                break;

            //RED
            case 11://left tape


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-10);


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setxFirstPoint(FieldConstantsRed.XMYM.LeftTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.LeftTapeMid.getY() - Constants.RobotConstants.length / 2 -
                        Constants.TapeConstants.tapeLength / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxSecondPoint(FieldConstantsRed.stageDoorLineUpPose2.getX());

                ActiveMotionValues.setySecondPoint(FieldConstantsRed.stageDoorLineUpPose2.getY());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.slowToStageDoorPose);

                ActiveMotionValues.setClearStageDoorPose(FieldConstantsRed.clearStageDoorPose);



                ActiveMotionValues.setParkPose(FieldConstantsRed.farBackstagePark);


                break;

            case 12://center


                ActiveMotionValues.setyOffset(0);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(12);// MUST MUST MOT BE ZERO!!!!!!!!!!!!!!! + value makes retract move more negative


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose


                ActiveMotionValues.setxFirstPoint(ActiveMotionValues.getStartPose().getX());

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.CenterTapeMid.getY() -
                        Constants.RobotConstants.length / 2 - ActiveMotionValues.getyOffset());

                ActiveMotionValues.setxSecondPoint(FieldConstantsRed.stageDoorLineUpPose.getX());

                ActiveMotionValues.setySecondPoint(FieldConstantsRed.stageDoorLineUpPose.getY());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.slowToStageDoorPose);

                ActiveMotionValues.setClearStageDoorPose(FieldConstantsRed.clearStageDoorPose);



                ActiveMotionValues.setParkPose(FieldConstantsRed.farBackstagePark);

                break;


            case 13://right tape Red


                //robot moves in Y

                ActiveMotionValues.setyOffset(1);

                ActiveMotionValues.setxOffset(0);

                ActiveMotionValues.setRetractDistance(-6);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.StartPos);//start pose

                //robot moves in Y

                ActiveMotionValues.setxFirstPoint(
                        FieldConstantsRed.XMYM.RightTapeMid.getX() + ActiveMotionValues.getxOffset());

                ActiveMotionValues.setyFirstPoint(FieldConstantsRed.XMYM.RightTapeMid.getY() - Constants.TapeConstants.tapeLength / 2 -
                        Constants.RobotConstants.length / 2 - ActiveMotionValues.getyOffset());


                ActiveMotionValues.setxSecondPoint(FieldConstantsRed.stageDoorLineUpPose1.getX());

                ActiveMotionValues.setySecondPoint(FieldConstantsRed.stageDoorLineUpPose1.getY());


                ActiveMotionValues.setFinalPose(FieldConstantsRed.slowToStageDoorPose);

                ActiveMotionValues.setClearStageDoorPose(FieldConstantsRed.clearStageDoorPose);

                ActiveMotionValues.setParkPose(FieldConstantsRed.farBackstagePark);

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
