package sample;

public class Main {
    public static void main(String[] args) {
        DB_connection connection = new DB_connection();
        connection.connect();
        //connection.filter_by_price( 10, 1000);
        connection.filter_by_category("Fruit");
        //connection.showProducts();
    }
}