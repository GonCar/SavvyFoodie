package sample;

import Interface.IConnection;

import java.sql.*;

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

    @Override
    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }
}
