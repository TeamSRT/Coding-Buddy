/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import static Forum.ShowQuesController.showObj;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class AskQuesController implements Initializable {

    @FXML
    private AnchorPane apForumAQ;
    @FXML
    private TextArea taTitle;
    @FXML
    private TextArea taBody;
    @FXML
    private TextArea taTag;
    @FXML
    private Button btnPost;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblBody;
    @FXML
    private Label lblTag;
    @FXML
    private Pane PIntro;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnPostClicked(ActionEvent event) throws SQLException {
         String titleText = taTitle.getText();
         String bodyText = taBody.getText();
         String tagText = taTag.getText();
         if(titleText.equals(""))
         {
             Alert alertText = new Alert(AlertType.ERROR);
             alertText.setTitle("Title box empty");
             alertText.setContentText("Please enter a title");
             alertText.show();
         }
         else if(bodyText.equals(""))
         {
             Alert alertText = new Alert(AlertType.ERROR);
             alertText.setTitle("Body box empty");
             alertText.setContentText("Please enter information about the problem");
             alertText.show();
         }
         else if(tagText.equals(""))
         {  
             Alert alertText = new Alert(AlertType.ERROR);
             alertText.setTitle("Tag box empty");
             alertText.setContentText("Please enter atleast one tag");
             alertText.show(); 
         }
         else
         {  
             Forum sendObj = new Forum();            
             sendObj.connection();
             sendObj.writeForum(titleText, bodyText, tagText);
             taTitle.setText(null);
             taBody.setText(null);
             taTag.setText(null);
         }
    }    
    
}