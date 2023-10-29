package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

public class ActiveMotionValues {

    public static Pose2d getRobotPose() {
        return robotPose;
    }

    public static void setRobotPose(Pose2d pose) {
        robotPose = pose;
    }

    private static Pose2d robotPose = new Pose2d();


    public static Pose2d getTrussSDLineUpPose() {
        return trussSDLineUpPose;
    }

    public static void setTrussSDLineUpPose(Pose2d lineUpPose) {
        trussSDLineUpPose = lineUpPose;
    }

    public static Pose2d getStrafePose() {
        return strafePose;
    }

    public static void setStrafePose(Pose2d pose) {
        strafePose = pose;
    }

    public static Pose2d getStrafeToAprilTagPose() {
        return strafeToAprilTagPose;
    }

    public static void setStrafeToAprilTagPose(Pose2d tagPose) {
        strafeToAprilTagPose = tagPose;
    }

    private static Pose2d trussSDLineUpPose = new Pose2d();
    private static Pose2d retractPose = new Pose2d();

    private static Pose2d strafePose = new Pose2d();

    public static Pose2d getLineupPose() {
        return lineupPose;
    }

    public static void setLineupPose(Pose2d pose) {
      lineupPose = pose;
    }

    private static Pose2d lineupPose = new Pose2d();
    private static Pose2d strafeToAprilTagPose = new Pose2d();


    private static Pose2d startPose = new Pose2d();

    private static AprilTagPoseFtc currentTagPose =
            new AprilTagPoseFtc(0, 0, 0, 0, 0, 0, 0, 0, 0);

    private static Pose2d parkPose = new Pose2d();

    public static Pose2d getLastPose() {
        return lastPose;
    }

    public static double getStrafeDistance() {
        return strafeDistance;
    }

    public static void setStrafeDistance(double dist) {
      strafeDistance = dist;
    }

    private static double strafeDistance = 0;

    public static void setLastPose(Pose2d pose) {
        lastPose = pose;
    }

    private static Pose2d lastPose = new Pose2d();


    private static Pose2d activeAprilTagPose = new Pose2d();


    private static Pose2d clearStageDoorPose = new Pose2d();

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

    public static void setClearStageDoorPose(Pose2d pose) {
        clearStageDoorPose = pose;
    }

    public static Pose2d getClearStageDoorPose() {
        return clearStageDoorPose;
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


}
