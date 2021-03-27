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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Thread tSub1, tSub2, tSub3;
    @FXML
    private MenuItem btnLangCsharp;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSubmitPressed(ActionEvent event) throws SQLException {
        String code = txtCode.getText();
        input = new ProblemSQL().getInput(problemID, 1);
        expectedOutput1 = new ProblemSQL().getOutput(problemID, 1);
        tSub1 = new Thread(new Compiler(code, input, currLang, 1, this));
        tSub1.start();
        input = new ProblemSQL().getInput(problemID, 2);
        expectedOutput2 = new ProblemSQL().getOutput(problemID, 2);
        tSub2 = new Thread(new Compiler(code, input, currLang, 2, this));
        tSub2.start();
        input = new ProblemSQL().getInput(problemID, 3);
        expectedOutput3 = new ProblemSQL().getOutput(problemID, 3);
        tSub3 = new Thread(new Compiler(code, input, currLang, 3, this));
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
            if (!m1) {
                lblStatus.setText("Status: Wrong Answer in Test Case 1.");
                lblStatus.setStyle("-fx-text-fill : red");
            } else if (!m2) {
                lblStatus.setText("Status: Wrong Answer in Test Case 2.");
                lblStatus.setStyle("-fx-text-fill : red");
            } else if (!m3) {
                lblStatus.setText("Status: Wrong Answer in Test Case 3.");
                lblStatus.setStyle("-fx-text-fill : red");
            } else {
                lblStatus.setText("Status: Accepted.");
                lblStatus.setStyle("-fx-text-fill : green");
            }

            try {
                if (!m1 || !m2 || !m3) {
                    new ProblemSQL().recordSubmission(problemID, -1, currLang, txtCode.getText());
                } else {
                    new ProblemSQL().recordSubmission(problemID, 1, currLang, txtCode.getText());
                }
            } catch (SQLException ex) {
                System.out.println("Error While Submitting Record " + ex);
            }
        }
    }

   
}
