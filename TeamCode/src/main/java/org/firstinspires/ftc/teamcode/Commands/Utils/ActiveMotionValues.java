package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

public class ActiveMotionValues {


    public static Pose2d startPose = new Pose2d();
    public static AprilTagPoseFtc currentTagPose;
    public static double strafeDistance = 0;
    public static double yFirstPoint = 0;
    public static Pose2d finalPose = new Pose2d();
    public static int atag = 1;

    public static Pose2d getStartPose() {
        return startPose;
    }

    public static void setStartPose(Pose2d startPose) {
        ActiveMotionValues.startPose = startPose;
    }

    public static double getxFirstPoint() {
        return xFirstPoint;
    }

    public static void setxFirstPoint(double xFirstPoint) {
        ActiveMotionValues.xFirstPoint = xFirstPoint;
    }

    public static double getySecondPoint() {
        return ySecondPoint;
    }

    public static void setySecondPoint(double ySecondPoint) {
        ActiveMotionValues.ySecondPoint = ySecondPoint;
    }

    public static Pose2d getTagLookAheadPose() {
        return tagLookAheadPose;
    }

    public static void setTagLookAheadPose(Pose2d tagLookAheadPose) {
        ActiveMotionValues.tagLookAheadPose = tagLookAheadPose;
    }

    public static double getyOffset() {
        return yOffset;
    }

    public static void setyOffset(double yOffset) {
        ActiveMotionValues.yOffset = yOffset;
    }

    public static double getxOffset() {
        return xOffset;
    }

    public static void setxOffset(double xOffset) {
        ActiveMotionValues.xOffset = xOffset;
    }

    public static double getRetractDistance() {
        return retractDistance;
    }

    public static void setRetractDistance(double retractDistance) {
        ActiveMotionValues.retractDistance = retractDistance;
    }

    public static boolean getRedAlliance() {
        return redAlliance;
    }

    public static void setRedAlliance(boolean redAlliance) {
        ActiveMotionValues.redAlliance = redAlliance;
    }

    public static boolean getBBStart() {
        return bbStart;
    }

    public static void setBBStart(boolean bbStart) {
        ActiveMotionValues.bbStart = bbStart;
    }

    public static int getLcrpos() {
        return lcrpos;
    }

    public static void setLcrpos(int lcrpos) {
        ActiveMotionValues.lcrpos = lcrpos;
    }

    public static int getActTag() {
        return actTag;
    }

    public static void setActTag(int actTag) {
        ActiveMotionValues.actTag = actTag;
    }

    public static double xFirstPoint;
    public static double ySecondPoint;

    public static Pose2d tagLookAheadPose= new Pose2d();
    public static double yOffset;
    public static double xOffset;
    public static double retractDistance = 0;


    //auto running
    public static boolean redAlliance;

    public static boolean bbStart;

    public static boolean isNearPark() {
        return nearPark;
    }

    public static void setNearPark(boolean nearPark) {
        ActiveMotionValues.nearPark = nearPark;
    }

    private static boolean nearPark;
    private static int lcrpos = 2;

    private static int actTag = 1;


    private ActiveMotionValues() {

    }

    public double HSVAreaL = 0;
    public double HSVAreaC = 0;
    public double HSVAreaR = 0;

    public boolean HSVRed = false;

    private static AprilTagPoseFtc tagPoseFtc;

    private static Pose2d aprilTagPos2d = new Pose2d();

    public static void setPoseFromTag(AprilTagPoseFtc ftcPose) {
        tagPoseFtc = ftcPose;
    }

    public static AprilTagPoseFtc getPoseFromTag() {
        return tagPoseFtc;
    }

    public static void setTagPose2d(Pose2d tagPose2d) {
        aprilTagPos2d = tagPose2d;
    }

    public static Pose2d getAprilTagPos2d() {
        return aprilTagPos2d;
    }
}
