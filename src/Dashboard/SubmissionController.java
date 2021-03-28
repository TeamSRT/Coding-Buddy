/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Main.Utility;
import Problemset.ProblemSQL;
import Problemset.Submission;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class SubmissionController implements Initializable {

    @FXML
    private Button btnBack;
    private int sizeCount;
    private int currPage;
    private final int BoxPerPage = 12;
    ArrayList<Submission> receive;
    
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnCenter;
    @FXML
    private HBox h1sub;
    @FXML
    private HBox h2sub;
    @FXML
    private HBox h3sub;
    @FXML
    private HBox h4sub;
    @FXML
    private HBox h5sub;
    @FXML
    private HBox h6sub;
    @FXML
    private HBox h7sub;
    @FXML
    private HBox h8sub;
    @FXML
    private HBox h9sub;
    @FXML
    private HBox h10sub;
    @FXML
    private HBox h11sub;
    @FXML
    private HBox h12sub;
    @FXML
    private Label lbl1ProbName;
    @FXML
    private Label lbl1Ans;
    @FXML
    private Label lbl1Sub;
    @FXML
    private Label lbl1Lang;
    @FXML
    private Label lbl1SubDate;
    @FXML
    private Label lbl2ProbName;
    @FXML
    private Label lbl2Ans;
    @FXML
    private Label lbl2Sub;
    @FXML
    private Label lbl2Lang;
    @FXML
    private Label lbl2SubDate;
    @FXML
    private Label lbl3ProbName;
    @FXML
    private Label lbl3Ans;
    @FXML
    private Label lbl3Sub;
    @FXML
    private Label lbl3Lang;
    @FXML
    private Label lbl3SubDate;
    @FXML
    private Label lbl4ProbName;
    @FXML
    private Label lbl4Ans;
    @FXML
    private Label lbl4Sub;
    @FXML
    private Label lbl4Lang;
    @FXML
    private Label lbl4SubDate;
    @FXML
    private Label lbl5ProbName;
    @FXML
    private Label lbl5Ans;
    @FXML
    private Label lbl5Sub;
    @FXML
    private Label lbl5Lang;
    @FXML
    private Label lbl5SubDate;
    @FXML
    private Label lbl6ProbName;
    @FXML
    private Label lbl6Ans;
    @FXML
    private Label lbl6Sub;
    @FXML
    private Label lbl6Lang;
    @FXML
    private Label lbl6SubDate;
    @FXML
    private Label lbl7ProbName;
    @FXML
    private Label lbl7Ans;
    @FXML
    private Label lbl7Sub;
    @FXML
    private Label lbl7Lang;
    @FXML
    private Label lbl7SubDate;
    @FXML
    private Label lbl8ProbName;
    @FXML
    private Label lbl8Ans;
    @FXML
    private Label lbl8Sub;
    @FXML
    private Label lbl8Lang;
    @FXML
    private Label lbl8SubDate;
    @FXML
    private Label lbl9ProbName;
    @FXML
    private Label lbl9Ans;
    @FXML
    private Label lbl9Sub;
    @FXML
    private Label lbl9Lang;
    @FXML
    private Label lbl9SubDate;
    @FXML
    private Label lbl10ProbName;
    @FXML
    private Label lbl10Ans;
    @FXML
    private Label lbl10Sub;
    @FXML
    private Label lbl10Lang;
    @FXML
    private Label lbl10SubDate;
    @FXML
    private Label lbl11ProbName;
    @FXML
    private Label lbl11Ans;
    @FXML
    private Label lbl11Sub;
    @FXML
    private Label lbl11Lang;
    @FXML
    private Label lbl11SubDate;
    @FXML
    private Label lbl12ProbName;
    @FXML
    private Label lbl12Ans;
    @FXML
    private Label lbl12Sub;
    @FXML
    private Label lbl12Lang;
    @FXML
    private Label lbl12SubDate;
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
       loadAll();
    }       
    
    public void loadAll() {
        currPage = 0;
        receive = new ProblemSQL().readSubmission();
        sizeCount = receive.size();
        
        if (sizeCount % 12 != 0) {
            for (int i = 0; i < 12 - (sizeCount % 12); i++) {
                Submission obj = new Submission("", "", "", "", "2021-01-01", "00:00:00");
                receive.add(obj);
            }
        }
        sizeCount = receive.size();       
        if(sizeCount == 0)
        {
            for (int i = 0; i < 12; i++) {
                Submission obj = new Submission("", "", "", "", "2021-01-01", "00:00:00");
                receive.add(obj);
            }
        }
         try {
            loadSubmission();
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        if (currPage >= sizeCount / BoxPerPage - 1) {
            btnNext.setDisable(true);
        }
        btnPrev.setDisable(true);
        btnCenter.setText(currPage + 1 + "");
       checkAvailability();
    }
    
    public void checkAvailability() {
        if (receive.get(currPage * BoxPerPage + 0).lang.equals("")) {
            h1sub.setOpacity(0);
            h1sub.setDisable(true);
        } else {
            h1sub.setOpacity(1);
            h1sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 1).lang.equals("")) {
            h2sub.setOpacity(0);
            h2sub.setDisable(true);
        } else {
            h2sub.setOpacity(1);
            h2sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 2).lang.equals("")) {
            h3sub.setOpacity(0);
            h3sub.setDisable(true);
        } else {
            h3sub.setOpacity(1);
            h3sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 3).lang.equals("")) {
            h4sub.setOpacity(0);
            h4sub.setDisable(true);
        } else {
            h4sub.setOpacity(1);
            h4sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 4).lang.equals("")) {
            h5sub.setOpacity(0);
            h5sub.setDisable(true);
        } else {
            h5sub.setOpacity(1);
            h5sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 5).lang.equals("")) {
            h6sub.setOpacity(0);
            h6sub.setDisable(true);
        } else {
            h6sub.setOpacity(1);
            h6sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 6).lang.equals("")) {
            h7sub.setOpacity(0);
            h7sub.setDisable(true);
        } else {
            h7sub.setOpacity(1);
            h7sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 7).lang.equals("")) {
            h8sub.setOpacity(0);
            h8sub.setDisable(true);
        } else {
            h8sub.setOpacity(1);
            h8sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 8).lang.equals("")) {
            h9sub.setOpacity(0);
            h9sub.setDisable(true);
        } else {
            h9sub.setOpacity(1);
            h9sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 9).lang.equals("")) {
            h10sub.setOpacity(0);
            h10sub.setDisable(true);
        } else {
            h10sub.setOpacity(1);
            h10sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 10).lang.equals("")) {
            h11sub.setOpacity(0);
            h11sub.setDisable(true);
        } else {
            h11sub.setOpacity(1);
            h11sub.setDisable(false);
        }
        if (receive.get(currPage * BoxPerPage + 11).lang.equals("")) {
            h12sub.setOpacity(0);
            h12sub.setDisable(true);
        } else {
            h12sub.setOpacity(1);
            h12sub.setDisable(false);
        }
    }
    public void loadSubmission() throws ParseException {
        //problemName//       
        if (receive.get(currPage * BoxPerPage + 0).problemID == 0) {
            lbl1ProbName.setText("Custom Problem");
        } else {
            lbl1ProbName.setText(receive.get(currPage * BoxPerPage + 0).probName);
        }
        if (receive.get(currPage * BoxPerPage + 1).problemID == 0) {
            lbl2ProbName.setText("Custom Problem");
        } else {
            lbl2ProbName.setText(receive.get(currPage * BoxPerPage + 1).probName);
        }
        if (receive.get(currPage * BoxPerPage + 2).problemID == 0) {
            lbl3ProbName.setText("Custom Problem");
        } else {
            lbl3ProbName.setText(receive.get(currPage * BoxPerPage + 2).probName);
        }
        if (receive.get(currPage * BoxPerPage + 3).problemID == 0) {
            lbl4ProbName.setText("Custom Problem");
        } else {
            lbl4ProbName.setText(receive.get(currPage * BoxPerPage + 3).probName);
        }
        if (receive.get(currPage * BoxPerPage + 4).problemID == 0) {
            lbl5ProbName.setText("Custom Problem");
        } else {
            lbl5ProbName.setText(receive.get(currPage * BoxPerPage + 4).probName);
        }
        if (receive.get(currPage * BoxPerPage + 5).problemID == 0) {
            lbl6ProbName.setText("Custom Problem");
        } else {
            lbl6ProbName.setText(receive.get(currPage * BoxPerPage + 5).probName);
        }
        if (receive.get(currPage * BoxPerPage + 6).problemID == 0) {
            lbl7ProbName.setText("Custom Problem");
        } else {
            lbl7ProbName.setText(receive.get(currPage * BoxPerPage + 6).probName);
        }
        if (receive.get(currPage * BoxPerPage + 7).problemID == 0) {
            lbl8ProbName.setText("Custom Problem");
        } else {
            lbl8ProbName.setText(receive.get(currPage * BoxPerPage + 7).probName);
        }
        if (receive.get(currPage * BoxPerPage + 8).problemID == 0) {
            lbl9ProbName.setText("Custom Problem");
        } else {
            lbl9ProbName.setText(receive.get(currPage * BoxPerPage + 8).probName);
        }
        if (receive.get(currPage * BoxPerPage + 9).problemID == 0) {
            lbl10ProbName.setText("Custom Problem");
        } else {
            lbl10ProbName.setText(receive.get(currPage * BoxPerPage + 9).probName);
        }
        if (receive.get(currPage * BoxPerPage + 10).problemID == 0) {
            lbl11ProbName.setText("Custom Problem");
        } else {
            lbl11ProbName.setText(receive.get(currPage * BoxPerPage + 10).probName);
        }
        if (receive.get(currPage * BoxPerPage + 11).problemID == 0) {
            lbl12ProbName.setText("Custom Problem");
        } else {
            lbl12ProbName.setText(receive.get(currPage * BoxPerPage + 11).probName);
        }
        //problemName//

        //verdict//
        switch (receive.get(currPage * BoxPerPage + 0).verdict) {
            case 0:
                lbl1Ans.setText("");
                break;
            case -1:
                lbl1Ans.setText("Wrong Answer");
                lbl1Ans.setTextFill(Color.RED);
                break;
            default:
                lbl1Ans.setText("Accepted");
                lbl1Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 1).verdict) {
            case 0:
                lbl2Ans.setText("");
                break;
            case -1:
                lbl2Ans.setText("Wrong Answer");
                lbl2Ans.setTextFill(Color.RED);
                break;
            default:
                lbl2Ans.setText("Accepted");
                lbl2Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 2).verdict) {
            case 0:
                lbl3Ans.setText("");
                break;
            case -1:
                lbl3Ans.setText("Wrong Answer");
                lbl3Ans.setTextFill(Color.RED);
                break;
            default:
                lbl3Ans.setText("Accepted");
                lbl3Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 3).verdict) {
            case 0:
                lbl4Ans.setText("");
                break;
            case -1:
                lbl4Ans.setText("Wrong Answer");
                lbl4Ans.setTextFill(Color.RED);
                break;
            default:
                lbl4Ans.setText("Accepted");
                lbl4Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 4).verdict) {
            case 0:
                lbl5Ans.setText("");
                break;
            case -1:
                lbl5Ans.setText("Wrong Answer");
                lbl5Ans.setTextFill(Color.RED);
                break;
            default:
                lbl5Ans.setText("Accepted");
                lbl5Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 5).verdict) {
            case 0:
                lbl6Ans.setText("");
                break;
            case -1:
                lbl6Ans.setText("Wrong Answer");
                lbl6Ans.setTextFill(Color.RED);
                break;
            default:
                lbl6Ans.setText("Accepted");
                lbl6Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 6).verdict) {
            case 0:
                lbl7Ans.setText("");
                break;
            case -1:
                lbl7Ans.setText("Wrong Answer");
                lbl7Ans.setTextFill(Color.RED);
                break;
            default:
                lbl7Ans.setText("Accepted");
                lbl7Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 7).verdict) {
            case 0:
                lbl8Ans.setText("");
                break;
            case -1:
                lbl8Ans.setText("Wrong Answer");
                lbl8Ans.setTextFill(Color.RED);
                break;
            default:
                lbl8Ans.setText("Accepted");
                lbl1Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 8).verdict) {
            case 0:
                lbl9Ans.setText("");
                break;
            case -1:
                lbl9Ans.setText("Wrong Answer");
                lbl9Ans.setTextFill(Color.RED);
                break;
            default:
                lbl9Ans.setText("Accepted");
                lbl9Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 9).verdict) {
            case 0:
                lbl10Ans.setText("");
                break;
            case -1:
                lbl10Ans.setText("Wrong Answer");
                lbl10Ans.setTextFill(Color.RED);
                break;
            default:
                lbl10Ans.setText("Accepted");
                lbl10Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 10).verdict) {
            case 0:
                lbl11Ans.setText("");
                break;
            case -1:
                lbl11Ans.setText("Wrong Answer");
                lbl11Ans.setTextFill(Color.RED);
                break;
            default:
                lbl11Ans.setText("Accepted");
                lbl11Ans.setTextFill(Color.GREEN);
                break;
        }
        switch (receive.get(currPage * BoxPerPage + 11).verdict) {
            case 0:
                lbl12Ans.setText("");
                break;
            case -1:
                lbl12Ans.setText("Wrong Answer");
                lbl12Ans.setTextFill(Color.RED);
                break;
            default:
                lbl12Ans.setText("Accepted");
                lbl12Ans.setTextFill(Color.GREEN);
                break;
        }

        //verdict//
        //submission track//
        lbl1Sub.setText(receive.get(currPage * BoxPerPage + 0).track + "");
        lbl2Sub.setText(receive.get(currPage * BoxPerPage + 1).track + "");
        lbl3Sub.setText(receive.get(currPage * BoxPerPage + 2).track + "");
        lbl4Sub.setText(receive.get(currPage * BoxPerPage + 3).track + "");
        lbl5Sub.setText(receive.get(currPage * BoxPerPage + 4).track + "");
        lbl6Sub.setText(receive.get(currPage * BoxPerPage + 5).track + "");
        lbl7Sub.setText(receive.get(currPage * BoxPerPage + 6).track + "");
        lbl8Sub.setText(receive.get(currPage * BoxPerPage + 7).track + "");
        lbl9Sub.setText(receive.get(currPage * BoxPerPage + 8).track + "");
        lbl10Sub.setText(receive.get(currPage * BoxPerPage + 9).track + "");
        lbl11Sub.setText(receive.get(currPage * BoxPerPage + 10).track + "");
        lbl12Sub.setText(receive.get(currPage * BoxPerPage + 11).track + "");
        //submission track//

        //language//
        lbl1Lang.setText(receive.get(currPage * BoxPerPage + 0).lang);
        lbl2Lang.setText(receive.get(currPage * BoxPerPage + 1).lang);
        lbl3Lang.setText(receive.get(currPage * BoxPerPage + 2).lang);
        lbl4Lang.setText(receive.get(currPage * BoxPerPage + 3).lang);
        lbl5Lang.setText(receive.get(currPage * BoxPerPage + 4).lang);
        lbl6Lang.setText(receive.get(currPage * BoxPerPage + 5).lang);
        lbl7Lang.setText(receive.get(currPage * BoxPerPage + 6).lang);
        lbl8Lang.setText(receive.get(currPage * BoxPerPage + 7).lang);
        lbl9Lang.setText(receive.get(currPage * BoxPerPage + 8).lang);
        lbl10Lang.setText(receive.get(currPage * BoxPerPage + 9).lang);
        lbl11Lang.setText(receive.get(currPage * BoxPerPage + 10).lang);
        lbl12Lang.setText(receive.get(currPage * BoxPerPage + 11).lang);

        //Date and Time//
        lbl1SubDate.setText(receive.get(currPage * BoxPerPage + 0).subdate + " " + receive.get(currPage * BoxPerPage + 0).subtime);
        lbl2SubDate.setText(receive.get(currPage * BoxPerPage + 1).subdate + " " + receive.get(currPage * BoxPerPage + 1).subtime);
        lbl3SubDate.setText(receive.get(currPage * BoxPerPage + 2).subdate + " " + receive.get(currPage * BoxPerPage + 2).subtime);
        lbl4SubDate.setText(receive.get(currPage * BoxPerPage + 3).subdate + " " + receive.get(currPage * BoxPerPage + 3).subtime);
        lbl5SubDate.setText(receive.get(currPage * BoxPerPage + 4).subdate + " " + receive.get(currPage * BoxPerPage + 4).subtime);
        lbl6SubDate.setText(receive.get(currPage * BoxPerPage + 5).subdate + " " + receive.get(currPage * BoxPerPage + 5).subtime);
        lbl7SubDate.setText(receive.get(currPage * BoxPerPage + 6).subdate + " " + receive.get(currPage * BoxPerPage + 6).subtime);
        lbl8SubDate.setText(receive.get(currPage * BoxPerPage + 7).subdate + " " + receive.get(currPage * BoxPerPage + 7).subtime);
        lbl9SubDate.setText(receive.get(currPage * BoxPerPage + 8).subdate + " " + receive.get(currPage * BoxPerPage + 8).subtime);
        lbl10SubDate.setText(receive.get(currPage * BoxPerPage + 9).subdate + " " + receive.get(currPage * BoxPerPage + 9).subtime);
        lbl11SubDate.setText(receive.get(currPage * BoxPerPage + 10).subdate + " " + receive.get(currPage * BoxPerPage + 10).subtime);
        lbl12SubDate.setText(receive.get(currPage * BoxPerPage + 11).subdate + " " + receive.get(currPage * BoxPerPage + 11).subtime);

    }

    @FXML
    private void btnCustomBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/Dashboard.fxml");
    }

    @FXML
    private void btnPrevClicked(ActionEvent event) {
        currPage--;
        try {
            loadSubmission();
        } catch (ParseException ex) {
            System.out.println("Submission Loading Failed");
        }
        if (currPage == 0) {
            btnPrev.setDisable(true);
        }
        btnNext.setDisable(false);
        btnCenter.setText(currPage + 1 + "");
        checkAvailability();

    }

    @FXML
    private void btnNextClicked(ActionEvent event) {
        currPage++;
        try {
            loadSubmission();
                    } catch (ParseException ex) {
            System.out.println("Submission loading failed");
        }
        if (currPage + 1 == sizeCount / BoxPerPage) {
            btnNext.setDisable(true);
        }
        btnPrev.setDisable(false);
        btnCenter.setText(currPage + 1 + "");
        checkAvailability();
    }

    @FXML
    private void lbl1SubClicked(MouseEvent event) throws IOException {
        ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 0); 
        new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
        
    }

    @FXML
    private void lbl2SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 1);
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl3SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 2);
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl4SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 3); 
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl5SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 4);  
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl6SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 5); 
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl7SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 6);
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl8SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 7);
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl9SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 8);
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl10SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 9);
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl11SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 10); 
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }

    @FXML
    private void lbl12SubClicked(MouseEvent event) throws IOException {
         ShowSubmissionController.sub = receive.get(currPage * BoxPerPage + 11);
         new Utility().loadPane("/Dashboard/ShowSubmission.fxml");
    }
    @FXML
    private void btnShowAllSubPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/ShowAllSubmission.fxml");
    }

    
    
}
