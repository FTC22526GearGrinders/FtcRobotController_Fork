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
public class SpikeTapePipelineColor extends OpenCvPipeline {


   public  Scalar lower = new Scalar(0, 63, 0);
  public   Scalar upper = new Scalar(163, 255, 255);


    Telemetry telemetry;
    ArrayList<double[]> frameList;
    private int numContours;


    double[] xval = {1, 2, 3, 4, 5, 6, 7, 8};//new double[10];
    double[] yval = new double[10];
    double[] angle = new double[10];
    double[] width = new double[10];
    double[] height = new double[10];

    int usableContours = 0;
    private int lcr;

    private int loopCtr = 0;

    public SpikeTapePipelineColor(Telemetry telemetry) {
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


        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        List<MatOfPoint> validContours = new ArrayList<MatOfPoint>();

        List<MatOfPoint> boxContours = new ArrayList<>();


        Point[] vertices = new Point[4];

        Mat hierarchy = new Mat();


        Imgproc.findContours(filtered, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        numContours = contours.size();

        if (numContours > 9) numContours = 9;

        validContours.clear();

        for (int i = 0; i < numContours; i++) {

            Rect tempR = Imgproc.boundingRect(contours.get(i));

            if (tempR.area() > 300) {

                validContours.add(contours.get(i));

                usableContours++;

            } else break;

            Imgproc.drawContours(dst, validContours, i, new Scalar(0, 0, 355), 2);

        }


        for (int i = 0; i < xval.length; i++) {
            xval[i] = 0;
            yval[i] = 0;
            angle[i] = 0;
            width[i] = 0;
            height[i] = 0;
        }


        Scalar color = new Scalar(0, 0, 255);
        if (validContours.size() >= 3) {
            for (int i = 0; i < usableContours; i++) {
                MatOfPoint2f temp = new MatOfPoint2f();

                validContours.get(i).convertTo(temp, CvType.CV_32F);
                RotatedRect rr = Imgproc.minAreaRect(temp);
                xval[i] = rr.center.x;
                yval[i] = rr.center.y;
                angle[i] = rr.angle;
                height[i] = rr.size.height;
                width[i] = rr.size.width;

                if (width[i] > height[i]) {
                    double tempw = width[i];
                    width[i] = height[i];
                    height[i] = tempw;
                    angle[i] += 90.f;
                }


                rr.points((vertices));

                boxContours.add(new MatOfPoint(vertices));

                Imgproc.drawContours(dst, boxContours, i, new Scalar(128, 128, 128), -1);


                Imgproc.putText(dst, String.valueOf(i), new Point(rr.center.x + 20, rr.center.y), 7, Imgproc.FONT_HERSHEY_PLAIN,
                        color, 1);
            }
            sortArraysByWidth();
        }


        if (usableContours == 3) {
            if (xval[0] > xval[1] && xval[0] > xval[2])
                lcr = 3;

            if (xval[0] < xval[1] && xval[0] < xval[2])
                lcr = 1;

            if (xval[0] > xval[1] && xval[0] < xval[2] ||
                    xval[0] < xval[1] && xval[0] > xval[2])

                lcr = 2;
        }


        telemetry.addData("LoopCount", loopCtr);
        telemetry.addData("NumCont", contours.size());
        telemetry.addData("ValCont", usableContours);
        telemetry.addData("LCR", lcr);


        for (
                int i = 0;
                i < usableContours; i++) {
            telemetry.addData("X " + String.valueOf(i), xval[i]);
            telemetry.addData("Y " + String.valueOf(i), yval[i]);
            telemetry.addData("Angle " + String.valueOf(i), angle[i]);
            telemetry.addData("Width " + String.valueOf(i), width[i]);

            telemetry.addData("Height " + String.valueOf(i), height[i]);

        }

        telemetry.update();

        return dst;

    }


    void sortArraysByWidth() {

        int n = usableContours;
        for (int i = 1; i < n; i++) {
            double key = width[i];
            int j = i - 1;

            // Move elements of arrays[0..i-1], that are
            // less than key, to one position ahead
            // of their current position
            while (j >= 0 && width[j] > key) {
                width[j + 1] = width[j];
                xval[j + 1] = xval[j];
                yval[j + 1] = yval[j];
                angle[j + 1] = angle[j];
                height[j + 1] = height[j];

                j = j - 1;
            }
            width[j + 1] = key;// widdth 1 to width 0
            xval[j + 1] = xval[i];
            yval[j + 1] = yval[i];
            angle[j + 1] = angle[i];
            height[j + 1] = height[i];


        }

    }

}