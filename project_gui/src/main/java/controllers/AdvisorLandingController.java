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

public class AdvisorLandingController implements Initializable {

    @FXML
    private Label lbl_welcome;

    private FACADE facade;
    private Advisor advisor;

    @FXML
    void btnAddAdviseeClicked(MouseEvent event) throws IOException {
        App.setRoot("addadvisee");
    }

    @FXML
    void btnLogoutClicked(MouseEvent event) throws IOException {
        facade.logout();
        App.setRoot("home");
    }

    @FXML
    void btnViewAdviseesClicked(MouseEvent event) throws IOException {
        App.setRoot("viewadvisees");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FACADE.getInstance();
        advisor = facade.getAdvisor();
        lbl_welcome.setText("Welcome, " + advisor.getFirstName() + " " + advisor.getLastName());
    }

}
