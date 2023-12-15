package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;


public final class FieldConstantsBlue {

    /*
     *
     *
     * */

    public static final double startAngle = Math.toRadians(90);

    public static Pose2d stageDoorLineUpPose13 = new Pose2d(-36, 12, startAngle);

    public static Pose2d stageDoorLineUpPose2 = new Pose2d(-48, 12, startAngle);

    public static Pose2d nearOptionPose = new Pose2d(36, 60, startAngle);
    public static Pose2d centerOptionPose = new Pose2d(36, 12, startAngle);


    public static Pose2d nearParkPose = new Pose2d(50, 60, startAngle);
    public static Pose2d centerParkPose = new Pose2d(50, 12, startAngle);

    public static Pose2d nearTrussLineUpPose = new Pose2d(-36, 60, startAngle);

    public static double addedYdist = 2;

    public static double pixelCenterComp = .5;//compensate for left to right difference due to hole in pixel

    public static final class AprilTagConstants {

        static final double atagAngle = 0;


        //  public static final Pose2d tagLookAheadPose = new Pose2d(14 , 0, Math.toRadians(0));

        public static final Pose2d tagLookAheadPose = new Pose2d(12 + Constants.RobotConstants.length / 2, 0, Math.toRadians(0));

        public static final Pose2d tagStrafeOffsetPose = new Pose2d(-60.25 + 36 +2, 0, 0);


        public static final Pose2d atag1 = new Pose2d(60.25, 42, Math.toRadians(atagAngle));
        public static final Pose2d atag2 = new Pose2d(60.25, 36, Math.toRadians(atagAngle));
        public static final Pose2d atag3 = new Pose2d(60.25, 30, Math.toRadians(atagAngle));


    }

    public static Pose2d getActiveTagPose(int num) {

        switch (num) {
            case 1:
                return AprilTagConstants.atag1;

            case 2:
                return AprilTagConstants.atag2;

            case 3:
                return AprilTagConstants.atag3;

            default:
                return AprilTagConstants.atag1;

        }
    }

    public static final class XPYP {
        //left and right are from the view of the robot

        public static final Pose2d startPos = new Pose2d(12, 60, startAngle);

        public static final Pose2d leftTapeMid = new Pose2d(23.5, 30);
        public static final Pose2d centerTapeMid = new Pose2d(12, 24.5);
        public static final Pose2d rightTapeMid = new Pose2d(0.5, 30);

        public static Pose2d advancePose = new Pose2d(12,
                leftTapeMid.getY() - addedYdist, startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);
        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX() + pixelCenterComp ,
                leftTapeMid.getY() - addedYdist, startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY() - pixelCenterComp - 2, startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX() - pixelCenterComp,
                rightTapeMid.getY() - addedYdist - 2
                , startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);

        public static final Pose2d retPose = new Pose2d(0, 2);
        public static final Pose2d retCPose = new Pose2d(0, 8);

        public static final Pose2d leftRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retCPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retPose);

        public static final Pose2d clearPose = advancePose.plus(retPose);

    }


    public static final class XMYP {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants

        ;

        public static final Pose2d startPose = new Pose2d(-36, Constants.FieldConstants.length / 2
                - Constants.RobotConstants.length / 2, startAngle);


        public static final Pose2d leftTapeMid = new Pose2d(-24.5, 30);
        public static final Pose2d centerTapeMid = new Pose2d(-36, 24.5);
        public static final Pose2d rightTapeMid = new Pose2d(-47.5, 30);

        public static Pose2d advancePose = new Pose2d(-36,
                leftTapeMid.getY() - addedYdist, startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);

        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX() + pixelCenterComp,
                leftTapeMid.getY() - addedYdist, startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY() - pixelCenterComp, startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX() - pixelCenterComp,
                rightTapeMid.getY() - addedYdist, startAngle)
                .minus(Constants.RobotConstants.activeDropOffsetPose);

        public static final Pose2d retPose = new Pose2d(0, 2);
        public static final Pose2d leftRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retPose);
        public static final Pose2d clearPose = advancePose.plus(retPose);
    }


}