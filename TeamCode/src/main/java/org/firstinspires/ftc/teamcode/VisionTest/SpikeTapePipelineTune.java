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

    public int blur = 0;


    public int lo = 0;

    public int hi = 255;

    private int lcr;
    private int numContours;
    private int validContours;

    private Mat hsvMat = new Mat();

    Mat filtered = new Mat();

    Telemetry telemetry;

    int numContoursFound;
    public Scalar lower = new Scalar(50, 0, 0);
    public Scalar upper = new Scalar(110, 255, 255);

    List<Rect> r = new ArrayList<>();
    public SpikeTapePipelineTune(Telemetry telemetry)
    {
        this.telemetry=telemetry;
        frameList = new ArrayList<>();
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat mat = input;
        if (mat.empty()) {
            return input;
        }

        Imgproc.blur(input, mat, new Size(5, 5));

        //mat turns into HSV value
        Imgproc.cvtColor(mat, hsvMat, Imgproc.COLOR_RGB2HSV);


        Core.inRange(hsvMat, lower, upper, filtered);


        List<MatOfPoint> contours = new ArrayList<>();


//
        Mat hierarchy = new Mat();
//
//
//        //find contours, input scaledThresh because it has hard edges
        Imgproc.findContours(filtered, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//
        numContours = contours.size();

        if(numContours>10)numContours=10;
////
        if (numContours != 0)
            validContours = 0;

        r.clear();

        for (int i = 0; i < contours.size(); i++) {

            Rect tempR = Imgproc.boundingRect(contours.get(i));

            if (tempR.area() > 150) {

                r.add(validContours, tempR);

                validContours++;
            }
        }


      //  contours.clear();


        if (validContours >= 3) {

            r.sort(Comparator.comparing(Rect::area).reversed());
        }

        telemetry.addData("NumCont",numContours);
        telemetry.addData("Valont",validContours);
        telemetry.addData("Area0",r.get(0).area());
        telemetry.addData("Area1",r.get(1).area());


        telemetry.update();

        return filtered;


    }

    public int getNumberContours() {
        return numContoursFound;
    }

    public int getValidContours() {
        return validContours;
    }

    public List<Rect> getRects(){
        return r;
    }
}




