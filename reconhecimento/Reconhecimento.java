package reconhecimento;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_PLAIN;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_face.*; 

import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;


public class Reconhecimento {
    public static void main(String args[]) throws FrameGrabber.Exception {
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        String[] pessoas = {"", "Biel", "Julia", "Samuel"};
        camera.start();
        
        CascadeClassifier detectorFace = new CascadeClassifier("D:\\projeto escola\\FaceID\\src\\recursos\\haarcascade_frontalface_alt.xml");
        
        // FaceRecognizer reconhecedor = EigenFaceRecognizer.create();            
        // reconhecedor.read("src\\recursos\\classificadorEigenFaces.yml");        
        // reconhecedor.setThreshold(15);
        
        // FaceRecognizer reconhecedor = FisherFaceRecognizer.create();
        // reconhecedor.read("src\\recursos\\classificadorFisherFaces.yml");
        
        FaceRecognizer reconhecedor = LBPHFaceRecognizer.create();
        reconhecedor.read("D:\\projeto escola\\FaceID\\src\\recursos\\classificadorLBPH.yml");
        reconhecedor.setThreshold(80);        
        CanvasFrame cFrame = new CanvasFrame("Reconhecimento", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        Mat imagemColorida = new Mat();
        
        while ((frameCapturado = camera.grab()) != null) {
            imagemColorida = converteMat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorida, imagemCinza, COLOR_BGRA2GRAY);
            RectVector facesDetectadas = new RectVector();
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 2, 0, new Size(100,100), new Size(500,500));
            for (int i = 0; i < facesDetectadas.size(); i++) {
                Rect dadosFace = facesDetectadas.get(i);
                rectangle(imagemColorida, dadosFace, new Scalar(0,255,0,0));
                Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                
                IntPointer rotulo = new IntPointer(1);
                DoublePointer confianca = new DoublePointer(1);
                
                System.out.println("w="+faceCapturada.size(0)+"  /  h="+faceCapturada.size(1));
                if ((faceCapturada.size(0) == 160) || (faceCapturada.size(1) == 160)){
                    continue;
                }  
                resize(faceCapturada, faceCapturada, new Size(160,160));
                //Size tamanho = new Size(faceCapturada); 
                reconhecedor.predict(faceCapturada, rotulo, confianca);
                int predicao = rotulo.get(0);
                String nome;
                if (predicao == -1) {
                    nome = "Desconhecido";
                } else {
                    nome = pessoas[predicao] + " - " + confianca.get(0);
                }
                
                int x = Math.max(dadosFace.tl().x() - 10, 0);
                int y = Math.max(dadosFace.tl().y() - 10, 0);
                putText(imagemColorida, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0,255,0,0));
            }
            if (cFrame.isVisible()) {
                cFrame.showImage(frameCapturado);
            }
        }
        cFrame.dispose();
        camera.stop();
    }
}
