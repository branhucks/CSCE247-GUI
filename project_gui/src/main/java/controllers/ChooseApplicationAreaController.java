package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;
import project_gui.App;

public class ChooseApplicationAreaController implements Initializable {

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
    private Label lbl_application_areas;
    @FXML
    private CheckBox box_digital_design;
    @FXML
    private CheckBox box_dd1;
    @FXML
    private CheckBox box_dd2;
    @FXML
    private CheckBox box_dd3;

    private FACADE facade;
    private Student student;

    @FXML
    void btnPlanCourses(MouseEvent event) throws IOException {
        facade = FACADE.getInstance();
        student = facade.getStudent();
        if (box_digital_design.isSelected() && box_dd1.isSelected() && box_dd2.isSelected() && box_dd3.isSelected()) {
            student.setApplicationType(ApplicationType.DigitalDesign);
            facade.addApplicationAreaCourses("ARTS", "102", "ARTS", "245", "ARTS", "246", 6, 7, 8);
            facade.saveUsers();
        } else {
            return;
        }
        App.setRoot("studentapplicationarea");
    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        App.setRoot("studentcompleted");
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
        lbl_application_areas.setText(facade.printMajorApplicationAreas(student.getMajor()));
    }

}
