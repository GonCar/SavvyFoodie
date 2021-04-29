package tests;

import org.junit.jupiter.api.Test;
import sample.DB_connection;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DB_connectionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Failed connection should display "connection failed!"
    @Test
    void failedConnection(){
        System.setOut(new PrintStream(outContent));
        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
        dbCon.connect();
        String result = outContent.toString();
        assertEquals(true, result.contains("connection failed!"));
        System.setOut(originalOut);
    }

    // Failed connection should display "closing the resources failed!"
    @Test
    void testDisconnect(){
        System.setOut(new PrintStream(outContent));
        DB_connection dbCon = new DB_connection(new FakeFoodieConnection());
        dbCon.disconnect();
        String result = outContent.toString();
        assertEquals(true, result.contains("closing the resources failed!"));
        System.setOut(originalOut);
    }
}
