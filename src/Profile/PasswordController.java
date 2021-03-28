/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile;

import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class PasswordController implements Initializable {

    @FXML
    private JFXPasswordField passCurr;
    @FXML
    private JFXPasswordField passNew;
    @FXML
    private JFXPasswordField passConfirm;
    @FXML
    private Label lblStrength;
    @FXML
    private Button btnChange;
    
    int pStrength = 0;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void passNewOnTyped(KeyEvent event) {
        int pSymbol = 0, pCaps = 0, pSmall = 0, pNum = 0;
        pStrength = 0;
        String pass = passNew.getText();
        for (int i = 0; i < pass.length(); ++i) {
            if (pass.charAt(i) >= '0' && pass.charAt(i) <= '9') {
                pNum = 1;
            } else if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z') {
                pCaps = 1;
            } else if (pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z') {
                pSmall = 1;
            } else {
                pSymbol = 1;
            }
        }
        pStrength = pNum + pCaps + pSmall + pSymbol;
        if (pass.length() <= 7) {
            lblStrength.setText("Passowrd must contain more than 7 character.");
            lblStrength.setTextFill(Color.RED);
        } else if (pStrength < 2) {
            lblStrength.setText("Weak Password");
            lblStrength.setTextFill(Color.RED);
        } else if (pStrength < 3) {
            lblStrength.setText("Medium Password");
            lblStrength.setTextFill(Color.BLUEVIOLET);
        } else {
            lblStrength.setText("Strong Password");
            lblStrength.setTextFill(Color.BLUE);
        }
    }

    @FXML
    private void btnChangeOnAction(ActionEvent event) throws SQLException, IOException {
        int isCorrect = 0;
        boolean changeWindow = false;
        Statement st = Main.Utility.conn.createStatement();
        String query = "SELECT COUNT(password) FROM userinfo WHERE username = '" + Main.Utility.username + "' AND password = '" + passCurr.getText() + "'";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            isCorrect = rs.getInt(1);
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        System.out.println("IS" + isCorrect);
        if(passNew.getText().equals("")) {
            alert.setContentText("New Password Can't be empty.");
        }
        else if(isCorrect < 1) {
            alert.setContentText("Current Password is not Correct.");
            
        }
        else if(pStrength < 3 || passNew.getText().length() <= 7) {
            alert.setContentText("Password isn't strong enough.");
        }
        else if(!passNew.getText().equals(passConfirm.getText())) {
            alert.setContentText("New Password doesn't match with Confirm Passowrd.");
        }
        else {
            String queryU = "UPDATE userinfo SET password = '" + passNew.getText() + "' WHERE username = '" + Main.Utility.username + "'";
            st.executeUpdate(queryU);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Password Changed.");
            changeWindow = true;
        }
        alert.show();
        if(changeWindow) {
            new Main.Utility().loadPane("/Profile/Profile.fxml");
        }
    }

    @FXML
    private void btnBackOnAction(ActionEvent event) throws IOException {
        new Main.Utility().loadPane("/Profile/Profile.fxml");
    }

}
