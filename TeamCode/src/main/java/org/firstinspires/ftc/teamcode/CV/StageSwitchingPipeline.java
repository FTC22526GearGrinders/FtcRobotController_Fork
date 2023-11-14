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

package org.firstinspires.ftc.teamcode.CV;

import static android.os.SystemClock.sleep;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
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

    Mat src = new Mat();

    int numContoursFound;

    int usableContours;

    int thresh = 0;

    public int redThreshold = 170;
    public int blueThreshold = 150;

    public boolean red;
    private int column = 1;

    public int left = 125;

    public int right = 210;

    double[] areas = {0, 0, 0};
    double[] xvalues = {0, 0, 0};
    double lastX = 0;

    public StageSwitchingPipeline(boolean red) {
        this.red = red;
    }


    @Override
    public Mat processFrame(Mat input) {

        src = input;
        if (src.empty()) {
            return input;
        }


        sleep(100);

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

        List<MatOfPoint> contoursList = new ArrayList<>();

        Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        numContoursFound = contoursList.size();

        src.copyTo(contoursOnFrameMat);

        Imgproc.drawContours(contoursOnFrameMat, contoursList, -1, new Scalar(255, 255, 255), 3, -1);

        List<RotatedRect> rr = new ArrayList<>();

        List<Double> rrxval = new ArrayList<>();

        List<Double> rrAreas = new ArrayList<>();

        usableContours = 0;

        for (int p = 0; p < areas.length; p++) {
            areas[p] = 0;
            xvalues[0] = 0;

        }
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
            double smallLimit = 400;

            double largLimit = 10000;

            double xCloseLimit = 10;

            if (temp.size.area() > smallLimit && temp.size.area() < largLimit) {

                double currentX = temp.center.x;

                if (Math.abs(currentX - lastX) > xCloseLimit) {

                    rr.add(temp);

                    rrAreas.add(temp.size.area());

                    rrxval.add(temp.center.x);

                    areas[usableContours] = rrAreas.get(usableContours);

                    xvalues[usableContours] = rrxval.get(usableContours);

                    usableContours++;

                }
                lastX = currentX;
            }
        }


        if (usableContours >= 2) {
            sort(rrAreas, rrxval);
        }

        int lcr = 0;


        if (usableContours >= 1) {
            int n = 0;
            if (rrxval.get(n) < left) lcr = 1;

            if (rrxval.get(n) > left && rrxval.get(n) < right) lcr = 2;

            if (rrxval.get(n) > right) lcr = 3;
        }
        ActiveMotionValues.setLcrpos(lcr);

        return src;
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
        return areas[n];
    }

    public double getX(int n) {
        return xvalues[n];
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