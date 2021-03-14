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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class ProblemViewController implements Initializable {

    @FXML
    private AnchorPane anchorPracticeCustom;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtInput;
    @FXML
    private TextArea txtOutput;
    @FXML
    private TextArea txtProblem;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnBack;
    
    public static Problem currProblem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProblem();
    }    

    @FXML
    private void btnSubmitPressed(ActionEvent event) {
        
    }

    @FXML
    private void btnBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }
    
    public void loadProblem() {
        txtTitle.setText(currProblem.title);
        txtInput.setText(currProblem.input);
        txtOutput.setText(currProblem.output);
        txtProblem.setText(currProblem.body);
    }
    
}