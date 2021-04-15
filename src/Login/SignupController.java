/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import static Login.EController.Confirmpassword;
import static Login.EController.Name;
import static Login.EController.Occupation;
import static Login.EController.Password;
import static Login.EController.Username;
import static Login.EController.email;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SaMiR
 */
public class SignupController implements Initializable {

    public static String email;
    Alert a = new Alert(AlertType.NONE);

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

    boolean pass_length_checker = false;

    int pass_strength_checke;

    @FXML
    private Label weak_lbl;
    @FXML
    private Label mid_lbl;
    @FXML
    private Label strong_lbl;
    @FXML
    private Label Passwordlength_lbl;

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
        email = tf_Signup_Email.getText();
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
                if (pass_length_checker == true && pass_strength_checke >= 2) {
                    if (Password.equals(Confirmpassword)) {
                        passwrd_matching.setText(null);
                        if (!(tf_Signup_Username.getText().equals(tf_Signup_Password.getText()))) {
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
                                //d.setData(Name, Username, email, Password, Confirmpassword, Occupation);
                                // d.setData(Name, Username, email, Password, Confirmpassword, Occupation);
                                // System.out.println(Name+Username+email+Password+Confirmpassword+Occupation);
                                //boolean emailSent_or_not = false;
                               Email.send("samirsarker055@gmail.com", "CodingBuddySamir@1", email, "Emai Verification", rand);
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
                            }                           //EController.sent_otp=rand;
                        } else {
                            a.setAlertType(AlertType.INFORMATION);
                            a.setContentText("Username can not be used as Password");
                            a.show();
                        }
                    } else {
                        String pass_match = "Does not Match with Password!";
                        passwrd_matching.setText(pass_match);
                    }
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
          s.setResizable(false);
        s.show();

    }

    @FXML
    private void tfPasswordOnTyped(KeyEvent event) {
        System.out.println("working");
        passwrd_matching.setText(null);
        Pass_Strength p = new Pass_Strength();
        pass_length_checker = p.pass_length_check(tf_Signup_Password.getText());
        if (pass_length_checker == true) {
            Passwordlength_lbl.setVisible(false);
            pass_strength_checke = p.pass_strength_check(tf_Signup_Password.getText());
            if (pass_strength_checke == 1) {
                System.out.println("1");
                weak_lbl.setStyle("-fx-background-color: white;");
                weak_lbl.setVisible(true);
                mid_lbl.setVisible(false);
                strong_lbl.setVisible(false);
            } else if (pass_strength_checke == 2) {
                System.out.println("2");
                mid_lbl.setStyle("-fx-background-color: white;");
                mid_lbl.setVisible(true);
                weak_lbl.setVisible(false);
                strong_lbl.setVisible(false);
            } else if (pass_strength_checke == 3) {
                System.out.println("3");
                strong_lbl.setStyle("-fx-background-color: white;");
                strong_lbl.setVisible(true);
                weak_lbl.setVisible(false);
                mid_lbl.setVisible(false);
            }
        } else {
            Passwordlength_lbl.setVisible(true);
            weak_lbl.setVisible(false);
            mid_lbl.setVisible(false);
            strong_lbl.setVisible(false);
        }
    }

    @FXML
    private void tf_Signup_Password(ActionEvent event) {
    }

    @FXML
    private void tf_Signup_Confirmpassword(ActionEvent event) {
    }

}
