/*
 * Copyright (c) 2020 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.VisionTest;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

//@Disabled
public class StageSwitchingPipeline extends OpenCvPipeline {
    Mat yCbCrChan2Mat = new Mat();
    Mat thresholdMat = new Mat();
    Mat contoursOnFrameMat = new Mat();
    List<MatOfPoint> contoursList = new ArrayList<>();
    int numContoursFound;

    int usableContours;

    int thresh = 0;

    public int redThreshold = 70;
    public int blueThreshold = 70;

    public boolean red = true;
    private int column = 1;

    public int left = 95;

    public int right = 183;
    int maskTop = 92;
    int maskBottom = 180;
    private int imgHeight = 240;

    private int imgWidth = 320;


    enum Stage {
        YCbCr_CHAN0,
        THRESHOLD,
        CONTOURS_OVERLAYED_ON_FRAME,
        RAW_IMAGE,
    }

    // private Stage stageToRenderToViewport = Stage.YCbCr_CHAN2;
    private Stage stageToRenderToViewport = Stage.CONTOURS_OVERLAYED_ON_FRAME;


    private Stage[] stages = Stage.values();

    private Telemetry telemetry;

    public StageSwitchingPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void onViewportTapped() {
        /*
         * Note that this method is invoked from the UI thread
         * so whatever we do here, we must do quickly.
         */

        int currentStageNum = stageToRenderToViewport.ordinal();

        int nextStageNum = currentStageNum + 1;

        if (nextStageNum >= stages.length) {
            nextStageNum = 0;
        }

        stageToRenderToViewport = stages[nextStageNum];
    }

    @Override
    public Mat processFrame(Mat input) {

        contoursList.clear();

        Point leftTop = new Point(left, 0);
        Point leftBottom = new Point(left, imgHeight);
        Point rightTop = new Point(right, 0);
        Point rightBottom = new Point(right, imgHeight);

        Point t = new Point(30, maskTop);

        Point b = new Point(imgWidth - 30, maskBottom);

        Rect roi = new Rect(t, b);


        Mat cropped = input.submat(roi);

        Imgproc.line(cropped, leftTop, leftBottom, new Scalar(128, 128, 0));

        Imgproc.line(cropped, rightTop, rightBottom, new Scalar(128, 128, 0));

        if (redThreshold < 100) redThreshold = 100;
        if (blueThreshold < 100) blueThreshold = 139;

        if (!red) {
            column = 2;
            thresh = blueThreshold;
        }
        if (red) {
            column = 1;
            thresh = redThreshold;
        }


        Imgproc.cvtColor(cropped, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb);

        Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, column);

        Imgproc.threshold(yCbCrChan2Mat, thresholdMat, thresh, 255, Imgproc.THRESH_BINARY_INV);

        Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        numContoursFound = contoursList.size();

        cropped.copyTo(contoursOnFrameMat);

        Imgproc.drawContours(contoursOnFrameMat, contoursList, -1, new Scalar(255, 255, 255), 3, -1);

        List<RotatedRect> rr = new ArrayList<>();

        List<Double> rrxval = new ArrayList<>();

        List<Double> rrAreas = new ArrayList<>();
        usableContours=0;
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        //For each contour found
        for (int i = 0; i < numContoursFound; i++) {
            //Convert contours(i) from MatOfPoint to MatOfPoint2f
            MatOfPoint2f contour2f = new MatOfPoint2f(contoursList.get(i).toArray());
            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;

            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            //Convert back to MatOfPoint
            MatOfPoint mp2f = new MatOfPoint(approxCurve.toArray());

            RotatedRect temp = Imgproc.minAreaRect(contour2f);


            if (temp.size.area() > 400) {

                rr.add(temp);

                rrAreas.add(temp.size.area());

                rrxval.add(temp.center.x);

                usableContours++;

            }
        }


        if (usableContours >= 2) {


            sort(rrAreas, rrxval);
        }

        int lcr = 0;


        if (usableContours >= 2) {

            if (rrxval.get(1) < left) lcr = 1;

            if (rrxval.get(1) > left && rrxval.get(1) < right) lcr = 2;

            if (rrxval.get(1) > right) lcr = 3;
        }

        telemetry.addData("LCR", lcr);


        telemetry.addData("[Stage]", stageToRenderToViewport);
        telemetry.addData("[Found Contours]", "%d", numContoursFound);
        telemetry.addData("ValCon", usableContours);

        if (usableContours > 1) {
            telemetry.addData("X0", rrxval.get(1));
            telemetry.addData("Areaa0", rrAreas.get(1));
        }
        if (usableContours >= 2) {
            telemetry.addData("X1", rrxval.get(1));
            telemetry.addData("Areaa1", rrAreas.get(1));
        }

        telemetry.addData("RRSize", rr.size());
        telemetry.addData("XVALSize", rrxval.size());
        telemetry.addData("AreasSize", rrAreas.size());

        telemetry.update();

        switch (stageToRenderToViewport) {
            case YCbCr_CHAN0: {
                return yCbCrChan2Mat;
            }

            case THRESHOLD: {
                return thresholdMat;
            }

            case CONTOURS_OVERLAYED_ON_FRAME: {
                return contoursOnFrameMat;
            }

            case RAW_IMAGE: {
                return cropped;
            }

            default: {
                return cropped;
            }

        }
    }

    public int getNumContoursFound() {
        return numContoursFound;
    }


    void sort(List<Double> rrAreas, List<Double> rrxval) {
        int n = rrAreas.size();
        for (int i = 1; i < n; ++i) {
            double key = rrAreas.get(i);
            double temp = rrxval.get(i);
            int j = i - 1;

            // Move elements of arr[0..i-1], that are
            // greater than key, to one position ahead
            // of their current position
            while (j >= 0 && rrAreas.get(j) < key) {
                rrAreas.set(j + 1, rrAreas.get(j));
                rrxval.set(j + 1, rrxval.get(j));

                j = j - 1;
            }
            rrAreas.set(j + 1, key);
            rrxval.set(j + 1, temp);
        }
    }
}