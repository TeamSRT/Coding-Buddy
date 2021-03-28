/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Main.Utility;
import Problemset.Submission;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Srishti
 */

 
public class ShowSubmissionController implements Initializable {

    @FXML
    private Button btnBack;
    public static Submission sub;
    @FXML
    private TextArea taSourceCode;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCode();
    }    
    public void showCode()
    {
       taSourceCode.setText(sub.code);
    }
    @FXML
    private void btnCustomBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/submission.fxml");
    }
    
}
