package sample;

import java.util.*;

public class app_Logic {
    static List shipping_cart = new ArrayList();
    static int current_user_id;
    public static void main(String[] args) {
        DB_connection connection = new DB_connection();
        connection.connect();
        //connection.filter_by_price( 10, 1000);
        //connection.filter_by_category("Fruit");
        //connection.filter_by_city("Stockholm");
        //connection.showProducts();
        //connection.change_price(102,22);
        //connection.change_user_name(8001, "Richard");
        connection.disconnect();
    }

}
