package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class UtilizaDAO extends GenericDao {

    public void salvar(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO ALUNO(matricula, nome, cpf, telefone, foto) values (?, ?, ?, ?, ?)";
        save(sql, aluno.getMatricula(), aluno.getNome(), aluno.getCpf(), aluno.getTelefone(), aluno.getFoto());
    }

    public List listarCadastros() throws SQLException {
        List alunos = new ArrayList();
        try {
            String sql = "SELECT * FROM ALUNO";
            PreparedStatement state = getConnection().prepareStatement(sql);
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setFoto(rs.getBytes("foto"));
                alunos.add(aluno);
            }

            rs.close();
            state.close();
            getConnection().close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro de listagem" + e.getLocalizedMessage(), "Listagem", 0);
        }

        return alunos;
    }

    public void editarSemImg(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome=?, cpf=?, telefone=? WHERE matricula=?";
        update(sql, aluno.getMatricula(), aluno.getNome(), aluno.getCpf(), aluno.getTelefone());
    }

    public void editar(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome=?, cpf=?, telefone=?, foto=? WHERE matricula=?";
        update(sql, aluno.getMatricula(), aluno.getNome(), aluno.getCpf(), aluno.getTelefone(), aluno.getFoto());
    }

    public void excluir(String matricula) throws SQLException {
        String sql = "DELETE FROM Aluno WHERE matricula = ?";
        delete(sql, matricula);
    }

    public Aluno pesquisarCad(String matricula) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";
        Aluno aluno = null;
        PreparedStatement state = getConnection().prepareStatement(sql);

        state.setString(1, matricula);
        ResultSet rs = state.executeQuery();

        while (rs.next()) {
            aluno = new Aluno();
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setFoto(rs.getBytes("foto"));

        }
        rs.close();
        state.close();
        getConnection().close();

        return aluno;
    }

}
