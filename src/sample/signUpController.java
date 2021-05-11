package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUpController implements Initializable {

    @FXML
    private TextField userTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Label warningLabel;

    @FXML
    private Label invalidEmailLabel;

    @FXML
    private Label mismatchedPasswordLabel;

    @FXML
    private Label userExistsLabel;

    @FXML
    private ComboBox<String> entityComboBox;

    @FXML
    private Button registerButton;

    @FXML
    private Button returnButton;

    DB_connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet resultSet;

    ObservableList<String> entitiesObservableList = FXCollections.observableArrayList("Private", "Supermarket");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        entityComboBox.setItems(entitiesObservableList);
        entityComboBox.getSelectionModel().selectFirst();
    }

    public void RegisterButton(ActionEvent event) throws IOException
    {
        String user = userTextField.getText();
        String email = emailTextField.getText();
        String entity = entityComboBox.getValue();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);


        if(user.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
        {
            warningLabel.setText("Remember to fill all the fields");
        }

        if(!matcher.matches())
        {
            invalidEmailLabel.setText("Invalid email");
        }

        if(!password.equals(confirmPassword))
        {
            mismatchedPasswordLabel.setText("Passwords must match");

        }

        if(checkUserExists())
        {
            warningLabel.setText("");
            userExistsLabel.setText("User already exists");
        }

        if(!user.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && matcher.matches() && password.equals(confirmPassword) && !checkUserExists())
        {
            insertUser();
            Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.setTitle("Login");
            window.show();
        }

    }

    public boolean checkUserExists()
    {
        boolean userExists = false;
        try {
            connection = new DB_connection(new FoodieConnection());
            connection.connect();
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT count(*) FROM users WHERE user_name = '"+ userTextField.getText() +"' AND email = '"+ emailTextField.getText() +"'");


            while(resultSet.next())
            {
                if(resultSet.getInt(1) == 1)
                {
                    userExists = true;
                }
            }

        }catch (SQLException exception)
        {
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }

        return userExists;

    }

    private void insertUser()
    {
        try {

            connection = new DB_connection(new FoodieConnection());
            connection.connect();
            String query = "INSERT INTO `users` (`user_name`, `entity`, `password`, `email`) VALUES(?,?,?,?)";
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, userTextField.getText());
            ps.setString(2, entityComboBox.getValue());
            ps.setString(3, passwordTextField.getText());
            ps.setString(4, emailTextField.getText());
            ps.execute();


        } catch (SQLException exception) {
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }
    }

    public void returnButtonOnAction(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.setTitle("Login");
        window.show();
    }
}
