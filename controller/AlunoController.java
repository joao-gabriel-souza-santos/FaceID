package controller;

import java.awt.List;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import dao.UtilizaDAO;
import model.Aluno;

public class AlunoController {
    public void salvar(String matricula, String nome, String cpf, String telefone, byte[] foto)
            throws SQLException, ParseException {
        Aluno aluno = new Aluno();
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
        aluno.setFoto(foto);
        new UtilizaDAO().salvar(aluno);
    }

    
    public void editarSemImg(String matricula, String nome, String cpf, String telefone)
            throws SQLException, ParseException
    {
        Aluno aluno = new Aluno();
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
     
        new UtilizaDAO().editarSemImg(aluno);
    }

    public void editar(String matricula, String nome, String cpf, String telefone, byte[] foto)
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


    public void excluir (String matricula) throws SQLException{
        new UtilizaDAO().excluir(matricula);
    }

    public Aluno pesquisarCad(String matricula) throws SQLException{
        UtilizaDAO dao = new UtilizaDAO();
        return dao.pesquisarCad(matricula);
    }

    
}
