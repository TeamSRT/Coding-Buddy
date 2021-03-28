package Login;

import Main.DateAndTime;
import Main.Home;
import Main.Utility;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainGUIController implements Initializable {

    Database d = new Database();

    @FXML
    private AnchorPane anchorLogin;
    @FXML
    private Button sign_In_Btn;
    @FXML
    private JFXButton sign_Up_Btn;
    @FXML
    private JFXButton Forgetpassword_btn;
    @FXML
    private TextField tfLoginUsername;
    @FXML
    private JFXPasswordField tfLoginPassword;
    @FXML
    private JFXCheckBox PasswordShow_btn;
    @FXML
    private JFXTextField passShow_tf;

    public static String password;
    public static String username;
    @FXML
    private JFXCheckBox rememberMe_checkBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Home.pre = Preferences.userNodeForPackage(MainGUIController.class);

        if (Home.pre != null) {
            if (Home.pre.get("username", null) != null && !Home.pre.get("password", null).isEmpty()) {
                tfLoginUsername.setText(Home.pre.get("username", null));
                tfLoginPassword.setText(Home.pre.get("password",null));
                passShow_tf.setText(Home.pre.get("password",null));
           
            }
        }
    }

    @FXML
    private void sign_in_btn_Action(ActionEvent event) throws IOException {

        boolean data_match = false;    //Checking if database has this data

        if (!PasswordShow_btn.isSelected()) //show password button is not select
        {
            password = tfLoginPassword.getText();   //ball sign textfield is working
        } else {
            password = passShow_tf.getText();  //password show textfield is working
        }

        username = tfLoginUsername.getText();

        boolean empty; //checking is textfiels is empty

        Exception e = new Exception();

        empty = e.SignInException(username, password);

        if (empty == true) {

            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Username And Password can not be kept blank ");
            a.show();

        } else {

            data_match = d.getData(username, password);  //checking that database has this inputed data
        }

        if (data_match == true) {
            
            
            
            if (rememberMe_checkBtn.isSelected()) //remember me working
            {
                Home.pre.put("username", tfLoginUsername.getText());
                Home.pre.put("password", tfLoginPassword.getText());
                if(tfLoginPassword.getText().equals(""))
                    Home.pre.put("password", passShow_tf.getText());
                //System.out.println(tfLoginPassword.getText());
                
            } else {
                Home.pre.put("username", "");
                Home.pre.put("password", "");
            }
            Utility.setUsername(tfLoginUsername.getText());
            Utility.initUser();           
            Parent root = FXMLLoader.load(getClass().getResource("/Main/Home.fxml"));
            Scene src = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(src);
            s.show();
        }

    }

    
    @FXML
    private void sign_Up_Btn_Action(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();
    }

    @FXML
    private void Forgetpassword_btn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("passUpdate.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();

    }

    @FXML
    private void PasswordShow_btn(ActionEvent event) //Password show or hide
    {

        if (PasswordShow_btn.isSelected()) {

            passShow_tf.setVisible(true);

            passShow_tf.setText(tfLoginPassword.getText());

            tfLoginPassword.setVisible(false);

        } else if (!(PasswordShow_btn.isSelected())) {

            tfLoginPassword.setText(passShow_tf.getText());

            passShow_tf.setVisible(false);

            tfLoginPassword.setVisible(true);
        }

    }

    @FXML
    private void rememberMe_checkBtn(ActionEvent event) {

    }

}
