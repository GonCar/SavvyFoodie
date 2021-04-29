package sample;

import Interface.IConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FoodieConnection implements IConnection {

    private Connection connection;

    @Override
    public Connection getConnection(String url) throws SQLException {
        this.connection = DriverManager.getConnection(url);
        return this.getConnection(url);
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
