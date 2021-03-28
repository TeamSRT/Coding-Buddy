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
public class ShowAllSubmissionCodeController implements Initializable {

    @FXML
    private TextArea taSubAllSourceCode;
    @FXML
    private Button btnBack;
    public static Submission sub;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showSubAllCode();
    }    
    public void showSubAllCode()
    {
       taSubAllSourceCode.setText(sub.code);
    }  
    @FXML
    private void btnBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/ShowAllSubmission.fxml");
    }
    
}
