/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SaMiR
 */
public class SignupController implements Initializable {

    Database d = new Database();

    @FXML
    private AnchorPane AnchorSignup;
    @FXML
    private JFXButton Back_To_Login_btn;
    @FXML
    private JFXButton ConfirmSignup_btn;
    @FXML
    private JFXPasswordField tf_Signup_Password;
    @FXML
    private JFXTextField tf_Signup_Username;
    @FXML
    private JFXTextField tf_Signup_Email;
    @FXML
    private JFXTextField tf_Signup_Occupation;
    @FXML
    private JFXTextField tf_Signup_Name;
    @FXML
    private JFXPasswordField tf_Signup_Confirmpassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ConfirmSignup_btn(ActionEvent event) {

        boolean check;
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.INFORMATION);

        String Name = tf_Signup_Name.getText();
        String Username = tf_Signup_Username.getText();
        String Email = tf_Signup_Email.getText();
        String Password = tf_Signup_Password.getText();
        String Confirmpassword = tf_Signup_Confirmpassword.getText();
        String Occupation = tf_Signup_Occupation.getText();

        //exception
        Exception e = new Exception();
        check = e.SignUpException(Name, Username, Email, Password, Confirmpassword);

        if (check == true) {

            a.setContentText("All fields with Star sign are must be filled"); //Exception
            a.show();

        } else if (check == false) {
            if (Password.equals(Confirmpassword)) {
                d.setData(Name, Username, Email, Password, Confirmpassword, Occupation);
            } else {

                a.setContentText("Password has not matched with Confirm Password"); //Exception
                a.show();
            }
        }

    }

    @FXML
    private void Back_To_Login_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainGUI.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();

    }

}
