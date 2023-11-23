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
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

//@Disabled
public class StageSwitchingPipelineTst extends OpenCvPipeline {
    public int redThreshold = 170;
    public int blueThreshold = 150;
    public boolean red = true;
    public int left = 146;
    public int right = 231;
    Mat yCbCrChan2Mat = new Mat();
    Mat thresholdMat = new Mat();
    Mat contoursOnFrameMat = new Mat();
    List<MatOfPoint> contoursList = new ArrayList<>();
    List<RotatedRect> rr = new ArrayList<>();
    List<Double> rrxval = new ArrayList<>();
    List<Double> rrAreas = new ArrayList<>();
    int numContoursFound;
    int usableContours;
    int thresh = 0;
    double lastX = 0;
    int lpctr;
    Telemetry telemetry;
    private int column = 1;
    private final int imgHeight = 240;
    private final int imgWidth = 320;

    Point leftTop = new Point(left, 0);
    Point leftBottom = new Point(left, imgHeight);
    Point rightTop = new Point(right, 0);
    Point rightBottom = new Point(right, imgHeight);

    private Stage stageToRenderToViewport = Stage.CONTOURS_OVERLAYED_ON_FRAME;
    private final Stage[] stages = Stage.values();

    public StageSwitchingPipelineTst(Telemetry telemetry) {
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

        Mat src = input;
        if (src.empty()) {
            return input;
        }

        lpctr = 0;

        Imgproc.line(src, leftTop, leftBottom, new Scalar(128, 128, 0));

        Imgproc.line(src, rightTop, rightBottom, new Scalar(128, 128, 0));

        if (!red) {
            column = 2;
            thresh = blueThreshold;
        }
        if (red) {
            column = 1;
            thresh = redThreshold;
        }


        Imgproc.cvtColor(src, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb);

        Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, column);

        Imgproc.threshold(yCbCrChan2Mat, thresholdMat, thresh, 255, Imgproc.THRESH_BINARY_INV);

        usableContours = 0;
        contoursList.clear();
        rr.clear();
        rrAreas.clear();
        rrxval.clear();

        Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        numContoursFound = contoursList.size();

        src.copyTo(contoursOnFrameMat);

        Imgproc.drawContours(contoursOnFrameMat, contoursList, -1, new Scalar(255, 255, 255), 3, -1);


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


            //don't use too small or too large (image frame)
            double smallLimit = 0;


            double xCloseLimit = 1000;

            if (temp.size.area() > smallLimit) {

                double currentX = temp.center.x;

                //  if (Math.abs(currentX - lastX) > xCloseLimit) {

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

        if (usableContours >= 1) {

            double temp = getX(1);
            if (temp < left) lcr = 1;

            if (temp > left && temp < right) lcr = 2;

            if (temp > right) lcr = 3;
        }

        telemetry.addData("LCR", lcr);
        telemetry.addData("NumCon", numContoursFound);
        telemetry.addData("ValCon", usableContours);
        if (!rr.isEmpty())
            telemetry.addData("RRSize", rr.size());
        if (!rrxval.isEmpty())
            telemetry.addData("XVALSize", rrxval.size());
        if (!rrAreas.isEmpty())
            telemetry.addData("AreasSize",rrAreas.size());


        telemetry.addData("Area0", getArea(0));
        telemetry.addData("X0", getX(0));
        telemetry.addData("Area1", getArea(1));
        telemetry.addData("X1", getX(1));
        telemetry.addData("Area2", getArea(2));
        telemetry.addData("X2", getX(2));


        telemetry.update();

        lpctr = 0;
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
                return src;
            }

            default: {
                return src;
            }

        }
    }

    public int getNumContoursFound() {
        return numContoursFound;
    }

    public boolean getRedPipeline() {
        return this.red;
    }

    public void setRedThreshold(int num) {
        redThreshold = num;
    }

    public void setBlueThresholed(int num) {
        blueThreshold = num;
    }

    public int getNumberContours() {
        return numContoursFound;
    }

    public int getUsableContours() {
        return usableContours;
    }

    public double getArea(int n) {
        if (rrAreas.isEmpty())
            return 0;
        else if (rrAreas.size() > n)
            return Math.round(rrAreas.get(n));
        else return 0;
    }

    public double getX(int n) {
        if (rrxval.isEmpty())
            return 0;
        else if (rrxval.size() > n)
            return Math.round(rrxval.get(n));
        else return 0;
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


    enum Stage {
        YCbCr_CHAN0,
        THRESHOLD,
        CONTOURS_OVERLAYED_ON_FRAME,
        RAW_IMAGE,
    }
}