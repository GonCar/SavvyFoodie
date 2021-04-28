package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DB_connectionTest {

    @Test
    void connect() {
        //an executable url
        // an optional error message
        String url = "jdbc:mysql://127.0.0.1:3306/classicmodels?user=root&password=root";
        assertAll("Expception Message");
    }

    @Test
    void disconnect() {
    }
}