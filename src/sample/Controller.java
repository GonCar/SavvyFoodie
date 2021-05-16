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

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.*;

public class Controller implements Initializable {
    PreparedStatement ps;
    ResultSet resultSet;

    @FXML
    protected TableView<Products> table_info;

    @FXML
    protected TableColumn<Products, String> col_name;

    @FXML
    protected TableColumn<Products, String> col_category;

    @FXML
    protected TableColumn<Products, Integer> col_weight;

    @FXML
    protected TableColumn<Products, Integer> col_price;

    @FXML
    protected TableColumn<Products, java.sql.Date> col_date;

    @FXML
    private Button addProductButton;

    @FXML
    private Button removeProductButton;

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
            app_Logic.removeExpired();
            productsList = FXCollections.observableArrayList(app_Logic.DB.getAllProducts());
            table_info.setItems(productsList);
        } catch (Exception exception) {
            System.out.println("Failed to refresh table");
            exception.printStackTrace();
        }
    }
    public void removeProductButtonOnAction(){
        try {
            ObservableList<Products> selectedItems = table_info.getSelectionModel().getSelectedItems();
            int i = selectedItems.size() - 1;
            while (i >= 0) {
                app_Logic.DB.removeProduct(selectedItems.get(i).getExpiry_date(), selectedItems.get(i).getProduct_name());
                productsList.remove(selectedItems.get(i));
                i--;
            }
        }catch(Exception e){
            System.out.println("Something went wrong deleting the product");
            System.out.println(e);
        }
    }
    private void loadData()
    {
        col_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("product_weight"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
        refreshTable();
    }
}
