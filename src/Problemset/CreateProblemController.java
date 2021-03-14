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
    private TextArea txtInput;
    @FXML
    private TextArea txtOutput;
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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void btnSubmitPressed(ActionEvent event) {
        new ProblemSQL().writeProblem(txtTitle.getText(), txtProblem.getText(), txtInput.getText(), txtOutput.getText(), this);
    }
    
    @FXML
    private void btnBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }
    
    public void clearFields() {
        txtTitle.setText(""); 
        txtProblem.setText(""); 
        txtInput.setText("");
        txtOutput.setText("");
    }
    
}
