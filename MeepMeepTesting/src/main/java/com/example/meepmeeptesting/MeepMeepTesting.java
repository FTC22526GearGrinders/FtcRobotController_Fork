package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static Pose2d startPose = new Pose2d();

    public static double dropOffDely = .75;
    public static double xFirstPoint;
    public static double xSecondPoint;
    public static double yFirstPoint;


    public static double firstTurnangle;
    public static double ySecondPoint;
    public static Pose2d finalPose;



    public static void main(String[] args) {


        boolean redAlliance = true;

        boolean bbstart = false;//t to false for start on stack side of truss

        int lcr = 2;//eft tape ==1, center tape = 2, right tape = 3 from robot view

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

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)


                // .setDimensions(FieldConstantsRed.ROBOT.width, FieldConstantsRed.ROBOT.height)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                .followTrajectorySequence(drive ->

                        drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                .lineTo(new Vector2d(ActiveMotionValues.getxFirstPoint(), ActiveMotionValues.getyFirstPoint()))

                                .waitSeconds(1)

                                .lineTo(new Vector2d((ActiveMotionValues.getxSecondPoint()), (ActiveMotionValues.getySecondPoint())))


//
                               // .lineToLinearHeading(ActiveMotionValues.getFinalPose())

                                .lineToLinearHeading(ActiveMotionValues.getParkPose())
//


                                .build());


        myBot.getDrive().setPoseEstimate(startPose);
        ShowField.showIt(meepMeep, myBot);


    }
}
