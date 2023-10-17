package org.firstinspires.ftc.teamcode.CV;

import android.provider.ContactsContract;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.List;

//for dashboard
/*@Config*/
public class SpikeTapePipelineRed extends OpenCvPipeline {

    // private String location = "nothing"; // output
    public Scalar lower = new Scalar(0, 0, 0); // HSV threshold bounds
    public Scalar upper = new Scalar(34, 255, 255);

    private int numContours;
    private int validContours;

    private Mat mat = new Mat();
    private Mat hsvMat = new Mat(); // converted image
    private Mat binaryMat = new Mat(); // imamge analyzed after thresholding
    private Mat maskedInputMat = new Mat();

    int imgHeight = 480;
    int imgWidth = 640;

    int sideBorder = 20;
    int sideWidth = 100;

    int sideYHeight = 200;

    int sideTopY = 200;

    int centerTopBorder = 250;
    int centerTopHeight = 100;

    int centerLeftX = 250;

    int centerTopWidth = 200;


    // Rectangle regions to be scanned
    private Point left = new Point(sideBorder, sideTopY), bottomRight1 = new Point(sideBorder + sideWidth, sideTopY + sideYHeight);
    private Point center = new Point(centerLeftX, centerTopBorder), bottomRight2 = new Point(centerLeftX + centerTopWidth, centerTopBorder + centerTopHeight);

    private Point right = new Point(imgWidth - sideBorder - sideWidth, sideTopY), bottomRight3 = new Point(imgWidth - sideBorder, sideTopY + sideYHeight);
    public double w1 = 0;

    public double w2 = 0;
    public double w3 = 0;




    Scalar white = new Scalar(255, 255, 255);
    Scalar red = new Scalar(0, 0, 255);
    private int lcr;


    public SpikeTapePipelineRed() {


        //   this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        mat = input;
        if (mat.empty()) {
            return input;
        }
        Imgproc.blur(input, mat, new Size(15, 15));

        // Convert from BGR to HSV
        Imgproc.cvtColor(mat, hsvMat, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsvMat, lower, upper, binaryMat);

        imgWidth = binaryMat.width();

        imgHeight = binaryMat.height();

        Imgproc.rectangle(input, left, bottomRight1, white, 1);

        Imgproc.rectangle(input, center, bottomRight2, white, 1);

        Imgproc.rectangle(input, right, bottomRight3, white, 1);


        Imgproc.rectangle(binaryMat, left, bottomRight1, red, 1);

        Imgproc.rectangle(binaryMat, center, bottomRight2, red, 1);

        Imgproc.rectangle(binaryMat, right, bottomRight3, red, 1);





        int rows = binaryMat.rows();
        int cols = binaryMat.cols();

        w1=0;
        w2=0;
        w3=0;


        // Scan both rectangle regions, keeping track of how many
        // pixels meet the threshold value, indicated by the color
        // in the binary image

        // process the pixel value for each rectangle  (255 = W, 0 = B)

        for (int j = (int) left.y; j <= bottomRight1.y; j++) {
            for (int i = (int) left.x; i <= bottomRight1.x; i++) {
                if (binaryMat.get(j, i)[0] == 255) {
                    w1++;
                }
            }
        }


        for (int j = (int) center.y; j <= bottomRight2.y; j++) {
            for (int i = (int) center.x; i <= bottomRight2.x; i++) {
                if (binaryMat.get(j, i)[0] == 255) {
                    w2++;
                }
            }
        }


        for (int j = (int) right.y; j <= bottomRight3.y; j++) {
            for (int i = (int) right.x; i <= bottomRight3.x; i++) {
                if (binaryMat.get(j, i)[0] == 255) {
                    w3++;
                }


            }
        }

//        // Determine object location

         lcr = 0;
        if (w1 > w2 && w1 > w3) {
            lcr = 1;
        } else if (w2 > w1 && w2 > w3) {
            lcr = 2;
        } else if (w3 > w1 && w3 > w2) {
            lcr = 3;
        }

        return binaryMat;
    }

    public double getW1() {
        return w1;
    }

    public double getW2() {
        return w2;
    }

    public double getW3() {
        return w3;
    }

    public double getImgCols() {
        return binaryMat.cols();
    }

    public int getImgRows() {
        return binaryMat.rows();
    }

    public int getImgWidth() {
        return imgWidth;
    }
    public int getLCR() {
        return lcr;
    }


    public int getNumberContours() {
        return numContours;
    }

    public int getValidContours() {
        return validContours;
    }


}







