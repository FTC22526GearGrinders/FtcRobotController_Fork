package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class Constants {

    public static final class RobotConstants {

        public static final double length = 13.25;
        static final double height = 13;
        static final double width = 16;

        public static final Pose2d robotPose = new Pose2d(length / 2, width / 2);

        static final double camToCenterX = 6;
        static final double camToCenterY = 6;
        public static Pose2d pixelDropPose = new Pose2d(0, -RobotConstants.length / 2 + 1, 0);


        public static Pose2d kCameraToRobot = new Pose2d(RobotConstants.length / 2, 0);
    }

    public static final class FieldConstants {

        static final double length = 144;
        static final double width = 144;

    }

    public static final class TapeConstants {

        public static final double tapeWidth = 1;
        public static final double tapeLength = 12;
        static final double gapBetweenTapeY = 20;
    }


    public static final class DriveConstants {
        public static final double MAX_MOTOR_RPM = 312;
        public static final double MOTOR_GEAR_RATIO = 13.7;

        public static final double GEARBOX_RATIO = 1;
        public static final double WHEEL_DIAMETER_INCH = 4;

        public static final double WHEELBASE = 10.5;//front yo back

        public static final double TRACKWIDTH = 14.5;//acress width


        public static final double ENCODER_COUNTS_PER_WHEEL_REV = 537.7;//1:1 RATIO
        public static final double WHEEL_CIRCUMFERENCE_INCH = Math.PI * WHEEL_DIAMETER_INCH;//12.57
        public static final double INCHES_PER_ENCODER_COUNT = WHEEL_CIRCUMFERENCE_INCH / ENCODER_COUNTS_PER_WHEEL_REV;//.0234
        public static final double MAX_IPM = MAX_MOTOR_RPM * WHEEL_CIRCUMFERENCE_INCH;// 312 *12.57/00 = 60 IPS
        public static final double BATTERY_VOLTS = 12;

        public static final double kV = BATTERY_VOLTS / MAX_IPM;//12/60 = .2 MAX THEORETICAL VALUE
        public static final double ENCODER_COUNTS_PER_MOTOR_REV = 537.7;

        public static final double POSITION_Kp = .03;
        public static final double POSITION_Ki = 0;
        public static final double POSITION_Kd = 0;
        public static final double POSITION_TOLERANCE = 1;

        public static final double ROTATE_Kp = .022;
        public static final double ROTATE_Ki = 0;
        public static final double ROTATE_Kd = 0;

        public static final double ROTATE_TOLERANCE = 2;

        public static final double MOVE_STRAIGHT_Kp = .01;

        public static final double POSITION_POWER = .5;
        public static final double ROTATE_SPEED = .75;
        //  public static final double LATERAL_MULTIPLIER = .7;


    }


    public static final class PixelHandlerConstants {
        public static final double CLAW_CLOSE_POSITION = .74;
        public static final double CLAW_OPEN_POSITION = .20;
        public static final double DROP_OPEN_POSITION = 0;
        public static final double DROP_CLOSED_POSITION = .7;

        public static final double CLAW_ARM_EXTEND_POSITION = .90;
        public static final double CLAW_ARM_RETRACT_POSITION = .10;


        public static final double CLAW_ARM_DROP_DISTANCE = .5;
    }

    public static final class ArmConstants {

        public static final double MAX_MOTOR_RPM = 435;

        public static final double MAX_MOTOR_RPSEC = 312 / 60;


        public static final double ENCODER_COUNTS_PER_MOTOR_REV = 384.5;

        public static final double GEARING_RATIO = .16667;// motor revs per inch

        public static final double ENCODER_COUNTS_PER_INCH = ENCODER_COUNTS_PER_MOTOR_REV * GEARING_RATIO;

        public static final double MOTOR_REVS_PER_INCH = GEARING_RATIO;

        public static final double MAX_INCHES_PER_SECOND = MAX_MOTOR_RPSEC / GEARING_RATIO;
        public static final double POSITION_TOLERANCE_INCHES = .5;
        public static final double UPPER_POSITION_LIMIT = 35.00;

        public static final int LOWER_POSITION_LIMIT = -1;


        public static final double JOG_UP_POWER = +.5;

        public static final double JOG_DOWN_POWER = -.3;


        public static double kP = .25;
        public static double kI = 0;
        public static double kD = 0;

        public static double POSITION_Kg;

        public static final int NUMBER_LEVELS = armExtensions.values().length;


        public enum armExtensions {

            HOME(0, 1.3),
            LOW(5, 2.4),
            MID(12, 3.5),
            HIGH(19, 4.6);

            public final double extension;
            public final double tagDistance;


            armExtensions(double extension, double tagDistance) {
                this.extension = extension;
                this.tagDistance = tagDistance;
            }


        }


    }

    public static final class CatapultConstants {

        public static final double CATAPULT_LOCK_POSITION = .10;
        public static final double CATAPULT_RELEASE_POSITION = .90;


    }
}