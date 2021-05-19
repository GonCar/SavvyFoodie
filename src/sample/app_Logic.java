package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class app_Logic {
    static int current_user_id;
    static DB_connection DB = new DB_connection();
    static Connection connection = DB.connect();
    static Set<Products> filteredProducts = new HashSet<>();
    static ObservableList<Products> productsList = FXCollections.observableArrayList();

    public static void removeExpired(){
        String today = DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now());
        long today_Date = Integer.parseInt(today);
        app_Logic.DB.getAllProducts().stream()
                .filter(x-> x.getExpiry_date() < today_Date)
                .map(Products::getExpiry_date)
                .forEach(x ->app_Logic.DB.removeProduct(x));
    }
}








//    public static void main(String[] args) {
//        //DB_connection connection = new DB_connection(new FoodieConnection());
//        //connection.connect();
//        //connection.filter_by_price( 10, 1000);
//        //connection.filter_by_category("Fruit");
//        //connection.filter_by_city("Stockholm");
//        //connection.showProducts();
//        //connection.change_price(102,22);
//        //connection.change_user_name(8001, "Richard");
//        //connection.disconnect();
//    }

