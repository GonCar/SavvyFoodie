package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
        primaryStage.setTitle("Main store");
        primaryStage.setScene(new Scene(root, 900, 750));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

        /*
        DB_connection connection = new DB_connection();
        connection.connect();
        connection.showProducts();

         */
    }
}
