package Interface;
import java.sql.*;

public interface IConnection {

    Connection getConnection(String url) throws SQLException;

    void close() throws SQLException;
}
