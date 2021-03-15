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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
    @FXML
    private Button btnpostComm;
    @FXML
    private ImageView ivUpVote;
    @FXML
    private ImageView ivDownVote;
    @FXML
    private Label lblCommVote;
    public static boolean upvoted = false, downvoted = false;
    @FXML
    private Label lblShowCommCount;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadShowPost();
        } catch (SQLException ex) {
            Logger.getLogger(ShowQuesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadComment();              
    }
    public void loadShowPost() throws SQLException
    {
        Forum obj = new Forum();
        obj.connection();
        lblSQTitle.setText(showObj.title);
        lblSQUser.setText("Asked by "+ showObj.username);
        taSQBody.setText(showObj.body);
        lblSQTag.setText(showObj.tag);
        lblCommVote.setText(Integer.toString(showObj.vote));   
        try {
            lblShowCommCount.setText(obj.readAnsCount(showObj.postID) + " Comments");
        } catch (SQLException ex) {
            System.out.println("SQl Exception");
        }
    }
    public void loadComment() {
        Forum obj = new Forum();
        try {
            obj.connection();
        } catch (SQLException ex) {
            System.out.println("Exception");
        }
        commInfo = obj.readComment(showObj.postID);
        showObj.answer = commInfo.size();
        if (commInfo.size() == 0)
        {
            Text error = new Text("There are no comments yet");
            error.setFont(Font.font("System", 18));            
            vbSQ.setAlignment(Pos.CENTER);
            vbSQ.getChildren().add(error);
        }
        else
        {
            for (int i = 0; i < commInfo.size(); i++) {
                Text user = new Text(commInfo.get(i).userName);
                Text whiteSpace1 = new Text();
                Text whiteSpace2 = new Text();
                Text body = new Text(commInfo.get(i).commentBody);
                Separator sp = new Separator(Orientation.HORIZONTAL);
                sp.isVisible();
                sp.setPrefWidth(629);
                user.setFont(Font.font("System", 19));
                user.setFill(Color.DARKBLUE);
                body.setFont(Font.font("System", 16));
                body.setWrappingWidth(633);
                vbSQ.setTranslateX(28.0f);
                vbSQ.getChildren().add(sp);
                vbSQ.getChildren().add(user);
                vbSQ.getChildren().add(whiteSpace1);
                vbSQ.getChildren().add(body);
                vbSQ.getChildren().add(whiteSpace2);
                vbSQ.setSpacing(5);
            }
        }
    }

    @FXML
    private void btnpostCommClicked(ActionEvent event) throws SQLException {
        String commContent = taSQComm.getText();
        if (commContent.equals(null)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Content box empty");
            alert.setContentText("Please fill the comment box first");
            alert.show();
        } else {
            taSQComm.setText(null);
            Forum sendComm = new Forum();
            sendComm.connection();
            sendComm.writeComment(showObj.postID, commContent);
            try {
                lblShowCommCount.setText(sendComm.readAnsCount(showObj.postID)+ " Comments");
            } catch (SQLException ex) {
               System.out.println("SQl Exception");
            }
        }
        //loadComment();
    }

    @FXML
    private void ivUpVoteClicked(MouseEvent event) throws SQLException {
        if(downvoted)
        {
            showObj.vote++;
            ivDownVote.setImage(new Image("/Image/Down1.png"));            
        }
        if(!upvoted)
        {   
            showObj.vote++;
            lblCommVote.setText(Integer.toString(showObj.vote));
            upvoted = true;
            downvoted = false;
            ivUpVote.setImage(new Image("/Image/Up2.png"));
            setVote();
        }
        else
        {
            showObj.vote--;
            lblCommVote.setText(Integer.toString(showObj.vote));
            upvoted = false;
            downvoted = false;
            ivUpVote.setImage(new Image("/Image/Up1.png"));
            setVote();
        }
    }

    @FXML
    private void ivDownVoteClicked(MouseEvent event) throws SQLException {
        if(upvoted)
        {
            showObj.vote--;
            ivUpVote.setImage(new Image("/Image/Up1.png"));                       
        }
        if(!downvoted)
        {    
            showObj.vote--;
            lblCommVote.setText(Integer.toString(showObj.vote));
            downvoted = true;
            upvoted = false;
            ivDownVote.setImage(new Image("/Image/Down2.png"));            
            setVote();
        }
        else
        {
            showObj.vote++;
            lblCommVote.setText(Integer.toString(showObj.vote));
            downvoted = false;
            upvoted = false;
            ivDownVote.setImage(new Image("/Image/Down1.png"));
            setVote();
            
        }
    }

    public void setVote() throws SQLException {
        Forum obj = new Forum();
        obj.connection();
        obj.writeVote(showObj.postID, showObj.vote);
    }


}
