package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.lang.model.util.ElementScanner14;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

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
import controller.FuncionarioController;
import controller.VisitanteController;
import dao.UtilizaDAO;
import model.Aluno;
import model.Funcionario;
import model.Visitante;

public class AlterarRec extends JFrame {
    // variaveis de instancia

    private AlterarRec.DaemonThread myThread = null;

    // JavaCV
    VideoCapture webSource = null; //variavel da webcam
    Mat cameraImage = new Mat(); //variavel que converte a imagem para matriz
    CascadeClassifier cascade = new CascadeClassifier(
            "D:\\projeto escola\\FaceID\\src\\recursos\\haarcascade_frontalface_alt.xml"); //arquivo haarscade

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector(); //armazena as faces detectadas
    int numAmostras = 25, amostras = 1; //verifica o numero de fotos capturadas

    String matricula;

    int id;

    private String nome, cpf, telefone, cargo, motivo;

    int codigo;
    private JLabel labelCaptura, labelContador;
    private JButton botaoIniciarCaptura, botaoCaptura, voltar;
    private ImageIcon fundo = new ImageIcon(getClass().getResource("imagens\\TelaCaptura.png"));

    public AlterarRec(Aluno aluno) {
        iniciarComponentes();
        this.id = aluno.getId();
        this.matricula = aluno.getMatricula();
        this.nome = aluno.getNome();
        this.cpf = aluno.getCpf();
        this.telefone = aluno.getTelefone();

        iniciarCamera();

    }

    public AlterarRec(Funcionario func) {
        iniciarComponentes();
        this.id = func.getId();
        this.codigo = func.getCodigo();
        this.nome = func.getNome();
        this.cargo = func.getCargo();
        this.telefone = func.getTelefone();

        iniciarCamera();
    }

    public AlterarRec(Visitante visi) {
        iniciarComponentes();
        this.id = visi.getId();
        this.cpf = visi.getCpf();
        this.nome = visi.getNome();
        this.motivo = visi.getMotivo();
        this.telefone = visi.getTelefone();

        iniciarCamera();
    }

    private void iniciarComponentes() {
        setSize(841, 600); // define tamanho da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // define fun????o de abrir e fechar
        setLocationRelativeTo(null); // abrir no meio
        setVisible(true); // deixar a tela visivel
        setPreferredSize(new java.awt.Dimension(841, 600)); // a tela j?? abrir com um tamanho inicial

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
        labelContador.setFont(new Font("Times new Roman", Font.BOLD, 17));
        labelContador.setForeground(Color.WHITE);
        painel.add(labelContador);
        labelContador.setBounds(410, 477, 40, 20);

        ////////////////////////////// BOT??O CAPTURA ///////////////////////////////
        botaoCaptura = new JButton(" Captura");
        painel.add(botaoCaptura);
        botaoCaptura.setBounds(350, 530, 150, 20);
        botaoCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed(evt);
            }
        });

        voltar = new JButton();
        painel.add(voltar);
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens\\botaoVoltar.png"))); // NOI18N
        voltar.setBounds(20, 10, 30, 40);
        // A????O DO BOTAO VOLTAR
        voltar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

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

    // Cria uma thread que faz com que a imagem da camera fique dentro da label
    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) { // pega o frame da camera
                            webSource.retrieve(cameraImage); // cameraImage vai pegar os dados da imagem
                            Graphics g = labelCaptura.getGraphics(); // mostra a imagem no jlabel

                            Mat imageColor = new Mat(); // imagem colorida
                            imageColor = cameraImage; // armazena os dados da imagem colorida

                            Mat imageGray = new Mat(); // imagem cinza
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);
                            // transforma a imagem em cinza

                            RectVector detectedFaces = new RectVector(); // variavel que armazena as faces detectadas
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150),
                                    new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) { // repeti????o pra encontrar as faces
                                Rect dadosFace = detectedFaces.get(0); // vai salvar a primeira face detectada

                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 0, 2), 3, 0, 0); // formma um
                                                                                                       // retangulo na
                                                                                                       // primeira face
                                                                                                       // detectada

                                Mat face = new Mat(imageGray, dadosFace); // pega os dados do rosto
                                opencv_imgproc.resize(face, face, new Size(160, 160)); // mantem sempre a face no
                                                                                       // tamanho padr??o

                                if (botaoCaptura.getModel().isPressed()) { // quando apertar o bot??o de captura

                                    if (amostras <= numAmostras) {
                                        // salva a imagem cortada [160,160]
                                        // com o nome do arquivo: idpessoa + a contagem de fotos. ex:
                                        // pessoa.10(id).6(sexta foto).jpg
                                        String cropped = "D:\\projeto escola\\FaceID\\src\\fotos\\pessoa." + id + "."
                                                + amostras + ".jpg"; // onde vai ser salvo e nome da foto capturada
                                        imwrite(cropped, face); /// salva a captura

                                        // System.out.println("Foto " + amostra + " capturada\n");
                                        labelContador.setText(String.valueOf(amostras + 1) + "/25"); // mostra quantas
                                                                                                     // fotos foram
                                                                                                     // tiradas
                                        amostras++; // aumenta o contador das fotos

                                    }

                                    if (amostras >= numAmostras) { // verifica se o total de fotos tiradas foi maior que
                                                                   // 25 e se for maior
                                        try {
                                            inserirBanco(); // insere os dados no banco
                                            new TrainLBPH().trainPhotos(); // realiza o treinamento das imagens
                                            JOptionPane.showMessageDialog(null, "Atualiza????o efetuado", "Cadastro", 1);
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Atualiza????o n??o efetuado," + e.getMessage(), "Cadastro", 0);
                                        }
                                        System.out.println("Arquivo gerado");

                                        pararCamera(); // e fecha a camera

                                    }

                                }

                                // metodos para adicionar a camera na label
                                imencode(".bmp", cameraImage, mem);
                                Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                                BufferedImage buff = (BufferedImage) im;
                                try {
                                    if (g.drawImage(buff, 0, 0, labelCaptura.getWidth(), labelCaptura.getHeight(), 0, 0,
                                            buff.getWidth(), buff.getHeight(), null)) {
                                        if (runnable == false) {
                                            System.out.println("Salve a Foto");
                                            this.wait();
                                        }
                                    }
                                } catch (Exception e) {

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

    public void inserirBanco() {
        try {
            if (this.codigo == 0 && motivo == null) { //verifica se a pessoa que esta alterando o cadastro ?? um aluno
                AlunoController ac = new AlunoController();
                Aluno al = new Aluno();
                al.setId(this.id);
                al.setNome(this.nome);
                al.setTelefone(telefone);
                al.setCpf(this.cpf);
                ac.editar(al);
                TelaGerenciaMatricula.atualizaTabela();
            } else if (this.matricula == null && motivo == null) { //verifica se a pessoa que est?? alterando o cadastro ?? um funcionario
                FuncionarioController fc = new FuncionarioController();
                Funcionario func = new Funcionario();
                func.setId(this.id);
                func.setCodigo(this.codigo);
                func.setCargo(this.cargo);
                func.setNome(this.nome);
                func.setTelefone(this.telefone);
                fc.editar(func);
                GerenciaFuncionario.atualizaTabela();
            } else if (this.motivo != null) { //verifica se a pessoa que est?? atualizando o cadastro ?? um visitante
                VisitanteController vc = new VisitanteController();
                Visitante visi = new Visitante();
                visi.setCpf(this.cpf);
                visi.setNome(this.nome);
                visi.setMotivo(this.motivo);
                visi.setTelefone(this.telefone);
                visi.setId(this.id);
                vc.editar(visi);

            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Cadastro n??o efetuado," + a.getMessage(), "Cadastro", 0);
        }
    }

    public void pararCamera() {
        myThread.runnable = false;
        webSource.release();

    }

    public void iniciarCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);

                myThread = new AlterarRec.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }

}
