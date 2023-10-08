package org.firstinspires.ftc.teamcode.CV;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

//for dashboard
/*@Config*/
public class SpikeTapePipelineRed2 extends OpenCvPipeline {

    private String location = "nothing"; // output
    public Scalar lower = new Scalar(0, 0, 0); // HSV threshold bounds
    public Scalar upper = new Scalar(10, 255, 255);

    private Mat hsvMat = new Mat(); // converted image
    private Mat binaryMat = new Mat(); // imamge analyzed after thresholding
    private Mat maskedInputMat = new Mat();

    // Rectangle regions to be scanned
    private Point left = new Point(10, 200), bottomRight1 = new Point(40, 400);
    private Point center = new Point(200, 200), bottomRight2 = new Point(400, 240);

    private Point right = new Point(400, 200), bottomRight3 = new Point(500, 400);

    Scalar black =new Scalar(255,255,255);


    public SpikeTapePipelineRed2() {

    }

    @Override
    public Mat processFrame(Mat input) {
        // Convert from BGR to HSV
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsvMat, lower, upper, binaryMat);

        Imgproc.rectangle(input,left,bottomRight1,black,5);

        Imgproc.rectangle(input,center,bottomRight2,black,5);

        Imgproc.rectangle(input,right,bottomRight3,black,5);




        // Scan both rectangle regions, keeping track of how many
        // pixels meet the threshold value, indicated by the color white 
        // in the binary image
        double w1 = 0, w2 = 0, w3 = 0;
        // process the pixel value for each rectangle  (255 = W, 0 = B)
        for (int i = (int) left.x; i <= bottomRight1.x; i++) {
            for (int j = (int) left.y; j <= bottomRight1.y; j++) {
                if (binaryMat.get(i, j)[0] == 255) {
                    w1++;
                }
            }
        }
        ;
        for (int i = (int) center.x; i <= bottomRight2.x; i++) {
            for (int j = (int) center.y; j <= bottomRight2.y; j++) {
                if (binaryMat.get(i, j)[0] == 255) {
                    w2++;
                }
            }
        }

        for (int i = (int) right.x; i <= bottomRight3.x; i++) {
            for (int j = (int) right.y; j <= bottomRight3.y; j++) {
                if (binaryMat.get(i, j)[0] == 255) {
                    w3++;
                }
            }
        }

        // Determine object location
        if (w1 > w2 && w1 > w3) {
            location = "1";
        } else if (w2 > w1 && w2 > w3) {
            location = "2";
        } else if (w3 > w1 && w3 > w2) {
            location = "3";
        }


        return binaryMat;
    }


}





