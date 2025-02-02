package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class ActiveMotionValues {

    public static double getStopSecs() {
        return stopSecs;
    }

    public static void setStopSecs(double secs) {stopSecs = secs;
    }

    public static double stopSecs=.1;

    public static boolean getAprilTagSeen() {
        return aprilTagSeen;
    }

    public static void setAprilTagSeen(boolean tagSeen) {
        aprilTagSeen = tagSeen;
    }

    private static boolean aprilTagSeen = false;

    private static AprilTagDetection detection;

    public static AprilTagDetection getDetection(){
        return detection;
    }

    public static void setDetection(AprilTagDetection detect){
        detection=detect;
    }


    public static Pose2d getFinalTagPose() {
        return finalTagPose;
    }

    public static void setFinalTagPose(Pose2d pose) {
        finalTagPose = pose;
    }

    private static Pose2d finalTagPose = new Pose2d();

    public static Pose2d getTrussSDLineUpPose() {
        return trussSDLineUpPose;
    }

    public static void setTrussSDLineUpPose(Pose2d lineUpPose) {
        trussSDLineUpPose = lineUpPose;
    }

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

    public static Pose2d getClearPose() {
        return clearPose;
    }

    public static void setClearPose(Pose2d pose) {
        clearPose = pose;
    }

    private static Pose2d clearPose = new Pose2d();







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

    //auto running
    private static boolean redAlliance;

    private static boolean bbStart;


    private static int lcrpos = 0;

    private static int actTag = 0;

    private static boolean useStageDoor = false;

    private static boolean centerPark = false;

    public static void setCenterPark(boolean cPark) {
        centerPark = cPark;
    }

    public static boolean getCenterPark() {
        return centerPark;
    }

    private static boolean nearPark = false;

    public static void setNearPark(boolean nPark) {
        nearPark = nPark;
    }

    public static boolean getNearPark() {
        return nearPark;
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



}
