package sample;

import Interface.IConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AddProductController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField dateTextField;

    @FXML
    private CheckBox veganCheckbox;

    @FXML
    private CheckBox glutenFreeCheckbox;

    @FXML
    private Button addProductButton;

    @FXML
    private Label warningLabel;

    @FXML
    private Button returnButton;

    DB_connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet resultSet;

    String name;
    String category;
    String price;
    String date;
    String description;
    Boolean isVegan = false;
    Boolean isGlutenFree = false;

    public void addProductButtonOnAction(ActionEvent event) throws IOException
    {
        name = nameTextField.getText();
        category = categoryTextField.getText();
        price = priceTextField.getText();
        date = dateTextField.getText();
        description = descriptionTextArea.getText();

        if(veganCheckbox.isSelected())
        {
            isVegan = true;
        }

        if(glutenFreeCheckbox.isSelected())
        {
            isGlutenFree = true;
        }

        if(name.isEmpty() || category.isEmpty() || price.isEmpty())
        {
            warningLabel.setText("Please fill the required fields");
        }else
        {
            insertProducts();
        }
    }

    public void returnButtonOnAction(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.setTitle("Main store");
        window.show();
    }

    public void insertProducts()
    {
        try {

            connection = new DB_connection(new FoodieConnection());
            connection.connect();
            String query = "INSERT INTO `food_products` (`product_name`, `product_catagory`, `price`, `product_description`, `is_veggie`, `is_gluten_free`, `Users_user_id`) VALUES(?,?,?,?,?,?,?)";
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, nameTextField.getText());
            ps.setString(2, categoryTextField.getText());
            ps.setInt(3, Integer.parseInt(priceTextField.getText()));
            ps.setString(4, descriptionTextArea.getText());
            ps.setBoolean(5, isVegan);
            ps.setBoolean(6, isGlutenFree);
            ps.setInt(7, 8001);
            ps.execute();

        } catch (SQLException exception) {
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }
    }
}
