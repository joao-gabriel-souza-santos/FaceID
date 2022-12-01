package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.UtilizaDAO;
import model.Aluno;
import model.Funcionario;

public class FuncionarioController {
    public void salvarFunc(int id, int codigo, String nome, String cargo, String telefone)
            throws SQLException, ParseException {
        Funcionario func = new Funcionario();
        func.setId(id);
        func.setCodigo(codigo);
        func.setNome(nome);
        func.setCargo(cargo);
        func.setTelefone(telefone);
        new UtilizaDAO().salvarFunc(func);
    }

    public void editar(Funcionario func)
            throws SQLException, ParseException {

        new UtilizaDAO().editarFuncionario(func);
    }

    public void excluir(int codigo, int id)
            throws SQLException, ParseException {

        new UtilizaDAO().excluirFuncionario(codigo, id);
    }

    public Funcionario pesquisarFunc(int id) throws SQLException {
        UtilizaDAO dao = new UtilizaDAO();
        return dao.pesquisarFunc(id);
    }

    public static Funcionario pesquisarCadFunc(int codigo) throws SQLException {
        UtilizaDAO dao = new UtilizaDAO();
        return dao.pesquisarCadFunc(codigo);
    }

    public List listarFunc() {
        UtilizaDAO dao = new UtilizaDAO();
        try {
            return dao.listarFuncionarios();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas ao localizar cadastros" + e.getLocalizedMessage());
        }

        return null;
    }
}
