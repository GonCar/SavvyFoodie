package tests;

import org.junit.jupiter.api.Test;
import sample.DB_connection;
import sample.Products;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DB_connectionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Test
    public void failedConnection() throws SQLException {

        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.driverConnect()).thenThrow(new SQLException());

        System.setOut(new PrintStream(outContent));
        connectionMock.connect();
        String result = outContent.toString();
        assertEquals(true, result.contains("connection failed!"));
        System.setOut(originalOut);
    }

    @Test
    public void testDisconnect() throws SQLException {

        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.getConnection()).thenThrow(new SQLException());
        connectionMock.setConnection(mock(Connection.class));

        System.setOut(new PrintStream(outContent));
        connectionMock.disconnect();
        String result = outContent.toString();
        assertEquals(true, result.contains("closing the resources failed!"));
        System.setOut(originalOut);
    }

    @Test
    void testFilterPrice() throws SQLException {
        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.getConnection()).thenThrow(new SQLException());
        connectionMock.setConnection(mock(Connection.class));

        System.setOut(new PrintStream(outContent));
        connectionMock.filter_by_price(2, 3);
        String result = outContent.toString();
        assertEquals(true, result.contains("Query failed to execute"));
        System.setOut(originalOut);
    }

    @Test
    void testFilterByCategory() throws SQLException{
        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.getConnection()).thenThrow(new SQLException());
        connectionMock.setConnection(mock(Connection.class));

        System.setOut(new PrintStream(outContent));
        connectionMock.filter_by_category("gluten free");
        String result = outContent.toString();
        assertEquals(true, result.contains("Query failed to execute"));
        System.setOut(originalOut);
    }

    @Test
    void testFilterByCity() throws SQLException {
        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.getConnection()).thenThrow(new SQLException());
        connectionMock.setConnection(mock(Connection.class));

        System.setOut(new PrintStream(outContent));
        connectionMock.filter_by_city("Lund");
        String result = outContent.toString();
        assertEquals(true, result.contains("Query failed to execute"));
        System.setOut(originalOut);
    }
}





