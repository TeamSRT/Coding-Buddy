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
import javafx.scene.control.Label;
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
    JFXTextField tf_Signup_Email;
    @FXML
    private JFXTextField tf_Signup_Occupation;
    @FXML
    private JFXTextField tf_Signup_Name;
    @FXML
    private JFXPasswordField tf_Signup_Confirmpassword;
    @FXML
    private Label passwrd_matching;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ConfirmSignup_btn(ActionEvent event) throws IOException {

        boolean empty;
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.INFORMATION);

        String Name = tf_Signup_Name.getText();
        String Username = tf_Signup_Username.getText();
        String email = tf_Signup_Email.getText();
        String Password = tf_Signup_Password.getText();
        String Confirmpassword = tf_Signup_Confirmpassword.getText();
        String Occupation = tf_Signup_Occupation.getText();

        //exception
        Exception e = new Exception();
       
        empty = e.SignUpException(Name, Username, email, Password, Confirmpassword);

        if (empty == true) {

            a.setContentText("All fields with Star sign are must be filled"); //Exception
            a.show();

        } else if (empty == false) {

            boolean invalid = false;

            invalid = e.signup_SpecharacterException(Name, Username, email, Occupation);

            System.out.println("invalid checked");

            if (invalid == false) {

                if (Password.equals(Confirmpassword)) {
                    
                    passwrd_matching.setText(null);
                   
                    boolean data_same = false;

                    data_same = d.check_same_data(Username, email);
                    if (data_same == true) {

                        a.setAlertType(AlertType.INFORMATION);
                        a.setContentText("This Username or Email has been already used");
                        a.show();
                    } else {

                        EController.Name = Name;
                        EController.Username = Username;
                        EController.email = email;
                        EController.Password = Password;
                        EController.Confirmpassword = Confirmpassword;
                        EController.Occupation = Occupation;
                        //double rand_double = Math.random();
                        int rand = (int) (Math.random() * 4000);
                        EController.sent_otp = rand;

                        //boolean emailSent_or_not = false;

                       Email.send("samirsarker055@gmail.com", "Samir1234", email, "Emai Varification", rand);

                       // if (emailSent_or_not == true) {
                            Parent root = FXMLLoader.load(getClass().getResource("email.fxml"));
                            Scene src = new Scene(root);
                            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            s.setScene(src);
                            s.show();
                      //  }
                    /*else {
                            a.setAlertType(AlertType.INFORMATION);
                            a.setContentText("Invalid Email Address");
                            a.show();
                        }*/
                        /*  tf_Signup_Name.setText(null);
                    tf_Signup_Username.setText(null);
     tf_Signup_Email.setText(null);
     tf_Signup_Password.setText(null);
     tf_Signup_Confirmpassword.setText(null);
     tf_Signup_Occupation.setText(null);*/

                        //if(data_taken==true)
                        //{
                        //int rand= (int) Math.random()+1000;
                        //EController.sent_otp=rand;
                    }
                } else {

                    String pass_match = "Not Matched with Confirm Password  !";
                    passwrd_matching.setText(pass_match);

                }

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
