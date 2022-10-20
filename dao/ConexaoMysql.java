package dao;

import java.sql.*;


public class ConexaoMysql {
    

    public Connection getConnection() throws SQLException{
      
        try{
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/FaceID", "root", "00789Biel*");
        return conexao;
        }catch(SQLException e){
            throw new RuntimeException("erro ao conectar com o banco de dados" + e);
        }
    }

   public void closeConnection(Connection con){
        try{
            if(con!=null){
                con.close();
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao fechar o banco de dados" + e);
        }
   }
    
}
