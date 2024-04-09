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

    FACADE facade = FACADE.getInstance();
 
     @FXML // ResourceBundle that was given to the FXMLLoader
     private ResourceBundle resources;
 
     @FXML // URL location of the FXML file that was given to the FXMLLoader
     private URL location;
 
     @Override
     public void initialize(URL url, ResourceBundle rb) {

     }

     @FXML
     void btnStuSignup(MouseEvent event) throws IOException {
        String firstName = stu_first_name.getText();
        String lastName = stu_last_name.getText();
        String username = stu_username.getText();
        //String password = stu_password.getText();
        String major = stu_first_name.getText();
        String classification = stu_classification.getText();
        
        if(facade.registerStudent(username, firstName, lastName, "Student", major, classification)) {
            App.setRoot("studentlanding");
        } else{
            stu_lbl_error.setText("Invalid");
        }
     }

     @FXML
     void btnAdvSignup(MouseEvent event) throws IOException {
        String firstName = adv_first_name.getText();
        String lastName = adv_last_name.getText();
        String username = adv_username.getText();
        String department = adv_department.getText();
        
        if(facade.registerAdvisor(username, firstName, lastName, "Advisor", department)) {
            App.setRoot("advisorlanding");
        } else{
            adv_lbl_error.setText("Invalid");
        }
     }

     
 
 }
 // test
 