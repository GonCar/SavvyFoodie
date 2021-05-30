package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import sample.model.app_Logic;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUpController implements Initializable {
    PreparedStatement ps;
    ResultSet resultSet;
    @FXML private TextField userTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField countryTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField confirmPasswordTextField;
    @FXML private Label warningLabel;
    @FXML private Label invalidEmailLabel;
    @FXML private Label mismatchedPasswordLabel;
    @FXML private Label userExistsLabel;
    @FXML private ComboBox<String> entityComboBox;
    ObservableList<String> entitiesObservableList = FXCollections.observableArrayList("Private", "Supermarket", "Store", "Restaurant");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entityComboBox.setItems(entitiesObservableList);
        entityComboBox.getSelectionModel().selectFirst();
        cityTextField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
        countryTextField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
    }

    /**When the user fills every thing and clicks to signup*/
    public void RegisterButton(ActionEvent event) throws IOException {
        String user = userTextField.getText();
        String email = emailTextField.getText();
        String entity = entityComboBox.getValue();
        String city = cityTextField.getText();
        String country = countryTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        warningLabel.setText("");
        invalidEmailLabel.setText("");
        mismatchedPasswordLabel.setText("");
        userExistsLabel.setText("");
        if(user.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            warningLabel.setText("Remember to fill all the fields");
        }
        if(!matcher.matches()) {
            invalidEmailLabel.setText("Invalid email");
        }
        if(!password.equals(confirmPassword)) {
            mismatchedPasswordLabel.setText("Passwords must match");
        }
        if(checkUserExists()) {
            warningLabel.setText("");
            userExistsLabel.setText("User already exists");
        }
        if(!user.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && matcher.matches() && password.equals(confirmPassword) && !checkUserExists()) {
            app_Logic.DB.insertUser(user, entity, password, city, country, email);
            returnButtonOnAction(event);
        }
    }

    /**Check if the username already exists*/
    public boolean checkUserExists() {
        boolean userExists = false;
        try {
            ps = app_Logic.connection.prepareStatement("SELECT count(*) FROM users WHERE user_name = '"+ userTextField.getText() +"' AND email = '"+ emailTextField.getText() +"'");
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt(1) == 1) {
                    userExists = true;
                }
            }
        }catch (SQLException exception) {
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }
        return userExists;
    }

    /**Change view to the main store*/
    public void returnButtonOnAction(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("model/login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        tableViewScene.getStylesheets().add("sample/view/style.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setTitle("Login");
        window.show();
    }
}
