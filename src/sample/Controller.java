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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML protected TableView<Products> table_info;
    @FXML protected TableColumn<Products, String> col_name;
    @FXML protected TableColumn<Products, String> col_category;
    @FXML protected TableColumn<Products, Integer> col_weight;
    @FXML protected TableColumn<User, String> col_Email;
    @FXML protected TableColumn<Products, Integer> col_price;
    @FXML protected TableColumn<Products, java.sql.Date> col_date;
    ObservableList<Products> productsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table_info.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadData();
    }

    /**Change to the add products view*/
    public void addProductButtonOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("sample/style.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Add Product");
        window.show();
    }

    /**Refresh and decide which products to view on the main store*/
    private void refreshTable() {
        try {
            productsList.clear();
            app_Logic.removeExpired();
            if (app_Logic.filteredProducts != null){
                productsList.addAll(app_Logic.filteredProducts);
            } else {
                productsList = FXCollections.observableArrayList(app_Logic.DB.getAllProducts());
            }
            table_info.setItems(productsList);
        } catch (Exception exception) {
            System.out.println("Failed to refresh table");
            exception.printStackTrace();
        }
    }

    /**remove the selected food items*/
    public void removeProductButtonOnAction(){
        try {
            ObservableList<Products> selectedItems = table_info.getSelectionModel().getSelectedItems();
            int i = selectedItems.size() - 1;
            while (i >= 0) {
                if (app_Logic.current_user_id == app_Logic.DB.get_user_id(selectedItems.get(i))){
                    app_Logic.DB.removeProduct(selectedItems.get(i).getExpiry_date(), selectedItems.get(i).getProduct_name());
                    productsList.remove(selectedItems.get(i)); }
                i--;
            }
        }catch(Exception e){
            System.out.println("Something went wrong deleting the product");
            System.out.println(e);
        }
    }

    /**Load the items to the tableview*/
    private void loadData() {
        col_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("product_weight"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("owner_contact"));
        refreshTable();
    }

/**Log out the current logged in user*/
   public void logOutProductButtonOnAction(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        tableViewScene.getStylesheets().add("sample/style.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setTitle("");
        window.show();
//        app_Logic.DB.disconnect();
        app_Logic.current_user_id = 0;
    }


    /**Change view to the filter options class*/
    public void filterProductButtonOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("filterOptions.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("sample/style.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Filter");
        window.show();
    }
}
