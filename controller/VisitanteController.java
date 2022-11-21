package controller;

import model.Visitante;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.UtilizaDAO;

public class VisitanteController {
    public void salvarVisi(int id, String cpf, String nome, String motivo, String telefone)
            throws SQLException, ParseException {
        Visitante visi = new Visitante();
        visi.setId(id);
        visi.setCpf(cpf);
        visi.setNome(nome);
        visi.setMotivo(motivo);
        visi.setTelefone(telefone);
        new UtilizaDAO().salvarVisi(visi);
        ;
    }

    public void editar(Visitante visi)
            throws SQLException, ParseException {

        new UtilizaDAO().editarVisitante(visi);
    }

    public List listarVisitante() {
        UtilizaDAO dao = new UtilizaDAO();
        try {
            return dao.listarVisitantes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas ao localizar visitantes" + e.getLocalizedMessage());
        }

        return null;
    }

    public void excluir(String cpf){
        UtilizaDAO dao = new UtilizaDAO();
        try {
            dao.excluirVisitante(cpf);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }    

    public Visitante pesquisarVisi(int id) throws SQLException {
        UtilizaDAO dao = new UtilizaDAO();
        return dao.pesquisarVisi(id);
    }
}
