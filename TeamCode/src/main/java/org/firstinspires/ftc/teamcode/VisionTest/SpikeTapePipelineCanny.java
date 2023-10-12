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
public class SpikeTapePipelineCanny extends OpenCvPipeline {

    public int threshhold1 = 90;
    public int threshhold2 = 140;


    Telemetry telemetry;
    ArrayList<double[]> frameList;
    private int numContours;

    public SpikeTapePipelineCanny(Telemetry telemetry) {
        this.telemetry = telemetry;
        frameList = new ArrayList<>();
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat src = input;
        if (src.empty()) {
            return input;
        }


        // Blur the image to filter out the noise.
        //Creating an empty matrices to store edges, source, destination
        Mat gray = new Mat(src.rows(), src.cols(), src.type());
        Mat edges = new Mat(src.rows(), src.cols(), src.type());
        Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
        Mat blur = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
        Mat hsvMat = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
        Mat blur2 = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));


        Imgproc.blur(src, blur, new Size(1, 1));

        Imgproc.cvtColor(blur, hsvMat, Imgproc.COLOR_BGR2HSV);

        //Converting the image to Gray
        Imgproc.cvtColor(hsvMat, gray, Imgproc.COLOR_BGR2GRAY);
        //Blurring the image
        Imgproc.blur(gray, blur2, new Size(3, 3));
        //Detecting the edges
        Imgproc.Canny(blur2, edges, threshhold1, threshhold2);
//        //Copying the detected edges to the destination matrix


        Mat inverted = new Mat();
        Core.bitwise_not(edges, inverted);
        inverted.copyTo(dst);


        List<MatOfPoint> contours = new ArrayList<MatOfPoint>(20);
        List<MatOfPoint> validContours = new ArrayList<MatOfPoint>(20);

        List<MatOfPoint> boxContours = new ArrayList<>();

        double[] xval = new double[10];
        double[] yval = new double[10];
        double[] angle = new double[10];
        double[] width = new double[10];
        double[] height = new double[10];
        Point[] vertices = new Point[4];

        Mat hierarchy = new Mat();
        int usableContours = 0;

        Imgproc.findContours(inverted, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        numContours = contours.size();

        if (numContours > 9) numContours = 9;

        validContours.clear();

        for (int i = 0; i < numContours; i++) {

            Rect tempR = Imgproc.boundingRect(contours.get(i));

            if (tempR.area() > 500) {

                validContours.add(contours.get(i));

                usableContours++;

            } else break;

            Imgproc.drawContours(dst, validContours, i, new Scalar(0, 0, 355), 2);

        }


        for (int i = 0; i < xval.length - 1; i++) {
            xval[i] = 0;
            yval[i] = 0;
            angle[i] = 0;
            width[i] = 0;
            height[i] = 0;
        }

        Scalar color = new Scalar(0, 0, 255);
        for (int i = 0; i < numContours; i++) {
            MatOfPoint2f temp = new MatOfPoint2f();
            contours.get(i).convertTo(temp, CvType.CV_32F);
            RotatedRect rr = Imgproc.minAreaRect(temp);
            xval[i] = rr.center.x;
            yval[i] = rr.center.y;
            angle[i] = rr.angle;
            height[i] = rr.size.height;
            width[i] = rr.size.width;

            rr.points((vertices));
            boxContours.add(new MatOfPoint(vertices));

            Imgproc.drawContours(dst, boxContours, i, new Scalar(128, 128, 128), -1);


            Imgproc.putText(dst, String.valueOf(i), rr.center, 7, Imgproc.FONT_HERSHEY_PLAIN,
                    color, 1);


        }

        sortArraysByX(xval, yval, angle, width, height, usableContours);


        telemetry.addData("NumCont", contours.size());
        telemetry.addData("ValCont", usableContours);

        for (int i = 0; i < numContours; i++) {
            telemetry.addData("X " + String.valueOf(i), xval[i]);
            telemetry.addData("Y " + String.valueOf(i), yval[i]);
            telemetry.addData("Angle " + String.valueOf(i), angle[i]);
            telemetry.addData("Width " + String.valueOf(i), width[i]);

            telemetry.addData("Height " + String.valueOf(i), height[i]);


        }

        telemetry.update();

        return inverted;

    }

    void sortArraysByX(double[] xval, double[] yval, double[] angle, double[] width, double[] height, int valCont) {
        //void sortArraysByX(){


        int n = valCont;
        for (int i = 1; i < n; ++i) {
            double key = xval[i];
            int j = i - 1;

            // Move elements of arrays[0..i-1], that are
            // less than key, to one position ahead
            // of their current position
            while (j >= 0 && xval[j] > key) {
                xval[j + 1] = xval[j];
                yval[j + 1] = yval[j];
                angle[j + 1] = angle[j];
                width[j + 1] = width[j];
                height[j + 1] = height[j];

                j = j - 1;
            }
            xval[j + 1] = key;
        }


    }

}