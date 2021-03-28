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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class SubmitSolutionController extends SubmitCodeController implements Initializable {

    @FXML
    private AnchorPane anchorPracticeCustom;
    @FXML
    private TextArea txtOutput;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCustomBack;
    @FXML
    private MenuButton btnLang;
    @FXML
    private MenuItem btnLangC;
    @FXML
    private MenuItem btnLangCpp;
    @FXML
    private MenuItem btnLangJava;
    @FXML
    private MenuItem btnLangPython;
    @FXML
    private TextArea txtCode;
    @FXML
    private Label lblStatus;
    private String currLang = "c";
    public static int problemID;
    //public static String output;
    private String expectedOutput1 = "";
    private String expectedOutput2 = "";
    private String expectedOutput3 = "";
    private String input;
    public static Problem currProblem;
    Thread tSub1, tSub2, tSub3;
    @FXML
    private MenuItem btnLangCsharp;
    @FXML
    private Label lblStatus1;
    @FXML
    private Label lblStatus2;
    @FXML
    private Label lblStatus3;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblStatus1.setOpacity(0);
        lblStatus2.setOpacity(0);
        lblStatus3.setOpacity(0);
    }

    @FXML
    private void btnSubmitPressed(ActionEvent event) throws SQLException {
        String code = txtCode.getText();
        input = new ProblemSQL().getInput(problemID, 1);
        expectedOutput1 = new ProblemSQL().getOutput(problemID, 1);
        tSub1 = new Thread(new Compiler(code, input, currLang, 1, currProblem.timeLimit, this));
        tSub1.start();
        input = new ProblemSQL().getInput(problemID, 2);
        expectedOutput2 = new ProblemSQL().getOutput(problemID, 2);
        tSub2 = new Thread(new Compiler(code, input, currLang, 2,currProblem.timeLimit, this));
        tSub2.start();
        input = new ProblemSQL().getInput(problemID, 3);
        expectedOutput3 = new ProblemSQL().getOutput(problemID, 3);
        tSub3 = new Thread(new Compiler(code, input, currLang, 3,currProblem.timeLimit, this));
        tSub3.start();
    }

    @FXML
    private void btnCustomBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }

    @FXML
    private void btnLangCPressed(ActionEvent event) {
        currLang = "c";
        btnLang.setText("C");
    }

    @FXML
    private void btnLangCppPressed(ActionEvent event) {
        currLang = "cpp";
        btnLang.setText("C++");
    }

    @FXML
    private void btnLangJavaPressed(ActionEvent event) {
        currLang = "java";
        btnLang.setText("Java");
    }

    @FXML
    private void btnLangPythonPressed(ActionEvent event) {
        currLang = "python3";
        btnLang.setText("Python3");
    }
    
    @FXML
    private void btnLangCsharpPressed(ActionEvent event) {
        currLang = "csharp";
        btnLang.setText("C#");
    }
    
    @Override
    public void flipbtnSubmit() {
        btnSubmit.setDisable(!btnSubmit.isDisable());
        if (!btnSubmit.isDisable()) {
            Platform.runLater(() -> updateStatus());
        }
    }

    public void updateStatus() {
        if (!tSub1.isAlive() && !tSub2.isAlive() && !tSub3.isAlive()) {
            boolean m1 = expectedOutput1.equals(Compiler.expectedOutput1);
            boolean m2 = expectedOutput2.equals(Compiler.expectedOutput2);
            boolean m3 = expectedOutput3.equals(Compiler.expectedOutput3);
            lblStatus1.setOpacity(1);
            lblStatus2.setOpacity(2);
            lblStatus3.setOpacity(3);
            if (!m1) {
                lblStatus1.setText("Status: Wrong Answer in Test Case 1.");
                lblStatus1.setStyle("-fx-text-fill : red");
            } else {
                lblStatus1.setText("Status: Passed Test Case 1.");
                lblStatus1.setStyle("-fx-text-fill : green");
            } 
            if (!m2) {
                lblStatus2.setText("Status: Wrong Answer in Test Case 2.");
                lblStatus2.setStyle("-fx-text-fill : red");
            } else {
                lblStatus2.setText("Status: Passed Test Case 2.");
                lblStatus2.setStyle("-fx-text-fill : green");
            } 
            if (!m3) {
                lblStatus3.setText("Status: Wrong Answer in Test Case 3.");
                lblStatus3.setStyle("-fx-text-fill : red");
            } else {
                lblStatus3.setText("Status: Passed Test Case 3.");
                lblStatus3.setStyle("-fx-text-fill : green");
            }
            try {
                if (!m1 || !m2 || !m3) {
                    new ProblemSQL().recordSubmission(problemID, -1, currLang, txtCode.getText());
                } else {
                    new ProblemSQL().recordSubmission(problemID, 1, currLang, txtCode.getText());
                    new ProblemSQL().completeToDo(problemID);
                }
            } catch (SQLException ex) {
                System.out.println("Error While Submitting Record " + ex);
            }
        }
    }

   
}
