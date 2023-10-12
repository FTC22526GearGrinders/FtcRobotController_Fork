package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

//import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

public class ActiveMotionValues {


    private static Pose2d startPose = new Pose2d();
    //  private static AprilTagPoseFtc currentTagPose;
    private static double yFirstPoint = 0;
    private static Pose2d finalPose = new Pose2d();

    private static Pose2d parkPose = new Pose2d();

    private static Pose2d stageDoorLineUoPose = new Pose2d();


    public static void setStageDoorlineUoPose(Pose2d pose) {
        stageDoorLineUoPose = pose;
    }

    public static Pose2d getStageDoorlineUoPose() {
        return stageDoorLineUoPose;
    }


    private static int atag = 1;

    public static Pose2d getStartPose() {
        return startPose;
    }

    public static void setStartPose(Pose2d pose) {
        startPose = pose;
    }

    public static Pose2d getFinalPose() {
        return finalPose;
    }
    public static void setParkPose(Pose2d pose) {
        parkPose = pose;
    }

    public static Pose2d getParkPose() {
        return parkPose;
    }
    public static void setFinalPose(Pose2d pose) {
        finalPose = pose;
    }


    public static double getxFirstPoint() {
        return xFirstPoint;
    }

    public static void setxFirstPoint(double x1stPoint) {
        xFirstPoint = x1stPoint;
    }

    public static double getxSecondPoint() {
        return xSecondPoint;
    }

    public static void setxSecondPoint(double x2ndPoint) {
        xSecondPoint = x2ndPoint;
    }


    public static double getyFirstPoint() {
        return yFirstPoint;
    }

    public static void setyFirstPoint(double y1stPoint) {
        yFirstPoint = y1stPoint;
    }


    public static double getySecondPoint() {
        return ySecondPoint;
    }

    public static void setySecondPoint(double y2ndPoint) {
        ySecondPoint = y2ndPoint;
    }

    public static Pose2d getTagLookAheadPose() {
        return tagLookAheadPose;
    }

    public static void setTagLookAheadPose(Pose2d tagLAPose) {
        tagLookAheadPose = tagLAPose;
    }

    public static double getyOffset() {
        return yOffset;
    }

    public static void setyOffset(double yOff) {
        yOffset = yOff;
    }

    public static double getxOffset() {
        return xOffset;
    }

    public static void setxOffset(double xOff) {
        xOffset = xOff;
    }

    public static double getRetractDistance() {
        return retractDistance;
    }

    public static void setRetractDistance(double distance) {
        retractDistance = distance;
    }

    public static boolean getRedAlliance() {
        return redAlliance;
    }

    public static void setRedAlliance(boolean redA) {
        redAlliance = redA;
    }

    public static boolean getBBStart() {
        return bbStart;
    }

    public static void setBBStart(boolean bbSt) {
        bbStart = bbSt;
    }

    public static int getLcrpos() {
        return lcrpos;
    }

    public static void setLcrpos(int lcr) {
        lcrpos = lcr;
    }

    public static int getActTag() {
        return actTag;
    }

    public static void setActTag(int act) {
        actTag = act;
    }

    private static double xFirstPoint;

    private static double xSecondPoint;


    private static double ySecondPoint;

    private static Pose2d tagLookAheadPose = new Pose2d();
    private static double yOffset;
    private static double xOffset;
    private static double retractDistance = 0;


    //auto running
    private static boolean redAlliance;

    private static boolean bbStart;


    private static int lcrpos = 2;

    private static int actTag = 1;


    private ActiveMotionValues() {

    }

    public double HSVAreaL = 0;
    public double HSVAreaC = 0;
    public double HSVAreaR = 0;

    public boolean HSVRed = false;

    //private static AprilTagPoseFtc tagPoseFtc;

    private static Pose2d aprilTagPos2d = new Pose2d();

//    public static void setPoseFromTag(AprilTagPoseFtc ftcPose) {
//        tagPoseFtc = ftcPose;
//    }

    // public static AprilTagPoseFtc getPoseFromTag() {
    //  return tagPoseFtc;
    // }

    public static void setTagPose2d(Pose2d tagPose2d) {
        aprilTagPos2d = tagPose2d;
    }

    public static Pose2d getAprilTagPos2d() {
        return aprilTagPos2d;
    }
}
