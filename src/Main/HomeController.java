/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class HomeController implements Initializable {
    
    @FXML
    private FontAwesomeIconView iconHome;
    @FXML
    private Button btnProbSet;
    @FXML
    private Button btnForum;
    @FXML
    private Button btnHome;
    @FXML
    private FontAwesomeIconView iconProbSet;
    @FXML
    private FontAwesomeIconView iconForum;
    @FXML
    private Button btnDashboard;
    @FXML
    private FontAwesomeIconView iconDashboard;
    private AnchorPane APContent;
    @FXML
    private Label lblUserName;
    @FXML
    private BorderPane testContent;
    @FXML
    private AnchorPane apBase;
    @FXML
    private AnchorPane apBtn;
    @FXML
    private Button btnSignOut;
    @FXML
    private AnchorPane apTop;
    @FXML
    private Button btnProfile;
    @FXML
    private FontAwesomeIconView iconProfile;
    @FXML
    private ImageView iconSIgnOut;
    @FXML
    private Button btnComplier;
    @FXML
    private FontAwesomeIconView iconComplier;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        try {
            Utility.setHome(this);
            lblUserName.setText("Welcome " + Utility.username + "!");
            new Utility().loadPane("/Home/Home.fxml");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    @FXML
    private void btnForumOnAction(ActionEvent event) throws IOException {
        //  btnForum.setStyle("-fx-background-color: #4e4e4e;");
        focusFix(btnForum);
        new Utility().loadPane("/Forum/ForumBase.fxml");
    }
    
    @FXML
    private void btnProbSetOnAction(ActionEvent event) throws IOException {
        focusFix(btnProbSet);
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }
    
    public BorderPane getTestContent() {
        return testContent;
    }
    
    @FXML
    private void btnSignOutClicked(ActionEvent event) throws IOException {
        focusFix(btnSignOut);
        Home.pre.put("username", "");
        Home.pre.put("password", "");
        Parent root = FXMLLoader.load(getClass().getResource("/Login/mainGUI.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();
    }
    
    @FXML
    private void btnDashboardOnAction(ActionEvent event) throws IOException {
        focusFix(btnDashboard);
        new Utility().loadPane("/Dashboard/Dashboard.fxml");
    }
    
    @FXML
    private void btnCompilerOnAction(ActionEvent event) throws IOException {
        focusFix(btnComplier);
        new Utility().loadPane("/Problemset/SubmitCode.fxml");
    }
    
    @FXML
    private void btnHomeOnAction(ActionEvent event) throws IOException {
        focusFix(btnHome);
        new Utility().loadPane("/Home/Home.fxml");
    }

    @FXML
    private void btnProfileOnAction(ActionEvent event) throws IOException {
        focusFix(btnProfile);
        new Utility().loadPane("/Profile/Profile.fxml");
    }
    
    @FXML
    private void btnCodingBuddyPressed(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AboutUs/AboutUs.fxml"));
        Stage stage = new Stage();
        stage.setTitle("About US");
        stage.setScene(new Scene(root, 600, 275));
        stage.show();
    }
    
    public void focusFix(Button btnCurr) {
        btnComplier.setStyle("-fx-background-color:  #1D2026;");
        btnDashboard.setStyle("-fx-background-color:  #1D2026;");
        btnForum.setStyle("-fx-background-color:  #1D2026;");
        btnHome.setStyle("-fx-background-color:  #1D2026;");
        btnProbSet.setStyle("-fx-background-color:  #1D2026;");
        btnProfile.setStyle("-fx-background-color:  #1D2026;");
        btnSignOut.setStyle("-fx-background-color:  #1D2026;");
        btnCurr.setStyle("-fx-background-color: #4e4e4e;");
    }
    
    public Button getBtnForum() {
        return btnForum;
    }
    
    public Button getBtnProbSet() {
        return btnProbSet;
    }
    
}
