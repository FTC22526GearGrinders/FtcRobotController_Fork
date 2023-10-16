package com.example.meepmeeptesting;

public final class Constants {

    public static final class RobotConstants {

        static final double height = 13;

        static final double width = 16;

        public static final double length = 13.25;


        static final double camToCenterX = 6;
        static final double camToCenterY = 6;

    }

    public static final class FieldConstants {

        static final double length = 144;
        static final double width = 144;

    }

    public static final class TapeConstants {

        static final double fieldLength = 164;
        static final double fieldWidth = 164;
        static final double tapeWidth = 2;
        public static final double tapeLength = 12;
        static final double gapBetweenTapeY = 20;
    }


    public static final class DriveConstants {

        public static final double MOTOR_GEAR_RATIO = 13.7;

        public static final double GEARBOX_RATIO = 1;
        public static final double WHEEL_DIAMETER_INCH = 4;

        public static final double WHEELBASE = 10.5;//front yo back

        public static final double TRACKWIDTH = 14.5;//acress width
        public static final double WHEEL_CIRCUMFERENCE_INCH = Math.PI * WHEEL_DIAMETER_INCH;//12.57
        public static final double ENCODER_COUNTS_PER_MOTOR_REV = 537.7;
        public static final double ENCODER_COUNTS_PER_WHEEL_REV = 537.7;//1:1 RATIO
        public static final double INCHES_PER_ENCODER_COUNT = WHEEL_CIRCUMFERENCE_INCH/ENCODER_COUNTS_PER_WHEEL_REV;//.0234


        public static final double MAX_MOTOR_RPM = 312;
        public static final double MAX_IPM = MAX_MOTOR_RPM * WHEEL_CIRCUMFERENCE_INCH;// 312 *12.57/00 = 60 IPS

        public static final double BATTERY_VOLTS = 12;

        public static final double kV = BATTERY_VOLTS/MAX_IPM;//12/60 = .2 MAX THEORETICAL VALUE

        public static final double POSITION_Kp = .03;
        public static final double POSITION_Ki = 0;
        public static final double POSITION_Kd = 0;
        public static final double POSITION_TOLERANCE = 1;

        public static final double ROTATE_Kp = .002;
        public static final double ROTATE_Ki = 0;
        public static final double ROTATE_Kd = 0;

        public static final double ROTATE_TOLERANCE = 2;

        public static final double MOVE_STRAIGHT_Kp = .01;

        public static final double POSITION_POWER = .5;
        public static final double ROTATE_SPEED = .75;
        public static final double LATERAL_MULTIPLIER = .8;
    }


}
