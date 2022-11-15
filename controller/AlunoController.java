package controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.UtilizaDAO;
import model.Aluno;

public class AlunoController {
    public void salvar(int matricula, String nome, String cpf, String telefone)
            throws SQLException, ParseException {
        Aluno aluno = new Aluno();
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
        new UtilizaDAO().salvar(aluno);
    }

    
    public void editarSemImg(int matricula, String nome, String cpf, String telefone)
            throws SQLException, ParseException
    {
        Aluno aluno = new Aluno();
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
     
        new UtilizaDAO().editarSemImg(aluno);
    }

    public void editar(int matricula, String nome, String cpf, String telefone, byte[] foto)
            throws SQLException, ParseException
    {
        Aluno aluno = new Aluno();
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
        aluno.setFoto(foto);
        new UtilizaDAO().editar(aluno);
    }

    public java.util.List listarCadastros(){
        UtilizaDAO dao = new UtilizaDAO();
        try{
            return  dao.listarCadastros();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
            "Problemas ao localizar contaton" +  e.getLocalizedMessage());
        }

    return null;
    }

    public List mostraID(int matricula){
        UtilizaDAO dao = new UtilizaDAO();
        try{
            return  dao.mostrarID(matricula);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
            "Problemas ao localizar contaton" +  e.getLocalizedMessage());
        }

    return null;
    }

    public void excluir (int i) throws SQLException{
        new UtilizaDAO().excluir(i);
    }

    public Aluno pesquisarCad(int matricula) throws SQLException{
        UtilizaDAO dao = new UtilizaDAO();
        return dao.pesquisarCad(matricula);
    }

    
}