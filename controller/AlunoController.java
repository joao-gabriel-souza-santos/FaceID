package controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.UtilizaDAO;
import model.Aluno;
import model.Funcionario;

public class AlunoController {
    public void salvarAl(int id, String matricula, String nome, String cpf, String telefone)
            throws SQLException, ParseException {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
        new UtilizaDAO().salvarAl(aluno);
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

    public void editar(Aluno aluno)
            throws SQLException, ParseException
    {

        new UtilizaDAO().editar(aluno);
    }

    public java.util.List listarCadastros(){
        UtilizaDAO dao = new UtilizaDAO();
        try{
            return  dao.listarCadastros();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
            "Problemas ao localizar cadastro" +  e.getLocalizedMessage());
        }

    return null;
    }

    


    public void excluir (String string, int id) throws SQLException{
        new UtilizaDAO().excluir(string, id);
    }

    public Aluno pesquisarCad(int id) throws SQLException{
        UtilizaDAO dao = new UtilizaDAO();
        return dao.pesquisarCad(id);
    }

    public Aluno pesquisaMatricula(String matricula) throws SQLException{
        UtilizaDAO dao = new UtilizaDAO();
        return dao.pesquisaMatricula(matricula);
    }

    
}