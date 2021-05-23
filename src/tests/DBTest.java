package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mockito.Mock;
import sample.DB_connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class DBTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Test
    @DisplayName("Connection Test")
    void ConnectionTest() throws Exception {
        System.out.println("Connection Testing");
        DB_connection result = new DB_connection();
        assertEquals(result != null, true);
    }


    @Test
    @DisplayName("Add Product")
    public void testAddProduct() throws Exception {
        System.out.println("Test for Adding Products into Database.");
        String insertInitialData = "INSERT INTO products\n"
                + "(product_name, category)\n"
                + "VALUES\n"
                + "('FaluKorv', 'Meat');";
        try {

            PreparedStatement statement = null;
            ResultSet rs = statement.executeQuery(insertInitialData);
            String product_name = rs.getString("product_name");
            String cataegory = rs.getString("catagory");
            assertEquals("FaluKorv", product_name);
            assertEquals("Meat", cataegory);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}





/*
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
    public void testGetUserId() throws SQLException{
        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.getUserId("Geng")).thenThrow(new SQLException());

        System.setOut(new PrintStream(outContent));
        connectionMock.connect();
        String result = outContent.toString();
        assertEquals(true, result.contains("Failed to get user Id"));
        System.setOut(originalOut);

    }

    @Test
    void testFilterPrice() throws SQLException{
        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.getConnection()).thenThrow(new SQLException());
        connectionMock.setConnection(mock(Connection.class));

        System.setOut(new PrintStream(outContent));
        connectionMock.filter_by_price(2, 3);
        String result = outContent.toString();
        assertEquals(true, result.contains("Query failed to execute"));
        System.setOut(originalOut);
    }

}

 */
