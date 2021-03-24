/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;



import Main.DateAndTime;
import java.net.URL;
import Main.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.layout.HBox;
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
    public static boolean upvoted = false, downvoted = false, commUpVoted = false, commDownVoted = false;
    
    
    @FXML
    private Label lblShowCommCount;
 //   private VBox vbContent;
   
    @FXML
    private VBox vbComment;
    @FXML
    private Label lblSQPostTime;
   
    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            loadShowPost();
            loadComment();
            pVoteStatus();
        } catch (ParseException ex) {
            System.out.println("Comment time loading error occured");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void loadShowPost() throws SQLException, ParseException
    {
        Forum obj = new Forum();
        obj.connection();
        lblSQTitle.setText(showObj.title);
        lblSQUser.setText("Asked by "+ showObj.username);      
        String timeAgo = new DateAndTime().passedTime(new Date(),format.parse(showObj.postDate + showObj.postTime));        
        lblSQPostTime.setText("Posted " + timeAgo);
        taSQBody.setText(showObj.body);
        lblSQTag.setText(showObj.tag);
        lblCommVote.setText(Integer.toString(showObj.vote));   
        try {
            lblShowCommCount.setText(obj.readAnsCount(showObj.postID) + " Comments");
        } catch (SQLException ex) {
            System.out.println("SQl Exception");
        }
    }
    public void loadComment() throws ParseException {
        Forum obj = new Forum();
        try {
            obj.connection();
        } catch (SQLException ex) {
            System.out.println("Exception");
        }
        commInfo = obj.readComment(showObj.postID);
        showObj.answer = commInfo.size();
        if (commInfo.isEmpty()) {
            Text error = new Text("There are no comments yet");
            error.setFont(Font.font("System", 18));
            vbComment.setAlignment(Pos.CENTER);
            vbComment.getChildren().add(error);
        } else
        {
            for (int i = 0; i < commInfo.size(); i++) {
                VBox vbCommVote = new VBox();
                VBox vbCommContent = new VBox();
                HBox addVbox = new HBox();
                Text user = new Text(commInfo.get(i).userName);
                Text commTime = new Text("Posted "+ new DateAndTime().passedTime(new Date(), format.parse(commInfo.get(i).commDate + commInfo.get(i).commTime)));
                commTime.setTranslateX(508.0f);               
                Image upVote = new Image("/Image/Up1.png");
                ImageView ivCommUpVote = new ImageView(upVote);
                ivCommUpVote.setFitWidth(15);
                ivCommUpVote.setFitHeight(25);
                Image downVote = new Image("/Image/Down1.png");
                ImageView ivCommDownVote = new ImageView(downVote);
                ivCommDownVote.setFitWidth(15);
                ivCommDownVote.setFitHeight(25);
                Image upVoteP = new Image("/Image/Up2.png");
                ImageView ivCommUpVoteP = new ImageView(upVoteP);
                ivCommUpVoteP.setFitWidth(15);
                ivCommUpVoteP.setFitHeight(25);
                Image downVoteP = new Image("/Image/Down2.png");
                ImageView ivCommDownVoteP = new ImageView(downVoteP);
                ivCommDownVoteP.setFitWidth(15);
                ivCommDownVoteP.setFitHeight(25);
                ivCommUpVote.setOnMouseClicked(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        commUpVoted = true;
                    }
                });
                ivCommDownVote.setOnMouseClicked(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        commDownVoted = true;
                    }
                });
                Text whiteSpace1 = new Text();
                Text whiteSpace2 = new Text();
                Text whiteSpace3 = new Text();
                Text whiteSpace4 = new Text();
                Button btn = new Button();
                Text body = new Text(commInfo.get(i).commentBody);
                Text commVote = new Text("30");
                commVote.setTextAlignment(TextAlignment.CENTER);
                commVote.setFont(Font.font("Times New Roman", 18));
                Separator sp = new Separator(Orientation.HORIZONTAL);
                sp.isVisible();
                sp.setPrefWidth(629);
                user.setFont(Font.font("System", 19));
                user.setFill(Color.DARKBLUE);
                commTime.setFont(Font.font("System", 12));
                commTime.setFill(Color.LIGHTSLATEGRAY);
                body.setFont(Font.font("System", 16));
                body.setWrappingWidth(633);
                vbCommContent.getChildren().add(sp);
                sp.setTranslateX(0.0f);
                if (commUpVoted) {
                    vbCommVote.getChildren().addAll(ivCommUpVoteP, commVote, ivCommDownVote, whiteSpace2);
                } else if (commDownVoted) {
                    vbCommVote.getChildren().addAll(ivCommUpVote, commVote, ivCommDownVoteP, whiteSpace2);
                } else {
                    vbCommVote.getChildren().addAll(ivCommUpVote, commVote, ivCommDownVote, whiteSpace2);
                }
                vbCommVote.setPrefWidth(28.0f);
                vbCommVote.setSpacing(5);
                vbCommContent.getChildren().addAll(user, body, whiteSpace1, commTime, whiteSpace2);
                vbCommContent.setSpacing(5);
                addVbox.getChildren().addAll(vbCommVote, vbCommContent);
                vbComment.getChildren().add(addVbox);
                commUpVoted = false;
                commDownVoted = false;
            }
        }
    }

    @FXML
    private void btnpostCommClicked(ActionEvent event) throws SQLException {
        String commContent = taSQComm.getText();
        if (commContent == null) {
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
            setVote(1);
        }
        else
        {
            showObj.vote--;
            lblCommVote.setText(Integer.toString(showObj.vote));
            upvoted = false;
            downvoted = false;
            ivUpVote.setImage(new Image("/Image/Up1.png"));
            setVote(0);
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
            setVote(-1);
        }
        else
        {
            showObj.vote++;
            lblCommVote.setText(Integer.toString(showObj.vote));
            downvoted = false;
            upvoted = false;
            ivDownVote.setImage(new Image("/Image/Down1.png"));
            setVote(0);
            
        }
    }

    public void setVote(int postVote) throws SQLException {
        Forum obj = new Forum();
        obj.connection();
        obj.writeVote(showObj.postID, showObj.vote);
        obj.updateVote(postVote, showObj.postID);
    }
   
    public void pVoteStatus() throws SQLException
    {
        Forum obj = new Forum();
        obj.connection();
        int status = obj.readVoteStatus(showObj.postID);
        switch (status) {
            case 0:
                ivUpVote.setImage(new Image("/Image/Up1.png"));
                ivDownVote.setImage(new Image("/Image/Down1.png"));
                upvoted = false;
                downvoted = false;
                break;
            case 1:
                ivUpVote.setImage(new Image("/Image/Up2.png"));
                ivDownVote.setImage(new Image("/Image/Down1.png"));
                upvoted = true;
                downvoted = false;
                break;
            case -1:
                ivUpVote.setImage(new Image("/Image/Up1.png"));
                ivDownVote.setImage(new Image("/Image/Down2.png"));
                upvoted = false;
                downvoted = true;
                break;
        }
    }

}
