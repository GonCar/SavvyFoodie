package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class filterOptionsController implements Initializable {
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private TextField cityField;
    @FXML private TextField maxValue;
    @FXML private TextField minValue;
    @FXML private Label warningLabel;
    ObservableList<String> categoryComboBoxObservableList = FXCollections.observableArrayList("Fruits", "Vegetables");

    public void returnButtonOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("sample/style.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Main store");
        window.show();
    }

    public void filterProductsButtonOnAction(ActionEvent event) throws IOException {
        String city = cityField.getText();
        String maxPrice = maxValue.getText();
        String minPrice = minValue.getText();
        String category = categoryComboBox.getValue();
        if (!(cityField.getText().trim().isEmpty())){
            System.out.println("im here");
            System.out.println(cityField.getText().trim().isEmpty());
            app_Logic.DB.filter_by_city(city);
        }
       if (category != null){
          app_Logic.DB.filter_by_category(category);
        }
        if (!(maxValue.getText().trim().isEmpty()) & !(minValue.getText().trim().isEmpty())){
            int min = Integer.parseInt(minPrice);
            int max = Integer.parseInt(maxPrice);
            if (max > min){app_Logic.DB.filter_by_price(min, max);
                System.out.println("im doing filter by price");}
        }
        Parent parent = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("sample/style.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Main store");
        window.show();
        app_Logic.filteredProducts = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryComboBox.setItems(categoryComboBoxObservableList);
        minValue.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        maxValue.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        cityField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
    }
}
