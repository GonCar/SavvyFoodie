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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProductController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField priceTextField;


    @FXML
    private TextField dateTextField;

    @FXML
    private TextField weightTextField;

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
    String weight;

    public void addProductButtonOnAction(ActionEvent event) throws IOException
    {
        name = nameTextField.getText();
        category = categoryTextField.getText();
        price = priceTextField.getText();
        date = dateTextField.getText();
        weight = weightTextField.getText();

        if(name.isEmpty() || category.isEmpty() || price.isEmpty())
        {
            warningLabel.setText("Please fill the required fields");
        }else
        {
            connection = new DB_connection(new FoodieConnection());
            connection.connect();
            connection.insertProduct(name, category, price, date, weight);
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
}
