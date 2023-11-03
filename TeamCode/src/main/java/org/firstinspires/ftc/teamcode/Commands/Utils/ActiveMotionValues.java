package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class ActiveMotionValues {

    public static boolean getAprilTagSeen() {
        return aprilTagSeen;
    }

    public static void setAprilTagSeen(boolean tagSeen) {
        aprilTagSeen = tagSeen;
    }

    private static boolean aprilTagSeen = false;


    public static int getBaseTag() {
        return baseTag;
    }

    public static void setBaseTag(int tag) {
        baseTag = tag;
    }

    private static int baseTag = 1;


    public static int getBackboardLevel() {
        return backboardlevel;
    }

    public static void setBackboardLevel(int level) {
        backboardlevel = level;
    }

    private static int backboardlevel = 1;

    public static Pose2d getFinalTagPose() {
        return finalTagPose;
    }

    public static void setFinalTagPose(Pose2d pose) {
        finalTagPose = pose;
    }

    private static Pose2d finalTagPose = new Pose2d();

    public static Pose2d getCurrentRobotPose() {
        return currentRobotPose;
    }

    public static void setCurrentRobotPose(Pose2d pose) {
        currentRobotPose = pose;
    }

    private static Pose2d currentRobotPose = new Pose2d();


    public static Pose2d getAutoRobotPose() {
        return autoRobotPose;
    }

    public static void setAutoRobotPose(Pose2d pose) {
        autoRobotPose = pose;
    }

    private static Pose2d autoRobotPose = new Pose2d();


    public static Pose2d getTrussSDLineUpPose() {
        return trussSDLineUpPose;
    }

    public static void setTrussSDLineUpPose(Pose2d lineUpPose) {
        trussSDLineUpPose = lineUpPose;
    }

//    public static Pose2d getStrafePose() {
//        return strafePose;
//    }
//
//    public static void setStrafePose(Pose2d pose) {
//        strafePose = pose;
//    }

    public static Pose2d getOptionStopPose() {
        return optionStopPose;
    }

    public static void setOptionStopPose(Pose2d opPose) {
        optionStopPose = opPose;
    }

    private static Pose2d trussSDLineUpPose = new Pose2d();
    private static Pose2d retractPose = new Pose2d();

    private static Pose2d optionStopPose = new Pose2d();


    private static Pose2d startPose = new Pose2d();

    private static Pose2d parkPose = new Pose2d();

    public static Pose2d getPreTagPose() {
        return preTagPose;
    }

    public static double getStrafeDistance() {
        return strafeDistance;
    }

    public static void setStrafeDistance(double dist) {
        strafeDistance = dist;
    }

    private static double strafeDistance = 0;

    public static void setPreTagPose(Pose2d pose) {
        preTagPose = pose;
    }

    private static Pose2d preTagPose = new Pose2d();


    private static Pose2d activeAprilTagPose = new Pose2d();


    public static Pose2d getSecondAprilTagPose() {
        return secondAprilTagPose;
    }

    public static void setSecondAprilTagPose(Pose2d pose) {
        ActiveMotionValues.secondAprilTagPose = pose;
    }

    private static Pose2d secondAprilTagPose = new Pose2d();


    public static Pose2d getStartPose() {
        return startPose;
    }

    public static void setStartPose(Pose2d pose) {
        startPose = pose;
    }


    private static boolean secondPixel = false;

    public static void setSecondPixel(boolean secPixel) {
        secondPixel = secPixel;
    }

    public static boolean getSecondPixel() {
        return secondPixel;
    }

    private static Pose2d advancePose = new Pose2d();


    public static void setAdvancePose(Pose2d pose) {
        advancePose = pose;
    }

    public static Pose2d getAdvancePose() {
        return advancePose;
    }


    public static Pose2d getRetractPose() {
        return retractPose;
    }

    public static void setRetractPose(Pose2d pose) {
        retractPose = pose;
    }

    public static Pose2d getDropOffPose() {
        return dropOffPose;
    }

    public static void setDropOffPose(Pose2d pose) {
        dropOffPose = pose;
    }

    private static Pose2d dropOffPose = new Pose2d();


//    public static Pose2d getTagLookAheadPose() {
//        return tagLookAheadPose;
//    }
//
//    public static void setTagLookAheadPose(Pose2d tagLAPose) {
//        tagLookAheadPose = tagLAPose;
//    }

    public static void setActiveTagPose(Pose2d pose) {
        activeAprilTagPose = pose;
    }


    public static Pose2d getActiveTagPose() {
        return activeAprilTagPose;
    }


    public static void setYOffsetPose(Pose2d yOff) {
        yOffsetPose = yOff;
    }

    public static Pose2d getYOffsetPose() {
        return xOffsetPose;
    }

    public static void setXOffsetPose(Pose2d xOff) {
        xOffsetPose = xOff;
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

    public static void setParkPose(Pose2d pose) {
        parkPose = pose;
    }

    public static Pose2d getParkPose() {
        return parkPose;
    }


    //private static double xFirstPoint;

    private static double xSecondPoint;


    private static double ySecondPoint;

    private static Pose2d tagLookAheadPose = new Pose2d();
    private static Pose2d yOffsetPose;
    private static Pose2d xOffsetPose;
    private static double retractDistance = 0;


    //auto running
    private static boolean redAlliance;

    private static boolean bbStart;


    private static int lcrpos = 2;

    private static int actTag = 5;

    public double HSVAreaL = 0;
    public double HSVAreaC = 0;
    public double HSVAreaR = 0;

    public boolean HSVRed = false;

    // private static AprilTagPoseFtc tagPoseFtc;

    private static Pose2d aprilTagPos2d = new Pose2d();

//    public static void setPoseFromTag(AprilTagPoseFtc ftcPose) {
//        tagPoseFtc = ftcPose;
//    }
//
//    public static AprilTagPoseFtc getPoseFromTag() {
//        return tagPoseFtc;
//    }

    public static void setTagPose2d(Pose2d tagPose2d) {
        aprilTagPos2d = tagPose2d;
    }

    public static Pose2d getAprilTagPos2d() {
        return aprilTagPos2d;
    }


    private static boolean useStageDoor = false;

    private static boolean centerPark = false;

    public static void setCenterPark(boolean cPark) {
        centerPark = cPark;
    }

    public static boolean getCenterPark() {
        return centerPark;
    }

    public static void setUseStageDoor(boolean useStDoor) {
        useStageDoor = useStDoor;
    }

    public static boolean getUseStageDoor() {
        return useStageDoor;
    }

    private static Pose2d optionTargetPose = new Pose2d();

    public static void setOptionTargetPose(Pose2d opt) {
        optionTargetPose = opt;
    }

    public static Pose2d getOptionTargetPose() {
        return optionTargetPose;
    }

    private static Pose2d endAutoPose = new Pose2d();

    public static void setEndAutoPose(Pose2d opt) {
        endAutoPose = opt;
    }

    public static Pose2d getEndAutoPose() {
        return endAutoPose;
    }

    public static boolean getLeftPark() {
        return leftPark;
    }

    public static void setParkInPlace(boolean park) {
        ActiveMotionValues.leftPark = park;
    }

    private static boolean leftPark = false;


}
