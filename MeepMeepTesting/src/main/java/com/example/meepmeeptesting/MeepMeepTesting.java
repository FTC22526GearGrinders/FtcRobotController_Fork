package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static Pose2d startPose = new Pose2d();


    public static void main(String[] args) {


        boolean redAlliance = true;

        boolean bbstart = true;//aaset to false for start on stack side of truss

        int lcr = 2;//left tape ==1, center tape = 2, right tape = 3 from robot view

        if (lcr < 1 || lcr > 3) lcr = 2;

        boolean useStageDoor = false;

        boolean centerPark = false;

        boolean secondPixel = false;

        ActiveMotionValues.setRedAlliance(redAlliance);

        ActiveMotionValues.setBBStart(bbstart);
        ActiveMotionValues.setLcrpos(lcr);
        ActiveMotionValues.setUseStageDoor(useStageDoor);
        ActiveMotionValues.setCenterPark(centerPark);
        ActiveMotionValues.setSecondPixel(secondPixel);


        if (ActiveMotionValues.getRedAlliance()) {
//
            new SelectMotionValuesRed();
//
        } else {
//
            new SelectMotionValuesBlue();

        }


//


        MeepMeep meepMeep = new MeepMeep(800);

        if (bbstart) {

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                    .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                    .followTrajectorySequence(drive ->

                            drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

//
                                    .lineTo(new Vector2d(ActiveMotionValues.getxPoint(1), ActiveMotionValues.getyPoint(1)))
                                    .waitSeconds(2)
                                    .lineTo(new Vector2d(ActiveMotionValues.getxPoint(2), ActiveMotionValues.getyPoint(2)))



//                                    .lineTo(new Vector2d(ActiveMotionValues.getxPoint(3), ActiveMotionValues.getyPoint(3) ))
//
//
//                                    .lineTo(new Vector2d(ActiveMotionValues.getxPoint(4), ActiveMotionValues.getyPoint(4)))

                                    //

                                    .lineToLinearHeading(ActiveMotionValues.getLastPose())


                                    .waitSeconds(1)


                                    .build());


            myBot.getDrive().setPoseEstimate(startPose);

            ShowField.showIt(meepMeep, myBot);


        } else {

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                    .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                    .followTrajectorySequence(drive ->

                            drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                    .lineTo(new Vector2d(ActiveMotionValues.getxPoint(1), ActiveMotionValues.getyPoint(1)))

                                    .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)), (ActiveMotionValues.getyPoint(2))))
//
//                                    .waitSeconds(2)
//
                                    .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)), (ActiveMotionValues.getyPoint(3))))
//
                                    .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)), (ActiveMotionValues.getyPoint(4))))

                                    .lineTo(new Vector2d((ActiveMotionValues.getxPoint(5)), (ActiveMotionValues.getyPoint(5))))
//
//
//                                  .lineTo(new Vector2d((ActiveMotionValues.getxPoint(6)), (ActiveMotionValues.getyPoint(6))))

                                    //    .lineTo(new Vector2d((ActiveMotionValues.getxPoint(7)), (ActiveMotionValues.getyPoint(7))))

                                    // .lineTo(new Vector2d((ActiveMotionValues.getxPoint(8)), (ActiveMotionValues.getyPoint(8))))


                                    .waitSeconds(1)

                                    // .turn(-10)

                                    //  .lineToLinearHeading(ActiveMotionValues.getActiveTagPose())

                                    .waitSeconds(1)


                                    //     .lineToLinearHeading(ActiveMotionValues.getParkPose())


//


                                    .build());


            myBot.getDrive().setPoseEstimate(startPose);
            ShowField.showIt(meepMeep, myBot);
        }

    }
}
