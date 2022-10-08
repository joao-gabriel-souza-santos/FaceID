package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ConexaoMysql;

public class TeladeCadastro extends JFrame {
    // variaveis de ambiente
    private ImageIcon fundo = new ImageIcon(getClass().getResource("telaCad.png"));
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtmatricula;
    private JLabel documento;
    private JButton salvar, carregarImg, voltar;

    public TeladeCadastro() {
        super("Tela de cadastro");
        // CONFIGURAÇÃO DA TELA
        setSize(841, 600); // define o tamanho da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // ação de fechar e aumentar a tela
        setLocationRelativeTo(null); // deixa a tela centralizada
        setVisible(true); // deixa a tela visivel
        setPreferredSize(new java.awt.Dimension(841, 600)); // faz com que a tela abra no tamanho que eu quero

        // adicionando a imagem de fundo
        Panel painel = new Panel();
        painel.setLayout(null);
        add(painel);

        // adicionar campo nome
        txtNome = new JTextField();
        painel.add(txtNome);
        txtNome.setBounds(100, 160, 320, 30);

        // adicionar campo cpf
        txtCpf = new JTextField();
        painel.add(txtCpf);
        txtCpf.setBounds(100, 240, 320, 30);

        // adicionar campo telefone
        txtTelefone = new JTextField();
        painel.add(txtTelefone);
        txtTelefone.setBounds(100, 320, 320, 30);

        // adicionar campo matricula

        txtmatricula = new JTextField();
        painel.add(txtmatricula);
        txtmatricula.setBounds(100, 390, 320, 30);

        // adicionar campo documento
        documento = new JLabel();
        painel.add(documento);
        documento.setBounds(580, 220, 230, 190);

        // adicionar botao salvar
        salvar = new JButton("Salvar");
        painel.add(salvar);
        salvar.setBounds(340, 500, 120, 40);
        salvar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent t) {
                try {
                    Connection conexao = new ConexaoMysql().getConnection();
                    String sql = "INSERT INTO Aluno values('"
                            + txtNome.getText() + "', " + txtCpf.getText() + ", " + txtTelefone.getText() + ", "
                            + txtmatricula.getText() + ");";
                    
                PreparedStatement state = conexao.prepareStatement(sql);
                state.execute();

                conexao.close();
                JOptionPane.showMessageDialog(null, "Cadastro efetuado","Cadastro", 1);
                } catch (SQLException e) {
                   
                    JOptionPane.showMessageDialog(null, "Cadastro não efetuado,", "Cadastro", 0);
                }

            }

        });

        // botao carregar imagem
        carregarImg = new JButton("Carregar imagem");
        painel.add(carregarImg);
        carregarImg.setBounds(590, 410, 135, 35);

        // EVENTO DO BOTÃO CARREGAR
        carregarImg.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carregarImgActionPerfomed(e); // chama o metodo feito abaixo do carregamento de imagem

            }

        });

        // adicionar botao voltar
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

    public static void main(String[] args) {
        new TeladeCadastro();
    }

    // CLASSES INTERNAS

    // Classe para adicionar uma imagem no painel
    private class Panel extends JPanel {

        public void paintComponent(Graphics g) { // metodo para pintar a imagem no painel
            super.paintComponent(g); // invoca a classe Graphic
            Image img = fundo.getImage(); // converte a imagem para o tipo image
            g.drawImage(img, 0, 0, this); // redimensiona a imagem
        }

    }

    // METODO PARA CARREGAR UM ARQUIVO DE IMAGEM
    private void carregarImgActionPerfomed(java.awt.event.ActionEvent e) {
        JFileChooser arquivo = new JFileChooser(); // cria a janela de escolha
        arquivo.setDialogTitle("Selecione uma imagem"); // titulo da janela de escolha
        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY); // define a ação de escolher imagem
        int op = arquivo.showOpenDialog(this); // op vai receber a ação de escolher a imagem
        if (op == JFileChooser.APPROVE_OPTION) { // verifica se a imagem foi escolhida ou não
            File file = new File(""); // vai pegar o caminho da imagem
            file = arquivo.getSelectedFile(); // vai receber a imagem

            ImageIcon image = new ImageIcon(file.getPath()); // converter a imagem para o tipo ImageIcon
            documento.setIcon(new ImageIcon(image.getImage().getScaledInstance(documento.getWidth(),
                    documento.getHeight(), Image.SCALE_AREA_AVERAGING))); // redimensiona a imagem e a coloca na label
        }

    }
}
