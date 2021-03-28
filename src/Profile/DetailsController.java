/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class DetailsController implements Initializable {

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtOcc;
    @FXML
    private JFXTextField txtOrg;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnBack;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtName.setText(Main.Utility.name);
        txtOcc.setText(Main.Utility.occupation);
        txtOrg.setText(Main.Utility.org);
    }    

    @FXML
    private void btnChangeOnAction(ActionEvent event) throws SQLException, IOException {
        boolean notValid = false, changeWindow = false;
        String name = txtName.getText().replaceAll(" ", "");
        for(int i = 0; i < name.length(); ++i) {
            char c = txtName.getText().charAt(i);
            if(!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                notValid = true;
                break;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Edit Profile!");
        if(notValid || name.length() == 0) {
            alert.setContentText("Invalid Name Format");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Details Succesfully Changed!");
            changeWindow = true;
            Statement st = Main.Utility.conn.createStatement();
            st.executeUpdate("UPDATE userinfo SET name = '" + txtName.getText() + "', occupation = '" + txtOcc.getText() + "', organization = '" + txtOrg.getText() + "' WHERE username = '" + Main.Utility.username + "'");
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
