package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class filterOptionsController {

    public void locationButtonOnAction(ActionEvent actionEvent) {
    }

    public void priceButtonOnAction(ActionEvent actionEvent) {
    }

    public void categoryButtonOnAction(ActionEvent actionEvent) {
    }

    public void returnButtonOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Main store");
        window.show();
    }

    public void filterProductsButtonOnAction(ActionEvent actionEvent) {

    }
}