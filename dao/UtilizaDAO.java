package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Funcionario;
import model.Visitante;

public class UtilizaDAO extends GenericDao {

    public void salvarAl(Aluno aluno) throws SQLException {
        String sql2 = "INSERT INTO USUARIOID(id) values (?)";
        String sql = "INSERT INTO ALUNO(id_al, matricula, nome, cpf, telefone) values (?, ?, ?, ?, ?)";
        saveID(sql2, aluno.getId());
        save(sql,aluno.getId(), aluno.getMatricula(), aluno.getNome(), aluno.getCpf(), aluno.getTelefone());
    }

    public void salvarFunc(Funcionario func) throws SQLException {
        String sql2 = "INSERT INTO USUARIOID(id) values (?)";
        String sql = "INSERT INTO Funcionario(id_func, codigo, nome, cargo, telefone) values (?, ?, ?, ?, ?)";
        saveID(sql2, func.getId());
        save(sql,func.getId(), func.getCodigo(), func.getNome(), func.getCargo(), func.getTelefone());
    }

    public void salvarVisi(Visitante visi) throws SQLException {
        String sql2 = "INSERT INTO USUARIOID(id) values (?)";
        String sql = "INSERT INTO Visitante(id_visi, cpf, nome, motivo, telefone) values (?, ?, ?, ?, ?)";
        saveID(sql2, visi.getId());
        save(sql,visi.getId(), visi.getCpf(), visi.getNome(), visi.getMotivo(), visi.getTelefone());
    }

    public void salvardatahoraAl(LocalDate localDate, LocalTime localHora, int id) throws SQLException {
        String sql2 = "insert into Entrar (dataRec, hora, usuarioID ) values (?, ?, ?)";
        String sql = "UPDATE aluno SET dataEntrar = ?, horarioEntrar = ? WHERE id_AL= ?";
        saveDataHora(sql2, localDate, localHora, id);
        update(sql, localDate, localHora, id);
    }
    public void salvardatahoraFunc(LocalDate localDate, LocalTime localHora, int id) throws SQLException {
        String sql2 = "insert into Entrar (dataRec, hora, usuarioID ) values (?, ?, ?)";
        String sql = "UPDATE Funcionario SET dataEntrar = ?, horarioEntrar = ? WHERE id_func= ?";
        saveDataHora(sql2, localDate, localHora, id);
        update(sql, localDate, localHora, id);
    }
    public void salvardatahoraVisi(LocalDate localDate, LocalTime localHora, int id) throws SQLException {
        String sql2 = "insert into Entrar (dataRec, hora, usuarioID ) values (?, ?, ?)";
        String sql = "UPDATE Visitante SET dataEntrar = ?, horarioEntrar = ? WHERE id_visi= ?";
        saveDataHora(sql2, localDate, localHora, id);
        update(sql, localDate, localHora, id);
    }

    public void salvardatahoraSaidaAl(LocalDate localDate, LocalTime localHora, int id) throws SQLException {
        String sql2 = "insert into Sair (dataSaida, hora, usuarioID ) values (?, ?, ?)";
        String sql = "UPDATE aluno SET dataSair = ?, horarioSair= ? WHERE id_AL= ?";
        saveDataHora(sql2, localDate, localHora, id);
        update(sql, localDate, localHora, id);
    }
    public void salvardatahoraSaidaFunc(LocalDate localDate, LocalTime localHora, int id) throws SQLException {
        String sql2 = "insert into Sair(dataSaida, hora, usuarioID ) values (?, ?, ?)";
        String sql = "UPDATE Funcionario SET dataSair = ?, horarioSair = ? WHERE id_func= ?";
        saveDataHora(sql2, localDate, localHora, id);
        update(sql, localDate, localHora, id);
    }
    public void salvardatahoraSaidaVisi(LocalDate localDate, LocalTime localHora, int id) throws SQLException {
        String sql2 = "insert into Sair(dataSaida, hora, usuarioID ) values (?, ?, ?)";
        String sql = "UPDATE Visitante SET dataSair = ?, horarioSair = ? WHERE id_visi= ?";
        saveDataHora(sql2, localDate, localHora, id);
        update(sql, localDate, localHora, id);
    }

    public  int mostrarID() throws SQLException {
        int id = 0;
        try {
            String sql = "SELECT * FROM UsuarioID ORDER BY id DESC LIMIT 1";
            PreparedStatement state = getConnection().prepareStatement(sql);
            ResultSet rs = state.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            id++;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro de listagem" + e.getLocalizedMessage(), "Listagem", 0);
        }

        return id;
    }


    public List listarCadastros() throws SQLException {
        List alunos = new ArrayList();
        try {
            String sql = "SELECT * FROM ALUNO";
            PreparedStatement state = getConnection().prepareStatement(sql);
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id_AL"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setDataEntrar(rs.getString("dataEntrar"));
                aluno.setHorarioEntrar(rs.getString("horarioEntrar"));
                aluno.setDataSair(rs.getString("dataSair"));
                aluno.setHorarioSair(rs.getString("horarioSair"));
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

    public List listarFuncionarios() throws SQLException {
        List funcionarios = new ArrayList();
        try {
            String sql = "SELECT * FROM Funcionario";
            PreparedStatement state = getConnection().prepareStatement(sql);
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                Funcionario func= new Funcionario();
                func.setId(rs.getInt("id_func"));
                func.setCodigo(rs.getInt("codigo"));
                func.setNome(rs.getString("nome"));
                func.setCargo(rs.getString("cargo"));
                func.setTelefone(rs.getString("telefone"));
                func.setDataEntrar(rs.getString("dataEntrar"));
                func.setHorarioEntrar(rs.getString("horarioEntrar"));
                func.setDataSair(rs.getString("dataSair"));
                func.setHorarioSair(rs.getString("horarioSair"));               
               
                funcionarios.add(func);
            }

            rs.close();
            state.close();
            getConnection().close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro de listagem" + e.getLocalizedMessage(), "Listagem", 0);
        }

        return funcionarios;
    }

    public List listarVisitantes() throws SQLException {
        List visitantes = new ArrayList();
        try {
            String sql = "SELECT * FROM Visitante";
            PreparedStatement state = getConnection().prepareStatement(sql);
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                Visitante visi= new Visitante();
                visi.setId(rs.getInt("id_visi"));
                visi.setCpf(rs.getString("cpf"));
                visi.setNome(rs.getString("nome"));
                visi.setMotivo(rs.getString("motivo"));
                visi.setTelefone(rs.getString("telefone"));
                visi.setDataEntrar(rs.getString("dataEntrar"));
                visi.setHorarioEntrar(rs.getString("horarioEntrar"));
                visi.setDataSair(rs.getString("dataSair"));
                visi.setHorarioSair(rs.getString("horarioSair"));  
                visitantes.add(visi);
            }

            rs.close();
            state.close();
            getConnection().close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro de listagem" + e.getLocalizedMessage(), "Listagem", 0);
        }

        return visitantes;
    }


    public void editarSemImg(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome=?, cpf=?, telefone=? WHERE matricula=?";
        update(sql, aluno.getMatricula(), aluno.getNome(), aluno.getCpf(), aluno.getTelefone());
    }

    public void editar(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome=?, cpf=?, telefone=? WHERE id_AL=?";
        update(sql, aluno.getNome(), aluno.getCpf(), aluno.getTelefone(), aluno.getId());
    }
    public void editarFuncionario(Funcionario func) throws SQLException {
        String sql = "UPDATE Funcionario SET nome=?, cargo=?, telefone=? WHERE id_func=?";
        update(sql, func.getNome(), func.getCargo(), func.getTelefone(), func.getId());
    }

    public void editarVisitante(Visitante visi) throws SQLException {
        String sql = "UPDATE Visitante SET nome=?, motivo=?, telefone=? WHERE id_visi=?";
        update(sql, visi.getNome(), visi.getMotivo(), visi.getTelefone(), visi.getId());
    }


    public void excluir(String string, int id) throws SQLException {
        String sql = "DELETE FROM Aluno WHERE matricula = ?";
        String sql1 = "DELETE FROM Entrar WHERE usuarioId = ?";
        String sql3 = "DELETE FROM Sair WHERE usuarioID = ?";
        String sql2 = "DELETE FROM UsuarioID WHERE id = ?";
    
        delete(sql, string);
        deleteEntrar(sql1, id);
        deleteSair( sql3, id);
        deleteID(sql2, id);
    }

    public void excluirFuncionario(int codigo, int id) throws SQLException {
       
        String sql = "DELETE FROM Funcionario WHERE codigo = ?";
        String sql1 = "DELETE FROM Entrar WHERE usuarioId = ?";
        String sql3 = "DELETE FROM Sair WHERE usuarioID = ?";
        String sql2 = "DELETE FROM UsuarioID WHERE id = ?";
    
        delete(sql, codigo);
        deleteEntrar(sql1, id);
        deleteSair( sql3, id);
        deleteID(sql2, id);
    }

    public void excluirVisitante(String cpf, int id) throws SQLException {
        String sql = "DELETE FROM Visitante WHERE cpf = ?";
        String sql1 = "DELETE FROM Entrar WHERE usuarioId = ?";
        String sql3 = "DELETE FROM Sair WHERE usuarioID = ?";
        String sql2 = "DELETE FROM UsuarioID WHERE id = ?";
    
        delete(sql, cpf);
        deleteEntrar(sql1, id);
        deleteSair( sql3, id);
        deleteID(sql2, id);
    }


    public Aluno pesquisaMatricula(String matricula) throws SQLException {
        String sql = "select distinct * from aluno a join usuarioID on a.matricula =?";
        Aluno aluno = null;
        PreparedStatement state = getConnection().prepareStatement(sql);

        state.setString(1,matricula);
        ResultSet rs = state.executeQuery();

        while (rs.next()) {
            aluno = new Aluno();
            aluno.setId(rs.getInt("id_AL"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setDataEntrar(rs.getString("dataEntrar"));
            aluno.setHorarioEntrar(rs.getString("horarioEntrar"));
        }
        rs.close();
        state.close();
        getConnection().close();

        return aluno;
    }
 //metodo que verifica se a pessoa reconhecida está no banco dos alunos
    public Aluno pesquisarCad(int id) throws SQLException {
        String sql = "select distinct * from aluno a join usuarioID on a.id_AL =?";
        Aluno aluno = null;
        PreparedStatement state = getConnection().prepareStatement(sql);

        state.setString(1,Integer.toString(id));
        ResultSet rs = state.executeQuery();

        while (rs.next()) {
            aluno = new Aluno();
            aluno.setId(rs.getInt("id_AL"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setTelefone(rs.getString("telefone"));
        }
        rs.close();
        state.close();
        getConnection().close();

        return aluno;
    }
    //metodo que verifica se a pessoa reconhecida está no banco dos funcionarios
    public Funcionario pesquisarFunc(int id) throws SQLException {
        String sql = "select distinct * from funcionario f  join usuarioID i on f.id_func = ?";
        Funcionario func = null;
        PreparedStatement state = getConnection().prepareStatement(sql);

        state.setString(1,Integer.toString(id));
        ResultSet rs = state.executeQuery();

        while (rs.next()) {
            func = new Funcionario();
            func.setId(rs.getInt("id_func"));
            func.setCodigo(rs.getInt("codigo"));
            func.setNome(rs.getString("nome"));
            func.setCargo(rs.getString("cargo"));
            func.setTelefone(rs.getString("telefone"));
            func.setDataEntrar(rs.getString("dataEntrar"));
            func.setHorarioEntrar(rs.getString("horarioEntrar"));
            func.setDataSair(rs.getString("dataSair"));
            func.setHorarioSair(rs.getString("horarioSair")); 
        }
        rs.close();
        state.close();
        getConnection().close();

        return func;
    }

    public Funcionario pesquisarCadFunc(int codigo) throws SQLException {
        String sql = "select * from funcionario where codigo = ?";
        Funcionario func = null;
        PreparedStatement state = getConnection().prepareStatement(sql);

        state.setString(1,Integer.toString(codigo));
        ResultSet rs = state.executeQuery();

        while (rs.next()) {
            func = new Funcionario();
            func.setId(rs.getInt("id_func"));
            func.setCodigo(rs.getInt("codigo"));
            func.setNome(rs.getString("nome"));
            func.setCargo(rs.getString("cargo"));
            func.setTelefone(rs.getString("telefone"));
            func.setDataEntrar(rs.getString("dataEntrar"));
            func.setHorarioEntrar(rs.getString("horarioEntrar"));
            func.setDataSair(rs.getString("dataSair"));
            func.setHorarioSair(rs.getString("horarioSair")); 
        }
        rs.close();
        state.close();
        getConnection().close();

        return func;
    }

    public Visitante pesquisarCadVisi(String cpf) throws SQLException {
        String sql = "select * from Visitante where cpf= ?";
        Visitante visi = null;
        PreparedStatement state = getConnection().prepareStatement(sql);

        state.setString(1,cpf);
        ResultSet rs = state.executeQuery();

        while (rs.next()) {
            visi= new Visitante();
            visi.setId(rs.getInt("id_visi"));
            visi.setCpf(rs.getString("cpf"));
            visi.setNome(rs.getString("nome"));
            visi.setMotivo(rs.getString("motivo"));
            visi.setTelefone(rs.getString("telefone"));
            visi.setDataEntrar(rs.getString("dataEntrar"));
            visi.setHorarioEntrar(rs.getString("horarioEntrar"));
            visi.setDataSair(rs.getString("dataSair"));
            visi.setHorarioSair(rs.getString("horarioSair"));  
    
        }
        rs.close();
        state.close();
        getConnection().close();

        return visi;
    }
     //metodo que verifica se a pessoa reconhecida está no banco dos visitantes
    public Visitante pesquisarVisi(int id) throws SQLException {
        String sql = "select distinct * from visitante v  join usuarioID i on v.id_visi = ?";
        Visitante visi = null;
        PreparedStatement state = getConnection().prepareStatement(sql);

        state.setString(1,Integer.toString(id));
        ResultSet rs = state.executeQuery();

        while (rs.next()) {
            visi = new Visitante();
            visi.setId(rs.getInt("id_visi"));
            visi.setCpf(rs.getString("cpf"));
            visi.setNome(rs.getString("nome"));
            visi.setMotivo(rs.getString("motivo"));
            visi.setTelefone(rs.getString("telefone"));
        }
        rs.close();
        state.close();
        getConnection().close();

        return visi;
    }

}