package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sample.DB_connection;
import sample.FoodieConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBTest {
    @Test
    @DisplayName("Connection Test")
    void ConnectionTest() throws Exception{
        System.out.println("Open Connection");
        DB_connection result = new DB_connection(new FoodieConnection());
        result.connect();
        assertEquals(result != null, true);
    }

    @Test
    @DisplayName("Add Product")
    public void testAddProduct() throws Exception{
        System.out.println("Test for Adding Products into Database.");
        String insertInitialData = "INSERT INTO products\n"
                + "(product_name, category)\n"
                + "VALUES\n"
                + "('FaluKorv', 'Meat');";
        try{

            PreparedStatement statement = null;
            ResultSet rs = statement.executeQuery(insertInitialData);
            String product_name = rs.getString("product_name");
            String cataegory = rs.getString("catagory");
            assertEquals("FaluKorv",product_name);
            assertEquals("Meat", cataegory);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
