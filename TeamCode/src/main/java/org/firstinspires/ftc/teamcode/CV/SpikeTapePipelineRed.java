package org.firstinspires.ftc.teamcode.CV;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

//for dashboard
/*@Config*/
public class SpikeTapePipelineRed extends OpenCvPipeline {

    // private String location = "nothing"; // output
    public Scalar lower = new Scalar(0, 0, 0); // HSV threshold bounds
    public Scalar upper = new Scalar(140, 255, 255);

    private Mat mat = new Mat();
    private Mat hsvMat = new Mat(); // converted image
    private Mat binaryMat = new Mat(); // imamge analyzed after thresholding
    private Mat maskedInputMat = new Mat();

    int imgHeight = 480;
    int imgWidth = 640;

    int sideBorder = 40;
    int sideWidth = 100;

    int sideYHeight = 150;

    int sideTopY = 300;

    int centerTopBorder= 250;
    int centerTopHeight = 100;

    int centerLeftX=250;

    int centerTopWidth= 200;


    // Rectangle regions to be scanned
    private Point left = new Point(sideBorder, sideTopY), bottomRight1 = new Point(sideBorder + sideWidth, sideTopY + sideYHeight);
    private Point center = new Point(centerLeftX, centerTopBorder), bottomRight2 = new Point(centerLeftX+centerTopWidth, centerTopBorder+centerTopHeight);

    private Point right = new Point(imgWidth - sideBorder - sideWidth, sideTopY), bottomRight3 = new Point(imgWidth - sideBorder, sideTopY + sideYHeight);

    public double w1 = 0;
    public double w2 = 0;
    public double w3 = 0;


    Scalar white = new Scalar(255, 255, 255);

    public SpikeTapePipelineRed() {


        //   this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        mat = input;
        if (mat.empty()) {
            return input;
        }
        imgWidth = mat.width();
        imgHeight = mat.height();
        // Convert from BGR to HSV
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsvMat, lower, upper, binaryMat);

        Imgproc.rectangle(input, left, bottomRight1, white, 1);

        Imgproc.rectangle(input, center, bottomRight2, white, 1);

        Imgproc.rectangle(input, right, bottomRight3, white, 1);


        // Scan both rectangle regions, keeping track of how many
        // pixels meet the threshold value, indicated by the color
        // in the binary image

        // process the pixel value for each rectangle  (255 = W, 0 = B)
        for (int i = (int) left.x; i <= bottomRight1.x; i++) {
            for (int j = (int) left.y; j <= bottomRight1.y; j++) {
                if (binaryMat.get(i, j)[0] == 255) {
                    w1++;
                }
            }
        }


        for (int p = (int) center.x; p <= bottomRight2.x; p++) {
            for (int q = (int) center.y; q <= bottomRight2.y; q++) {
                if (binaryMat.get(p, q)[0] == 255) {
                    w2++;
                }
            }
        }
//
//        for (int i = (int) right.x; i <= bottomRight3.x; i++) {
//            for (int j = (int) right.y; j <= bottomRight3.y; j++) {
//                if (binaryMat.get(i, j)[0] == 255) {
//                    w3++;
//                }
//            }
//        }

//        // Determine object location
//        if (w1 > w2 && w1 > w3) {
//            location = "1";
//        } else if (w2 > w1 && w2 > w3) {
//            location = "2";
//        } else if (w3 > w1 && w3 > w2) {
//            location = "3";
//        }


//

        return input;
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

    public int getImgHeight() {
        return imgHeight;
    }

    public int getImgWidth() {
        return imgWidth;
    }


}





