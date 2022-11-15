// lbph.save("D:\\projeto escola\\FaceID\\src\\recursos\\classificadorLBPH.yml");

package UTIL;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;

public class TrainLBPH {

    
    public void trainPhotos() {
        File directory = new File("D:\\projeto escola\\FaceID\\src\\fotos");
        FilenameFilter filter = (File dir, String name1) -> name1.endsWith(".jpg") || name1.endsWith(".png");

        File[] files = directory.listFiles(filter);
        MatVector photos = new MatVector(files.length);
        Mat labels = new Mat(files.length, 1, CV_32SC1);
        IntBuffer labelsBuffer = labels.createBuffer();

        int counter = 0;
        for (File image : files) {
            Mat photo = imread(image.getAbsolutePath(), COLOR_BGRA2GRAY);
            int idP = Integer.parseInt(image.getName().split("\\.")[1]);
            opencv_imgproc.resize(photo, photo, new Size(160, 160));

            photos.put(counter, photo);
            labelsBuffer.put(counter, idP);
            counter++;
        }

        LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(1, 8, 8, 8, 12);
        lbph.train(photos, labels);
        lbph.save("D:\\projeto escola\\FaceID\\src\\recursos\\classificadorLBPH.yml");
    }

}