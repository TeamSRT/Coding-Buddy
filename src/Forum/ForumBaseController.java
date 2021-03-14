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
import java.util.*;
import java.util.ResourceBundle;
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
    private Button btnPrevious;
    @FXML
    private Button btnNext;
    @FXML
    private ImageView imgHolder1;
    @FXML
    private ImageView imgHolder2;
    @FXML
    private ImageView imgHolder3;
    @FXML
    private ImageView imgHolder4;
    @FXML
    private ImageView imgHolder5;
    @FXML
    private ImageView imgHolder6;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Forum reader = new Forum();
        try {
            reader.connection();
        } catch (SQLException ex) {
            System.out.println("Connection failed");
        }
        receive = reader.readForum(this);
        loadForumBase();
    }

    @FXML
    private void btnAskQClicked(ActionEvent event) throws IOException {
        new Main.Utility().loadPane("/Forum/AskQues.fxml");
    }

    private void loadForumBase() {
        imgHolder1.setImage(Utility.getImage(receive.get(0).username));
        imgHolder2.setImage(Utility.getImage(receive.get(1).username));
        imgHolder3.setImage(Utility.getImage(receive.get(2).username));
        imgHolder4.setImage(Utility.getImage(receive.get(3).username));
        imgHolder5.setImage(Utility.getImage(receive.get(4).username));
        imgHolder6.setImage(Utility.getImage(receive.get(5).username));
        lblForumUser1.setText("Asked by " + receive.get(0).username);
        lblShowTitle1.setText(receive.get(0).title);
        lblVote1.setText(receive.get(0).vote + " ");
        lblAns1.setText(receive.get(0).answer + " ");
        lblForumUser2.setText("Asked by " + receive.get(1).username);
        lblShowTitle2.setText(receive.get(1).title);
        lblVote2.setText(receive.get(1).vote + " ");
        lblAns2.setText(receive.get(1).answer + " ");
        lblForumUser3.setText("Asked by " + receive.get(2).username);
        lblShowTitle3.setText(receive.get(2).title);
        lblVote3.setText(receive.get(2).vote + " ");
        lblAns3.setText(receive.get(2).answer + " ");
        lblForumUser4.setText("Asked by " + receive.get(3).username);
        lblShowTitle4.setText(receive.get(3).title);
        lblVote4.setText(receive.get(3).vote + " ");
        lblAns4.setText(receive.get(3).answer + " ");
        lblForumUser5.setText("Asked by " + receive.get(4).username);
        lblShowTitle5.setText(receive.get(4).title);
        lblVote5.setText(receive.get(4).vote + " ");
        lblAns5.setText(receive.get(4).answer + " ");
        lblForumUser6.setText("Asked by " + receive.get(5).username);
        lblShowTitle6.setText(receive.get(5).title);
        lblVote6.setText(receive.get(5).vote + " ");
        lblAns6.setText(receive.get(5).answer + " ");
        lblForumUser7.setText("Asked by " + receive.get(6).username);
        lblShowTitle7.setText(receive.get(6).title);
        lblVote7.setText(receive.get(6).vote + " ");
        lblAns7.setText(receive.get(6).answer + " ");
    }

    @FXML
    private void lblShowTitle1Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(0);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle2Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(1);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle3Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(2);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle4Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(3);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle5Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(4);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle6Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(5);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }

    @FXML
    private void lblShowTitle7Clicked(MouseEvent event) throws IOException {
        ShowQuesController.showObj = receive.get(6);
        Pane ForumShowQ = FXMLLoader.load(getClass().getResource("/Forum/ShowQues.fxml"));
        Main.Utility.Home.getTestContent().setCenter(ForumShowQ);
    }
}
