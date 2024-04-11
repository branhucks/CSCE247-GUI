package controllers;

import java.io.IOError;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.FACADE;
import project_gui.App;

public class MakeNoteController {

    @FXML // fx:id="student_id"
    private TextField txt_note; // Value injected by FXMLLoader

    @FXML
    void btnSendNote(MouseEvent event) throws IOException {
        String note = txt_note.getText();
        FACADE facade = FACADE.getInstance();
        facade.makeNote(facade.getStudent().getStudentID(), note);
        App.setRoot("advisorcompleted");
    }

}
