package org.firstinspires.ftc.teamcode;

/*

This sample OpMode demonstrates using the Datalogger class, which allows
FTC OpModes to log data to a CSV file, for direct import to Excel or Google Sheets.

This OpMode collects and writes the data to a log file in the RC device folder
FIRST/java/src/Datalogs.  Specify the filename in this OpMode code.

Further info is available here:
https://github.com/WestsideRobotics/FTC-Datalogging/wiki

The source of data here is the IMU contained in the REV Control Hub and most
REV Expansion Hubs.  Two fields are logged: Count (index number of each reading)
and Heading (Z-angle in degrees).

In the CSV/spreadsheet, these two columns are preceded by two default columns:
"Time" shows # of seconds since the datalogging class was instantiated, and
"d ms" shows # of milliseconds since the last data reading.



1/4/2022
This v05 calls Datalogger v05, which supports downloading from OnBot Java,
instead of file transfer via USB cable or wireless Android Debug Bridge (adb).

v04 called Datalogger v04, which inserts the timestamp at the beginning of
a completed line, just before writing the line to the datalog file.  Previously
the timestamp was inserted at the beginning of a new line, after writing
the last completed line. Neither method records the exact moment of data
capture, but this method has less time lag and relates to the current
line rather than the previous line.

The v02 version added logging data on a **timed interval**, greater than the
average cycle time of the unregulated while() loop -- observed to be 8-10 ms in v01.



Notes:

1. Do not run this OpMode while the RC device is connected via USB cable
(in file transfer/MTP mode) to a Windows laptop.  The datalog file
will be created, but no data will be logged to the file.

It's OK to run this OpMode while the RC device is connected wirelessly
via Android Debug Bridge (adb) to a Windows laptop.

2. The first values of "Time" and "d ms" will be the same.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 x

Credit to Olavi Kamppari, who shared a more advanced version dated 9/9/2015.
*/

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import com.qualcomm.hardware.bosch.BNO055IMU;   // IMU used in REV Hubs

@Autonomous(name = "DataLog IMU v05", group = "Datalogging")

public class W_DL_OpMode_IMU_v05 extends LinearOpMode {

    // Declare members.
    public IMU imu;

    YawPitchRollAngles angles;
    W_Datalogger_v05 imuDL;     // edit name to Datalogger, or the name you used

    double myHeading;
    int readCount = 0;
    String datalogFilename = "myDatalog_006";   // modify name for each run

    ElapsedTime dataTimer;              // timer object
    int logInterval = 50;               // target interval in milliseconds

    @Override
    public void runOpMode() {

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                Constants.DriveConstants.LOGO_FACING_DIR, Constants.DriveConstants.USB_FACING_DIR));
        imu.initialize(parameters);


        // Instantiate Datalogger class.  Edit name as needed.
        imuDL = new W_Datalogger_v05(datalogFilename);

        // Instantiate datalog timer.
        dataTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        // Name the fields (column labels) generated by this OpMode.
        imuDL.addField("Count");
        imuDL.addField("IMU Heading Angle");
        imuDL.firstLine();                        // end first line (row)

        telemetry.addData("Datalogging Sample -- IMU Heading", "Touch START to continue...");
        telemetry.update();

        waitForStart();

        // Reset timer for datalogging interval.
        dataTimer.reset();

        // Optional to reset log timers (columns 1 & 2) as data collection begins.
        //imuDL.resetTime();

        while (opModeIsActive()) {

            if (dataTimer.time() > logInterval)  {

                angles = imu.getRobotYawPitchRollAngles();
                myHeading = angles.getYaw(AngleUnit.DEGREES);      // store Z-angle or heading
                readCount ++;

                // Populate the fields to be loggAed.  Must appear in the same order
                // as the column headings, to ensure data is logged to the intended
                // field name.
                imuDL.addField(readCount);
                imuDL.addField(myHeading);
                imuDL.newLine();                // end the current set of readings

                // Show live telemetry on the Driver Station screen.
                telemetry.addData("Count", readCount);
                telemetry.addData("myHeading", "%.1f", myHeading);

                // Show IMU system status and calibration status.
                telemetry.addLine();
                telemetry.addData("IMU status", imu.getDeviceName());
                telemetry.addData("IMU calibration status", imu.getConnectionInfo());
                telemetry.update();

                dataTimer.reset();      // start the interval timer again

            }   // end if(timer)

        }   // end main while() loop

        imuDL.closeDataLogger();            // close Datalogger when finished

    }   // end runOpMode()

}   // end class
