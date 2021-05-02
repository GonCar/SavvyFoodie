package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.*;

public class Controller implements Initializable {



    @FXML
    protected TableView<Products> table_info;

    @FXML
    protected TableColumn<Products, String> col_name;

    @FXML
    protected TableColumn<Products, String> col_category;

    @FXML
    protected TableColumn<Products, Integer> col_weight;

    @FXML
    protected TableColumn<Products, Boolean> col_gluten;

    @FXML
    protected TableColumn<Products, Boolean> col_vegan;

    @FXML
    protected TableColumn<Products, Integer> col_price;

    @FXML
    protected TableColumn<Products, java.sql.Date> col_date;

    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet resultSet;

    ObservableList<Products> productsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadData();
    }

    private void refreshTable() {
        try {
            productsList.clear();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select product_name, product_catagory, is_veggie, is_gluten_free, product_weight, price, expiary_left from food_products");


            while (resultSet.next()){
                productsList.add(new Products(
                        resultSet.getString("product_name"),
                        resultSet.getString("product_catagory"),
                        resultSet.getBoolean("is_veggie"),
                        resultSet.getBoolean("is_gluten_free"),
                        resultSet.getInt("product_weight"),
                        resultSet.getInt("price")));
                        //resultSet.getDate("expiary_left")));
                table_info.setItems(productsList);
            }


        } catch (SQLException exception) {
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }



    }

    private void loadData()
    {

        connection = DB_connection.connect();


        col_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("product_weight"));
        col_gluten.setCellValueFactory(new PropertyValueFactory<>("is_gluten_free"));
        col_vegan.setCellValueFactory(new PropertyValueFactory<>("is_veggie"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        //col_date.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));

        refreshTable();

    }


}
