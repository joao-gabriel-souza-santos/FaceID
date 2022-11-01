package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



import UTIL.ManipularImagem;
import UTIL.Mascara;
import controller.AlunoController;
import dao.UtilizaDAO;
import model.Aluno;

public class AlterarCadastro extends JFrame {
    // variaveis de ambiente
    private ImageIcon fundo = new ImageIcon(getClass().getResource("telaCad.png"));
    private JFormattedTextField  txtCpf, txtTelefone;
    private JTextField txtNome;
    private JLabel documento;
    private JButton salvar, carregarImg, voltar;
    private java.awt.image.BufferedImage imagem;
    private int linhaSelecionada;
    private Aluno aluno;
    public AlterarCadastro(int linhaSelecionada) {
        super("Tela de atualização de");

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
   
            txtCpf = new JFormattedTextField();
            painel.add(Mascara.mascaraCpf(txtCpf));
            txtCpf.setBounds(100, 240, 320, 30);
         
    
            // adicionar campo telefone
            txtTelefone = new JFormattedTextField();
            painel.add(Mascara.mascaraTelefone(txtTelefone));
            txtTelefone.setBounds(100, 320, 320, 30);



        // adicionar campo matricula

        final JFormattedTextField txtmatricula = new JFormattedTextField();
        painel.add(Mascara.mascaraMatricula(txtmatricula));
        txtmatricula.setBounds(100, 390, 320, 30);

        // adicionar campo documento
        documento = new JLabel();
        painel.add(documento);
        documento.setBounds(580, 220, 230, 190);

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

        this.linhaSelecionada = linhaSelecionada;
        UtilizaDAO dao = new UtilizaDAO();
        try {
            aluno = (Aluno) dao.listarCadastros().get(linhaSelecionada);
        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        txtmatricula.setText(String.valueOf(aluno.getMatricula()));
        txtNome.setText(String.valueOf(aluno.getNome()));
        txtCpf.setText(String.valueOf(aluno.getCpf()));
        txtTelefone.setText(String.valueOf(aluno.getTelefone()));
        ManipularImagem.exibiImagemLabel(aluno.getFoto(), documento);


        // adicionar botao salvar
        salvar = new JButton("Salvar");
        painel.add(salvar);
        salvar.setBounds(340, 500, 120, 40);
        salvar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent t) {
                try {
                    if(imagem == null){
                        AlunoController ac = new AlunoController();
                        ac.editarSemImg(txtmatricula.getText(),txtNome.getText(), txtCpf.getText(), txtTelefone.getText());
                        
                        JOptionPane.showMessageDialog(null, "Atualização efetuada", "Cadastro", 1);
                  
                        dispose();
                        TelaGerenciaMatricula.atualizaTabela();
                    }else{
                        AlunoController ac = new AlunoController();
                        ac.editar(txtmatricula.getText(),txtNome.getText(), txtCpf.getText(), txtTelefone.getText(), ManipularImagem.getImgBytes(imagem));
                        
                        JOptionPane.showMessageDialog(null, "Atualização efetuada", "Cadastro", 1);
                  
                        dispose();
                        TelaGerenciaMatricula.atualizaTabela();
                    }
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(null, "Atualização não efetuada," + a.getLocalizedMessage(), "Cadastro", 0);
                }
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

        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            try {
                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);

                documento.setIcon(new ImageIcon(imagem));

            } catch (Exception ex) {
                // System.out.println(ex.printStackTrace().toString());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
        }

    }
}
