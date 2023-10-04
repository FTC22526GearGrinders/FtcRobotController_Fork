package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class FieldConstantsRed {


    public static Pose2d wingPose = new Pose2d(-60, 60, Math.toRadians(0));

    public static final class AprilTagConstants {
        public static int leftAtag = 0; //index into arrtay tags ar 1,2,3

        public static int canterAtag = 1;

        public static int rightAtag = 2;

        static final double atagAngle = 0;


        public static final Pose2d tagLookAheadPose = new Pose2d(-24, 0);


        // tages 4,5 6
        public static final Pose2d atag4 = new Pose2d(60.25, -29.41, Math.toRadians(atagAngle));
        public static final Pose2d atag5 = new Pose2d(60.25, -35.41, Math.toRadians(atagAngle));
        public static final Pose2d atag6 = new Pose2d(60.25, -41.41, Math.toRadians(atagAngle));


    }

    public static final class TAPE {

        static final double fieldLength = 164;
        static final double fieldWidth = 164;
        static final double tapeWidth = 2;
        public static final double tapeLength = 12;
        static final double gapBetweenTapeY = 20;
    }


    public static final class ROBOT {

        static final double height = 13;

        static final double width = 13;

        public static final double length = 13;


        static final double camToCenterX = 6;
        static final double camToCenterY = 6;

    }

    public static final class FIELD {

        static final double length = 144;
        static final double width = 144;

    }

    public static final class XPYM {
        //left and right are from the view of the robot

        public static final double LeftTapeAngle = Math.toRadians(0);//incremental  number
        static final double CenterTapeAngle = Math.toRadians(90);//incremental  number
        public static final double RightTapeAngle = Math.toRadians(0);//incremental  number
        public static final Pose2d StartPos = new Pose2d(12, -FIELD.length/2 + ROBOT.length/2, Math.toRadians(90));
        public static final Pose2d rightTapeMid = new Pose2d(24, -30, Math.toRadians(RightTapeAngle));
        public static final Pose2d CenterTapeMid = new Pose2d(12, -14, Math.toRadians(CenterTapeAngle));
        public static final Pose2d LeftTapeMid = new Pose2d(0, -30, LeftTapeAngle);


    }

    public static final class XMYM {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants

        //  static double xShift = 48;
        public static final double LeftTapeAngle = Math.toRadians(90);//incremental  number
        static final double CenterTapeAngle = Math.toRadians(90);//incremental  number
        public static final double RightTapeAngle = Math.toRadians(90);//incremental  number
        public static final Pose2d StartPos = new Pose2d(-36, -(FIELD.length - ROBOT.length) / 2, Math.toRadians(90));
        public static final Pose2d RightTapeMid = new Pose2d(-24, -30, Math.toRadians(LeftTapeAngle));
        public static final Pose2d CenterTapeMid = new Pose2d(-36, -24, Math.toRadians(CenterTapeAngle));
        public static final Pose2d LeftTapeMid = new Pose2d(-48, -30, RightTapeAngle);


    }


}