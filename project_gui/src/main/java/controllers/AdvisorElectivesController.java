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

public class AdvisorElectivesController implements Initializable {

    @FXML
    private Label lbl_application_area;
    @FXML
    private Label lbl_class_info;
    @FXML
    private Label lbl_college;
    @FXML
    private Label lbl_id;
    @FXML
    private Label lbl_major_gpa;
    @FXML
    private Label lbl_major_info;
    @FXML
    private Label lbl_name;
    @FXML
    private Label lbl_name_info;
    @FXML
    private Label lbl_overall_gpa;
    @FXML
    private Label lbl_elective_progress;

    private FACADE facade;
    private Student student;
    private Advisor advisor;

    @FXML
    void btnCompleted(MouseEvent event) throws IOException {
        App.setRoot("advisorcompleted");
    }

    @FXML
    void btnIncomplete(MouseEvent event) throws IOException {
        App.setRoot("advisorincomplete");
    }

    @FXML
    void btnApplicationArea(MouseEvent event) throws IOException {
        App.setRoot("advisorapplicationarea");
    }

    @FXML
    void btnMakeNote(MouseEvent event) throws IOException {
        App.setRoot("makenote");
    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        App.setRoot("advisorlanding");
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
        if (facade.login("thill")) {
            student = facade.getStudent();
        }
        lbl_name.setText(advisor.getFirstName() + " " + advisor.getLastName());
        lbl_name_info.setText(student.getFirstName() + " " + student.getLastName());
        lbl_id.setText(student.getStudentID());
        lbl_class_info.setText(student.getClassYear());
        lbl_overall_gpa.setText("3.72");
        lbl_college.setText("College of Engr and Computing");
        lbl_major_info.setText(facade.getMajorByUUID(student.getMajor()).getMajorName());
        lbl_application_area.setText(student.getApplicationType().toString());
        lbl_major_gpa.setText("3.67");
        lbl_elective_progress.setText(facade.printElectiveProgress());
    }

}
