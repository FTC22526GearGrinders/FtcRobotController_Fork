package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Claw_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class CenterStageTrajectories {

    private static Drive_Subsystem drive;

    private static Claw_Subsystem claw;


    public CenterStageTrajectories(Drive_Subsystem driive, Claw_Subsystem claw) {
        this.drive = driive;
        this.claw = claw;
    }

    public enum trajseqs {

        BACKBOARD_CENTER(backboardCenter),//used for both alliances when center tape is selected 5 moves drive to tag, place pixel and park are separate,
//pixel placemant after 1st step
        BACKBOARD_LEFT_RIGHT(backboardLeftRight),//same as above but pixel placement is after 2nd step

        FIVE_STEP_CENTER(fiveStepCenter),

        FOUR_STEP_CENTER(fourStepCenter),

        FIVE_STEP_LR(fiveStepLR),

        FOUR_STEP_LR(fourStepLR);


        private TrajectorySequence trajseq;

        private trajseqs(TrajectorySequence trajseq) {
            this.trajseq = trajseq;

        }


    }

    /**
     * Use th 5 step center for stage door selection
     * <p>
     * It has the pixel delivery after the first step
     */
    static TrajectorySequence backboardCenter = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                    ActiveMotionValues.getyPoint(1)))

            .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(claw))

            .waitSeconds(3)//pixel drop off time

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(2)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(3)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(4)))

            .lineToLinearHeading(ActiveMotionValues.getActiveTagPose())


            .build();

    static TrajectorySequence backboardLeftRight = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                    ActiveMotionValues.getyPoint(1)))


            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(2)))

            .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(claw))

            .waitSeconds(3)//pixel drop off time

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(3)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(4)))

            .lineToLinearHeading(ActiveMotionValues.getActiveTagPose())


            .build();

    /**
     * Use th 5 step center for stage door selection
     * <p>
     * It has the pixel delivery after the first step
     */
    static TrajectorySequence fiveStepCenter = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                    ActiveMotionValues.getyPoint(1)))

            .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(claw))

            .waitSeconds(3)//pixel drop off time

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(2)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(3)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(4)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(5)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(5)))


            .build();


    /**
     * Use the 4 step center for  when stage door is not selected
     * <p>
     * It has the pixel delivery after the first step
     */

    static TrajectorySequence fourStepCenter = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                    ActiveMotionValues.getyPoint(1)))

            .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(claw))

            .waitSeconds(3)//pixel drop off time

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(2)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(3)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(4)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(5)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(5)))


            .build();

    /**
     * Use th 5 step left right tapes for stage door selection
     * <p>
     * It has the pixel delivery after the second step
     */

    static TrajectorySequence fiveStepLR = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                    ActiveMotionValues.getyPoint(1)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(2)))

            .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(claw))

            .waitSeconds(3)//pixel drop off time

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(3)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(4)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(5)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(5)))


            .build();

    public static TrajectorySequence testSeq = drive.drive.trajectorySequenceBuilder(new Pose2d())

            .splineTo(new Vector2d(10, 10), 0)
            .turn(Math.toRadians(90))
            .splineTo(new Vector2d(25, -15), 0)
            .waitSeconds(3)
            .turn(Math.toRadians(45))
            .forward(10)
            .strafeRight(5)
            .turn(Math.toRadians(90))
            .strafeLeft(5)
            .waitSeconds(1)
            .splineToLinearHeading(new Pose2d(-10, -10, Math.toRadians(45)), 0)
            .build();





    /**
     * Use th 5 step left right tapes for stage door selection
     * <p>
     * It has the pixel delivery after the second step
     */

    static TrajectorySequence fourStepLR = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(1)),//drive to drop off poinr

                    ActiveMotionValues.getyPoint(1)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(2)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(2)))

            .addTemporalMarker(() -> new DeliverPixelSpikeTapeCommand(claw))

            .waitSeconds(3)//pixel drop off time

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(3)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(3)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(4)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(4)))

            .lineTo(new Vector2d((ActiveMotionValues.getxPoint(5)),//move left or right on to middle of tape

                    ActiveMotionValues.getyPoint(5)))


            .build();

}
