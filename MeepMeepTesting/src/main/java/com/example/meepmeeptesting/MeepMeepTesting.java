package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static Pose2d startPose = new Pose2d();


    public static void main(String[] args) {


        boolean redAlliance = false;

        boolean bbstart = false;//aaset to false for start on stack side of truss

        int lcr = 2;//left tape ==1, center tape = 2, right tape = 3 from robot view

        if (lcr < 1 || lcr > 3) lcr = 2;

        boolean useStageDoor = true;

        boolean truss = !useStageDoor;

        boolean centerPark = false;

        boolean secondPixel = true;

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


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                .followTrajectorySequence(drive ->

                        drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

//
                             //  .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                .waitSeconds(1)

                                .lineToLinearHeading(ActiveMotionValues.getRetractPose())

//
                                .strafeLeft(ActiveMotionValues.getStrafeDistance())
//
//                                .lineToLinearHeading(ActiveMotionValues.getLastPose())

                                //  .lineToLinearHeading(ActiveMotionValues.getClearStageDoorPose())

                                .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())
//
                                .lineToLinearHeading(ActiveMotionValues.getStrafeToAprilTagPose())

                                   .lineToLinearHeading(ActiveMotionValues.getSecondAprilTagPose())


                      //          .lineToLinearHeading(ActiveMotionValues.getParkPose())


                                .build());


        myBot.getDrive().setPoseEstimate(startPose);

        ShowField.showIt(meepMeep, myBot);


    }
}
