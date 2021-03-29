/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SaMiR
 */
public class Update_pass_otpController implements Initializable {

    public static int UpdatePassSent_otp;
    public static String user;
    public static String Updatedpassword;
    Database d = new Database();
    @FXML
    private JFXButton PassUpdateVarify_btn;
    @FXML
    private Label NotMatch_lbl;
    @FXML
    private JFXTextField OtpPassUpdate_tf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void PassUpdateVarify_btn(ActionEvent event) throws IOException, SQLException {
        String OtpNotMatch_lbl = "Not matched!";
        String tf_get_otp = OtpPassUpdate_tf.getText();//inputed varification code
        String final_sent_otp = String.valueOf(UpdatePassSent_otp); //int to string conversion                                                                  //random sent otp
        if (!(tf_get_otp.isEmpty())) {
            if (tf_get_otp.equals(final_sent_otp)) {
                d.forgot_password_update();     //Updating forget passoword
                Parent root = FXMLLoader.load(getClass().getResource("mainGUI.fxml"));
                Scene src = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(src);
                s.show();
            } else {
                NotMatch_lbl.setText(OtpNotMatch_lbl);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Fillup the Varification Textfield");
            a.show();
        }
    }

}
