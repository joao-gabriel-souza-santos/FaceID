package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;

import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

import UTIL.TrainLBPH;
import controller.AlunoController;
import dao.UtilizaDAO;
import model.Aluno;






public class Captura extends JFrame {
    // variaveis de instancia

    private Captura.DaemonThread myThread = null;

    //JavaCV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("D:\\projeto escola\\FaceID\\src\\recursos\\haarcascade_frontalface_alt.xml");

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();
    int numAmostras = 25, amostras = 1;

    int matricula;

    private String nome, cpf, telefone;
    private JLabel labelCaptura, labelContador;
    private JButton botaoIniciarCaptura,botaoCaptura, voltar;
    private ImageIcon fundo = new ImageIcon(getClass().getResource("TelaCaptura.png"));

    public Captura(int matricula, String nome, String cpf, String telefone) {
        iniciarComponentes();

        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;

        iniciarCamera();

    }








    private void iniciarComponentes(){
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
        labelCaptura = new JLabel();
        painel.add(labelCaptura);
        labelCaptura.setBounds(260, 160, 340, 300);

        ////////////////////////////// LABEL CONTADOR ///////////////////////////////
        labelContador = new JLabel("00");
        painel.add(labelContador);
        labelContador.setBounds(398, 480, 40, 20);
        
        ////////////////////////////// BOTÃO CAPTURA ///////////////////////////////
        botaoCaptura = new JButton(" Captura");
        painel.add(botaoCaptura);
        botaoCaptura.setBounds(200, 540, 150, 20);
        botaoCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed(evt);
            }
        });
        
        botaoIniciarCaptura = new JButton(" Iniciar Captura");
        painel.add(botaoIniciarCaptura);
        botaoIniciarCaptura.setBounds(350, 540, 150, 20);
        

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

    // classes privadas
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
                            Graphics g = labelCaptura.getGraphics(); //mostra a imagem no jlabel
                            

                            Mat imageColor = new Mat(); //imagem colorida
                            imageColor = cameraImage;

                            Mat imageGray = new Mat(); //imagem pb
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);
//                            flip(cameraImage, cameraImage, +1);

                            RectVector detectedFaces = new RectVector(); //face detectada
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) { //repetição pra encontrar as faces
                                Rect dadosFace = detectedFaces.get(0);

                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 0, 2), 3, 0, 0);

                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if (botaoCaptura.getModel().isPressed()) { //quando apertar o botão saveButton
                                        if (amostras <= numAmostras) {
//                                        salva a imagem cortada [160,160]
//                                        nome do arquivo: idpessoa + a contagem de fotos. ex: person.10(id).6(sexta foto).jpg
                                            String cropped = "D:\\projeto escola\\FaceID\\src\\fotos\\pessoa." + matricula+ "." + amostras + ".jpg";
                                            imwrite(cropped, face);

                                            //System.out.println("Foto " + amostra + " capturada\n");
                                            labelContador.setText(String.valueOf(amostras) + "/25");
                                            amostras++;
                                        }
                                        
                                        if (amostras >= numAmostras) {
                                            try{
                                                inserirBanco(); //insere os dados no banco
                                                new TrainLBPH().trainPhotos();
                                                JOptionPane.showMessageDialog(null, "Cadastro efetuado", "Cadastro", 1);
                                            }catch(Exception e){
                                                JOptionPane.showMessageDialog(null, "Cadastro não efetuado," + e.getMessage(), "Cadastro", 0);
                                            }    
                                            System.out.println("Arquivo gerado");
                                           
                                            pararCamera(); // e fecha a camera
                 
                                        }

                                    }

                                 
                                    imencode(".bmp", cameraImage, mem);
                                    Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                                    BufferedImage buff = (BufferedImage) im;
                                    try {
                                        if (g.drawImage(buff, 0, 0, labelCaptura.getWidth(), labelCaptura.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                            if (runnable == false) {
                                                System.out.println("Salve a Foto");
                                                this.wait();
                                            }
                                        }
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, "Cadastro não efetuado," + e.getMessage(), "Cadastro", 0);
                                        
                                    }
                                }
                            }
                        }

                     catch (IOException ex) {
                    
                    }
                }
            }
        }
    }


    public void inserirBanco(){
        try {
            AlunoController ac = new AlunoController();
            ac.salvar(this.matricula, this.nome, this.cpf, this.telefone);

            JOptionPane.showMessageDialog(null, "Cadastro efetuado", "Cadastro", 1);
            
           
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Cadastro não efetuado," + a.getMessage(), "Cadastro", 0);
        }
    }

    public static void main(String[] args) {
         String  nome = "Biel", cpf = "2313", telefone = "32132";
         int matricula = 2;
        Captura cap = new Captura(matricula, nome, cpf, telefone);
        cap.iniciarCamera();
    }

    public void pararCamera(){
        myThread.runnable = false;
        webSource.release();
        
    }


    public void iniciarCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);

                myThread = new Captura.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }

   
}   
   
