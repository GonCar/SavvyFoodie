package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
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

    @FXML
    private Button addProductButton;


    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet resultSet;

    ObservableList<Products> productsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        table_info.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadData();
    }

    public void addProductButtonOnAction(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.setTitle("Add Product");
        window.show();
    }

    private void refreshTable() {
        try {
            productsList.clear();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select product_id, product_name, product_catagory, is_veggie, is_gluten_free, product_weight, price, expiary_left from food_products");


            while (resultSet.next()){
                productsList.add(new Products(
                        resultSet.getInt("product_id"),
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

    public void Delete()
    {
        connection = DB_connection.connect();
        String query = "DELETE FROM food_products WHERE product_id = ?";
        try
        {
            ObservableList<Products> SelectedItems = table_info.getSelectionModel().getSelectedItems();
            int item;
            int sizeOfSelectedItems = SelectedItems.size() - 1;

            while (sizeOfSelectedItems >= 0)
            {
                ps = connection.prepareStatement(query);
                item = SelectedItems.get(sizeOfSelectedItems).getProduct_id();
                ps.setInt(1, item);
                ps.execute();
                sizeOfSelectedItems--;
            }

            refreshTable();

        }catch (SQLException exception)
        {
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }
    }


}