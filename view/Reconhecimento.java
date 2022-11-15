package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

import controller.AlunoController;
import model.Aluno;

import java.awt.image.BufferedImage;

public class Reconhecimento extends JFrame {
    private DaemonThread myThread = null; 
   private VideoCapture webSource = null;
   private Mat cameraImage = new Mat();
   private  CascadeClassifier cascade = new CascadeClassifier("D:\\projeto escola\\FaceID\\src\\recursos\\haarcascade_frontalface_alt.xml");
   private LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    String nome;
    int matricula;

    private ImageIcon fundo = new ImageIcon(getClass().getResource("TelaReconhecimento.png"));
    private JLabel labelMatricula, labelNome, labelReconhecimento;
    private JButton voltar;
    public Reconhecimento (){
        generateComponents();

        recognizer.read("D:\\projeto escola\\FaceID\\src\\recursos\\classificadorLBPH.yml");
        recognizer.setThreshold(80);
        iniciarCamera();
    }


    public void generateComponents(){
        setSize(841, 600); // define tamanho da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // define função de abrir e fechar
        setLocationRelativeTo(null); // abrir no meio
        setVisible(true); // deixar a tela visivel
        setPreferredSize(new java.awt.Dimension(841, 600)); // a tela já abrir com um tamanho inicial
    
         /////////////////////////////// ADICIONA PAINEL ///////////////////////////////
         Painel painel = new Painel();
         add(painel);
         painel.setLayout(null);

        ////////////////////////////// LABEL CAPTURA ///////////////////////////////
        labelNome = new JLabel("nome");
        painel.add(labelNome);
        labelNome.setBounds(630, 250, 160, 30);

        ////////////////////////////// LABEL CONTADOR ///////////////////////////////
        labelMatricula = new JLabel("Matricula");
        painel.add(labelMatricula);
        labelMatricula.setBounds(630, 390, 160, 30);


        labelReconhecimento = new JLabel();
        painel.add(labelReconhecimento);
        labelReconhecimento.setBounds(200, 170, 410, 310);
    
        voltar = new JButton();
        painel.add(voltar);
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("botaoVoltar.png"))); // NOI18N
        voltar.setBounds(20, 10, 30, 40);
        // AÇÃO DO BOTAO VOLTAR
        voltar.addActionListener(new java.awt.event.ActionListener() {

    

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaAdm adm = new TelaAdm();
                adm.setVisible(true);           
            }

        });

    }



    //classes internas
    private class Painel extends JPanel {
        public void paintComponent(Graphics g) { // metodo para pintar a imagem no painel
            super.paintComponent(g); // invoca a classe Graphic
            java.awt.Image img = fundo.getImage(); // converte a imagem para o tipo image
            g.drawImage(img, 0, 0, this); // redimensiona a imagem
            setLayout(null);

        }
    }

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = labelReconhecimento.getGraphics();

                            Mat imageGray = new Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                Rect dadosFace = detectedFace.get(i);
                                rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);
                               

                                if (prediction == -1) {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 0, 255, 3), 3, 0, 0);
                                    matricula = 0;
                                    labelNome.setText("Desconhecido");
                                    labelMatricula.setText("nao reconhecido");

                                } else {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                    System.out.println(confidence.get(0));
                                    matricula = prediction ;
//                                    System.out.println("PESSOA RECONHECIDA COMO: " + idPerson);
                                    reconhecimento();
                                    labelMatricula.setText(Integer.toString(matricula));
                                   
                                }

                                
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            try {
                                if (g.drawImage(buff, 0, 0, labelReconhecimento.getWidth(), labelReconhecimento.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }


    private void  reconhecimento(){
        Aluno aluno = new Aluno();
        try {
             aluno = new AlunoController().pesquisarCad(matricula);
             labelNome.setText(aluno.getNome());
        } catch (Exception e) {
        //   JOptionPane.showMessageDialog(null, e.getMessage(), "rec", 0);
        }
       
    }
    
    public void pararCamera(){
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    public void iniciarCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);

                myThread = new Reconhecimento.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }

    public static void main(String[] args) {
        new Reconhecimento();
    }
}
