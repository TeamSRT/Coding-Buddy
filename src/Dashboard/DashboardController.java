/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class DashboardController implements Initializable {

    @FXML
    private ImageView imgUser;
    @FXML
    private Button btnChangePic;
    @FXML
    private Label lblName;
    @FXML
    private Label lblUsername;
    @FXML
    private Label ilbMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnChangePicOnAction(ActionEvent event) {
        
    }
    
}
