package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TeladeLogin extends JFrame {
    // variaveis de instancia

    private ImageIcon fundo = new ImageIcon(getClass().getResource("teladelogin.png"));
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton entrar;
    private final String login = "admin";
    private final String senha = "Emmersonporfavornaomereprova";

    // construtor
    public TeladeLogin() {
        // CONFIGURAÇÃO DA TELA
        super("Tela de login");
        setSize(841, 600); // define tamanho da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // define função de abrir e fechar
        setLocationRelativeTo(null); // abrir no meio
        setVisible(true); // deixar a tela visivel
        setPreferredSize(new java.awt.Dimension(841, 600)); // a tela já abrir com um tamanho inicial

        // Adicionar painel com imagem
        Panel painel = new Panel();
        painel.setLayout(null);
        add(painel);

        // Adicionar campo de texto login
        txtLogin = new JTextField();
        painel.add(txtLogin);
        txtLogin.setBounds(213, 265, 340, 30);
        // //adicionar campo de texto senha
        txtSenha = new JPasswordField();
        painel.add(txtSenha);
        txtSenha.setBounds(215, 325, 330, 30);


        // botao entrar
        entrar = new JButton("Entrar");
        painel.add(entrar);
        entrar.setBounds(330, 410, 120, 40);
        // ação do botao
        entrar.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                //Verifica se o login ta certo
                if ((txtLogin.getText().equals(login) && txtSenha.getText().equals(senha))) {
                     dispose();
                     TelaAdm adm = new TelaAdm();
                     adm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro de login ou senha, tente novamente", "Caixa de acesso",
                            0);
                }

            }

        });



    }

    // METODO MAIN //
    public static void main(String[] args) {
        new TeladeLogin();

    }

    // CLASSES INTERNAS

    // classe da imagem
    private class Panel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // invoca a classe Graphic
            Image img = fundo.getImage(); // converte a imagem pro tipo image
            g.drawImage(img, 0, 0, this); // redesenha a imagem
        }
    }

    // tratar evento do botao cadastrar

}
