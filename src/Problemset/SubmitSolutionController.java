/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

import Main.Utility;
import static Problemset.SubmitCodeController.output;
import static Problemset.SubmitCodeController.problemID;
import static com.sun.prism.paint.Paint.Type.COLOR;
import java.awt.Color;
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
    private String expectedOutput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSubmitPressed(ActionEvent event) throws SQLException {
        String code = txtCode.getText();
        String input = new ProblemSQL().getInput(problemID);
        expectedOutput = new ProblemSQL().getOutput(problemID);
        Compiler submission = new Compiler(code, input, currLang, this);
        Thread subThread = new Thread(submission);
        subThread.start();

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

    @Override
    public void flipbtnSubmit() {
        btnSubmit.setDisable(!btnSubmit.isDisable());
        if(!btnSubmit.isDisable()) {
            Platform.runLater(()-> updateStatus());
        }
    }

    public void updateStatus() {
        System.out.println(expectedOutput.length() + " " + output.length());
        if (expectedOutput.equals(output)) {
            lblStatus.setText("Status: Accepted.");
            lblStatus.setStyle("-fx-text-fill : green");
        } else {
            lblStatus.setText("Status: Wrong Answer.");
            lblStatus.setStyle("-fx-text-fill : red");
        }
    }
}
