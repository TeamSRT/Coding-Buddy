/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Main.DateAndTime;
import Main.Utility;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
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
    public int sendCommID;
    @FXML
    private Label lblSQTag;
    private VBox vbSQ;
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
    public boolean commUpdated = false;
    @FXML
    private Label lblShowCommCount;
    //   private VBox vbContent;

    @FXML
    private VBox vbComment;
    @FXML
    private Label lblSQPostTime;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
    @FXML
    private Button btnRefresh;
    @FXML
    private FontAwesomeIconView iconRefresh;
    @FXML
    private Button btnEdit;
    @FXML
    private FontAwesomeIconView iconEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private FontAwesomeIconView iconDelete;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){             
        Tooltip.install(btnRefresh, new Tooltip("Refresh this page"));
        Tooltip.install(ivUpVote, new Tooltip("This post is helpful and clear"));
        Tooltip.install(ivDownVote, new Tooltip("This post is not helpful and unclear"));
        Tooltip.install(btnEdit, new Tooltip("Edit post"));
        Tooltip.install(btnDelete, new Tooltip("Delete post"));        
        loadAll();
    }
    
    public void loadAll() {
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

    public void loadShowPost() throws SQLException, ParseException {
        Forum obj = new Forum();
        obj.connection();
        lblSQTitle.setText(showObj.title);
        if(showObj.username.equals(Main.Utility.username))
        {
            btnEdit.setDisable(false);
            btnEdit.setOpacity(1);
            btnDelete.setDisable(false);
            btnDelete.setOpacity(1);
        }
        else
        {
            btnEdit.setDisable(true);
            btnEdit.setOpacity(0);
            btnDelete.setDisable(false);
            btnDelete.setOpacity(0);
        }
        if(showObj.username.equals(Main.Utility.username))
        {
            lblSQUser.setText("Asked by me");
        }
        else
        {    
            lblSQUser.setText("Asked by " + showObj.username);
        }
        String timeAgo = new DateAndTime().passedTime(new Date(), format.parse(showObj.postDate + showObj.postTime));
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

    public void loadComment() throws ParseException, SQLException {
        Forum obj = new Forum();
        Image upVote = new Image("/Image/Up1.png");
        Image upVoteP = new Image("/Image/Up2.png");

        try {
            obj.connection();
        } catch (SQLException ex) {
            System.out.println("Exception");
        }
        ArrayList<Comment> commInfo = obj.readComment(showObj.postID);
        showObj.answer = commInfo.size();
        vbComment.getChildren().clear();
        if (commInfo.isEmpty()) {
            Text error = new Text("There are no comments yet");
            error.setFont(Font.font("System", 18));
            vbComment.setAlignment(Pos.CENTER);
            vbComment.getChildren().add(error);
        } else {
            for (int i = 0; i < commInfo.size(); i++) {

                VBox vbCommVote = new VBox();
                VBox vbCommContent = new VBox();
                HBox addVbox = new HBox();
                String commBody = commInfo.get(i).commentBody;
                Button edit = new Button("Edit");
                Button delete = new Button("Delete");
                edit.setTextFill(Color.WHITE);
                edit.setPrefWidth(50);
                edit.getStyleClass().add("btnStyle");
                delete.setTextFill(Color.WHITE);
                delete.setPrefWidth(60);
                delete.getStyleClass().add("btnStyle");
                Text user;
                if (commInfo.get(i).userName.equals(Main.Utility.username)) {
                    user = new Text("Me");
                } else {
                    user = new Text(commInfo.get(i).userName);
                }
                Text body = new Text(commInfo.get(i).commentBody);
                Text commTime = new Text("Posted " + new DateAndTime().passedTime(new Date(), format.parse(commInfo.get(i).commDate + commInfo.get(i).commTime)));
                user.setOnMouseEntered((Event event) -> {
                    user.setCursor(Cursor.TEXT);
                });
                body.setOnMouseEntered((Event event) -> {
                    body.setCursor(Cursor.TEXT);
                });
                commTime.setOnMouseEntered((Event event) -> {
                    commTime.setCursor(Cursor.TEXT);
                });

                commTime.setTranslateX(508.0f);
                ImageView ivCommUpVote = new ImageView(upVote);
                ivCommUpVote.setFitWidth(15);
                ivCommUpVote.setFitHeight(25);
                Image downVote = new Image("/Image/Down1.png");
                ImageView ivCommDownVote = new ImageView(downVote);
                ivCommDownVote.setFitWidth(15);
                ivCommDownVote.setFitHeight(25);
                Image downVoteP = new Image("/Image/Down2.png");
                final int postID = showObj.postID;
                final int commentID = commInfo.get(i).commentID;
                final Text commVote = new Text(obj.commVoteCount(postID, commentID) + "");
                int check = obj.readCommVoteStatus(postID, commentID);
                switch (check) {
                    case 0:
                        ivCommUpVote.setImage(upVote);
                        ivCommDownVote.setImage(downVote);
                        break;
                    case 1:
                        ivCommUpVote.setImage(upVoteP);
                        ivCommDownVote.setImage(downVote);                        
                        break;
                    default:
                        ivCommUpVote.setImage(upVote);
                        ivCommDownVote.setImage(downVoteP);                        
                        break;
                }
                ivCommUpVote.setOnMouseEntered((Event event) -> {
                    Tooltip.install(ivCommUpVote, new Tooltip("This comment is helpful"));
                    ivCommUpVote.setCursor(Cursor.HAND);
                });
                ivCommUpVote.setOnMouseClicked((Event event) -> {
                    try {
                        int curr = obj.readCommVoteStatus(postID, commentID);
                        if (curr == 1) {
                            obj.updateCommVote(0, postID, commentID);
                            ivCommUpVote.setImage(upVote);
                            ivCommDownVote.setImage(downVote);
                        } else {
                            obj.updateCommVote(1, postID, commentID);
                            ivCommUpVote.setImage(upVoteP);
                            ivCommDownVote.setImage(downVote);
                        }
                        commVote.setText(obj.commVoteCount(postID, commentID) + "");

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                });
                ivCommDownVote.setOnMouseEntered((Event event) -> {
                    Tooltip.install(ivCommDownVote, new Tooltip("This comment is not helpful"));
                    ivCommDownVote.setCursor(Cursor.HAND);
                });
                ivCommDownVote.setOnMouseClicked((Event event) -> {
                    try {
                        int curr = obj.readCommVoteStatus(postID, commentID);
                        if (curr == -1) {
                            obj.updateCommVote(0, postID, commentID);
                            ivCommUpVote.setImage(upVote);
                            ivCommDownVote.setImage(downVote);
                        } else {
                            obj.updateCommVote(-1, postID, commentID);
                            ivCommDownVote.setImage(downVoteP);
                            ivCommUpVote.setImage(upVote);
                        }
                        commVote.setText(obj.commVoteCount(postID, commentID) + "");

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                });

                edit.setOnMouseClicked((Event event) -> {
                    taSQComm.setText(commBody);
                    commUpdated = true;
                    sendCommID = commentID;
                });
                delete.setOnMouseClicked((Event event) -> {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setHeaderText("Are you sure?");
                    alert.setContentText("Do you really want to delete this comment?This Process can't be undone!");
                    ButtonType yes = new ButtonType("Yes");
                    ButtonType no = new ButtonType("No");
                    alert.getButtonTypes().setAll(yes, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == yes) {
                        try {
                            obj.connection();
                            obj.deleteComment(commentID);
                            try {
                                loadComment();
                                lblShowCommCount.setText(obj.readAnsCount(showObj.postID) + " Comments");
                                
                            } catch (ParseException ex) {
                                System.out.println(ex);
                            }
                        } catch (SQLException ex) {
                            System.out.println("While deleting comment = " + ex);
                        }

                    }
                });
                edit.setOnMouseEntered((Event event) -> {
                    Tooltip.install(edit, new Tooltip("Edit this comment"));
                });
                delete.setOnMouseEntered((Event event) -> {
                    Tooltip.install(delete, new Tooltip("Delete this comment"));
                });
                if(obj.commVoteCount(postID, commentID)< 0)
                {
                    commVote.setTranslateX(0.0f);
                }
                else
                {
                    commVote.setTranslateX(4.0f);
                }
                Text whiteSpace1 = new Text();
                Text whiteSpace2 = new Text();
                Button btn = new Button();
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
                vbCommVote.getChildren().addAll(ivCommUpVote, commVote, ivCommDownVote, whiteSpace1);
                vbCommVote.setPrefWidth(28.0f);
                vbCommVote.setSpacing(5);
                if ((commInfo.get(i).userName).equals(Main.Utility.username)) {
                    HBox container = new HBox();
                    container.getChildren().addAll(edit, delete);
                    container.setSpacing(20);
                    vbCommContent.getChildren().addAll(user, body, whiteSpace1, container, commTime, whiteSpace2);
                } else {
                    vbCommContent.getChildren().addAll(user, body, whiteSpace1, commTime, whiteSpace2);
                }
                vbCommContent.setSpacing(5);
                addVbox.getChildren().addAll(vbCommVote, vbCommContent);
                vbComment.getChildren().add(addVbox);
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
            if (!commUpdated) {
                sendComm.writeComment(showObj.postID, commContent);
            } else {
                sendComm.updateComment(sendCommID, commContent);
                commUpdated = false;
            }

            try {
                loadComment();
                lblShowCommCount.setText(sendComm.readAnsCount(showObj.postID) + " Comments");
            } catch (SQLException ex) {
                System.out.println("SQl Exception");
            } catch (ParseException ex) {
                Logger.getLogger(ShowQuesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //loadComment();
    }

    @FXML
    private void ivUpVoteClicked(MouseEvent event) throws SQLException {

        if (downvoted) {
            showObj.vote++;
            ivDownVote.setImage(new Image("/Image/Down1.png"));
        }
        if (!upvoted) {
            showObj.vote++;
            lblCommVote.setText(Integer.toString(showObj.vote));
            upvoted = true;
            downvoted = false;
            ivUpVote.setImage(new Image("/Image/Up2.png"));
            setVote(1);
        } else {
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
        if (upvoted) {
            showObj.vote--;
            ivUpVote.setImage(new Image("/Image/Up1.png"));
        }
        if (!downvoted) {
            showObj.vote--;
            lblCommVote.setText(Integer.toString(showObj.vote));
            downvoted = true;
            upvoted = false;
            ivDownVote.setImage(new Image("/Image/Down2.png"));
            setVote(-1);
        } else {
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

    public void pVoteStatus() throws SQLException {
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

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        loadAll();
    }

    @FXML
    private void btnEditClicked(ActionEvent event) throws IOException {
        AskQuesController.recObj = showObj;
        new Utility().loadPane("/Forum/AskQues.fxml");
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you really want to delete this post?This Process can't be undone!");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            Forum obj = new Forum();
            obj.connection();
            obj.deleteForum(showObj.postID);
            new Utility().loadPane("/Forum/ForumBase.fxml");

        }
    }

}
