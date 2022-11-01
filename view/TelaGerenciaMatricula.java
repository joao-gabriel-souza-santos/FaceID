package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import UTIL.Mascara;
import controller.AlunoController;

import model.Aluno;

public class TelaGerenciaMatricula extends JFrame {
    // Variaveis de instancia
    private ImageIcon fundo = new ImageIcon(getClass().getResource("TelaGerenciaMatricula.png"));
    private JButton voltar, botaoPesquisar, botaoListar, botaoAtualizar, botaoDeletar;
    private JFormattedTextField txtPesquisar;
    private static JTable tabela;
    private static String[] colunas = { "Matricula", "Nome", "CPF", "Telefone" };
    private static Object[][] dados = {};
    private static List alunoList = new AlunoController().listarCadastros();

    // Construtor da classe
    public TelaGerenciaMatricula() {
        super("Gerenciamento de Matrículas");
        setSize(841, 600); // define tamanho da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // define função de abrir e fechar
        setLocationRelativeTo(null); // abrir no meio
        setVisible(true); // deixar a tela visivel
        setPreferredSize(new java.awt.Dimension(841, 600)); // a tela já abrir com um tamanho inicial

        /////////////////////////////// ADICIONA PAINEL ///////////////////////////////
        Painel painel = new Painel();
        add(painel);
        painel.setLayout(null);

        /////////////////////////////// ADICIONA JTABLE ///////////////////////////////
        TableModel tableModel = new DefaultTableModel(dados, colunas);
        tabela = new JTable(tableModel);
        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add(barraRolagem);
        barraRolagem.setBounds(190, 200, 500, 290);

        /////////////////////////////// ADICIONA CAMPO
        /////////////////////////////// PESQUISAR///////////////////////////////
        txtPesquisar = new JFormattedTextField();
        painel.add(Mascara.mascaraMatricula(txtPesquisar));
        txtPesquisar.setBounds(210, 160, 290, 30);

        /////////////////////////////// ADICIONA BOTÃO
        /////////////////////////////// PESQSUISAR///////////////////////////////
        botaoPesquisar = new JButton("Pesquisar");
        painel.add(botaoPesquisar);
        botaoPesquisar.setBounds(520, 160, 100, 30);
        botaoPesquisar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pesquisaCad(txtPesquisar.getText());
                    }

                });

        /////////////////////////////// ADICIONA BOTÃO
        /////////////////////////////// LISTAR///////////////////////////////
        botaoListar = new JButton("Listar Matricula");
        painel.add(botaoListar);
        botaoListar.setBounds(40, 240, 140, 40);
        botaoListar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        atualizaTabela();

                    }

                });

        /////////////////////////////// ADICIONA BOTÃO
        /////////////////////////////// ATUALIZAZR///////////////////////////////
        botaoAtualizar = new JButton("Atualizar Matricula");
        painel.add(botaoAtualizar);
        botaoAtualizar.setBounds(40, 320, 140, 40);
        botaoAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int linhaSelecionada = tabela.getSelectedRow();

                        if (linhaSelecionada >= 0) {

                            AlterarCadastro janelaAlterar = new AlterarCadastro(linhaSelecionada);
                            janelaAlterar.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, " necessario selecionar um Cadastro", "Aposento",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                });

        /////////////////////////////// ADICIONA BOTÃO DELETAR
        /////////////////////////////// ///////////////////////////////
        botaoDeletar = new JButton("Deletar Matricula");
        painel.add(botaoDeletar);
        botaoDeletar.setBounds(40, 400, 140, 40);

        /////////////////////////////// ADICIONA BOTÃO
        /////////////////////////////// VOLTAR///////////////////////////////
        voltar = new JButton();
        painel.add(voltar);
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("botaoVoltar.png"))); // NOI18N
        voltar.setBounds(20, 10, 30, 30);
        // AÇÃO DO BOTAO VOLTAR
        voltar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAdm adm = new TelaAdm();
                adm.setVisible(true);
                dispose();
            }

        });

    }

    public static void main(String[] args) {
        new TelaGerenciaMatricula();
    }

    // classes privadas
    private class Painel extends JPanel {
        public void paintComponent(Graphics g) { // metodo para pintar a imagem no painel
            super.paintComponent(g); // invoca a classe Graphic
            Image img = fundo.getImage(); // converte a imagem para o tipo image
            g.drawImage(img, 0, 0, this); // redimensiona a imagem
            setLayout(null);

        }

    }

    public void pesquisaCad(String matricula) {
        TableModel tTabela = (DefaultTableModel) tabela.getModel();
        ((DefaultTableModel) tTabela).setNumRows(0);
        try {

            Aluno aluno = new AlunoController().pesquisarCad(matricula);
            int linha = 0;

            ((DefaultTableModel) tTabela).addRow(new Object[] { 1 });
            tabela.setValueAt(aluno.getMatricula(), linha, 0);
            tabela.setValueAt(aluno.getNome(), linha, 1);
            tabela.setValueAt(aluno.getCpf(), linha, 2);
            tabela.setValueAt(aluno.getTelefone(), linha, 3);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas ao localizar contaton" + e.getLocalizedMessage());
        }

    }

    public static void atualizaTabela() {
        TableModel tTabela = (DefaultTableModel) tabela.getModel();
        ((DefaultTableModel) tTabela).setNumRows(0);
        alunoList = new AlunoController().listarCadastros();
        Aluno aluno = new Aluno();

        for (int linha = 0; linha < alunoList.size(); linha++) {
            aluno = (Aluno) alunoList.get(linha);

            ((DefaultTableModel) tTabela).addRow(new Object[] { 1 });

            tabela.setValueAt(aluno.getMatricula(), linha, 0);
            tabela.setValueAt(aluno.getNome(), linha, 1);
            tabela.setValueAt(aluno.getCpf(), linha, 2);
            tabela.setValueAt(aluno.getTelefone(), linha, 3);
        }
    }

}