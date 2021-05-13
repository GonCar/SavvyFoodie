package sample;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class app_Logic {
    static List shipping_cart = new ArrayList();
    static int current_user_id;
    static DB_connection DB = new DB_connection();
    static Connection connection = DB.connect();

    public static void main(String[] args) {
    }
    public static void startApp(){
    }


    public static void removeExpired(){
        String today = DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now());
        long todays_Date = Integer.parseInt(today);

        app_Logic.DB.getAllProducts().stream()
                .filter(x-> x.getExpiry_date() < todays_Date)
                .map(Products::getProduct_id)
                .forEach(x -> app_Logic.DB.removeProduct(x));
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

