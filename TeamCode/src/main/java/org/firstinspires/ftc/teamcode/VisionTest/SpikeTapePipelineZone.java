package org.firstinspires.ftc.teamcode.VisionTest;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.CvType;
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
public class SpikeTapePipelineZone extends OpenCvPipeline {

    //backlog of frames to average out to reduce noise
    ArrayList<double[]> frameList;


    private int lcr;
    private int numContours;
    private int usableContours;

    Scalar lower = new Scalar(0, 20, 0);
    Scalar upper = new Scalar(50, 255, 255);


//    public Scalar lower = new Scalar(0, 20, 0);
//    public Scalar upper = new Scalar(50, 255, 255);

//    int maxLeft = 80;
//     int left = 101;
//     int right = 166;
//     int maskTop = 100;
//     int maskBottom = 180;

    public int maxLeft = 80;
    public int left = 101;
    public int right = 166;
    public int maskTop = 100;
    public int maskBottom = 180;

    Mat src = new Mat();
    Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat blur = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat hsvMat = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat filtered = new Mat();

    Mat hierarchy = new Mat();

    Mat inverted = new Mat();

    Mat cropped = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));

    Mat mask = new Mat(src.rows(), src.cols(), CvType.CV_8U, Scalar.all(0));

    Telemetry telemetry;


    public SpikeTapePipelineZone(Telemetry telemetry) {
        frameList = new ArrayList<>();
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        src = input;
        if (src.empty()) {
            return input;
        }

        int imgWidth = input.width();
        int imgHeight = input.height();

        telemetry.addData("HEIGHT", imgHeight);
        telemetry.addData("HWidth", imgWidth);


        Point leftTop = new Point(left, 0);
        Point leftBottom = new Point(left, imgHeight);
        Point rightTop = new Point(right, 0);
        Point rightBottom = new Point(right, imgHeight);

        Imgproc.line(src, leftTop, leftBottom, new Scalar(128, 128, 128), 3);

        Imgproc.line(src, rightTop, rightBottom, new Scalar(128, 128, 128), 3);


        leftTop = new Point(0, 0);
        rightTop = new Point(0, 0);

        leftBottom = new Point(0, maskBottom);
        rightBottom = new Point(imgWidth, maskBottom);

        Imgproc.line(src, leftBottom, rightBottom, new Scalar(128, 128, 128), 3);

        if (maskTop > imgHeight / 2) maskTop = imgHeight / 2;

        if (maskBottom < imgHeight * 3 / 4) maskBottom = imgHeight * 3 / 4;

        if (maskBottom > imgHeight) maskBottom = imgHeight;

        Point a = new Point(0, maskTop);
        Point b = new Point(imgWidth, maskBottom);

        Rect roi = new Rect(a, b);


//
        cropped = src.submat(roi);

        src = cropped;


        Imgproc.blur(src, blur, new Size(1, 1));

        Imgproc.cvtColor(blur, hsvMat, Imgproc.COLOR_BGR2HSV);

        Core.inRange(hsvMat, lower, upper, filtered);


//        Core.bitwise_not(filtered, inverted);
//        inverted.copyTo(filtered);


        List<MatOfPoint> contours = new ArrayList<>();


        Imgproc.findContours(filtered, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
//
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

            if (temp.size.area() > 500) {

                rr.add(temp);

                rrAreas.add(temp.size.area());

                rrxval.add(temp.center.x);

                usableContours++;

            }


            Point points[] = new Point[4];

            temp.points(points);

            Scalar color = new Scalar(128, 128, 128);


            new Scalar(255, 0, 0);
        }


        //sort rr by area ann save x values

        if (usableContours >= 3) {


            sort(rrAreas, rrxval);


            if (rrxval.get(0) < left) lcr = 1;

            if (rrxval.get(0) > left && rrxval.get(0) < right) lcr = 2;

            if (rrxval.get(0) > right) lcr = 3;


            telemetry.addData("LCR", lcr);


            for (int i = 0; i < rr.size(); i++) {
                telemetry.addData("Area " + String.valueOf(i), rrAreas.get(i));
                telemetry.addData("X " + String.valueOf(i), rrxval.get(i));

            }
        }
        // Imgproc.line(src,new Point(100,0),new Point(100,imgHeight-1),new Scalar(128,128,128),6);

        telemetry.addData("NumCon", numContours);
        telemetry.addData("ValCon", usableContours);
        telemetry.addData("RRSize", rr.size());
        telemetry.addData("XVALSize", rrxval.size());
        telemetry.addData("AreasSize", rrAreas.size());


        telemetry.update();

        if (frameList.size() > 5) {
            frameList.remove(0);
        }

        //return filtered;
        return src;

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




