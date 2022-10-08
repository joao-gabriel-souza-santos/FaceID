package dao;

import javax.swing.JFrame;

public class TeladeCadastro  extends JFrame{
    public TeladeCadastro (){
        super("Teste");
        setSize(300,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TeladeCadastro();
    }
}
