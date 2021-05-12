package tests;

import Interface.IConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FakeFoodieConnection implements IConnection {

    @Override
    public Connection getConnection(String url) throws SQLException {
        throw new SQLException();
    }

    @Override
    public void close() throws SQLException {
        throw new SQLException();
    }

    @Override
    public Statement createStatement() throws SQLException {
        throw new SQLException();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        throw new SQLException();
    }
}
