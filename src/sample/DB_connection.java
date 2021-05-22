package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DB_connection {
    private static ResultSet resultSet;
    private static PreparedStatement ps;
    private Connection connection;
    final static String url = "jdbc:mysql://127.0.0.1:3306/savvyfoodie?user=root&password=root";

    public Connection connect() {
        try{
            connection = driverConnect();
            return connection;
        }
        catch (SQLException e){
            System.out.println("connection failed!");
        }
        return null;
    }

    public Connection driverConnect() throws SQLException {
        connection = DriverManager.getConnection(url);
        return connection;
    }

    public void disconnect() {
        try{
            if(connection != null){ connection.close();}
            if(ps != null){ ps.close();}
            if(resultSet != null){ resultSet.close();}
        }
        catch (SQLException e){
            System.out.println("closing the resources failed!");
        }
    }

    public List<Products> getAllProducts() {
        List<Products> allProducts = new ArrayList<>();
        try {
            ps = connection.prepareStatement("Select product_name, product_category, product_weight, price, expiry_date, Users_user_id from food_products");
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                PreparedStatement get_contact = connection.prepareStatement("Select email from users where user_id = ?");
                get_contact.setInt(1, resultSet.getInt("Users_user_id"));
                ResultSet resultSet2 = get_contact.executeQuery();
                resultSet2.next();
                allProducts.add(new Products(
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("product_weight"),
                        resultSet.getInt("price"),
                        resultSet.getLong("expiry_date"),
                        resultSet2.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println("Query failed to execute");
            e.printStackTrace();
        }
        return allProducts;
    }

    public void insertProduct(String name, String category, String price, String expiry_date, String weight)
    {
        try {
            String insert_query = "INSERT INTO food_products VALUES(?, ?, ?, ?, STR_TO_DATE('"+expiry_date+"', '%Y-%m-%d'), ?, ?)";
            String max_product_id_Query = "SELECT max(product_id) FROM food_products";
            resultSet = connection.prepareStatement(max_product_id_Query).executeQuery();
            resultSet.next();
            int current_product_id = resultSet.getInt(  1);
            ps = connection.prepareStatement(insert_query);
            ps.setInt(1, current_product_id + 1);
            ps.setString(2, name);
            ps.setString(3, category);
            ps.setInt(4, Integer.parseInt(price));
            ps.setInt(5, Integer.parseInt(weight));
            ps.setInt(6, app_Logic.current_user_id);
            ps.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Query failed to execute :)");
            exception.printStackTrace();
        }
    }

    public int getUserId( String user_name){
        try {
            ps = connection.prepareStatement("SELECT user_id FROM users WHERE user_name= ?");
            ps.setString(1, user_name);
            resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("Failed to get user Id");
            e.printStackTrace();
        }
        return 0;
    }

    public void insertUser(String user_name, String entity, String password, String city, String country, String email)
    {
        try {
            String query = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
            String max_user_id = "SELECT max(user_id) FROM users";
            resultSet = connection.prepareStatement(max_user_id).executeQuery();
            resultSet.next();
            int current_user_id = resultSet.getInt(  1) + 1;
            ps= connection.prepareStatement(query);
            ps.setInt(1, current_user_id);
            ps.setString(2, user_name);
            ps.setString(3, entity);
            ps.setString(4, password);
            ps.setString(5, city);
            ps.setString(6, country);
            ps.setString(7, email);
            ps.execute();
        } catch (SQLException exception) {
            System.out.println("Query failed to add the user!");
            exception.printStackTrace();
        }
    }

    public void removeProduct(long expiry_date){
        try {
            String query = "DELETE FROM food_products WHERE expiry_date=?";
            ps = connection.prepareStatement(query);
            ps.setLong(1, expiry_date);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Query failed to delete product :( ");
        }
    }

    public void removeProduct(long expiry_date, String name){
        try {
            String query = "select product_id FROM food_products WHERE expiry_date=? AND product_name=?";
            String query2 = "DELETE FROM food_products WHERE product_id=?";
            ps = connection.prepareStatement(query);
            ps.setLong(1, expiry_date);
            ps.setString(2, name);
            resultSet = ps.executeQuery();
            resultSet.next();
            int productId = resultSet.getInt(1);
            ps = connection.prepareStatement(query2);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Query failed to delete product :( ");
        }
    }

    public void filter_by_price(int min_price, int max_price){
        try{
            app_Logic.filteredProducts = new HashSet<>();
            ps = connection.prepareStatement("SELECT * FROM food_products WHERE price >= ? AND price <= ?");
            ps.setInt(1, min_price);
            ps.setInt(2, max_price);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                PreparedStatement get_contact = connection.prepareStatement("Select email from users where user_id = ?");
                get_contact.setInt(1, resultSet.getInt("Users_user_id"));
                ResultSet resultSet2 = get_contact.executeQuery();
                resultSet2.next();
                app_Logic.filteredProducts.add(new Products(
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("product_weight"),
                        resultSet.getInt("price"),
                        resultSet.getLong("expiry_date"),
                        resultSet2.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println("Query failed to execute");
            e.printStackTrace();
        }
    }

    public void filter_by_category(String category) {
        try{
            app_Logic.filteredProducts = new HashSet<>();
            ps = connection.prepareStatement("SELECT" +
                    " product_name, product_category," +
                    " product_weight, price, expiry_date, Users_user_id" +
                    " FROM food_products WHERE product_category = ?");
            ps.setString(1, category);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                PreparedStatement get_contact = connection.prepareStatement("Select email from users where user_id = ?");
                get_contact.setInt(1, resultSet.getInt("Users_user_id"));
                ResultSet resultSet2 = get_contact.executeQuery();
                resultSet2.next();
                app_Logic.filteredProducts.add(new Products(
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("product_weight"),
                        resultSet.getInt("price"),
                        resultSet.getLong("expiry_date"),
                        resultSet2.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println("Query failed to execute");
            e.printStackTrace();
        }
    }
    public void filter_by_city(String city) {
        try{
            app_Logic.filteredProducts = new HashSet<>();
            ps = connection.prepareStatement("SELECT product_name, product_category," +
                    " product_weight, price, expiry_date, Users_user_id FROM food_products" +
                    " INNER JOIN users ON  food_products.Users_user_id = users.user_id" +
                    " WHERE users.city = ?");
            ps.setString(1, city);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                PreparedStatement get_contact = connection.prepareStatement("Select email from users where user_id = ?");
                get_contact.setInt(1, resultSet.getInt("Users_user_id"));
                ResultSet resultSet2 = get_contact.executeQuery();
                resultSet2.next();
                Products x = new Products(
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("product_weight"),
                        resultSet.getInt("price"),
                        resultSet.getLong("expiry_date"),
                        resultSet2.getString(1));
                System.out.println("working with city filter method");
                app_Logic.filteredProducts.add(x);
            }
        } catch (SQLException e) {
            System.out.println("Query failed to execute");
            e.printStackTrace();
        }
    }

    public int get_user_id(Products selected){
        try {
            ps = connection.prepareStatement("select user_id from users where email=?");
            ps.setString(1,selected.getOwner_contact());
            resultSet = ps.executeQuery();
            resultSet.next();
            int x= resultSet.getInt(1);
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // *********************** To be added to the Gui functionalities ********************
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
}