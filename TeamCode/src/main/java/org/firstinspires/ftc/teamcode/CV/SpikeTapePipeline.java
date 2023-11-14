package org.firstinspires.ftc.teamcode.CV;


import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

//for dashboard
/*@Config*/
public class SpikeTapePipeline extends OpenCvPipeline {

    //backlog of frames to average out to reduce noise
    ArrayList<double[]> frameList;


    private int lcr;
    private int numContours;
    private int usableContours;


    public Scalar lowerRed = new Scalar(0, 86, 96);
    public Scalar upperRed = new Scalar(137.4, 255, 255);
    int left = 128;
    int right = 217;
    int maskTop = 92;
    int maskBottom = 180;

    private int lpctr = 0;

    Mat src = new Mat();
    Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat blur = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat hsvMat = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat filtered = new Mat();

    Mat hierarchy = new Mat();

    Mat cropped = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));


    public double[] areas = {0, 0, 0,0,0};

    public int imgWidth = 0;

    public int imgHeight = 0;

    public SpikeTapePipeline(boolean red) {
        frameList = new ArrayList<>();

//        lower = new Scalar(87, 51, 0);//(80,55,0);
//        upper = new Scalar(153, 255, 255);//(138, 255, 255);

//        if (!red) {
//            lower = new Scalar(0, 103, 72);
//            upper = new Scalar(77, 255, 255);
//        }
        lpctr = 0;

    }


    @Override
    public Mat processFrame(Mat input) {

        src = input;
        if (src.empty()) {
            return input;
        }

        Imgproc.resize(input, src, new Size(320, 240));

        if (!src.empty()) {
            imgWidth = src.width();

            imgHeight = src.height();

        }


        //left and right lines
        if (left > (int) (imgWidth * .4)) left = (int) (imgWidth * .4);

        if (right < (int) (imgWidth * .6)) right = (int) (imgWidth * .6);

        Point leftTop = new Point(left, 0);
        Point leftBottom = new Point(left, imgHeight);
        Point rightTop = new Point(right, 0);
        Point rightBottom = new Point(right, imgHeight);

        leftTop = new Point(0, 0);
        rightTop = new Point(0, 0);

        leftBottom = new Point(0, maskBottom);
        rightBottom = new Point(imgWidth, maskBottom);


        if (maskTop > imgHeight / 2) maskTop = imgHeight / 2;

        if (maskBottom < imgHeight * 3 / 4) maskBottom = imgHeight * 3 / 4;

        if (maskBottom > imgHeight) maskBottom = imgHeight;

        Point t = new Point(0, maskTop);

        Point b = new Point(imgWidth, maskBottom);

        Rect roi = new Rect(t, b);

        cropped = src.submat(roi);

        new Scalar(255, 0, 0);

        Imgproc.blur(cropped, blur, new Size(7, 7));

        Imgproc.cvtColor(blur, hsvMat, Imgproc.COLOR_BGR2HSV);

        Core.inRange(hsvMat, lowerRed, upperRed, filtered);

        List<MatOfPoint> contours = new ArrayList<>();


        Imgproc.findContours(filtered, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        numContours = contours.size();

        usableContours = 0;


        List<RotatedRect> rr = new ArrayList<>();

        List<Double> rrxval = new ArrayList<>();

        List<Double> rrAreas = new ArrayList<>();

        MatOfPoint2f approxCurve = new MatOfPoint2f();
        //For each contour found
        for (int i = 0; i < numContours; i++) {
            //Convert contours(i) from MatOfPoint to MatOfPoint2f
            MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(i).toArray());
            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;

            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            //Convert back to MatOfPoint
            MatOfPoint mp2f = new MatOfPoint(approxCurve.toArray());

            RotatedRect temp = Imgproc.minAreaRect(contour2f);

         //   if (temp.size.area() > 50) {

                rr.add(temp);

                rrAreas.add(temp.size.area());

                rrxval.add(temp.center.x);

                usableContours++;

       //     }


            Point points[] = new Point[4];

            temp.points(points);

            Scalar color = new Scalar(128, 128, 128);

        }


        //sort rr by area ann save x values

        if (usableContours >1) {


            sort(rrAreas, rrxval);

            areas[0] = rrAreas.get(0);
            areas[1] = rrAreas.get(1);
          //  areas[2] = rrAreas.get(2);

            lcr = 0;

            ActiveMotionValues.setLcrpos(lcr);

            lpctr++;


            if (usableContours >= 1) {

                if (rrxval.get(0) < left) lcr = 1;

                if (rrxval.get(0) > left && rrxval.get(0) < right) lcr = 2;

                if (rrxval.get(0) > right) lcr = 3;

                ActiveMotionValues.setLcrpos(lcr);

            }

        }


        if (frameList.size() > 5) {
            frameList.remove(0);
        }

        return filtered;
        // return src;
        //return cropped;
        // return  c;

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

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public int getNumContours() {
        return numContours;
    }

    public int getUsableContours() {
        return usableContours;
    }

    public double getArea(int n) {
        if (n <= usableContours)
            return areas[n];
        else return 0;
    }

    public String getScalarLo() {
        return lowerRed.toString();
    }

    public String getScalarHi() {
        return upperRed.toString();
    }

}




