package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.*;
import project_gui.App;

public class ViewAdviseesController implements Initializable {

    @FXML
    private Label lbl_name;
    @FXML
    private Label lbl_num_advisees;

    private FACADE facade;
    private Advisor advisor;

    @FXML
    void btnAddAdviseeClicked(MouseEvent event) {

    }

    @FXML
    void btnLogoutClicked(MouseEvent event) throws IOException {
        facade.logout();
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FACADE.getInstance();
        advisor = facade.getAdvisor();
        lbl_name.setText(advisor.getFirstName() + " " + advisor.getLastName());
    }

}