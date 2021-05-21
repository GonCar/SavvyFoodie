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
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    @FXML private TextField nameTextField;
    @FXML private ComboBox categoryComboBox;
    @FXML private TextField priceTextField;
    @FXML private DatePicker dateTextField;
    @FXML private TextField weightTextField;
    @FXML private Button addProductButton;
    @FXML private Label warningLabel;
    @FXML private Button returnButton;
    String name;
    String category;
    String price;
    String date;
    String weight;
    ObservableList<String> categoryObservableList = FXCollections.observableArrayList("Fruits", "Vegetables");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryComboBox.setItems(categoryObservableList);
        categoryComboBox.getSelectionModel().selectFirst();
    }

    public void addProductButtonOnAction(ActionEvent event) throws IOException {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter date_input = DateTimeFormatter.ofPattern(pattern);
        date = date_input.format(dateTextField.getValue());
        name = nameTextField.getText();
        category = (String) categoryComboBox.getValue();
        price = priceTextField.getText();

        weight = weightTextField.getText();
        if(name.isEmpty() || category.isEmpty() || price.isEmpty()) {
            warningLabel.setText("Please fill the required fields");
        }else {
            app_Logic.DB.insertProduct(name, category, price, date, weight);
            warningLabel.setText("  Product added :)");
        }
    }

    public void returnButtonOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Main store");
        window.show();
    }
}
