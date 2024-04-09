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

public class SignupController implements Initializable {

    @FXML
    private TextField stu_first_name;
    @FXML
    private TextField stu_last_name;
    @FXML
    private TextField stu_username;
    @FXML
    private TextField stu_password;
    @FXML
    private TextField stu_major;
    @FXML
    private TextField stu_classification;
    @FXML
    private Label stu_lbl_error;

    @FXML
    private TextField adv_first_name;
    @FXML
    private TextField adv_last_name;
    @FXML
    private TextField adv_username;
    @FXML
    private TextField adv_password;
    @FXML
    private TextField adv_department;
    @FXML
    private Label adv_lbl_error;

    private User user;
    private UserList userList = UserList.getInstance();
    FACADE facade = FACADE.getInstance();

    @FXML
    void btnStuSignup(MouseEvent event) throws IOException {
        String firstName = stu_first_name.getText();
        String lastName = stu_last_name.getText();
        String username = stu_username.getText();
        String major = stu_major.getText();
        String classification = stu_classification.getText();

        if (firstName == "" || lastName == "" || username == "" || major == "" || classification == "") {
            stu_lbl_error.setText("Invalid Credentials.");
            return;
        }

        if (facade.registerStudent(username, firstName, lastName, "Student", major, classification)) {
            facade.saveUsers();
            if (facade.login(username)) {
                user = facade.getUser();
                App.setRoot("studentlanding");
            }
        }
    }

    @FXML
    void btnAdvSignup(MouseEvent event) throws IOException {
        String firstName = adv_first_name.getText();
        String lastName = adv_last_name.getText();
        String username = adv_username.getText();
        String department = adv_department.getText();

        if (firstName == "" || lastName == "" || username == "" || department == "") {
            adv_lbl_error.setText("Invalid Credentials.");
            return;
        }

        if (facade.registerAdvisor(username, firstName, lastName, "Advisor", department)) {
            facade.saveUsers();
            if (facade.login(username)) {
                user = facade.getUser();
                App.setRoot("advisorlanding");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
