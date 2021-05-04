package sample;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class DB_connection {
    String url = "jdbc:mysql://127.0.0.1:3306/savvyfoodie?user=root&password=root";
    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet resultSet;
    ResultSetMetaData rsmd;
    public void connect() {
        try{
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException ex){
            System.out.println("connection failed!");
        }
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

    public void filter_by_price(int min_price, int max_price){
        try{
            ps = connection.prepareStatement("SELECT * FROM food_products WHERE price >= ? AND price <= ?");
            ps.setInt(1, min_price);
            ps.setInt(2, max_price);
            resultSet = ps.executeQuery();
            int a = 1;
            if (!resultSet.next()){
                System.out.println("\nNo food items of that price range!");
            } else {
                resultSet = ps.executeQuery();
                while(resultSet.next()){
                    System.out.println(
                            "\n" +    a +
                                    ". Product Name: " + resultSet.getString(2)+
                                    "\nQuantity in stock: " + resultSet.getInt(9)+
                                    "\nPrice per kg: " + resultSet.getDouble(4)+
                                    " USD\n-----------------------------");
                    a++;
                }
            }
        }catch(SQLException ex){
            System.out.println("Error on executing statement!");
        }
    }

    public void change_price(int product_id, int new_price){
        try{
            ps = connection.prepareStatement("SELECT * FROM food_products WHERE product_id=?");
            ps.setInt(1, product_id);
            resultSet = ps.executeQuery();
            if (!resultSet.next()){
                System.out.println("\nProduct not found!");
            } else {
                ps = connection.prepareStatement("UPDATE food_products SET price = ? WHERE product_id = ?");
                ps.setInt(1, new_price);
                ps.setInt(2, product_id);
                ps.executeUpdate();
                System.out.println("\nProduct price changed!");
                System.out.println("---------------------------");
                System.out.println(
                        "Product : " + resultSet.getString(2)+
                                ", " + resultSet.getString(3)+
                                "\nOld price: " + resultSet.getString(4)+
                                "\nNew price : " + new_price);
            }
        }catch(SQLException ex){
            System.out.println("Error on executing statement!");
        }
    }
    public void change_user_name(int user_id, String new_user_name){
        try{
            ps = connection.prepareStatement("SELECT * FROM users WHERE user_id=?");
            ps.setInt(1, user_id);
            resultSet = ps.executeQuery();
            if (!resultSet.next()){
                System.out.println("\nUser not found!");
            } else {
                ps = connection.prepareStatement("UPDATE users SET user_name = ? WHERE user_id = ?");
                ps.setString(1, new_user_name);
                ps.setInt(2, user_id);
                ps.executeUpdate();
                System.out.println("\nUser name changed!");
                System.out.println("---------------------------");
                System.out.println(
                        "User ID : " + resultSet.getString(1)+
                                "\nOld username: " + resultSet.getString(2)+
                                "\nNew price : " + new_user_name);
            }
        }catch(SQLException ex){
            System.out.println("Error on executing statement!");
        }
    }

    public void filter_by_category(String category) {
        try{
            ps = connection.prepareStatement("SELECT * FROM food_products WHERE product_catagory = ?");
            ps.setString(1, category);
            resultSet = ps.executeQuery();
            int a = 1;
            if (!resultSet.next()){
                System.out.println("\nNo category" + category + " available!");
            } else {
                resultSet = ps.executeQuery();
                while(resultSet.next()){
                    System.out.println(
                         "\n" +    a +
                         ". Product Name: " + resultSet.getString(2)+
                         "\nQuantity in stock: " + resultSet.getInt(9)+
                         "\nPrice per kg: " + resultSet.getDouble(4)+
                         " USD\n-----------------------------");
                    a++;
                }
            }
        }catch(SQLException ex){
            System.out.println("Error on executing statement!");
        }
    }

    public void filter_by_city(String city) {
        try{
            ps = connection.prepareStatement("SELECT user_name, product_name, " +
                    "product_catagory, price, product_weight , city FROM food_products" +
                    " INNER JOIN users ON  food_products.Users_user_id = users.user_id" +
                    " WHERE users.city = ?");
            ps.setString(1, city);
            resultSet = ps.executeQuery();
            int a = 1;
            if (!resultSet.next()){
                System.out.println("\nNo products in " + city + " available!");
            } else {
                resultSet = ps.executeQuery();
                while(resultSet.next()){
                    System.out.println(
                          "\n" +    a +
                          ". Product Name: " + resultSet.getString(2)+
                          "\nQuantity in stock: " + resultSet.getInt(5)+
                          "\nPrice per kg: " + resultSet.getDouble(4)+ " USD" +
                          "\nSeller : " + resultSet.getString(1)+
                          "\nCity : " + resultSet.getString(6)+
                          "\n-----------------------------");
                    a++;
                }
            }
        }catch(SQLException ex){
            System.out.println("Error on executing statement!");
        }
    }


    public void showProducts() {
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from products");
            rsmd = resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print("|" + resultSet.getString(i) + "| ");
                }
                System.out.println();
            }

        }catch (SQLException exception){
            System.out.println("Query failed to execute!");
            exception.printStackTrace();
        }
    }

    public void addProduct(String product_name, String category, boolean is_veggie, boolean is_gluten_free, int product_weight, int price, Date expiry_date) {
        try {

            statement = connection.createStatement();
            int veggie = (is_veggie)? 1 : 0;
            int gluten = (is_gluten_free)? 1: 0;
            statement.executeUpdate("INSERT INTO products" + "VALUES (" + product_name +","+ category +"," + veggie +"," + gluten +"," + product_weight +","+ price+","+ expiry_date +")");
        }catch (SQLException exception)
        {
            System.out.println("Could not add product to database!");
            exception.printStackTrace();
        }
    }

    public void deleteProduct(String productId) {
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