package tests;

import org.junit.jupiter.api.Test;
import sample.DB_connection;

import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
//
//public class DB_connectionTest {
//
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final PrintStream originalOut = System.out;
//
//    // Failed connection should display "connection failed!"
//    @Test
//    void failedConnection(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.connect();
//        String result = outContent.toString();
//        assertEquals(true, result.contains("connection failed!"));
//        System.setOut(originalOut);
//    }
//
//    // Failed connection should display "closing the resources failed!"
//    @Test
//    void testDisconnect(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.disconnect();
//        String result = outContent.toString();
//        assertEquals(true, result.contains("closing the resources failed!"));
//        System.setOut(originalOut);
//    }
//
//    /**
//    @Test
//    void testFilterPrice(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.filter_by_price(2, 3);
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Error on executing statement!"));
//        System.setOut(originalOut);
//    }
//    @Test
//    void testChangePrice(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.change_price(5, 6);
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Error on executing statement!"));
//        System.setOut(originalOut);
//    }
//    @Test
//    void testChangeUserName(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.change_user_name(3,"My");
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Error on executing statement!"));
//        System.setOut(originalOut);
//    }
//    @Test
//    void testFilterByCategory(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.filter_by_category("gluten free");
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Error on executing statement!"));
//        System.setOut(originalOut);
//    }
//    @Test
//    void testFilterByCity(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.filter_by_city("Lund");
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Error on executing statement!"));
//        System.setOut(originalOut);
//    }
//     **/
//    @Test
//    void testShowProducts(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.showProducts();
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Query failed to execute"));
//        System.setOut(originalOut);
//    }
//    @Test
//    void testAddProduct(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.addProduct("cola", "drink",false,false,150,10,  new Date());
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Could not add product to database"));
//        System.setOut(originalOut);
//    }
//    @Test
//    void testDeleteProduct(){
//        System.setOut(new PrintStream(outContent));
//        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
//        dbCon.deleteProduct("cola");
//        String result = outContent.toString();
//        assertEquals(true, result.contains("Product couldn't be deleted"));
//        System.setOut(originalOut);
//    }

public class DB_connectionTest {

    /**
    public static void main(String[] args) {
        String Url = "jdbc:mysql://127.0.0.1:3306/savvyfoodie?user=root&password=root";
     try {
        Class.forName("com.mysql.jdbc.Driver");
        //    System.out.println("Trying to connect");
            Connection con = DriverManager.getConnection(Url);

            System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                    + con.getMetaData().getDatabaseProductName());
            Statement stmt = con.createStatement();
            stmt.executeQuery("select * from user;");
      } catch (Exception e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
      }

    }
     **/
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
}





