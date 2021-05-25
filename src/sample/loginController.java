package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.io.IOException;

public class loginController implements Initializable {
    public AnchorPane anchorPane;
    PreparedStatement ps;
    ResultSet resultSet;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Label invalidLoginLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invalidLoginLabel.setText("");
        invalidLoginLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(invalidLoginLabel, 0.0);
        AnchorPane.setRightAnchor(invalidLoginLabel, 0.0);
        invalidLoginLabel.setAlignment(Pos.CENTER);
    }

    /**Sign the user up, change view to signUp view*/
    public void signUpButtonOnAction(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        tableViewScene.getStylesheets().add("sample/style.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setTitle("Sign Up");
        window.show();
    }

    /**Log the user in after validating logging in*/
    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            if(checkLogin(usernameTextField.getText(), passwordTextField.getText())) {
                app_Logic.current_user_id = app_Logic.DB.getUserId(usernameTextField.getText());
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("table.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                tableViewScene.getStylesheets().add("sample/style.css");
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.setTitle("Main store");
                window.show();
            }
        }else {
            invalidLoginLabel.setText("You must enter both username and password");
        }
    }

    /**Check if the users fields matches the username and password from he database*/
    public boolean checkLogin(String username, String password) {
        boolean userExists = false;
        try {
            ps = app_Logic.connection.prepareStatement( "SELECT count(*) FROM users WHERE user_name = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt(1) == 1) {
                    invalidLoginLabel.setText("You're in");
                    userExists = true;
                }else {
                    invalidLoginLabel.setText("User not found");
                }
            }
        }catch (SQLException exception) {
            System.out.println("Query failed to execute :( ");
            exception.printStackTrace();
        }
        return userExists;
    }
}
