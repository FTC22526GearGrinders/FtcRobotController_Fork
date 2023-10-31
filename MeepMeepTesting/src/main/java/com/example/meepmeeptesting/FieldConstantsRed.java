package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class FieldConstantsRed {


    public static Pose2d wingPose = new Pose2d(-60, 60, Math.toRadians(0));

    public static Pose2d farBackstagePark = new Pose2d(60, -12, Math.toRadians(90));// 'near' to drivers

    public static Pose2d nearBackstagePark = new Pose2d(60, -60, Math.toRadians(0));


    public static Pose2d slideToNearParkPose = new Pose2d(50, -60);

    public static Pose2d slideToCenterParkPose = new Pose2d(50, -14);

    public static Pose2d nearParkPose = new Pose2d(60, -60);

    public static Pose2d nearLookForAprilTagPose = new Pose2d(36, -60);
    public static Pose2d centerLookForAprilTagPose = new Pose2d(36, -12);

    public static Pose2d stageDoorLineUpPose13 = new Pose2d(-36, -12);

    public static Pose2d stageDoorLineUpPose2 = new Pose2d(-48, -12);


    public static Pose2d nearTrussLineUpPose = new Pose2d(-36, -60, Math.toRadians(180));

    public static Pose2d centerParkPose = new Pose2d(50, -12, Math.toRadians(180));

    public static double startAngle = Math.toRadians(-90);


    public static final class AprilTagConstants {
        public static int leftAtag = 0; //index into arrtay tags ar 1,2,3

        public static int canterAtag = 1;

        public static int rightAtag = 2;

        public static final Pose2d tagLookAheadPose = new Pose2d(14 + Constants.RobotConstants.length / 2, 0, Math.toRadians(180));

        public static final Pose2d tagStrafeOffsetPose = new Pose2d(-60.25 + 36, 0, 0);


        // tages 4,5 6
        public static final Pose2d atag4 = new Pose2d(60.25, -30);
        public static final Pose2d atag5 = new Pose2d(60.25, -36);
        public static final Pose2d atag6 = new Pose2d(60.25, -42);


    }

    public static Pose2d getActiveTagPose(int num) {
        int sel = num;
        switch (sel) {
            case 0:
            case 1:
            case 2:
                return AprilTagConstants.atag4;
            case 4:
                return AprilTagConstants.atag4;
            case 5:
                return AprilTagConstants.atag5;
            case 6:
                return AprilTagConstants.atag6;
            default:
                return AprilTagConstants.atag4;

        }
    }

    public static final class XPYM {
        //left and right are from the view of the robot

        public static final Pose2d startPos = new Pose2d(12, -Constants.FieldConstants.length / 2
                + Constants.RobotConstants.length / 2, Math.toRadians(-90));
        public static final Pose2d advancePose = new Pose2d(24, -48,startAngle);
        public static final Pose2d rightTapeMid = new Pose2d(23.5, -30);
        public static final Pose2d centerTapeMid = new Pose2d(12, -24.5);
        public static final Pose2d leftTapeMid = new Pose2d(0.5, -30);

        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(),
                leftTapeMid.getY() + Constants.TapeConstants.tapeLength / 4, startAngle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(), centerTapeMid.getY(), startAngle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(), rightTapeMid.getY()
                - Constants.TapeConstants.tapeLength / 4,startAngle)
                .plus(Constants.RobotConstants.pixelDropPose);

        public static final Pose2d retPose = new Pose2d(0, -4);

        public static final Pose2d leftRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retPose);

    }

    public static final class XMYM {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants

        //  static double xShift = 48;
        public static final double leftTapeAngle = Math.toRadians(90);//incremental  number
        static final double centerTapeAngle = Math.toRadians(90);//incremental  number
        public static final double rightTapeAngle = Math.toRadians(90);//incremental  number

        public static final Pose2d startPose = new Pose2d(-36, -(Constants.FieldConstants.length
                - Constants.RobotConstants.length) / 2, Math.toRadians(90));

        public static final Pose2d advancePose = new Pose2d(-48, -48, Math.toRadians(90));
        public static final Pose2d rightTapeMid = new Pose2d(-24.5, -30, Math.toRadians(leftTapeAngle));
        public static final Pose2d centerTapeMid = new Pose2d(-36, -24.5, Math.toRadians(centerTapeAngle));
        public static final Pose2d leftTapeMid = new Pose2d(-47.5, -30, rightTapeAngle);


        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(), leftTapeMid.getY()
                - Constants.TapeConstants.tapeLength / 4, Math.toRadians(90))
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(), centerTapeMid.getY(), Math.toRadians(90))
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(), rightTapeMid.getY()
                + Constants.TapeConstants.tapeLength / 4, Math.toRadians(90))
                .plus(Constants.RobotConstants.pixelDropPose);

        public static final Pose2d retPose = new Pose2d(0, -4);

        public static final Pose2d leftRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retPose);

        private static final Pose2d lrStafeByPose = new Pose2d(11.5, 0);

        public static final Pose2d lrStrafePose = rightRetractPose.plus(lrStafeByPose);


    }
}