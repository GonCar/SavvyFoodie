package sample;
import java.sql.*;
import java.util.Date;

public class DB_connection {
    //String url = "jdbc:mysql://127.0.0.1:3306/savvyfoodie?user=root&password=GyroZeppeli";
    //Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet resultSet;
    ResultSetMetaData rsmd;
    private static Connection connection;
    private static String url = "jdbc:mysql://127.0.0.1:3306/savvyfoodie?user=root&password=GyroZeppeli";

    /*public void connect() {
        try{
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException ex){
            System.out.println("connection failed!");
            ex.printStackTrace();
        }
    }*/

    public static Connection connect (){
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("connection failed!");
            ex.printStackTrace();
        }

        return  connection;
    }

    public void disconnect() {
        try{
            if(connection != null){ connection.close();}
            if(ps != null){ ps.close();}
            if(resultSet != null){ resultSet.close();}
        }
        catch (SQLException ex){
            System.out.println("closing the resources failed!");
        }
    }

    public void showProducts()
    {
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from food_products");
            rsmd = resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print("|" + resultSet.getString(i) + "| ");
                }
                System.out.println();
            }

        }catch (SQLException exception){
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }
    }

    public void addProduct(String product_name, String category, boolean is_veggie, boolean is_gluten_free, int product_weight, int price, Date expiry_date)
    {
        try {

            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO products" + "VALUES (" + product_name +","+ category +"," + is_veggie +"," + is_gluten_free +"," + product_weight +","+ price+","+ expiry_date +")");

        }catch (SQLException exception)
        {

            System.out.println("Could not add product to database");
            exception.printStackTrace();

        }
    }

    public void deleteProduct(String productId)
    {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SET FOREIGN_KEY_CHECKS=0;");

            statement = connection.createStatement();

            int row = statement.executeUpdate("Delete from products WHERE product_id= '"+ productId + "'");

            System.out.println("The customer was successfully deleted.\n");
            System.out.println(row + " affected");

        }catch (SQLException exception)
        {

            System.out.println("Product couldn't be deleted");
            exception.printStackTrace();

        }
    }

}