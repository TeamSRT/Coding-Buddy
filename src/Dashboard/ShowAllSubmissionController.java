/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Main.Utility;
import Problemset.Problem;
import Problemset.ProblemSQL;
import Problemset.Submission;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class ShowAllSubmissionController implements Initializable {

    @FXML
    private VBox vbShowAllSub;
    @FXML
    private Button btnBack;
    ArrayList<Submission> showAllSub;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadShowAllSub();
    }    
    
    public void loadShowAllSub()
    {
       
    }
    @FXML
    private void btnCustomBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/submission.fxml");
    }
    
}
