package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;


import model.Aluno;

public class UtilizaDAO {
    
    private static List<Aluno> alunos = new ArrayList<Aluno>();
    
    public static boolean inserirAl(Aluno aluno){
        Boolean retorno = false;
        String sql = "INSERT INTO Aluno (matricula, nome, cpf, telefone, foto) values (?, ?, ?, ?, ?)";


        try {
            Connection conexao = new ConexaoMysql().getConnection();
            PreparedStatement state = conexao.prepareStatement(sql);

            state.setString(1, aluno.getMatricula());
            state.setString(2, aluno.getNome());
            state.setString(3, aluno.getCpf());
            state.setString(4, aluno.getTelefone());
            state.setBytes(5, aluno.getFoto());

             retorno = state.execute();

            

        } catch (SQLException e) {
           
            JOptionPane.showMessageDialog(null, "Cadastro não efetuado,", "Cadastro", 0);
        }
  
        
        return retorno;
    }


    public static List<Aluno> listarCadastros(){
        String sql = "SELECT matricula, nome, cpf, telefone, foto FROM aluno";    
        try{
            Connection conexao = new ConexaoMysql().getConnection();
            PreparedStatement state = conexao.prepareStatement(sql);
            ResultSet rs = state.executeQuery();
            
            while(rs.next()){
    
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setFoto(rs.getBytes("foto"));
                alunos.add(aluno);
                
              
            }

            conexao.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "erro de listagem", "Listagem", 0);
        }
        return alunos;
    }


    public static void editar(Aluno aluno){
        String sql = "UPDATE aluno SET nome=?, cpf=?, telefone=?, foto=? WHERE matricula=?";
        try{
            Connection conexao = new ConexaoMysql().getConnection();
            PreparedStatement state = conexao.prepareStatement(sql);
            
            state.setString(1, aluno.getNome());
            state.setString(2, aluno.getCpf());
            state.setString(3, aluno.getTelefone());
            state.setBytes(4, aluno.getFoto());
            state.setString(5, aluno.getMatricula());
            

            state.execute();

            conexao.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela", "Cadastro", 0); 
        }

    }

    public static void excluir(Aluno aluno){
        String sql = "DELETE FROM Aluno WHERE matricula = ?";
        try{
            Connection conexao = new ConexaoMysql().getConnection();
            PreparedStatement state = conexao.prepareStatement(sql);
            
            state.setString(1, aluno.getMatricula());
            

            state.execute();

            conexao.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao excluir registro", "Excluir", 0); 
        }
    }

    public static Aluno pesquisarCad(String matricula){
        String sql = "SELECT * FROM aluno WHERE matricula = ?"; 
        Aluno aluno = new Aluno();  
        
        try{
            Connection conexao = new ConexaoMysql().getConnection();
            PreparedStatement state = conexao.prepareStatement(sql);
            ResultSet rs = state.executeQuery();
                  
       
            rs.first();
           
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setFoto(rs.getBytes("foto"));
            alunos.add(aluno);
            conexao.close();

           
        } catch(Exception e){
             
        }
        
        return aluno;
        
    }
   
}
