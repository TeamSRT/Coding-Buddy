/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

import Main.Utility;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class ProblemsetController implements Initializable {

    @FXML
    private AnchorPane anchorPracticeMain;
    @FXML
    private Button btnCreate;
    @FXML
    private Label lblProblem1;
    @FXML
    private Label lblAuthor1;
    @FXML
    private Label lblCount1;
    @FXML
    private Label lblProblem2;
    @FXML
    private Label lblAuthor2;
    @FXML
    private Label lblCount2;
    @FXML
    private Label lblProblem3;
    @FXML
    private Label lblAuthor3;
    @FXML
    private Label lblCount3;
    @FXML
    private Label lblProblem4;
    @FXML
    private Label lblAuthro4;
    @FXML
    private Label lblCount4;
    @FXML
    private Label lblProblem5;
    @FXML
    private Label lblAuthor5;
    @FXML
    private Label lblCount5;
    @FXML
    private Label lblProblem6;
    @FXML
    private Label lblAuthor6;
    @FXML
    private Label lblCount6;
    @FXML
    private Text txtPageCount;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;
    @FXML
    private Pane problemPane1;
    @FXML
    private Pane problemPane2;
    @FXML
    private Pane problemPane3;
    @FXML
    private Pane problemPane4;
    @FXML
    private Pane problemPane5;
    @FXML
    private Pane problemPane6;
    @FXML
    private Button btnRefresh;
    @FXML
    private FontAwesomeIconView iconRefresh;

    private ArrayList<Problem> listProblem;
    private int problemCount;
    private int currPage;
    private final int problemPerPage = 6;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAll();
        Tooltip.install(btnRefresh, new Tooltip("Refresh this page"));
    }

    public void loadAll() {
        try {
            if (Utility.checkPrivillage()) {
                btnCreate.setDisable(false);
            } else {
                btnCreate.setDisable(true);
            }
            listProblem = new ProblemSQL().readProblem();
            problemCount = listProblem.size();
            //System.out.println(problemCount);
            currPage = 0;
            if (problemCount % 6 != 0) {
                for (int i = 0; i < 6 - (problemCount % 6); ++i) {
                    listProblem.add(new Problem("", "", "", "", "", "", "", "", "", 0, -1));
                }
            }
            showProblem();
            problemCount = listProblem.size();
            if (currPage >= problemCount / problemPerPage - 1) {
                btnNext.setDisable(true);
            }
            btnPrevious.setDisable(true);
            txtPageCount.setText(currPage + "");
            checkAvailability();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error in loadAll of ProblemsetController.");
        }
    }

    public void showProblem() throws SQLException {
        lblProblem1.setText(listProblem.get(currPage * problemPerPage + 0).title);
        lblProblem2.setText(listProblem.get(currPage * problemPerPage + 1).title);
        lblProblem3.setText(listProblem.get(currPage * problemPerPage + 2).title);
        lblProblem4.setText(listProblem.get(currPage * problemPerPage + 3).title);
        lblProblem5.setText(listProblem.get(currPage * problemPerPage + 4).title);
        lblProblem6.setText(listProblem.get(currPage * problemPerPage + 5).title);
        lblAuthor1.setText("Author: " + listProblem.get(currPage * problemPerPage + 0).author);
        lblAuthor2.setText("Author: " + listProblem.get(currPage * problemPerPage + 1).author);
        lblAuthor3.setText("Author: " + listProblem.get(currPage * problemPerPage + 2).author);
        lblAuthro4.setText("Author: " + listProblem.get(currPage * problemPerPage + 3).author);
        lblAuthor5.setText("Author: " + listProblem.get(currPage * problemPerPage + 4).author);
        lblAuthor6.setText("Author: " + listProblem.get(currPage * problemPerPage + 5).author);
        lblCount1.setText("" + new ProblemSQL().countSubmission(listProblem.get(currPage * problemPerPage + 0).problemID));
        lblCount2.setText("" + new ProblemSQL().countSubmission(listProblem.get(currPage * problemPerPage + 1).problemID));
        lblCount3.setText("" + new ProblemSQL().countSubmission(listProblem.get(currPage * problemPerPage + 2).problemID));
        lblCount4.setText("" + new ProblemSQL().countSubmission(listProblem.get(currPage * problemPerPage + 3).problemID));
        lblCount5.setText("" + new ProblemSQL().countSubmission(listProblem.get(currPage * problemPerPage + 4).problemID));
        lblCount6.setText("" + new ProblemSQL().countSubmission(listProblem.get(currPage * problemPerPage + 5).problemID));
        if (new ProblemSQL().isSolved(listProblem.get(currPage * problemPerPage + 0).problemID)) {
            lblProblem1.setTextFill(Color.FORESTGREEN);
        } else {
            lblProblem1.setTextFill(Color.BLACK);
        }
        if (new ProblemSQL().isSolved(listProblem.get(currPage * problemPerPage + 1).problemID)) {
            lblProblem2.setTextFill(Color.FORESTGREEN);
        } else {
            lblProblem2.setTextFill(Color.BLACK);
        }
        if (new ProblemSQL().isSolved(listProblem.get(currPage * problemPerPage + 2).problemID)) {
            lblProblem3.setTextFill(Color.FORESTGREEN);
        } else {
            lblProblem3.setTextFill(Color.BLACK);
        }
        if (new ProblemSQL().isSolved(listProblem.get(currPage * problemPerPage + 3).problemID)) {
            lblProblem4.setTextFill(Color.FORESTGREEN);
        } else {
            lblProblem4.setTextFill(Color.BLACK);
        }
        if (new ProblemSQL().isSolved(listProblem.get(currPage * problemPerPage + 4).problemID)) {
            lblProblem5.setTextFill(Color.FORESTGREEN);
        } else {
            lblProblem5.setTextFill(Color.BLACK);
        }
        if (new ProblemSQL().isSolved(listProblem.get(currPage * problemPerPage + 5).problemID)) {
            lblProblem6.setTextFill(Color.FORESTGREEN);
        } else {
            lblProblem6.setTextFill(Color.BLACK);
        }
    }

    @FXML
    private void btnCreatePressed(ActionEvent event) throws IOException {
        CreateProblemController.curr = null;
        new Utility().loadPane("/Problemset/CreateProblem.fxml");
    }

    @FXML
    private void paneProb1Pressed(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = listProblem.get(currPage * problemPerPage + 0);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void paneProb2Pressed(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = listProblem.get(currPage * problemPerPage + 1);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void paneProb3Pressed(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = listProblem.get(currPage * problemPerPage + 2);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void paneProb4Pressed(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = listProblem.get(currPage * problemPerPage + 3);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void paneProb5Pressed(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = listProblem.get(currPage * problemPerPage + 4);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void paneProb6Pressed(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = listProblem.get(currPage * problemPerPage + 5);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void btnNextPressed(ActionEvent event) throws SQLException {
        currPage++;
        showProblem();
        if (currPage + 1 == problemCount / problemPerPage) {
            btnNext.setDisable(true);
        }
        btnPrevious.setDisable(false);
        txtPageCount.setText(currPage + "");
        checkAvailability();
    }

    @FXML
    private void btnPreviousPressed(ActionEvent event) throws SQLException {
        currPage--;
        showProblem();
        if (currPage == 0) {
            btnPrevious.setDisable(true);
        }
        btnNext.setDisable(false);
        txtPageCount.setText(currPage + "");
        checkAvailability();
    }

    private void checkAvailability() {
        if (listProblem.get(currPage * problemPerPage + 0).problemID == -1) {
            problemPane1.setOpacity(0);
            problemPane1.setDisable(true);
        } else {
            problemPane1.setOpacity(1);
            problemPane1.setDisable(false);
        }
        if (listProblem.get(currPage * problemPerPage + 1).problemID == -1) {
            problemPane2.setOpacity(0);
            problemPane2.setDisable(true);
        } else {
            problemPane2.setOpacity(1);
            problemPane2.setDisable(false);
        }
        if (listProblem.get(currPage * problemPerPage + 2).problemID == -1) {
            problemPane3.setOpacity(0);
            problemPane3.setDisable(true);
        } else {
            problemPane3.setOpacity(1);
            problemPane3.setDisable(false);
        }
        if (listProblem.get(currPage * problemPerPage + 3).problemID == -1) {
            problemPane4.setOpacity(0);
            problemPane4.setDisable(true);
        } else {
            problemPane4.setOpacity(1);
            problemPane4.setDisable(false);
        }
        if (listProblem.get(currPage * problemPerPage + 4).problemID == -1) {
            problemPane5.setOpacity(0);
            problemPane5.setDisable(true);
        } else {
            problemPane5.setOpacity(1);
            problemPane5.setDisable(false);
        }
        if (listProblem.get(currPage * problemPerPage + 5).problemID == -1) {
            problemPane6.setOpacity(0);
            problemPane6.setDisable(true);
        } else {
            problemPane6.setOpacity(1);
            problemPane6.setDisable(false);
        }
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        loadAll();
    }

}
