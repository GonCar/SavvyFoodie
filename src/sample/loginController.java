package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label invalidLoginLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    DB_connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet resultSet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        invalidLoginLabel.setText("");
        invalidLoginLabel.setMaxWidth(Double.MAX_VALUE);
        anchorPane.setLeftAnchor(invalidLoginLabel, 0.0);
        anchorPane.setRightAnchor(invalidLoginLabel, 0.0);
        invalidLoginLabel.setAlignment(Pos.CENTER);
    }

    public void signUpButtonOnAction(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.setTitle("Sign Up");
        window.show();
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank())
        {
            if(checkLogin())
            {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("table.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.setTitle("Main store");
                window.show();
            }


        }else
        {
            invalidLoginLabel.setText("You must enter both username and password");
        }
    }

    public boolean checkLogin()
    {
        boolean userExists = false;
        try {
            connection = new DB_connection(new FoodieConnection());
            connection.connect();
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT count(*) FROM users WHERE user_name = '"+ usernameTextField.getText() +"' AND password = '"+ passwordTextField.getText() +"'");


            while(resultSet.next())
            {
                if(resultSet.getInt(1) == 1)
                {
                    invalidLoginLabel.setText("You're in");

//
//                    ps = connection.prepareStatement("SELECT user_id FROM users WHERE user_name=?");
//                    ps.setString(1, usernameTextField.getText());
//                    resultSet = ps.executeQuery();
//                   app_Logic.current_user_id = resultSet.getInt("user_id");
                    userExists = true;

                }else
                {
                    invalidLoginLabel.setText("User not found");
                }
            }

        }catch (SQLException exception)
        {
            System.out.println("Query failed to execute");
            exception.printStackTrace();
        }

        return userExists;

    }
}
