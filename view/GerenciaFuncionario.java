package view;

import javax.swing.JFrame;

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
import controller.FuncionarioController;
import model.Aluno;
import model.Funcionario;

public class GerenciaFuncionario extends JFrame {
        // Variaveis de instancia
        private ImageIcon fundo = new ImageIcon(getClass().getResource("imagens\\GerenciaFunc.png"));
        private JButton voltar, botaoPesquisar, botaoListar, botaoAtualizar, botaoDeletar;
        private JFormattedTextField txtPesquisar;
        private static JTable tabela;
        private static String[] colunas = { "ID funcionario", "Codigo", "Nome", "Cargo", "Telefone",  "Data Entrada","Hora Entrada",  "DataSaida","HorarioSaida" };
        private static Object[][] dados = {};
        private static List funcionarioList = new FuncionarioController().listarFunc();


        GerenciaFuncionario(){
            super("Gerenciamento de funcionarios");
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
            painel.add(txtPesquisar);
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
                           pesquisaCad(Integer.parseInt(txtPesquisar.getText()));
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
    
                                AlterarFuncionario janelaAlterar = new AlterarFuncionario(linhaSelecionada);
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
                            FuncionarioController fc = new FuncionarioController();
                            Funcionario func = (Funcionario) fc.listarFunc().get(linhaSelecionada);
                            try {
                                fc.excluir(func.getCodigo(), func.getId());
                                atualizaTabela();
                                JOptionPane.showMessageDialog(null, "Cadastr deletado", "Cadastro", 1);
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null, " Cadastro não foi deletado com sucesso", "Cadastro",
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
            new GerenciaFuncionario();
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
    
        public void pesquisaCad(int codigo) {
            TableModel tTabela = (DefaultTableModel) tabela.getModel();
            ((DefaultTableModel) tTabela).setNumRows(0);
            try {
    
                Funcionario func = (Funcionario) FuncionarioController.pesquisarCadFunc(codigo);
                int linha = 0;
    
                ((DefaultTableModel) tTabela).addRow(new Object[] { 1 });
                tabela.setValueAt( func.getId(), linha, 0);
                tabela.setValueAt( func.getCodigo(), linha, 1);
                tabela.setValueAt( func.getNome(), linha, 2);
                tabela.setValueAt( func.getCargo(), linha, 3);
                tabela.setValueAt( func.getTelefone(), linha, 4);
                tabela.setValueAt( func.getDataEntrar(), linha, 5);
                tabela.setValueAt( func.getHorarioEntrar(), linha, 6);
                tabela.setValueAt( func.getDataSair(), linha, 7);
                tabela.setValueAt( func.getHorarioSair(), linha, 8);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Problemas ao localizar contaton" + e.getLocalizedMessage());
            }
    
        }
    
        public static void atualizaTabela() {
            TableModel tTabela = (DefaultTableModel) tabela.getModel();
            ((DefaultTableModel) tTabela).setNumRows(0);
            funcionarioList = new FuncionarioController().listarFunc();
            Funcionario func= new Funcionario();
    
            for (int linha = 0; linha < funcionarioList.size(); linha++) {
                func = (Funcionario) funcionarioList.get(linha);
    
                ((DefaultTableModel) tTabela).addRow(new Object[] { 1 });
                tabela.setValueAt( func.getId(), linha, 0);
                tabela.setValueAt( func.getCodigo(), linha, 1);
                tabela.setValueAt( func.getNome(), linha, 2);
                tabela.setValueAt( func.getCargo(), linha, 3);
                tabela.setValueAt( func.getTelefone(), linha, 4);
                tabela.setValueAt( func.getDataEntrar(), linha, 5);
                tabela.setValueAt( func.getHorarioEntrar(), linha, 6);
                tabela.setValueAt( func.getDataSair(), linha, 7);
                tabela.setValueAt( func.getHorarioSair(), linha, 8);
            }
        }
    
}
