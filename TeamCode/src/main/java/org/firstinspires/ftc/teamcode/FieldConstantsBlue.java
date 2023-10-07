package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class FieldConstantsBlue {

    public static Pose2d wingPose = new Pose2d(-60,-60, Math.toRadians(0));

    public static final class AprilTagConstants {

        static final double atagAngle = 0;


        public static final Pose2d tagLookAheadPose = new Pose2d(-24, 0);



        public static final Pose2d  atag1 = new Pose2d(60.25, 41.41, Math.toRadians(atagAngle));
        public static final Pose2d atag2= new Pose2d(60.25, 35.41, Math.toRadians(atagAngle));
        public static final Pose2d atag3 = new Pose2d(60.25, 29.41, Math.toRadians(atagAngle));



    }


    public static final class XPYP {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrant
        //
        public static final Pose2d StartPos = new Pose2d(12, 60, Math.toRadians(-90));
        public static final double LeftTapeAngle = Math.toRadians(90);//incremental  number
        static final double CenterTapeAngle = Math.toRadians(0);//incremental  number
        public static final double RightTapeAngle = Math.toRadians(90);//incremental number

        public static final Pose2d LeftTapeMid = new Pose2d(24, 30, Math.toRadians(LeftTapeAngle));
        public static final Pose2d CenterTapeMid = new Pose2d(12, 24, Math.toRadians(CenterTapeAngle));
        public static final Pose2d RightTapeMid = new Pose2d(0, 30, RightTapeAngle);

    }


    public static final class XMYP {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants
        public static final double LeftTapeAngle = Math.toRadians(0);//incremental  number
        static final double CenterTapeAngle = Math.toRadians(90);//incremental  number
        public static final double RightTapeAngle = Math.toRadians(0);//incremental  number
        public static final Pose2d StartPos = new Pose2d(-36, Constants.FieldConstants.length/2 - Constants.RobotConstants.length / 2, Math.toRadians(-90));
        public static final Pose2d LeftTapeMid = new Pose2d(-24, 36, Math.toRadians(LeftTapeAngle));
        public static final Pose2d CenterTapeMid = new Pose2d(-36, 24, Math.toRadians(CenterTapeAngle));
        public static final Pose2d RightTapeMid = new Pose2d(-48, 36, RightTapeAngle);


    }



}