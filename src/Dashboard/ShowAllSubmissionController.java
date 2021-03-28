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
public class ShowAllSubmissionController implements Initializable {

    @FXML
    private Button btnBack;   
    @FXML
    private HBox hTitle;
    @FXML
    private HBox h1sub;
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
    private HBox h2sub;
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
    private HBox h3sub;
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
    private HBox h4sub;
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
    private HBox h5sub;
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
    private HBox h6sub;
    @FXML
    private Label lbl6ProbName;
    @FXML
    private Label lbl6Ans;
    @FXML
    private Label lbl6Sub;
    @FXML
    private Label lbl6SubDate;
    @FXML
    private HBox h7sub;
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
    private HBox h8sub;
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
    private HBox h9sub;
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
    private HBox h10sub;
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
    private HBox h11sub;
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
    private HBox h12sub;
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
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnCenter;
    ArrayList<Submission> showAllSub;
    private int sizeCount;
    private int currPage;
    private final int BoxPerPage = 12;
    @FXML
    private Label lbl1User;
    @FXML
    private Label lbl2User;
    @FXML
    private Label lbl3User;
    @FXML
    private Label lbl4User;
    @FXML
    private Label lbl5User;
    @FXML
    private Label lbl6User;
    @FXML
    private Label lbl7User;
    @FXML
    private Label lbl8User;
    @FXML
    private Label lbl9User;
    @FXML
    private Label lbl10User;
    @FXML
    private Label lbl11User;
    @FXML
    private Label lbl12User;
    @FXML
    private Label lbl6Lang;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadAll();
    }    
    public void loadAll() {
        currPage = 0;
        showAllSub = new ProblemSQL().readAllSubmission();
        sizeCount = showAllSub.size();
        
        if (sizeCount % 12 != 0) {
            for (int i = 0; i < 12 - (sizeCount % 12); i++) {
                Submission obj = new Submission("", "", "", "","", "2021-01-01", "00:00:00");
                showAllSub.add(obj);
            }
        }
        sizeCount = showAllSub.size();       
        if(sizeCount == 0)
        {
            for (int i = 0; i < 12; i++) {
                Submission obj = new Submission("", "", "", "","", "2021-01-01", "00:00:00");
                showAllSub.add(obj);
            }
        }
        loadAllSub();
        if (currPage >= sizeCount / BoxPerPage - 1) {
            btnNext.setDisable(true);
        }
        btnPrev.setDisable(true);
        btnCenter.setText(currPage + 1 + "");
        checkAvailability();
    }
    public void loadAllSub()
    {
        lbl1ProbName.setText(showAllSub.get(currPage * BoxPerPage + 0).probName);
        lbl2ProbName.setText(showAllSub.get(currPage * BoxPerPage + 1).probName);
        lbl3ProbName.setText(showAllSub.get(currPage * BoxPerPage + 2).probName);
        lbl4ProbName.setText(showAllSub.get(currPage * BoxPerPage + 3).probName);
        lbl5ProbName.setText(showAllSub.get(currPage * BoxPerPage + 4).probName);
        lbl6ProbName.setText(showAllSub.get(currPage * BoxPerPage + 5).probName);
        lbl7ProbName.setText(showAllSub.get(currPage * BoxPerPage + 6).probName);
        lbl8ProbName.setText(showAllSub.get(currPage * BoxPerPage + 7).probName);
        lbl9ProbName.setText(showAllSub.get(currPage * BoxPerPage + 8).probName);
        lbl10ProbName.setText(showAllSub.get(currPage * BoxPerPage + 9).probName);
        lbl11ProbName.setText(showAllSub.get(currPage * BoxPerPage + 10).probName);
        lbl12ProbName.setText(showAllSub.get(currPage * BoxPerPage + 11).probName);
        
        
        lbl1User.setText(showAllSub.get(currPage * BoxPerPage + 0).username);
        lbl2User.setText(showAllSub.get(currPage * BoxPerPage + 1).username);
        lbl3User.setText(showAllSub.get(currPage * BoxPerPage + 2).username);
        lbl4User.setText(showAllSub.get(currPage * BoxPerPage + 3).username);
        lbl5User.setText(showAllSub.get(currPage * BoxPerPage + 4).username);
        lbl6User.setText(showAllSub.get(currPage * BoxPerPage + 5).username);
        lbl7User.setText(showAllSub.get(currPage * BoxPerPage + 6).username);
        lbl8User.setText(showAllSub.get(currPage * BoxPerPage + 7).username);
        lbl9User.setText(showAllSub.get(currPage * BoxPerPage + 8).username);
        lbl10User.setText(showAllSub.get(currPage * BoxPerPage + 9).username);
        lbl11User.setText(showAllSub.get(currPage * BoxPerPage + 10).username);
        lbl12User.setText(showAllSub.get(currPage * BoxPerPage + 11).username);
        
        if(showAllSub.get(currPage * BoxPerPage + 0).verdict == 1)
        {
            lbl1Ans.setText("Accepted");
            lbl1Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl1Ans.setText("Wrong Answer");
            lbl1Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 1).verdict == 1)
        {
            lbl2Ans.setText("Accepted");
            lbl2Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl2Ans.setText("Wrong Answer");
            lbl2Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 2).verdict == 1)
        {
            lbl3Ans.setText("Accepted");
            lbl3Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl3Ans.setText("Wrong Answer");
            lbl3Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 3).verdict == 1)
        {
            lbl4Ans.setText("Accepted");
            lbl4Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl4Ans.setText("Wrong Answer");
            lbl4Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 4).verdict == 1)
        {
            lbl5Ans.setText("Accepted");
            lbl5Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl5Ans.setText("Wrong Answer");
            lbl5Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 5).verdict == 1)
        {
            lbl6Ans.setText("Accepted");
            lbl6Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl6Ans.setText("Wrong Answer");
            lbl6Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 6).verdict == 1)
        {
            lbl7Ans.setText("Accepted");
            lbl7Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl7Ans.setText("Wrong Answer");
            lbl7Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 7).verdict == 1)
        {
            lbl8Ans.setText("Accepted");
            lbl8Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl8Ans.setText("Wrong Answer");
            lbl8Ans.setTextFill(Color.RED);
        }if(showAllSub.get(currPage * BoxPerPage + 8).verdict == 1)
        {
            lbl9Ans.setText("Accepted");
            lbl9Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl9Ans.setText("Wrong Answer");
            lbl9Ans.setTextFill(Color.RED);
        }if(showAllSub.get(currPage * BoxPerPage + 9).verdict == 1)
        {
            lbl10Ans.setText("Accepted");
            lbl10Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl10Ans.setText("Wrong Answer");
            lbl10Ans.setTextFill(Color.RED);
        }if(showAllSub.get(currPage * BoxPerPage + 10).verdict == 1)
        {
            lbl11Ans.setText("Accepted");
            lbl11Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl11Ans.setText("Wrong Answer");
            lbl11Ans.setTextFill(Color.RED);
        }
        if(showAllSub.get(currPage * BoxPerPage + 11).verdict == 1)
        {
            lbl12Ans.setText("Accepted");
            lbl12Ans.setTextFill(Color.GREEN);
        }
        else
        {
            lbl12Ans.setText("Wrong Answer");
            lbl12Ans.setTextFill(Color.RED);
        }
        
        
        lbl1Sub.setText(showAllSub.get(currPage * BoxPerPage + 0).track + "");
        lbl2Sub.setText(showAllSub.get(currPage * BoxPerPage + 1).track + "");
        lbl3Sub.setText(showAllSub.get(currPage * BoxPerPage + 2).track + "");
        lbl4Sub.setText(showAllSub.get(currPage * BoxPerPage + 3).track + "");
        lbl5Sub.setText(showAllSub.get(currPage * BoxPerPage + 4).track + "");
        lbl6Sub.setText(showAllSub.get(currPage * BoxPerPage + 5).track + "");
        lbl7Sub.setText(showAllSub.get(currPage * BoxPerPage + 6).track + "");
        lbl8Sub.setText(showAllSub.get(currPage * BoxPerPage + 7).track + "");
        lbl9Sub.setText(showAllSub.get(currPage * BoxPerPage + 8).track + "");
        lbl10Sub.setText(showAllSub.get(currPage * BoxPerPage + 9).track + "");
        lbl11Sub.setText(showAllSub.get(currPage * BoxPerPage + 10).track + "");
        lbl12Sub.setText(showAllSub.get(currPage * BoxPerPage + 11).track + "");
        
        lbl1Lang.setText(showAllSub.get(currPage * BoxPerPage + 0).lang);
        lbl2Lang.setText(showAllSub.get(currPage * BoxPerPage + 1).lang);
        lbl3Lang.setText(showAllSub.get(currPage * BoxPerPage + 2).lang);
        lbl4Lang.setText(showAllSub.get(currPage * BoxPerPage + 3).lang);
        lbl5Lang.setText(showAllSub.get(currPage * BoxPerPage + 4).lang);
        lbl6Lang.setText(showAllSub.get(currPage * BoxPerPage + 5).lang);
        lbl7Lang.setText(showAllSub.get(currPage * BoxPerPage + 6).lang);
        lbl8Lang.setText(showAllSub.get(currPage * BoxPerPage + 7).lang);
        lbl9Lang.setText(showAllSub.get(currPage * BoxPerPage + 8).lang);
        lbl10Lang.setText(showAllSub.get(currPage * BoxPerPage + 9).lang);
        lbl11Lang.setText(showAllSub.get(currPage * BoxPerPage + 10).lang);
        lbl12Lang.setText(showAllSub.get(currPage * BoxPerPage + 11).lang);
        
        
        lbl1SubDate.setText(showAllSub.get(currPage * BoxPerPage + 0).subdate + " " + showAllSub.get(currPage * BoxPerPage + 0).subtime);
        lbl2SubDate.setText(showAllSub.get(currPage * BoxPerPage + 1).subdate + " " + showAllSub.get(currPage * BoxPerPage + 1).subtime);
        lbl3SubDate.setText(showAllSub.get(currPage * BoxPerPage + 2).subdate + " " + showAllSub.get(currPage * BoxPerPage + 2).subtime);
        lbl4SubDate.setText(showAllSub.get(currPage * BoxPerPage + 3).subdate + " " + showAllSub.get(currPage * BoxPerPage + 3).subtime);
        lbl5SubDate.setText(showAllSub.get(currPage * BoxPerPage + 4).subdate + " " + showAllSub.get(currPage * BoxPerPage + 4).subtime);
        lbl6SubDate.setText(showAllSub.get(currPage * BoxPerPage + 5).subdate + " " + showAllSub.get(currPage * BoxPerPage + 5).subtime);
        lbl7SubDate.setText(showAllSub.get(currPage * BoxPerPage + 6).subdate + " " + showAllSub.get(currPage * BoxPerPage + 6).subtime);
        lbl8SubDate.setText(showAllSub.get(currPage * BoxPerPage + 7).subdate + " " + showAllSub.get(currPage * BoxPerPage + 7).subtime);
        lbl9SubDate.setText(showAllSub.get(currPage * BoxPerPage + 8).subdate + " " + showAllSub.get(currPage * BoxPerPage + 8).subtime);
        lbl10SubDate.setText(showAllSub.get(currPage * BoxPerPage + 9).subdate + " " + showAllSub.get(currPage * BoxPerPage + 9).subtime);
        lbl11SubDate.setText(showAllSub.get(currPage * BoxPerPage + 10).subdate + " " + showAllSub.get(currPage * BoxPerPage + 10).subtime);
        lbl12SubDate.setText(showAllSub.get(currPage * BoxPerPage + 11).subdate + " " + showAllSub.get(currPage * BoxPerPage + 11).subtime);
    }
    
    public void checkAvailability()
    {
        if (showAllSub.get(currPage * BoxPerPage + 0).lang.equals("")) {
            h1sub.setOpacity(0);
            h1sub.setDisable(true);
        } else {
            h1sub.setOpacity(1);
            h1sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 1).lang.equals("")) {
            h2sub.setOpacity(0);
            h2sub.setDisable(true);
        } else {
            h2sub.setOpacity(1);
            h2sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 2).lang.equals("")) {
            h3sub.setOpacity(0);
            h3sub.setDisable(true);
        } else {
            h3sub.setOpacity(1);
            h3sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 3).lang.equals("")) {
            h4sub.setOpacity(0);
            h4sub.setDisable(true);
        } else {
            h4sub.setOpacity(1);
            h4sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 4).lang.equals("")) {
            h5sub.setOpacity(0);
            h5sub.setDisable(true);
        } else {
            h5sub.setOpacity(1);
            h5sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 5).lang.equals("")) {
            h6sub.setOpacity(0);
            h6sub.setDisable(true);
        } else {
            h6sub.setOpacity(1);
            h6sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 6).lang.equals("")) {
            h7sub.setOpacity(0);
            h7sub.setDisable(true);
        } else {
            h7sub.setOpacity(1);
            h7sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 7).lang.equals("")) {
            h8sub.setOpacity(0);
            h8sub.setDisable(true);
        } else {
            h8sub.setOpacity(1);
            h8sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 8).lang.equals("")) {
            h9sub.setOpacity(0);
            h9sub.setDisable(true);
        } else {
            h9sub.setOpacity(1);
            h9sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 9).lang.equals("")) {
            h10sub.setOpacity(0);
            h10sub.setDisable(true);
        } else {
            h10sub.setOpacity(1);
            h10sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 10).lang.equals("")) {
            h11sub.setOpacity(0);
            h11sub.setDisable(true);
        } else {
            h11sub.setOpacity(1);
            h11sub.setDisable(false);
        }
        if (showAllSub.get(currPage * BoxPerPage + 11).lang.equals("")) {
            h12sub.setOpacity(0);
            h12sub.setDisable(true);
        } else {
            h12sub.setOpacity(1);
            h12sub.setDisable(false);
        }
    }
    
 
    @FXML
    private void btnPrevClicked(ActionEvent event) {
        currPage--;        
        loadAllSub();       
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
        loadAllSub();          
        if (currPage + 1 == sizeCount / BoxPerPage) {
            btnNext.setDisable(true);
        }
        btnPrev.setDisable(false);
        btnCenter.setText(currPage + 1 + "");
        checkAvailability();
    }

    
    @FXML
    private void btnCustomBackPressed(ActionEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/submission.fxml");
    }

    @FXML
    private void lbl1SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 0); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl2SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 1); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl3SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 2); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl4SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 3); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl5SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 4); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl6SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 5); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl7SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 6); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl8SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 7); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl9SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 8); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl10SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 9); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl11SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 10); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    @FXML
    private void lbl12SubClicked(MouseEvent event) throws IOException {
        ShowAllSubmissionCodeController.sub = showAllSub.get(currPage * BoxPerPage + 11); 
        new Utility().loadPane("/Dashboard/ShowAllSubmissionCode.fxml");
    }

    
    
}
