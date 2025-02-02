package org.firstinspires.ftc.teamcode.OpCodesTest;


/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.Commands.Drive.PositionToBackboard;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;


@Autonomous(name = "Auto:Position To April Tag", group = "Auto")
@Config
public class PositionToBackboardOpMode extends CommandOpMode {

    Vision_Subsystem vss;

    Drive_Subsystem drive;

    FtcDashboard dashboard;

    public static double DISTANCE_OFFSET = 6;

    double lastdistanceOffset;

    public static PIDCoefficients driveTunePID = new PIDCoefficients(0.06, 0, 0);

    public static double maxAccel = 2;

    public static double maxVel = 4;

    double lastAccel;

    double lastVel;



    @Override
    public void initialize() {
        vss = new Vision_Subsystem(this);
        drive = new Drive_Subsystem(this);
        dashboard = FtcDashboard.getInstance();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        CommandScheduler.getInstance().schedule(new PositionToBackboard(drive));

        while (!isStopRequested() && opModeIsActive()) {

            run();

            if (driveTunePID.p != drive.getPositionKp())
                drive.setPositionKp(driveTunePID.p);

            if (driveTunePID.i != drive.getPositionKi())
                drive.setPositionKi(driveTunePID.i);

            if (driveTunePID.d != drive.getPositionKd())
                drive.setPositionKp(driveTunePID.d);

            if (maxAccel != lastAccel || maxVel != lastVel) {
                drive.setTrapConstraints(maxVel, maxVel);
            }

            if (DISTANCE_OFFSET != lastdistanceOffset) {
                drive.profController.setGoal(DISTANCE_OFFSET);
                lastdistanceOffset = DISTANCE_OFFSET;
            }


            telemetry.update();

        }
        reset();

    }

}
