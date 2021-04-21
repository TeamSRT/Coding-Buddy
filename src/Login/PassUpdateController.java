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
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SaMiR
 */
public class PassUpdateController implements Initializable {

    @FXML
    private JFXButton changePass;
    @FXML
    private JFXTextField tf_username;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXPasswordField tf_updatedPass;
    @FXML
    private JFXPasswordField tf_confUpdatedpass;
    @FXML
    private JFXButton back_btn;
    @FXML
    private Label pass_matching_lebel;
    boolean pass_length_checker = false;
    int pass_strength_checke;
    @FXML
    private Label Passwordlength_lbl;
    @FXML
    private Label weak_lbl;
    @FXML
    private Label mid_lbl;
    @FXML
    private Label strong_lbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void changePass(ActionEvent event) throws IOException {
        boolean check_match;
        Database d = new Database();
        String user = tf_username.getText();
        String email = tf_email.getText();
        String updatedpassword = tf_updatedPass.getText();
        String confirmpass = tf_confUpdatedpass.getText();
        Exception e = new Exception();
        boolean empty;
        empty = e.forgotpasswordException(user, updatedpassword, email, confirmpass); //checking if the textfield is empty
        if (empty == true) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("All fields must be filled ");
            a.show();

        } else if (pass_length_checker == true && pass_strength_checke >= 2) {
            if (updatedpassword.equals(confirmpass)) {
                if (!(updatedpassword.equals(tf_username.getText()))) {
                    pass_matching_lebel.setText(null);
                    {
                        check_match = d.forgotpassword_data_matching_check(user, updatedpassword, email);
                        if (check_match == true) {
                            Email email_obj = new Email();
                            int rand = (int) (Math.random() * 40000); //generating varification code
                            //EController.sent_otp = rand;
                            Update_pass_otpController.UpdatePassSent_otp = rand;
                            email_obj.send("YOUR EMAIL", "PASSWORD", email, "Request To Change Password", rand);
                            Parent root = FXMLLoader.load(getClass().getResource("update_pass_otp.fxml"));
                            Scene src = new Scene(root);
                            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            s.setScene(src);
                            s.show();
                        }
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Username can not be used as Password ");
                    a.show();
                }
            } else {
                String lebel_text = "Not Matched with Confirm Password";
                pass_matching_lebel.setText(lebel_text);
            }
        }
    }

    @FXML
    private void tfPasswordOnTyped(KeyEvent event) {
        //System.out.println("working");
        pass_matching_lebel.setText(null);
        Pass_Strength p = new Pass_Strength();
        pass_length_checker = p.pass_length_check(tf_updatedPass.getText());
        if (pass_length_checker == true) {
            Passwordlength_lbl.setVisible(false);
            pass_strength_checke = p.pass_strength_check(tf_updatedPass.getText());
            if (pass_strength_checke == 1) {
                //System.out.println("1");
                weak_lbl.setVisible(true);
                mid_lbl.setVisible(false);
                strong_lbl.setVisible(false);
            } else if (pass_strength_checke == 2) {
                //System.out.println("2");
                mid_lbl.setVisible(true);
                weak_lbl.setVisible(false);
                strong_lbl.setVisible(false);
            } else {
                //System.out.println("3");
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
    private void back_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainGUI.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();
    }

}
