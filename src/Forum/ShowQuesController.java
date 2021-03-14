/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;


import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
    @FXML
    private VBox vbSQ;
    private ArrayList<Comment> commInfo;
    @FXML
    private TextArea taSQComm;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadShowPost();
        loadComment();

    }
    public void loadShowPost()
    {
        lblSQTitle.setText(showObj.title);
        lblSQUser.setText("Asked by "+ showObj.username);
        taSQBody.setText(showObj.body);
        lblSQTag.setText(showObj.tag);
    }
    public void loadComment() 
    {         
        Forum obj = new Forum();
        try {
            obj.connection();
        } catch (SQLException ex) {
            System.out.println("Exception");
        }
       commInfo = obj.readComment(showObj.postID);
       for(int i = 0; i < commInfo.size(); i++)
       {
            Text a  = new Text(commInfo.get(i).userName);
            a.setX(10.0f);
            a.setY(50.0f);
            Text b = new Text();
            Text c = new Text(commInfo.get(i).commentBody);
            a.setFont(Font.font("Verdana", 16));
            vbSQ.getChildren().add(a);
            vbSQ.getChildren().add(b);
            vbSQ.getChildren().add(c);
       }
    }
    
}
