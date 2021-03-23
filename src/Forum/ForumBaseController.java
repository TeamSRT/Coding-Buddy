/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Main.Utility;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.sql.Types.NULL;

import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class ForumBaseController implements Initializable {

    @FXML
    private Button btnAskQ;
    @FXML
    private Pane p1Title;
    @FXML
    private Pane p1Vote;
    @FXML
    private Label lblVote1;
    @FXML
    private Pane p1Ans;
    @FXML
    private Label lblAns1;
    @FXML
    private Pane p3Title;
    @FXML
    private Pane p3Vote;
    @FXML
    private Label lblVote3;
    @FXML
    private Pane p3Ans;
    @FXML
    private Label lblAns3;
    @FXML
    private Pane p2Title;
    @FXML
    private Pane p2Vote;
    @FXML
    private Pane p2Ans;
    @FXML
    private Label lblVote2;
    @FXML
    private Label lblAns2;
    @FXML
    private Pane p4Title;
    @FXML
    private Pane p4Vote;
    @FXML
    private Label lblVote4;
    @FXML
    private Pane p4Ans;
    @FXML
    private Label lblAns4;
    @FXML
    private Pane p5Title;
    @FXML
    private Pane p5Vote;
    @FXML
    private Label lblVote5;
    @FXML
    private Pane p5Ans;
    @FXML
    private Label lblAns5;
    @FXML
    private Pane p6Title;
    @FXML
    private Pane p6Vote;
    @FXML
    private Label lblVote6;
    @FXML
    private Pane p6Ans;
    @FXML
    private Label lblAns6;
    @FXML
    private Pane p7Title;
    @FXML
    private Pane p7Vote;
    @FXML
    private Label lblVote7;
    @FXML
    private Pane p7Ans;
    @FXML
    private Label lblAns7;

    @FXML
    private Label lblShowTitle1;
    @FXML
    private Label lblShowTitle3;
    @FXML
    private Label lblShowTitle2;
    @FXML
    private Label lblShowTitle4;
    @FXML
    private Label lblShowTitle5;
    @FXML
    private Label lblShowTitle6;
    @FXML
    private Label lblShowTitle7;

    ArrayList<Post> receive;
    @FXML
    private Label lblForumUser1;
    @FXML
    private Label lblForumUser3;
    @FXML
    private Label lblForumUser2;
    @FXML
    private Label lblForumUser4;
    @FXML
    private Label lblForumUser5;
    @FXML
    private Label lblForumUser6;
    @FXML
    private Label lblForumUser7;
    @FXML
    private AnchorPane apForumBase;
    @FXML
    private AnchorPane apShowQues;
    @FXML
    private Button btnPreviousForum;
    @FXML
    private Button btnNextForum;
    @FXML
    private ImageView imgHolder1;
    @FXML
    private ImageView imgHolder3;
    @FXML
    private ImageView imgHolder2;
    @FXML
    private ImageView imgHolder4;
    @FXML
    private ImageView imgHolder5;
    @FXML
    private ImageView imgHolder6;
    @FXML
    private ImageView imgHolder7;
    private int quesCount;
    private int currPage;
    private final int QuesPerPage = 7;
    @FXML
    private Button btnCenterForum;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Forum reader = new Forum();
        try {
            reader.connection();
        } catch (SQLException ex) {
            System.out.println("Connection Failed1");
        }
        receive = reader.readForum(this);
        quesCount = receive.size();
        currPage = 0;
        try {
            loadForumBase();
        } catch (SQLException ex) {
            System.out.println("Loading failed");
        }
        if (quesCount % 7 != 0) {
            for (int i = 0; i < 7 - (quesCount % 7); i++) {
                Post obj = new Post("", "", "", "", -1, 0, 0,"","");
                receive.add(obj);
            }
        }
        quesCount = receive.size();
        if (currPage >= quesCount / QuesPerPage - 1) {
            btnNextForum.setDisable(true);
        }
        btnPreviousForum.setDisable(true);
        btnCenterForum.setText(currPage + 1 + "");
        checkAvailability();
    }

    @FXML
    private void btnAskQClicked(ActionEvent event) throws IOException {
        new Main.Utility().loadPane("/Forum/AskQues.fxml");
    }

    private void loadForumBase() throws SQLException {
        imgHolder1.setImage(Utility.getImage(receive.get(currPage * QuesPerPage + 0).username, 72, 65));
        imgHolder2.setImage(Utility.getImage(receive.get(currPage * QuesPerPage + 1).username, 72, 65));
        imgHolder3.setImage(Utility.getImage(receive.get(currPage * QuesPerPage + 2).username, 72, 65));
        imgHolder4.setImage(Utility.getImage(receive.get(currPage * QuesPerPage + 3).username, 72, 65));
        imgHolder5.setImage(Utility.getImage(receive.get(currPage * QuesPerPage + 4).username, 72, 65));
        imgHolder6.setImage(Utility.getImage(receive.get(currPage * QuesPerPage + 5).username, 72, 65));
        imgHolder7.setImage(Utility.getImage(receive.get(currPage * QuesPerPage + 6).username, 72, 65));
        Forum obj = new Forum();
        obj.connection();
        lblForumUser1.setText("Asked by " + receive.get(currPage * QuesPerPage + 0).username);
        lblShowTitle1.setText(receive.get(currPage * QuesPerPage + 0).title);
        lblVote1.setText(receive.get(currPage * QuesPerPage + 0).vote + " ");        
        lblAns1.setText(obj.readAnsCount(receive.get(currPage * QuesPerPage + 0).postID));    
        lblForumUser2.setText("Asked by " + receive.get(currPage * QuesPerPage + 1).username);
        lblShowTitle2.setText(receive.get(currPage * QuesPerPage + 1).title);
        lblVote2.setText(receive.get(currPage * QuesPerPage + 1).vote + " ");
        lblAns2.setText(obj.readAnsCount(receive.get(currPage * QuesPerPage + 1).postID));
        lblForumUser3.setText("Asked by " + receive.get(currPage * QuesPerPage + 2).username);
        lblShowTitle3.setText(receive.get(currPage * QuesPerPage + 2).title);
        lblVote3.setText(receive.get(currPage * QuesPerPage + 2).vote + " ");
        lblAns3.setText(obj.readAnsCount(receive.get(currPage * QuesPerPage + 2).postID));
        lblForumUser4.setText("Asked by " + receive.get(currPage * QuesPerPage + 3).username);
        lblShowTitle4.setText(receive.get(currPage * QuesPerPage + 3).title);
        lblVote4.setText(receive.get(currPage * QuesPerPage + 3).vote + " ");
        lblAns4.setText(obj.readAnsCount(receive.get(currPage * QuesPerPage + 3).postID));
        lblForumUser5.setText("Asked by " + receive.get(currPage * QuesPerPage + 4).username);
        lblShowTitle5.setText(receive.get(currPage * QuesPerPage + 4).title);
        lblVote5.setText(receive.get(currPage * QuesPerPage + 4).vote + " ");
        lblAns5.setText(obj.readAnsCount(receive.get(currPage * QuesPerPage + 4).postID));
        lblForumUser6.setText("Asked by " + receive.get(currPage * QuesPerPage + 5).username);
        lblShowTitle6.setText(receive.get(currPage * QuesPerPage + 5).title);
        lblVote6.setText(receive.get(currPage * QuesPerPage + 5).vote + " ");
        lblAns6.setText(obj.readAnsCount(receive.get(currPage * QuesPerPage + 5).postID));
        lblForumUser7.setText("Asked by " + receive.get(currPage * QuesPerPage + 6).username);
        lblShowTitle7.setText(receive.get(currPage * QuesPerPage + 6).title);
        lblVote7.setText(receive.get(currPage * QuesPerPage + 6).vote + " ");
        lblAns7.setText(obj.readAnsCount(receive.get(currPage * QuesPerPage + 6).postID));
    }

    @FXML
    private void lblShowTitle1Clicked(MouseEvent event) throws IOException{
        ShowQuesController.showObj = receive.get(currPage * QuesPerPage + 0);        
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);       
       
    }

    @FXML
    private void lblShowTitle2Clicked(MouseEvent event) throws IOException { //
        ShowQuesController.showObj = receive.get(currPage * QuesPerPage + 1);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);       
    }

    @FXML
    private void lblShowTitle3Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(currPage * QuesPerPage + 2);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle4Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(currPage * QuesPerPage + 3);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle5Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(currPage * QuesPerPage + 4);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle6Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(currPage * QuesPerPage + 5);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle7Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(currPage * QuesPerPage + 6);       
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }
    
    public void checkAvailability() {
        if (receive.get(currPage * QuesPerPage + 0).postID == -1) {
            p1Title.setOpacity(0);
            p1Title.setDisable(true);
        } else {
            p1Title.setOpacity(1);
            p1Title.setDisable(false);
        }
        if (receive.get(currPage * QuesPerPage + 1).postID == -1) {
            p2Title.setOpacity(0);
            p2Title.setDisable(true);
        } else {
            p2Title.setOpacity(1);
            p2Title.setDisable(false);
        }
        if (receive.get(currPage * QuesPerPage + 2).postID == -1) {
            p3Title.setOpacity(0);
            p3Title.setDisable(true);
        } else {
            p3Title.setOpacity(1);
            p3Title.setDisable(false);
        }
        if (receive.get(currPage * QuesPerPage + 3).postID == -1) {
            p4Title.setOpacity(0);
            p4Title.setDisable(true);
        } else {
            p4Title.setOpacity(1);
            p4Title.setDisable(false);
        }
        if (receive.get(currPage * QuesPerPage + 4).postID == -1) {
            p5Title.setOpacity(0);
            p5Title.setDisable(true);
        } else {
            p5Title.setOpacity(1);
            p5Title.setDisable(false);
        }
        if (receive.get(currPage * QuesPerPage + 5).postID == -1) {
            p6Title.setOpacity(0);
            p6Title.setDisable(true);
        } else {
            p6Title.setOpacity(1);
            p6Title.setDisable(false);
        }
        if (receive.get(currPage * QuesPerPage + 6).postID == -1) {
            p7Title.setOpacity(0);
            p7Title.setDisable(true);
        } else {
            p7Title.setOpacity(1);
            p7Title.setDisable(false);
        }
    }

    @FXML
    private void btnPreviousForumClicked(ActionEvent event) {
        currPage--;
        try {
            loadForumBase();
        } catch (SQLException ex) {
            System.out.println("Loading failed");
        }
        if (currPage == 0) {
            btnPreviousForum.setDisable(true);
        }
        btnNextForum.setDisable(false);
        btnCenterForum.setText(currPage + 1 + "");
        checkAvailability();

    }

    @FXML
    private void btnNextForumClicked(ActionEvent event) {
        currPage++;
        try {
            loadForumBase();
        } catch (SQLException ex) {
            System.out.println("Loading Failed");
        }
        if (currPage + 1 == quesCount / QuesPerPage) {
            btnNextForum.setDisable(true);
        }
        btnPreviousForum.setDisable(false);
        btnCenterForum.setText(currPage + 1 + "");
        checkAvailability();

    }

}
