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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class SubmitCodeController implements Initializable {

    @FXML
    private AnchorPane anchorPracticeCustom;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextArea txtInput;
    @FXML
    private TextArea txtOutput;
    @FXML
    private MenuButton btnLang;
    @FXML
    private TextArea txtCode;
    @FXML
    private Button btnCustomBack;
    @FXML
    private MenuItem btnLangC;
    @FXML
    private MenuItem btnLangCpp;
    @FXML
    private MenuItem btnLangJava;
    @FXML
    private MenuItem btnLangPython;

    /**
     * Utility variables and Functions
     */
    private String currLang = "c";
    @FXML
    private Label lblInput;

    public static int problemID;
    public static String output;
    private boolean isCustom;
    @FXML
    private MenuItem btnLangCsharp;

    public void flipbtnSubmit() {
        btnSubmit.setDisable(!btnSubmit.isDisable());
    }

    public void settxtOutput(String output) {
        txtOutput.setText(output);
    }

    /**
     *
     * Combo Button Functions
     */
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSubmitPressed(ActionEvent event) throws SQLException {
        
        String code = txtCode.getText();
        String input = txtInput.getText();
        
        Compiler submission = new Compiler(code, input, currLang, this);
        Thread subThread = new Thread(submission);
        subThread.start();
    }

    @FXML
    private void btnCustomBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Problemset/Problemset.fxml");
    }

    @FXML
    private void btnLangCsharpPressed(ActionEvent event) {
        currLang = "csharp";
        btnLang.setText("C#");
    }

}
