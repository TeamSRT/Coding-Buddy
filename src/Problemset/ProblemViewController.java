/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

import Main.Utility;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Button btnToDo;
    @FXML
    private Label lblTime;
    @FXML
    private Button btnEdit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTime.setText("Time Limit: " + currProblem.timeLimit + "s");
        loadProblem();
        System.out.println(currProblem.author);
        if(currProblem.author.equals(Main.Utility.username)) {
            btnEdit.setDisable(false);
            btnEdit.setOpacity(1);
        } else {
            btnEdit.setDisable(true);
            btnEdit.setOpacity(0);
        }
    }    

    @FXML
    private void btnSubmitPressed(ActionEvent event) throws IOException {
        SubmitSolutionController.problemID = currProblem.problemID;
        SubmitSolutionController.currProblem = currProblem;
        new Utility().loadPane("/Problemset/SubmitSolution.fxml");
    }

    @FXML
    private void btnBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }
    
    public void loadProblem() {
        boolean isTodo = new ProblemSQL().checkToDo(currProblem.problemID);
        boolean isSub = new ProblemSQL().checkSubmission(currProblem.problemID);
        btnToDo.setDisable(isTodo || isSub);
        txtTitle.setText(currProblem.title);
        txtInput.setText(currProblem.input1);
        txtOutput.setText(currProblem.output1);
        txtProblem.setText(currProblem.body);
    }

    @FXML
    private void btnToDoOnAction(ActionEvent event) throws SQLException {
        new ProblemSQL().recordToDo(currProblem.problemID);
        loadProblem();
    }

    @FXML
    private void btnEditOnAction(ActionEvent event) throws IOException {
        CreateProblemController.curr = currProblem;
        new Utility().loadPane("/Problemset/CreateProblem.fxml");
    }
    
}
