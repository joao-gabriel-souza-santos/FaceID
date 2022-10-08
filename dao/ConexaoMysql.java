package dao;

import java.sql.*;
public class ConexaoMysql {
    

    public Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3307/FaceID", "root", "00789Bie*");
        return conexao;
    
    }
}
