package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class FieldConstantsRed {

    public static double startangle = Math.toRadians(-90);

    public static Pose2d slideToNearParkPose = new Pose2d(50, -60);

    public static Pose2d slideToCenterParkPose = new Pose2d(50, -14);

    public static Pose2d nearParkPose = new Pose2d(50, -60,startangle);

    public static Pose2d nearOptionPose = new Pose2d(42, -60, startangle);

    public static Pose2d centerOptionPose = new Pose2d(42, -12,startangle);

    public static Pose2d stageDoorLineUpPose13 = new Pose2d(-36, -12,startangle);

    public static Pose2d stageDoorLineUpPose2 = new Pose2d(-48, -12,startangle);


    public static Pose2d nearTrussLineUpPose = new Pose2d(-36, -60, startangle);

    public static Pose2d centerParkPose = new Pose2d(50, -12, startangle);


    public static final class AprilTagConstants {
        public static int leftAtag = 0; //index into arrtay tags ar 1,2,3

        public static int canterAtag = 1;

        public static int rightAtag = 2;

        static final double atagAngle = 0;


        public static final Pose2d tagLookAheadPose = new Pose2d(4+ Constants.RobotConstants.length/2, 0, Math.toRadians(0));

        public static final Pose2d tagStrafeOffsetPose = new Pose2d(-60.25 + 42, 0, 0);


        // tages 4,5 6
        public static final Pose2d atag4 = new Pose2d(60.25, -30, Math.toRadians(atagAngle));
        public static final Pose2d atag5 = new Pose2d(60.25, -36, Math.toRadians(atagAngle));
        public static final Pose2d atag6 = new Pose2d(60.25, -42, Math.toRadians(atagAngle));


    }

    public static Pose2d getActiveTagPose(int num) {
        int sel = num - 3;
        switch (sel) {
            case 0:
                return FieldConstantsRed.AprilTagConstants.atag4;
            case 1:
                return FieldConstantsRed.AprilTagConstants.atag4;
            case 2:
                return FieldConstantsRed.AprilTagConstants.atag5;
            case 3:
                return FieldConstantsRed.AprilTagConstants.atag6;
            default:
                return FieldConstantsRed.AprilTagConstants.atag4;

        }
    }

    public static final class XPYM {
        //left and right are from the view of the robot


        public static final Pose2d startPos = new Pose2d(12, -Constants.FieldConstants.length / 2
                + Constants.RobotConstants.length / 2, startangle);
        public static final Pose2d advancePose = new Pose2d(24, -48, startangle);
        public static final Pose2d rightTapeMid = new Pose2d(23.5, -30);
        public static final Pose2d centerTapeMid = new Pose2d(12, -24);
        public static final Pose2d leftTapeMid = new Pose2d(0.5, -30);

        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(),
                leftTapeMid.getY() + Constants.TapeConstants.tapeLength / 4, startangle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY(), startangle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(),
                rightTapeMid.getY() - Constants.TapeConstants.tapeLength / 4, startangle)
                .plus(Constants.RobotConstants.pixelDropPose);

        public static final Pose2d retPose = new Pose2d(0, -4);
        public static final Pose2d retCPose = new Pose2d(0, -8);


        public static final Pose2d leftRetractPose = leftDropPose.plus(retCPose);
        public static final Pose2d leftTrussRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retCPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retCPose);
    }

    public static final class XMYM {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants


        public static final Pose2d startPose = new Pose2d(-36, -(Constants.FieldConstants.length
                - Constants.RobotConstants.length) / 2, startangle);

        public static final Pose2d advancePose = new Pose2d(-48, -48, startangle);
        public static final Pose2d rightTapeMid = new Pose2d(-24.5, -30);
        public static final Pose2d centerTapeMid = new Pose2d(-36, -24.5);
        public static final Pose2d leftTapeMid = new Pose2d(-47.5, -30);


        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(), leftTapeMid.getY()
                - Constants.TapeConstants.tapeLength / 4, startangle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY(), startangle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(),
                rightTapeMid.getY() + Constants.TapeConstants.tapeLength / 4, startangle)
                .plus(Constants.RobotConstants.pixelDropPose);

        public static final Pose2d retPose = new Pose2d(0, -4);

        public static final Pose2d leftRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retPose);

        private static final Pose2d lrStafeByPose = new Pose2d(11.5, 0);

        public static final Pose2d lrStrafePose = leftRetractPose.plus(lrStafeByPose);
    }
}