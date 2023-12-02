package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class Constants {

    public static final class RobotConstants {

        public static final double length = 13.25;
        static final double height = 13;
        static final double width = 16;

        static double gripperXOffset = 4;
        public static Pose2d pixelDropPose = new Pose2d(0, -RobotConstants.length / 2 , 0);
        public static Pose2d leftGripperPose = new Pose2d(-gripperXOffset, -RobotConstants.length / 2 , 0);
        public static Pose2d rightGripperPose = new Pose2d(gripperXOffset, -RobotConstants.length / 2 , 0);

        public static double pixelDropyOffset = 1;

        public static Pose2d activeDropOffsetPose = pixelDropPose;

        public static Pose2d kCameraToRobot = new Pose2d(RobotConstants.length / 2, 6);
    }

    public static final class FieldConstants {

        static final double length = 144;
        static final double width = 144;

    }

    public static final class TapeConstants {

        public static final double tapeWidth = 1;
        public static final double tapeLength = 12;
    }


    public static final class DriveConstants {

        public static final boolean RUN_USING_ENCODER = false;


        public static final double MAX_MOTOR_RPM = 312;


        public static final double GEARBOX_RATIO = 1;
        public static final double WHEEL_DIAMETER_INCH = 4;

        public static final double WHEELBASE = 10.5;//distance between pairs of wheels on the same side of the robot

        public static final double TRACKWIDTH = 17;// from code sourec seemed to work better measured is 14.5;//lateral distance between pairs of wheels on different sides of the robot
        public static final double ENCODER_COUNTS_PER_WHEEL_REV = 537.7;//1:1 RATIO
        public static final double WHEEL_CIRCUMFERENCE_INCH = Math.PI * WHEEL_DIAMETER_INCH;//12.57
        public static final double INCHES_PER_ENCODER_COUNT = WHEEL_CIRCUMFERENCE_INCH / ENCODER_COUNTS_PER_WHEEL_REV;//.0234
        public static final double MAX_IPM = MAX_MOTOR_RPM * WHEEL_CIRCUMFERENCE_INCH;// 312 *12.57 = 3900 IPM

        public static final double MAX_IPS = MAX_IPM / 60;//65 IPS

        public static double MAX_VEL = MAX_IPS * .8;
        public static double MAX_ACCEL = 30;
        public static double MAX_ANG_VEL = Math.toRadians(60);
        public static double MAX_ANG_ACCEL = Math.toRadians(60);
        public static double TRAJ_VEL = 40;
        public static double TRAJ_ACCEL = 40;
        public static double TRAJ_ANG_VEL = Math.toRadians(40);
        public static double TRAJ_ANG_ACCEL = Math.toRadians(40);


        public static double getMotorVelocityF(double ticksPerSecond) {
            // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
            return 32767 / ticksPerSecond;
        }

        //     */
        public static double kV = .0171;//12 volts/60 ips = .02 max
        public static double kA = 0.002;// 0.0005;
        public static double kStatic = 0.022;//0.08;


//        public static double kV =  0.0140;
//        public static double kA = 0.0022;
//        public static double kStatic = 0.022;

        public static final double BATTERY_VOLTS = 12;

        //public static final double kV = BATTERY_VOLTS / MAX_IPM;//12/60 = .2 MAX THEORETICAL VALUE
        public static final double ENCODER_COUNTS_PER_MOTOR_REV = 537.7;


        public static final Pose2d tagOffsetPose = new Pose2d(RobotConstants.length / 2 + 2, 0, 0);


        public static double encoderTicksToInches(double ticks) {
            return WHEEL_DIAMETER_INCH * Math.PI * GEARBOX_RATIO * ticks / ENCODER_COUNTS_PER_MOTOR_REV;

        }

        public static double rpmToVelocity(double rpm) {
            return rpm * GEARBOX_RATIO * 2 * Math.PI * WHEEL_DIAMETER_INCH / 2 / 60.0;
        }
    }

    public static final class PixelHandlerConstants {
        public static final double DROP_OPEN_POSITION = 0;
        public static final double DROP_CLOSED_POSITION = .7;

        public enum LeftGripperSet {
            OPEN(.1),//open position
            MID(.3),//mid
            CLOSED(.5);//closed

            public final double position;

            LeftGripperSet(double position) {
                this.position = position;
            }
        }


        public enum RightGripperSet {
            OPEN(.1),
            MID(.3),
            CLOSED(.5);

            public final double position;

            RightGripperSet(double position) {
                this.position = position;
            }
        }


        public enum TurnGripperSet {
            PICKUP(.1),
            MID(.3),
            DELIVER(.5);

            public final double position;

            TurnGripperSet(double position) {
                this.position = position;
            }
        }
    }

    public enum TurnGripperJogSet {
        LO(.001),

        MED(.01),

        HI(.1);

        public final double increment;

        TurnGripperJogSet(double increment) {
            this.increment = increment;
        }
    }


    public static final class ArmConstants {

        public static final double MAX_MOTOR_RPM = 435;

        public static final double MAX_MOTOR_RPSEC = 312 / 60;


        public static final double ENCODER_COUNTS_PER_MOTOR_REV = 384.5;

        public static final double GEARING_RATIO = 4.72;// motor revs per inch

        public static final double ENCODER_COUNTS_PER_INCH = ENCODER_COUNTS_PER_MOTOR_REV / GEARING_RATIO;

        public static final double MOTOR_REVS_PER_INCH = GEARING_RATIO;

        public static final double MAX_INCHES_PER_SECOND = MAX_MOTOR_RPSEC / GEARING_RATIO;
        public static final double POSITION_TOLERANCE_INCHES = .5;
        public static final double UPPER_POSITION_LIMIT = 27.00;

        public static final int LOWER_POSITION_LIMIT = -1;


        public static final double JOG_UP_POWER = +.5;

        public static final double JOG_DOWN_POWER = -.5;

        public static double[] armPositionInches = {2, 3, 3.6, 4.3, 6.8, 9};


        public static double kP = .25;
        public static double kI = 0;
        public static double kD = 0;

        public static double POSITION_Kg;

    }

    public static final class CatapultConstants {

        public static final double CATAPULT_LOCK_POSITION = .10;
        public static final double CATAPULT_RELEASE_POSITION = .90;


    }


}

