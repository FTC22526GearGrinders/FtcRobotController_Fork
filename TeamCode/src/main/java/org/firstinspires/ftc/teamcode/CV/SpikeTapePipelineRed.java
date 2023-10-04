package org.firstinspires.ftc.teamcode.CV;

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
public class SpikeTapePipelineRed extends OpenCvPipeline {

    //backlog of frames to average out to reduce noise
    ArrayList<double[]> frameList;

    public int blur = 0;


    public int lo = 0;

    public int hi = 120;


    public ArrayList<Rect> r = new ArrayList<>(10);


    private int lcr;
    private int numContours;
    private int validContours;

    private Mat hsvMat = new Mat();

    Mat filtered = new Mat();

    Mat masked = new Mat();

    public Scalar lower = new Scalar(lo, 0, 0);
    public Scalar upper = new Scalar(hi, 255, 255);

    Mat blurred = new Mat();

    boolean altImage;

    Mat mat = new Mat();

    int lx = 320;//x start point of crop pixels left edge screen

    int lw = 319;//width of crop say 8"
    int ly = 240;//y start point of crop 6" down

    int lh = 239;//height of crop say 14"
    Rect spikeCrop = new Rect(lx, ly, lw, lh);

    private boolean holdPipeline;


    public SpikeTapePipelineRed() {

    }

    @Override
    public Mat processFrame(Mat input) {
        mat = input;
        if (mat.empty()) {
            return input;
        }


        // Mat cropped = input.submat(spikeCrop);

        // Mat src = cropped;

        Mat src = input;


        // Imgproc.GaussianBlur(cropped, src, new Size(15, 15), 0);
         Imgproc.blur(mat, blurred, new Size(7, 7));

        // Core.bitwise_and(mat, mat, tgt, mask);
        Imgproc.cvtColor(blurred, hsvMat, Imgproc.COLOR_BGR2HSV);

        Scalar greeb = new Scalar(0, 255, 0);
        Scalar blue = new Scalar(255, 0, 0);
        Scalar red = new Scalar(0, 0, 255);


        Core.inRange(hsvMat, lower, upper, filtered);


        List<MatOfPoint> contours = new ArrayList<>();
//
        Mat hierarchy = new Mat();


//        //find contours, input scaledThresh because it has hard edges
        Imgproc.findContours(filtered, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        numContours = contours.size();

        validContours = 0;

        if (numContours > 10)
            numContours = 10;

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

//       // contours.clear();
//
//
        if (validContours >= 3) {
            r.sort(Comparator.comparing(Rect::area).reversed());
        }


        return filtered;


    }


    public int getNumberContours() {
        holdPipeline = true;

        return numContours;
    }

    public int getValidContours() {
        return validContours;
    }

    public List<Rect> getRects() {
        return r;
    }


}





