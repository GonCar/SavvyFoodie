package sample;
import Interface.IConnection;

import java.sql.*;

public class DB_connection {
    String url = "jdbc:mysql://127.0.0.1:3306/classicmodels?user=root&password=root";
    IConnection connection;
    PreparedStatement ps;
    ResultSet resultSet;

    public DB_connection(IConnection connection){
        this.connection = connection;
    }

    public void connect() {
        try{
            connection.getConnection(this.url);
        }
        catch (SQLException ex){
            System.out.println("connection failed!");
        }
    }

    public void disconnect() {
        try{
            if(connection != null){ connection.close();}
            if(ps != null){ ps.close();}
            if(resultSet != null){ resultSet.close();}
        }
        catch (SQLException ex){
            System.out.println("closing the resources failed!");
        }
    }

}