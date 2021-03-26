/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Main.Utility;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class SubmissionController implements Initializable {

    @FXML
    private Button btnRefresh;
    @FXML
    private FontAwesomeIconView iconRefresh;
    @FXML
    private Button btnBack;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Tooltip.install(btnRefresh, new Tooltip("Refresh this page"));
    }       

    @FXML
    private void btnCustomBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/Dashboard.fxml");
    }
    
}
