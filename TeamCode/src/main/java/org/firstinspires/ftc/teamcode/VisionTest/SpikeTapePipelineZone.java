package org.firstinspires.ftc.teamcode.VisionTest;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
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


    public Scalar lower = new Scalar(0, 20, 0);
    public Scalar upper = new Scalar(50, 255, 255);

    int maxLeft = 80;

    public int left =50;
    public int right =100;


    Telemetry telemetry;


    public SpikeTapePipelineZone(Telemetry telemetry) {
        frameList = new ArrayList<>();
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat src = input;
        if (src.empty()) {
            return input;
        }

        int imgWidth = input.width();
        int imgHeight = input.height();

        telemetry.addData("HEIGHT", imgHeight);
        telemetry.addData("HWidth", imgWidth);


        int maxmid = 170;
        int macRight = imgWidth - 1;

        Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
        Mat blur = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
        Mat hsvMat = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
        Mat filtered = new Mat();


        Imgproc.blur(src, blur, new Size(1, 1));

        Imgproc.cvtColor(blur, hsvMat, Imgproc.COLOR_BGR2HSV);

        Core.inRange(hsvMat, lower, upper, filtered);


        Mat inverted = new Mat();
        Core.bitwise_not(filtered, inverted);
        inverted.copyTo(dst);


        List<MatOfPoint> contours = new ArrayList<>();


//
        Mat hierarchy = new Mat();
//
//
//        //find contours, input scaledThresh because it has hard edges
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

            Point leftTop = new Point(left, 0);
            Point leftBottom = new Point(left, imgHeight - 10);
            Point rightTop = new Point(right, 0);
            Point rightBottom = new Point(right, imgHeight - 10);



            Imgproc.line(src, leftTop, leftBottom, new Scalar(128, 128, 128),3);
            Imgproc.line(src, rightTop, rightBottom, new Scalar(128, 128, 128),3);


//            Imgproc.putText(filtered, String.valueOf(i), new Point(temp.center.x + 20, temp.center.y), 7, Imgproc.FONT_HERSHEY_PLAIN,
//                    color, 1);

            new Scalar(255, 0, 0);
        }


        //sort rr by area ann save x values


        sort(rrAreas, rrxval);


        if (rrxval.get(0) < left) lcr = 1;

        if (rrxval.get(0) > left && rrxval.get(0) < right) lcr = 2;

        if (rrxval.get(0) > right) lcr = 3;


        for (int i = 0; i < rr.size(); i++) {
            telemetry.addData("Area " + String.valueOf(i), rrAreas.get(i));
            telemetry.addData("X " + String.valueOf(i), rrxval.get(i));

        }

        // Imgproc.line(src,new Point(100,0),new Point(100,imgHeight-1),new Scalar(128,128,128),6);

        telemetry.addData("NumCon", numContours);
        telemetry.addData("ValCon", usableContours);
        telemetry.addData("RRSize", rr.size());
        telemetry.addData("XVALSize", rrxval.size());
        telemetry.addData("AreasSize", rrAreas.size());


        telemetry.addData("LCR", lcr);

        telemetry.update();

        if (frameList.size() > 5) {
            frameList.remove(0);
        }

        return filtered;


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




