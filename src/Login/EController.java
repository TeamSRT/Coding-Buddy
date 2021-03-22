package Login;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;

public class EController implements Initializable {

    public static int sent_otp;
    Database d = new Database();

    public static String Name;
    public static String Username;
    public static String email;
    public static String Password;
    public static String Confirmpassword;
    public static String Occupation;

    @FXML
    private JFXTextField tf_OTP;
    @FXML
    private JFXButton valid_btn;
    @FXML
    private JFXButton Back_btn;
    @FXML
    private Label otp_check_show;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void valid_btn(ActionEvent event) throws IOException {

        String lebel = "Not matched!";

        String tf_get_otp = tf_OTP.getText();

        String final_sent_otp = String.valueOf(sent_otp); //int to string conversion

        if (!(tf_get_otp.isEmpty())) {
            if (tf_get_otp.equals(final_sent_otp)) {

                
                d.setData(Name, Username, email, Password, Confirmpassword, Occupation);                
                Parent root = FXMLLoader.load(getClass().getResource("confirm_var_email.fxml"));
                Scene src = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(src);
                s.show();
            } else {
                otp_check_show.setText(lebel);

            }

        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);

            //set content text 
            a.setContentText("Fillup the Textfield");

            //show the dialog 
            a.show();
        }
    }

    @FXML
    private void back_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();

    }

    @FXML
    private void tf_OTP(ActionEvent event) {
    }

}
