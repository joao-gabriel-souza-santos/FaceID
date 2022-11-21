package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

public class SelecionaCad extends JFrame{
     // variaveis de ambiente
     private ImageIcon fundo = new ImageIcon(getClass().getResource("imagens\\TipoCad.png"));
     private JButton botaoAluno, botaoFuncionario, botaoVisitante, voltar ;
 
     // construtor
     public SelecionaCad() {
         // CONFIGURAÇÃO DA TELA
         super("Seleciona Cadastro");
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
         botaoAluno = new JButton("CADASTRAR ALUNO");
         painel.add(botaoAluno);
         botaoAluno.setBounds(230, 170, 380, 50);
         botaoAluno.addActionListener(new java.awt.event.ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 //JOptionPane.showMessageDialog(null, "Ainda sem função", "Reconhecimento facial", 1);
                 dispose();
                 TeladeCadastro cad = new TeladeCadastro();
                 cad.setVisible(true);
             }
 
         });
 
         // adicionar botao gerenciamento do sistema
         botaoFuncionario = new JButton("CADASTRAR FUNCIONARIO");
         painel.add(botaoFuncionario);
         botaoFuncionario.setBounds(230, 270, 380, 50);
         botaoFuncionario.addActionListener(new java.awt.event.ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose();
                 TelaCadFuncionario cadF = new TelaCadFuncionario();
                 cadF.setVisible(true);
             }
 
         });
         // botao cadastrar
         botaoVisitante = new JButton("CADASTRAR VISITANTE");
         painel.add(botaoVisitante);
         botaoVisitante.setBounds(230, 370, 380, 50);
         // ação do botao
         botaoVisitante.addActionListener(new java.awt.event.ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose();
                TelaCadVisitante cadV = new TelaCadVisitante();
                cadV.setVisible(true);
             }
 
         });
 

         // adicionar botao voltar
         voltar = new JButton();
         painel.add(voltar);
         voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens\\botaoVoltar.png"))); // NOI18N
         voltar.setBounds(20, 10, 30, 40);
         // AÇÃO DO BOTAO VOLTAR
         voltar.addActionListener(new java.awt.event.ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose();
                 TelaAdm adm= new TelaAdm();
                 adm.setVisible(true);
 
             }
 
         });
     }
 
     // metodo main
     public static void main(String[] args) {
         new SelecionaCad();
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
