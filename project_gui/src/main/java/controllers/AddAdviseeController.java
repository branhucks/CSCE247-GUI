package controllers;

import java.io.IOError;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.FACADE;
import project_gui.App;

public class AddAdviseeController {

    @FXML // fx:id="err_lbl"
    private Label lbl_error; // Value injected by FXMLLoader

    @FXML // fx:id="student_id"
    private TextField student_id; // Value injected by FXMLLoader

    @FXML
    void btnAddAdvisee(MouseEvent event) throws IOException {
        String studentId = student_id.getText();
        FACADE facade = FACADE.getInstance();
        if (!facade.addAdvisee(studentId)) {
            lbl_error.setText("Invalid Student ID.");
        } else {
            App.setRoot("advisorlanding");
        }

    }

}
