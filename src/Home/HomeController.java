/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import Forum.Forum;
import Forum.Post;
import Forum.ShowQuesController;
import Main.DateAndTime;
import Main.Utility;
import Problemset.Problem;
import Problemset.ProblemSQL;
import Problemset.ProblemViewController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class HomeController implements Initializable {

    @FXML
    private Pane p1Title;
    @FXML
    private Label lblShowTitle1;
    @FXML
    private Label lblForumUser1;
    @FXML
    private Pane p1Vote;
    @FXML
    private Label lblVote1;
    @FXML
    private Pane p1Ans;
    @FXML
    private Label lblAns1;
    @FXML
    private ImageView imgHolder1;
    @FXML
    private Label lblPostTime1;
    @FXML
    private Pane p1Title1;
    @FXML
    private Label lblShowTitle2;
    @FXML
    private Label lblForumUser2;
    @FXML
    private Pane p1Vote1;
    @FXML
    private Label lblVote2;
    @FXML
    private Pane p1Ans1;
    @FXML
    private Label lblAns2;
    @FXML
    private ImageView imgHolder2;
    @FXML
    private Label lblPostTime2;
    @FXML
    private Pane problemPane1;
    @FXML
    private Label lblProblem1;
    @FXML
    private Label lblAuthor1;
    @FXML
    private Label lblCount1;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");

    /**
     * Initializes the controller class.
     */
    Post topComm, topVoted, recentPost;
    Problem lastTried;
    @FXML
    private Pane p1Title11;
    @FXML
    private Label lblShowTitle3;
    @FXML
    private Label lblForumUser3;
    @FXML
    private Pane p1Vote11;
    @FXML
    private Label lblVote3;
    @FXML
    private Pane p1Ans11;
    @FXML
    private Label lblAns3;
    @FXML
    private ImageView imgHolder3;
    @FXML
    private Label lblPostTime3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAll();
    }

    public void loadAll() {
        try {
            Forum obj = new Forum();
            obj.connection();
            topComm = getTopCommPost();
            topVoted = getTopVotedPost();
            lastTried = getLastProblem();
            recentPost = getRecentProblem();

            lblShowTitle1.setText(topVoted.title);
            lblForumUser1.setText("Asked By " + topVoted.username);
            lblPostTime1.setText("Posted " + new DateAndTime().passedTime(new Date(), format.parse(topVoted.postDate + topVoted.postTime)));
            lblVote1.setText("" + topVoted.vote);
            lblAns1.setText(obj.readAnsCount(topVoted.postID));
            imgHolder1.setImage(Main.Utility.getImage(topVoted.username, 65, 55));

            lblShowTitle2.setText(topComm.title);
            lblForumUser2.setText("Asked By " + topComm.username);
            lblPostTime2.setText("Posted " + new DateAndTime().passedTime(new Date(), format.parse(topComm.postDate + topComm.postTime)));
            lblVote2.setText("" + topComm.vote);
            lblAns2.setText(obj.readAnsCount(topComm.postID));
            imgHolder2.setImage(Main.Utility.getImage(topComm.username, 65, 55));

            lblShowTitle3.setText(recentPost.title);
            lblForumUser3.setText("Asked By " + recentPost.username);
            lblPostTime3.setText("Posted " + new DateAndTime().passedTime(new Date(), format.parse(recentPost.postDate + recentPost.postTime)));
            lblVote3.setText("" + recentPost.vote);
            lblAns3.setText(obj.readAnsCount(recentPost.postID));
            imgHolder3.setImage(Main.Utility.getImage(recentPost.username, 65, 55));

            if (lastTried == null) {
                problemPane1.setDisable(true);
                problemPane1.setOpacity(0);
            } else {
                problemPane1.setDisable(false);
                problemPane1.setOpacity(1);
                lblProblem1.setText(lastTried.title);
                lblAuthor1.setText("Author: " + lastTried.author);
                lblCount1.setText("" + new ProblemSQL().countSubmission(lastTried.problemID));
                if (new ProblemSQL().isSolved(lastTried.problemID)) {
                    lblProblem1.setTextFill(Color.FORESTGREEN);
                } else {
                    lblProblem1.setTextFill(Color.BLACK);
                }
            }

        } catch (ParseException ex) {
            System.out.println(ex);
            System.out.println("Date Parse Exception");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("SQL Error in Read Ans Count");
        }
    }

    public Post getTopCommPost() {
        Post topComm = null;
        try {
            int topID = -1;
            String query = "SELECT postID, COUNT(postID) as postC FROM `comment` GROUP BY postID ORDER BY postC DESC";
            Statement st = Main.Utility.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                topID = rs.getInt("postID");
            }
            query = "SELECT * FROM `forum` WHERE postID = " + topID;
            Statement st1 = Main.Utility.conn.createStatement();
            ResultSet answer = st.executeQuery(query);
            if (answer.next()) {
                topComm = new Post();
                topComm.username = answer.getString("username");
                topComm.title = answer.getString("title");
                topComm.body = answer.getString("body");
                topComm.tag = answer.getString("tag");
                topComm.vote = answer.getInt("vote");
                topComm.postID = answer.getInt("postID");
                topComm.postDate = answer.getDate("postDate").toString();
                topComm.postTime = answer.getTime("postTime").toString();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error on fucntion getTopCommPost()");
        }
        return topComm;
    }

    public Post getTopVotedPost() {
        Post topVoted = null;
        try {
            int topID = -1;
            String query = "SELECT postID, SUM(postTrack) as postS FROM `postvote` GROUP BY postID ORDER BY postS DESC";
            Statement st = Main.Utility.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                topID = rs.getInt("postID");
            }
            query = "SELECT * FROM `forum` WHERE postID = " + topID;
            ResultSet answer = st.executeQuery(query);
            if (answer.next()) {
                topVoted = new Post();
                topVoted.username = answer.getString("username");
                topVoted.title = answer.getString("title");
                topVoted.body = answer.getString("body");
                topVoted.tag = answer.getString("tag");
                topVoted.vote = answer.getInt("vote");
                topVoted.postID = answer.getInt("postID");
                topVoted.postDate = answer.getDate("postDate").toString();
                topVoted.postTime = answer.getTime("postTime").toString();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error on fucntion getTopVotedPost()");
        }
        return topVoted;
    }

    public Problem getLastProblem() {
        Problem last = null;
        try {
            int lastID = -1;
            String query = "SELECT problemID FROM submission WHERE track = (SELECT MAX(track) FROM submission WHERE userName = '" + Main.Utility.username + "' AND (verdict = -1 OR verdict = 1)) AND userName = '" + Main.Utility.username + "' AND (verdict = -1 OR verdict = 1)";
            Statement st = Main.Utility.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                lastID = rs.getInt("problemID");
            }
            query = "SELECT * FROM `problemset` WHERE problemID = " + lastID;
            System.out.println(lastID);
            Statement st1 = Main.Utility.conn.createStatement();
            ResultSet currSet = st1.executeQuery(query);
            if (currSet.next()) {
                last = new Problem(currSet.getString("title"), currSet.getString("problemBody"), currSet.getString("input1"), currSet.getString("output1"), currSet.getString("input2"), currSet.getString("output2"), currSet.getString("input3"), currSet.getString("output3"), currSet.getString("username"), currSet.getInt("timeLimit"), currSet.getInt("problemID"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error on function getLastProblem()");
        }
        return last;
    }

    public Post getRecentProblem() {
        Post recent = null;
        try {
            String query = "SELECT * FROM forum ORDER BY postID DESC";
            Statement st = Main.Utility.conn.createStatement();
            ResultSet answer = st.executeQuery(query);
            if (answer.next()) {
                recent = new Post();
                recent.username = answer.getString("username");
                recent.title = answer.getString("title");
                recent.body = answer.getString("body");
                recent.tag = answer.getString("tag");
                recent.vote = answer.getInt("vote");
                recent.postID = answer.getInt("postID");
                recent.postDate = answer.getDate("postDate").toString();
                recent.postTime = answer.getTime("postTime").toString();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return recent;
    }

    @FXML
    private void paneProb1Pressed(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = lastTried;
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void paneUpvotedOnClick(MouseEvent event) throws IOException {
        ShowQuesController.showObj = topVoted;
        new Utility().loadPane("/Forum/ShowQues.fxml");
    }

    @FXML
    private void paneCommOnClick(MouseEvent event) throws IOException {
        ShowQuesController.showObj = topComm;
        new Utility().loadPane("/Forum/ShowQues.fxml");
    }

    @FXML
    private void paneRecentOnClick(MouseEvent event) throws IOException {
        ShowQuesController.showObj = recentPost;
        new Utility().loadPane("/Forum/ShowQues.fxml");
    }

}
