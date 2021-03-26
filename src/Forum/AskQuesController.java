/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public ArrayList<Post> rec;
    public static Post recObj;
    boolean update = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (recObj != null) 
        {
            update = true;
            editPost();
        }
    }

    @FXML
    private void btnPostClicked(ActionEvent event) throws SQLException {
        String titleText = taTitle.getText();
        String bodyText = taBody.getText();
        String tagText = taTag.getText();
        if (titleText.equals("")) 
        {
            Alert alertText = new Alert(AlertType.ERROR);
            alertText.setTitle("Title box empty");
            alertText.setContentText("Please enter a title");
            alertText.show();
        } 
        else if (bodyText.equals("")) 
        {
            Alert alertText = new Alert(AlertType.ERROR);
            alertText.setTitle("Body box empty");
            alertText.setContentText("Please enter information about the problem");
            alertText.show();
        } 
        else if (tagText.equals("")) 
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
            if (!update) 
            {
                sendObj.writeForum(titleText, bodyText, tagText);
            }
            else 
            {
                sendObj.updateForum(titleText, bodyText, tagText, recObj.postID);
                recObj = null;
            }
            taTitle.setText(null);
            taBody.setText(null);
            taTag.setText(null);
        }
    }

    private void editPost() {
        taTitle.setText(recObj.title);
        taBody.setText(recObj.body);
        taTag.setText(recObj.tag);
    }

}
