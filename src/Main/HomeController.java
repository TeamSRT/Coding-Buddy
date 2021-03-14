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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class HomeController implements Initializable {

    @FXML
    private FontAwesomeIconView iconHome;
    @FXML
    private JFXButton btnProbSet;
    @FXML
    private JFXButton btnForum;
    @FXML
    private JFXButton btnAbout;
    @FXML
    private JFXButton btnHome;
    @FXML
    private FontAwesomeIconView iconProbSet;
    @FXML
    private FontAwesomeIconView iconForum;
    @FXML
    private FontAwesomeIconView iconAbout;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private FontAwesomeIconView iconDashboard;
    @FXML
    private FontAwesomeIconView iconSignout;
    @FXML
    private Pane PaneNavigation;
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
    private JFXButton btnSignOut;
    @FXML
    private AnchorPane apTop;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnHome.setTooltip(new Tooltip("Home"));
        Utility.setHome(this);
        lblUserName.setText("Welcome " + Utility.username + "!");
    }

    @FXML
    private void iconProbSetEnter(MouseEvent event) {

    }

    @FXML
    private void btnForumOnAction(ActionEvent event) throws IOException {
        new Utility().loadPane("/Forum/ForumBase.fxml");
    }

    @FXML
    private void btnProbSetOnAction(ActionEvent event) throws IOException {
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }

    public BorderPane getTestContent() {
        return testContent;
    }

    @FXML
    private void btnSignOutClicked(ActionEvent event) {

    }
}
