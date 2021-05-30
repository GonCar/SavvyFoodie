package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.model.DB_connection;
import sample.model.Products;
import sample.model.app_Logic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DB_connectionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    //Try to do a failed connection test
    @Test
    @DisplayName("Try to do a failed connection test.")
    public void failedConnection() throws SQLException {

        DB_connection connectionMock = spy(new DB_connection());
        when(connectionMock.driverConnect()).thenThrow(new SQLException());

        System.setOut(new PrintStream(outContent));
        connectionMock.connect();
        String result = outContent.toString();
        assertEquals(true, result.contains("connection failed!"));
        System.setOut(originalOut);
    }

    //Test faied disconnection
    @Test
    @DisplayName("Test faied disconnection.")
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

    //Failing test for the feature of Filter Price
    @Test
    @DisplayName("Failing test for the feature of Filter Price")
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

    //Failing test for the feature of Filter By Category
    @Test
    @DisplayName("Failing test for the feature of Filter By Category")
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

    //Failing test for the feature of Filter By City
    @Test
    @DisplayName("Failing test for the feature of Filter By City")
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

    //Failing test for the feature of Insert and Show Products
    @Test
    @DisplayName("Failing test for the feature of Insert and Show Products")
    void insertAndFetchProduct(){
        DB_connection connection = new DB_connection();
        connection.connect();

        app_Logic.current_user_id = 8003;

        int preSum = connection.getAllProducts().size();
        connection.insertProduct("AppleTest", "Fruit", "5", "2021-05-23", "10");
        List<Products> products = connection.getAllProducts();
        Products pro = products.stream()
                .filter(product -> product.getProduct_name().equals("AppleTest")
                        && product.getCategory().equals("Fruit")
                        && product.getPrice() == 5
                        && product.getProduct_weight() == 10)
                .findAny()
                .orElse(null);

        assertNotNull(pro);
        assertNotEquals(products.size(), preSum);
    }

    //Failing test for the feature of User Registration
    @Test
    @DisplayName("Failing test for the feature of User Registration")
    void insertAndGetUser(){
        DB_connection connection = new DB_connection();
        connection.connect();

        connection.insertUser("RichardTest",
                "Private",
                "mike123",
                "Kristianstad",
                "Sweden",
                "test@gmail.com");
        int userId = connection.getUserId("RichardTest");

        boolean isGreatetThanZero = userId >= 0;
        assertTrue(isGreatetThanZero);
    }

}









