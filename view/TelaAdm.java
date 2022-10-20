package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaAdm extends JFrame {
    // variaveis de ambiente
    private ImageIcon fundo = new ImageIcon(getClass().getResource("telaAdm.png"));
    private JButton recFacial, gerenciamentoSist, sobre, voltar, cadastrar;

    // construtor
    public TelaAdm() {
        // CONFIGURAÇÃO DA TELA
        super("Tela administrador");
        setSize(841, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setPreferredSize(new java.awt.Dimension(841, 600));

        // Adicionar painel com imagem
        Panel painel = new Panel();
        add(painel);
        painel.setLayout(null);

        // adicionar botao reconhecimento facial dos alunos
        recFacial = new JButton("RECONHECIMENTO FACIAL DOS ALUNOS");
        painel.add(recFacial);
        recFacial.setBounds(230, 182, 360, 50);
        recFacial.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ainda sem função", "Reconhecimento facial", 1);

            }

        });

        // adicionar botao gerenciamento do sistema
        gerenciamentoSist = new JButton("GERENCIAMENTO DO SISTEMA");
        painel.add(gerenciamentoSist);
        gerenciamentoSist.setBounds(230, 260, 360, 50);
        gerenciamentoSist.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaGerenciaMatricula matric = new TelaGerenciaMatricula();
                matric.setVisible(true);
            }

        });
        // botao cadastrar
        cadastrar = new JButton("Cadastrar");
        painel.add(cadastrar);
        cadastrar.setBounds(230, 330, 360, 50);
        // ação do botao
        cadastrar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TeladeCadastro cad = new TeladeCadastro();
                cad.setVisible(true);
            }

        });

        // adicionar botao sobre
        sobre = new JButton("SOBRE...");
        painel.add(sobre);
        sobre.setBounds(230, 400, 360, 50);
        sobre.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Este software de reconhecimento facial tem por intuito principal gerenciar entrada e saída de alunos. \nFoi desenvolvido pelos alunos: João Gabriel Souza Santos, Jamily Santana de Araujo, Vinicius Souza da Silva e Raffaela Beatriz da Costa Cordeiro",
                        "Sobre", 1);

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
                TeladeLogin login = new TeladeLogin();
                login.setVisible(true);

            }

        });
    }

    // metodo main
    public static void main(String[] args) {
        new TelaAdm();
    }

    // CLASSES INTERNAS

    // Adicionar imagem no painel
    // classe da imagem
    private class Panel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // invoca a classe Graphic
            Image img = fundo.getImage(); // converte a imagem pro tipo image
            g.drawImage(img, 0, 0, this); // redesenha a imagem
        }
    }
}
