package org.firstinspires.ftc.teamcode.VisionTest;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//for dashboard
/*@Config*/
public class SpikeTapePipelineTune extends OpenCvPipeline {

    //backlog of frames to average out to reduce noise
    ArrayList<double[]> frameList;
    private Telemetry telemetry = null;

    int blur = 0;

    int lo = 0;
    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);

//    public ArrayList<Rect> r = new ArrayList<>(10);


    private int numContours;
    private int validContours;

    int lcr;
    Mat mat = new Mat();

    Mat blurred = new Mat();

    Mat hsvMat = new Mat();
    Mat filtered = new Mat();

    Mat threshold = new Mat();

    Mat mask;

    public SpikeTapePipelineTune(Telemetry telemetry) {
        frameList = new ArrayList<>();
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {

        //mat turns into HSV value
        mat = input;
        if (mat.empty())

            return input;


        int imgw = mat.width();
        int imgh = mat.height();


        Mat cropped = new Mat();
        int cropWidth = imgw;
        int cropHeight = (imgh/4);
        int cropStartx = 0;
        int cropstarty = imgh/2;


        cropped = mat.submat(new Rect(cropStartx, cropstarty, cropWidth, cropHeight));

        Imgproc.blur(cropped, blurred, new Size(5, 5));
//blurred=cropped;

        Imgproc.cvtColor(blurred,hsvMat, Imgproc.COLOR_BGR2HSV);


        Core.inRange(hsvMat, lower, upper, threshold);


        List<MatOfPoint> contours = new ArrayList<>();
////
        Mat hierarchy = new Mat();

//
//
//        //find contours, input scaledThresh because it has hard edges
        Imgproc.findContours(threshold, contours, hierarchy, Imgproc.CHAIN_APPROX_SIMPLE, Imgproc.CHAIN_APPROX_SIMPLE);

        numContours = contours.size();


        if (numContours > 10)
            numContours = 10;

        ArrayList<Rect> r = new ArrayList<>();


        if (r.size() < 12) {
            for (int i = 0; i < 12; i++) {
                r.add(new Rect());
            }
        }

        for (int i = 0; i < numContours; i++) {

            Rect tempR = Imgproc.boundingRect(contours.get(i));

            if (tempR.area() > 500) {
                r.set(validContours, tempR);
                validContours++;
            }
        }
        validContours = 0;
        for (int i = 0; i < numContours; i++) {

            Rect tempR = Imgproc.boundingRect(contours.get(i));

            if (tempR.area() > 10) {
                r.set(validContours, tempR);
                validContours++;
            }

        }

        //list of frames to reduce inconsistency, not too many so that it is still real-time, change the number from 5 if you want
        if (frameList.size() > 5) {
            frameList.remove(0);
        }

        if (numContours >= 3) {

            r.sort(Comparator.comparing(Rect::area).reversed());

            int n = 0;
//            if(validContours>1)
//                Imgproc.rectangle(masked, new Point(r.get(n).x, r.get(n).y), new Point(r.get(n).x + r.get(n).width, r.get(n).y + r.get(n).height),new Scalar(255,255,0),15);


            lcr = calcLCR(r);
        }


        // Core.bitwise_and(input, input, maskedInputMat,filtered );
//        telemetry.addData("[>]", "Change these values in tuner menu");
//
//        telemetry.addData("[Lower Scalar]", lower);
//        telemetry.addData("[Upper Scalar]", upper);
        telemetry.addData("NumCont", numContours);
        telemetry.addData("ValidCont", validContours);

        telemetry.addData("Area 0", r.get(0).area());
        telemetry.addData("X0", r.get(0).x);
        telemetry.addData("Y0", r.get(0).y);
        telemetry.addData("W0", r.get(0).width);
        telemetry.addData("H0", r.get(0).height);


        telemetry.addData("Area 1", r.get(1).area());
        telemetry.addData("Area 2", r.get(2).area());
        telemetry.addData("Area 3", r.get(3).area());


        telemetry.addData("X1", r.get(1).x);
        telemetry.addData("X2", r.get(2).x);
        telemetry.addData("X3", r.get(3).x);


        telemetry.update();


        return threshold;


    }

    private int calcLCR(List<Rect> r) {


        int xLeft = 0;
        int xRight = 0;
        int yTop = 0;


        int lcr = 0;

        xLeft = Math.min(r.get(0).x, Math.min(r.get(0).x, r.get(2).x));

        xRight = Math.max(r.get(0).x, Math.max(r.get(1).x, r.get(2).x));

        yTop = Math.min(r.get(0).y, Math.min(r.get(1).y, r.get(2).y));

        if (r.get(0).x == xLeft)
            lcr = 1;

        if (r.get(0).y == yTop) lcr = 2;


        if (r.get(0).x == xRight)
            lcr = 3;

        return lcr;
    }

}



