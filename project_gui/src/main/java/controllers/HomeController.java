package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;
import project_gui.App;

public class HomeController implements Initializable {

    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_username;
    @FXML
    private Label lbl_error;

    private User user;
    private UserList userList = UserList.getInstance();

    @FXML
    void btnSignInClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        FACADE facade = FACADE.getInstance();

        if (!facade.login(username)) {
            lbl_error.setText("Invalid login credentials");
            return;
        }

        user = userList.getUser(username);
        if (user instanceof Student)
            App.setRoot("studentlanding");
        else if (user instanceof Advisor)
            App.setRoot("advisorlanding");

        // App.setRoot("studentlanding");
    }

    @FXML
    void btnSignUpClicked(MouseEvent event) throws IOException {
        App.setRoot("signup");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
// test