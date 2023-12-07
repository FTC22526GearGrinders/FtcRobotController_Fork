package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Commands.Utils.RollingAverage;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


public class Drive_Subsystem extends SubsystemBase {

    public TrajectorySequence currentTrajSeq = null;
    public String trajName = "";
    public String runningTrajName = "";
    public boolean trajectoryBuilt;
    public boolean trajectoryBuilding;

    private ElapsedTime runtime = new ElapsedTime();


    public SampleMecanumDrive drive;

    CommandOpMode myOpmode;

    public boolean running = false;

    public boolean started = false;

    public boolean stopped = false;


    public ProfiledPIDController profController;


    public double strafe_gain = Constants.DriveConstants.STRAFE_GAIN;
    public double turn_gain = Constants.DriveConstants.TURN_GAIN;

    //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

   public double stopDistanceFromTag = 5;

    public Drive_Subsystem(CommandOpMode opMode) {
        myOpmode = opMode;
        runtime.reset();


        drive = new SampleMecanumDrive(myOpmode.hardwareMap);

        runtime.reset();

        profController = new ProfiledPIDController(
                Constants.DriveConstants.kP, Constants.DriveConstants.kI, Constants.DriveConstants.kD,
                new TrapezoidProfile.Constraints(Constants.DriveConstants.POSN_VEL, Constants.DriveConstants.POSN_ACCEL));

        profController.setTolerance(Constants.DriveConstants.POSITION_TOLERANCE_INCHES);

        profController.reset(0);
    }

    public void periodic() {


    }

    public void setPositionKp(double kp) {
        profController.setP(kp);
    }

    public double getPositionKp() {
        return profController.getP();
    }

    public double getPositionKi() {
        return profController.getD();
    }

    public void setPositionKi(double ki) {
        profController.setI(ki);
    }

    public double getPositionKd() {
        return profController.getD();
    }

    public void setPositionKd(double kd) {
        profController.setD(kd);
    }

    public void setTrapConstraints(double vel, double acc) {
        profController.setConstraints(new TrapezoidProfile.Constraints(vel, acc));
    }

    public double getStrafe_gain() {
        return strafe_gain;
    }

    public void setStrafe_gain(double gain) {
        strafe_gain = gain;
    }

    public double getTurn_gain() {
        return turn_gain;
    }

    public void setTurn_gain(double gain) {
        turn_gain = gain;
    }


    public void showtelemetry(Telemetry telemetry) {



        telemetry.update();

    }

}




