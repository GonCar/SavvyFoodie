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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class filterOptionsController implements Initializable {
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private TextField cityField;
    @FXML private TextField maxSlider;
    @FXML private TextField minSlider;
    ObservableList<String> categoryComboBoxObservableList = FXCollections.observableArrayList("Fruits", "Vegetables");

    public void returnButtonOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Main store");
        window.show();
    }

    public void filterProductsButtonOnAction(ActionEvent actionEvent) {
        String city = cityField.getText();
        int maxPrice = Integer.parseInt(maxSlider.getText());
        int minPrice = Integer.parseInt(minSlider.getText());
        String category = categoryComboBox.getValue();
        if (city != null){
            app_Logic.DB.filter_by_city(city);
        }
        if (category != null){
            app_Logic.DB.filter_by_category(category);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryComboBox.setItems(categoryComboBoxObservableList);
    }
}
