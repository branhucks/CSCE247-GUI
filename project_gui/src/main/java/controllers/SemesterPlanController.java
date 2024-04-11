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

public class SemesterPlanController implements Initializable {

    @FXML
    private Label lbl_application_area;
    @FXML
    private Label lbl_classification;
    @FXML
    private Label lbl_college;
    @FXML
    private Label lbl_id;
    @FXML
    private Label lbl_major;
    @FXML
    private Label lbl_major_gpa;
    @FXML
    private Label lbl_name;
    @FXML
    private Label lbl_overall_gpa;
    @FXML
    private Label lbl_semester_plan;

    private FACADE facade;
    private Student student;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        App.setRoot("studentlanding");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FACADE.getInstance();
        student = facade.getStudent();
        lbl_name.setText(student.getFirstName() + " " + student.getLastName());
        lbl_id.setText(student.getStudentID());
        lbl_classification.setText(student.getClassYear());
        lbl_overall_gpa.setText("3.63");
        lbl_college.setText("College of Engr and Computing");
        lbl_major.setText(facade.getMajorByUUID(student.getMajor()).getMajorName());
        lbl_application_area.setText(student.getApplicationType().toString());
        lbl_major_gpa.setText("3.38");
        lbl_semester_plan.setText(facade.printSemesterPlan());
    }

}
