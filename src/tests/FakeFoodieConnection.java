package tests;

import Interface.IConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class FakeFoodieConnection implements IConnection {

    @Override
    public Connection getConnection(String url) throws SQLException {
        throw new SQLException();
    }

    @Override
    public void close() throws SQLException {
        throw new SQLException();
    }
}
