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

public class StudentLandingController implements Initializable {

    @FXML
    private Label lbl_class;
    @FXML
    private Label lbl_major;
    @FXML
    private Label lbl_note;
    @FXML
    private Label lbl_welcome;

    private FACADE facade;
    private Student student;

    @FXML
    void btnLogoutClicked(MouseEvent event) throws IOException {
        facade.logout();
        App.setRoot("home");
    }

    @FXML
    void btnPrintClicked(MouseEvent event) throws IOException {
        App.setRoot("viewsemesterplan");
    }

    @FXML
    void btnViewClicked(MouseEvent event) throws IOException {
        App.setRoot("studentcompleted");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FACADE.getInstance();
        student = facade.getStudent();
        lbl_welcome.setText("Welcome, " + student.getFirstName() + " " + student.getLastName());
        lbl_major.setText("Major: " + facade.getMajorByUUID(student.getMajor()).getMajorName());
        lbl_class.setText("Classification: " + student.getClassYear());
        lbl_note.setText(student.getNoteFromAdvisor());
    }

}
