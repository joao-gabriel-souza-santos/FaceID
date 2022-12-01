package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class GenericDao {
    private Connection connection;
    
    protected GenericDao(){
        try {
            connection =  new ConexaoMysql().getConnection();
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
    }

    protected  Connection getConnection(){
        return connection;
    }

    protected void saveID (String sql, Object... parametros)throws SQLException{
        PreparedStatement state = getConnection().prepareStatement(sql);

        for(int i = 0 ; i  < parametros.length ; i++){
            state.setObject(i + 1, parametros[i]);
        }
        state.execute();
        state.close();
}  
protected void saveDataHora (String sql, Object... parametros)throws SQLException{
    PreparedStatement state = getConnection().prepareStatement(sql);

    for(int i = 0 ; i  < parametros.length ; i++){
        state.setObject(i + 1, parametros[i]);
    }
    state.execute();
    state.close();
}  

protected void deleteID (String sql, Object... parametros)throws SQLException{
    PreparedStatement state = getConnection().prepareStatement(sql);

    for(int i = 0 ; i  < parametros.length ; i++){
        state.setObject(i + 1, parametros[i]);
    }
    state.execute();
    state.close();
    connection.close();
}   

    protected void save (String sql, Object... parametros)throws SQLException{
            PreparedStatement state = getConnection().prepareStatement(sql);

            for(int i = 0 ; i  < parametros.length ; i++){
                state.setObject(i + 1, parametros[i]);
            }
            state.execute();
            state.close();
            connection.close();
    }   

    protected  void update(String sql,  Object... parametros)throws SQLException{
        PreparedStatement  state  = getConnection().prepareStatement(sql);

        for(int i = 0 ; i <parametros.length ; i++){
            state.setObject(i + 1, parametros[i]);
        }
     
        state.execute();
        state.close();
        connection.close();
    }

    protected void delete(String sql, Object...parametros)throws SQLException{
        PreparedStatement state = getConnection().prepareStatement(sql);

        for (int i = 0 ; i <parametros.length ; i++){
            state.setObject(i + 1, parametros[i]);
        }
        state.execute();
        state.close();

    }

    protected void deleteEntrar(String sql, Object...parametros)throws SQLException{
        PreparedStatement state = getConnection().prepareStatement(sql);

        for (int i = 0 ; i <parametros.length ; i++){
            state.setObject(i + 1, parametros[i]);
        }
        state.execute();
        state.close();

    }
    
    protected void deleteSair(String sql, Object...parametros)throws SQLException{
        PreparedStatement state = getConnection().prepareStatement(sql);

        for (int i = 0 ; i <parametros.length ; i++){
            state.setObject(i + 1, parametros[i]);
        }
        state.execute();
        state.close();

    }

    protected void pesquisar(String sql, Object...parametros)throws SQLException{
        PreparedStatement state = getConnection().prepareStatement(sql);

        for (int i = 0 ; i <parametros.length ; i++){
            state.setObject(i + 1, parametros[i]);
        }
        state.execute();
        state.close();
        connection.close();

    }



}