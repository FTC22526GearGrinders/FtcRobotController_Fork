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

        boolean bbstart = false;//set to false for start on stack side of truss

        int lcr = 3;//left tape ==1, center tape = 2, right tape = 3 from robot view

        if (lcr < 1 || lcr > 3) lcr = 2;


        ActiveMotionValues.setRedAlliance(redAlliance);
        ActiveMotionValues.setBBStart(bbstart);
        ActiveMotionValues.setLcrpos(lcr);

        if (ActiveMotionValues.getRedAlliance()) {
//
            new SelectMotionValuesRed().initialize();
//
        } else {
//
            new SelectMotionValuesBlue().initialize();

        }


//


        MeepMeep meepMeep = new MeepMeep(800);

        if (bbstart) {

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                    .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)


                    // .setDimensions(FieldConstantsRed.ROBOT.width, FieldConstantsRed.ROBOT.height)
                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(20, 20, Math.toRadians(600), Math.toRadians(50), Constants.DriveConstants.TRACKWIDTH)

                    .followTrajectorySequence(drive ->

                            drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                    .lineTo(new Vector2d(ActiveMotionValues.getxFirstPoint(), ActiveMotionValues.getyFirstPoint()))


                                    .waitSeconds(1)

                                    .lineTo(new Vector2d((ActiveMotionValues.getxSecondPoint()), (ActiveMotionValues.getySecondPoint())))

                                    .lineToLinearHeading(ActiveMotionValues.getFinalPose())

                                    .lineToLinearHeading(ActiveMotionValues.getActiveTagPose())

                                    .waitSeconds(1)

                                    .lineToLinearHeading(ActiveMotionValues.getFinalPose())

                                    .lineToLinearHeading(ActiveMotionValues.getParkPose())


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

                                    .lineTo(new Vector2d(ActiveMotionValues.getStartPose().getX(), ActiveMotionValues.getStartPose().getY() + 26))

                                   // .splineTo(new Vector2d(ActiveMotionValues.getxFirstPoint(), ActiveMotionValues.getyFirstPoint()),Math.toRadians(90))

                                    .strafeRight(12)
                                    .waitSeconds(1)
                                    .strafeLeft(24)
                    .lineTo(new Vector2d((ActiveMotionValues.getxSecondPoint()), (ActiveMotionValues.getySecondPoint())))

                    .lineToLinearHeading(ActiveMotionValues.getFinalPose())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getClearStageDoorPose())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getParkPose())
//


                    .build());


            myBot.getDrive().setPoseEstimate(startPose);
            ShowField.showIt(meepMeep, myBot);
        }

    }
}
