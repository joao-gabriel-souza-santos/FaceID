package view;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.HeadlessException;
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
import controller.FuncionarioController;
import controller.VisitanteController;
import model.Aluno;
import model.Funcionario;
import model.Visitante;

public class GerenciaVisitante extends JFrame {
    // Variaveis de instancia
    private ImageIcon fundo = new ImageIcon(getClass().getResource("imagens\\GerenciaVisitante.png"));
    private JButton voltar, botaoPesquisar, botaoListar, botaoAtualizar, botaoDeletar;
    private JFormattedTextField txtPesquisar;
    private static JTable tabela;
    private static String[] colunas = { "ID Visitante", "Cpf", "Nome", "motivo", "Telefone", "DataEntrada", "horaentrada", "DataSaida", "HorarioSaida"};
    private static Object[][] dados = {};
    private static List visitanteList = new VisitanteController().listarVisitante();

    public GerenciaVisitante() {
        super("Gerenciamento de visitantes");
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
        barraRolagem.setBounds(190, 200, 580, 290);

        /////////////////////////////// ADICIONA CAMPO
        /////////////////////////////// PESQUISAR///////////////////////////////
        txtPesquisar = new JFormattedTextField();
        painel.add(Mascara.mascaraCpf(txtPesquisar));
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
        botaoAtualizar = new JButton("Atualizar cadastro");
        painel.add(botaoAtualizar);
        botaoAtualizar.setBounds(40, 320, 140, 40);
        botaoAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int linhaSelecionada = tabela.getSelectedRow();

                        if (linhaSelecionada >= 0) {

                            AlteraVisitante janelaAlterar = new AlteraVisitante(linhaSelecionada);
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

        botaoDeletar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int linhaSelecionada = tabela.getSelectedRow();

                        if (linhaSelecionada >= 0) {
                            VisitanteController vc = new VisitanteController();
                            Visitante visi = (Visitante) vc.listarVisitante().get(linhaSelecionada);
                            try {
                               vc.excluir(visi.getCpf(), visi.getId());
                                JOptionPane.showMessageDialog(null, "Cadastr deletado", "Cadastro", 1);
                                atualizaTabela();
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null, " Cadastro não foi deletado com sucesso",
                                        "Cadastro",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, " necessario selecionar um Cadastro", "Aposento",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                });

        /////////////////////////////// ADICIONA BOTÃO
        /////////////////////////////// VOLTAR///////////////////////////////
        voltar = new JButton();
        painel.add(voltar);
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens\\botaoVoltar.png"))); // NOI18N
        voltar.setBounds(20, 10, 30, 30);
        // AÇÃO DO BOTAO VOLTAR
        voltar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GerenciaCad cad = new GerenciaCad();
                cad.setVisible(true);

            }

        });

    }

    public static void main(String[] args) {
        new GerenciaVisitante();
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

    public void pesquisaCad(String cpf) {
    TableModel tTabela = (DefaultTableModel) tabela.getModel();
    ((DefaultTableModel) tTabela).setNumRows(0);
    try {

    Visitante visi = new VisitanteController().pesquisarCadVisi(cpf);
    int linha = 0;

    ((DefaultTableModel) tTabela).addRow(new Object[] { 1 });
    tabela.setValueAt(visi.getId(), linha, 0);
    tabela.setValueAt(visi.getCpf(), linha, 1);
    tabela.setValueAt(visi.getNome(), linha, 2);
    tabela.setValueAt(visi.getMotivo(), linha, 3);
    tabela.setValueAt(visi.getTelefone(), linha, 4);
    tabela.setValueAt(visi.getDataEntrar(), linha, 5);
    tabela.setValueAt(visi.getHorarioEntrar(), linha, 6);
    tabela.setValueAt(visi.getDataSair(), linha, 7);
    tabela.setValueAt(visi.getHorarioSair(), linha, 8);
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
    "Problemas ao localizar contaton" + e.getLocalizedMessage());
    }

    }

    public static void atualizaTabela() {
        TableModel tTabela = (DefaultTableModel) tabela.getModel();
        ((DefaultTableModel) tTabela).setNumRows(0);
        visitanteList = new VisitanteController().listarVisitante();
        Visitante visi = new Visitante();

        for (int linha = 0; linha < visitanteList.size(); linha++) {
            visi = (Visitante) visitanteList.get(linha);

            ((DefaultTableModel) tTabela).addRow(new Object[] { 1 });
            tabela.setValueAt(visi.getId(), linha, 0);
            tabela.setValueAt(visi.getCpf(), linha, 1);
            tabela.setValueAt(visi.getNome(), linha, 2);
            tabela.setValueAt(visi.getMotivo(), linha, 3);
            tabela.setValueAt(visi.getTelefone(), linha, 4);
            tabela.setValueAt(visi.getDataEntrar(), linha, 5);
            tabela.setValueAt(visi.getHorarioEntrar(), linha, 6);
            tabela.setValueAt(visi.getDataSair(), linha, 7);
            tabela.setValueAt(visi.getHorarioSair(), linha, 8);
        }
    }
}
