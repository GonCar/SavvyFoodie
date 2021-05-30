package sample.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class app_Logic {
    /**Defining some important variables*/
    public static int current_user_id;
    static DB_connection DB = new DB_connection();
    static Connection connection = DB.connect();
    static Set<Products> filteredProducts;

    /**Automatically detect and remove expired products*/
    public static void removeExpired(){
        String today = DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now());
        long today_Date = Integer.parseInt(today);
        app_Logic.DB.getAllProducts().stream()
                .filter(x-> x.getExpiry_date() < today_Date)
                .map(Products::getExpiry_date)
                .forEach(x ->app_Logic.DB.removeProduct(x));
    }
}
