/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class ShowQuesController implements Initializable {

    @FXML
    private AnchorPane apShowQues;
    @FXML
    private Label lblSQTitle;
    @FXML
    private Label lblSQUser;
    @FXML
    private TextArea taSQBody;
    public static Post showObj;
    @FXML
    private Label lblSQTag;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadShowPost();
    }    
    public void loadShowPost()
    {
        lblSQTitle.setText(showObj.title);
        lblSQUser.setText("Asked by "+ showObj.username);
        taSQBody.setText(showObj.body);
        lblSQTag.setText(showObj.tag);
    }
}
