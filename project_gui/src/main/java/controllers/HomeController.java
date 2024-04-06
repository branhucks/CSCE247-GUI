package controllers;

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

    @FXML
    void btnSignInClicked(MouseEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText();

        FACADE facade = FACADE.getInstance();

        if (!facade.login(username)) {
            lbl_error.setText("Invalid login credentials");
            return;
        }

        /**
         * TODO
         * App.setRoot("StudentLanding");
         */
    }

    @FXML
    void btnSignUpClicked(MouseEvent event) {
        // App.setRoot("SignupStudent");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
