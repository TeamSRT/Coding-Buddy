/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

import Main.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class CreateProblemController implements Initializable {

    @FXML
    private TextArea txtProblem;
    @FXML
    private TextField txtTitle;
    @FXML
    private AnchorPane anchorPracticeCustom;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnBack;
    @FXML
    private TextArea txtInput1;
    @FXML
    private TextArea txtOutput2;
    @FXML
    private TextArea txtInput2;
    @FXML
    private TextArea txtInput3;
    @FXML
    private TextArea txtOutput1;
    @FXML
    private TextArea txtOutput3;
    @FXML
    private MenuItem btnT1;
    @FXML
    private MenuItem btnT2;
    @FXML
    private MenuItem btnT3;
    @FXML
    private MenuButton btnTime;
    private int Time = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSubmitPressed(ActionEvent event) {
        new ProblemSQL().writeProblem(txtTitle.getText(), txtProblem.getText(), txtInput1.getText(), txtOutput1.getText(), txtInput2.getText(), txtOutput2.getText(), txtInput3.getText(), txtOutput3.getText(), Time, this);
    }

    @FXML
    private void btnBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }

    public void clearFields() {
        txtTitle.setText("");
        txtProblem.setText("");
        txtInput1.setText("");
        txtOutput1.setText("");
        txtInput2.setText("");
        txtOutput2.setText("");
        txtInput3.setText("");
        txtOutput3.setText("");
    }

    @FXML
    private void btnT1OnAction(ActionEvent event) {
        btnTime.setText("1s");
        Time = 1;
    }

    @FXML
    private void btnT2OnAction(ActionEvent event) {
        btnTime.setText("2s");
        Time = 2;
    }

    @FXML
    private void btnT3OnAction(ActionEvent event) {
        btnTime.setText("3s");
        Time = 3;
    }

}
