package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;


public final class FieldConstantsRed {


    public static Pose2d wingPose = new Pose2d(-60, 60, Math.toRadians(0));

    public static Pose2d farBackstagePark = new Pose2d(60, -12, Math.toRadians(90));// 'near' to drivers

    public static Pose2d nearBackstagePark = new Pose2d(60, -60, Math.toRadians(0));

    public static Pose2d clearForSecondPixelTrussPose = new Pose2d(24, -60);
    public static Pose2d clearForSecondPixelSD = new Pose2d(24, 12);


    public static Pose2d slideToNearBBSideParkPose = new Pose2d(50, -60, Math.toRadians(0));

    public static Pose2d slideToCenterBBSideParkPose = new Pose2d(50, -14, Math.toRadians(0));

    public static Pose2d nearBBSideParkPose = new Pose2d(60, -60, Math.toRadians(90));

    public static Pose2d nearLookForAprilTagPose = new Pose2d(12, -60, Math.toRadians(90));
    public static Pose2d centerLookForAprilTagPose = new Pose2d(12, -12, Math.toRadians(90));

    public static Pose2d stageDoorLineUpPose13 = new Pose2d(-36, -12, Math.toRadians(90));

    public static Pose2d stageDoorLineUpPose2 = new Pose2d(-48, -12, Math.toRadians(90));

    public static Pose2d nearBBSDLineUpPose = new Pose2d(12, -12, Math.toRadians(0));

    public static Pose2d nearTrussLineUpPose = new Pose2d(-36, -60, Math.toRadians(0));

    public static Pose2d centerBBSideParkPose = new Pose2d(50, -12, Math.toRadians(0));


    public static final class AprilTagConstants {
        public static int leftAtag = 0; //index into arrtay tags ar 1,2,3

        public static int canterAtag = 1;

        public static int rightAtag = 2;

        static final double atagAngle = 0;


        public static final Pose2d tagLookAheadPose = new Pose2d(14, 0, Math.toRadians(180));


        // tages 4,5 6
        public static final Pose2d atag4 = new Pose2d(60.25, -29.41, Math.toRadians(atagAngle));
        public static final Pose2d atag5 = new Pose2d(60.25, -35.41, Math.toRadians(atagAngle));
        public static final Pose2d atag6 = new Pose2d(60.25, -41.41, Math.toRadians(atagAngle));


    }

    public static Pose2d setActiveTagPose(int num) {
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

        public static final double LeftTapeAngle = Math.toRadians(0);//incremental  number
        static final double CenterTapeAngle = Math.toRadians(90);//incremental  number
        public static final double RightTapeAngle = Math.toRadians(0);//incremental  number
        public static final Pose2d StartPos = new Pose2d(12, -Constants.FieldConstants.length / 2
                + Constants.RobotConstants.length / 2, Math.toRadians(-90));
        public static double autoAdvance = 12;
        public static final Pose2d advancePose = new Pose2d(24, XPYM.StartPos.getY()
                + autoAdvance, Math.toRadians(-90));


        public static final Pose2d RightTapeMid = new Pose2d(23.5, -30, Math.toRadians(RightTapeAngle));
        public static final Pose2d CenterTapeMid = new Pose2d(12, -24.5, Math.toRadians(CenterTapeAngle));
        public static final Pose2d LeftTapeMid = new Pose2d(0.5, -30, LeftTapeAngle);


    }

    public static final class XMYM {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants

        //  static double xShift = 48;
        public static final double LeftTapeAngle = Math.toRadians(90);//incremental  number
        static final double CenterTapeAngle = Math.toRadians(90);//incremental  number
        public static final double RightTapeAngle = Math.toRadians(90);//incremental  number
        public static final Pose2d StartPos = new Pose2d(-36, -(Constants.FieldConstants.length
                - Constants.RobotConstants.length) / 2, Math.toRadians(-90));


        public static double autoAdvance = 12;

        public static final Pose2d advancePose = new Pose2d(-48, FieldConstantsRed.XMYM.StartPos.getY()
                + autoAdvance, Math.toRadians(-90));

        public static final Pose2d RightTapeMid = new Pose2d(-24.5, -30, Math.toRadians(LeftTapeAngle));
        public static final Pose2d CenterTapeMid = new Pose2d(-36, -24.5, Math.toRadians(CenterTapeAngle));
        public static final Pose2d LeftTapeMid = new Pose2d(-47.5, -30, RightTapeAngle);


    }


}