package Login;

import Main.DateAndTime;
import Main.Utility;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void sign_in_btn_Action(ActionEvent event) throws IOException {
        boolean data_match = false;

        String username = tfLoginUsername.getText();
        String password = tfLoginPassword.getText();

        boolean empty;

        Exception e = new Exception();

        empty = e.SignInException(username, password);

        if (empty == true) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);

            //set content text 
            a.setContentText("Username And Password can not be kept blank ");

            //show the dialog 
            a.show();

        } else {

            data_match = d.getData(username, password);
        }

        if (data_match == true) {            
            Utility.setUsername(tfLoginUsername.getText());
            Utility.initUser();
            DateAndTime obj = new DateAndTime();
            try {
                obj.connection();              
            } catch (SQLException ex) {
                System.out.println("Login Connection failed");
            }
            try {
                obj.writeLoginTime();
            } catch (SQLException ex) {
                System.out.println("Login writing failed");
            }
            obj.readLoginTime();            
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
}
