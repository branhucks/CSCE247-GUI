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

public class StudentIncompleteController implements Initializable {

    @FXML
    private Label lbl_application_area;
    @FXML
    private Label lbl_class_header;
    @FXML
    private Label lbl_class_info;
    @FXML
    private Label lbl_college;
    @FXML
    private Label lbl_id;
    @FXML
    private Label lbl_major_gpa;
    @FXML
    private Label lbl_major_header;
    @FXML
    private Label lbl_major_info;
    @FXML
    private Label lbl_name_header;
    @FXML
    private Label lbl_name_info;
    @FXML
    private Label lbl_overall_gpa;
    @FXML
    private Label lbl_incomplete_courses;

    private FACADE facade;
    private Student student;

    @FXML
    void btnCompleted(MouseEvent event) throws IOException {
        App.setRoot("studentcompleted");
    }

    @FXML
    void btnElectives(MouseEvent event) throws IOException {
        App.setRoot("studentelectives");
    }

    @FXML
    void btnApplicationArea(MouseEvent event) throws Exception {
        App.setRoot("studentapplicationarea");
    }

    @FXML
    void btnChooseElectives(MouseEvent event) throws Exception {
        App.setRoot("chooseelectives");
    }

    @FXML
    void btnChooseApplicationArea(MouseEvent event) throws Exception {

    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        App.setRoot("studentlanding");
    }

    @FXML
    void btnLogoutClicked(MouseEvent event) throws IOException {
        facade.logout();
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FACADE.getInstance();
        student = facade.getStudent();
        lbl_name_header.setText(student.getFirstName() + " " + student.getLastName());
        lbl_major_header.setText("Major: " + facade.getMajorByUUID(student.getMajor()).getMajorName());
        lbl_class_header.setText("Classification: " + student.getClassYear());
        lbl_name_info.setText(student.getFirstName() + " " + student.getLastName());
        lbl_id.setText(student.getStudentID());
        lbl_class_info.setText(student.getClassYear());
        lbl_overall_gpa.setText("3.63");
        lbl_college.setText("College of Engr and Computing");
        lbl_major_info.setText(facade.getMajorByUUID(student.getMajor()).getMajorName());
        lbl_application_area.setText(student.getApplicationType().toString());
        lbl_major_gpa.setText("3.38");
        lbl_incomplete_courses.setText(facade.printIncompleteCourses());
    }

}
