/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Main.DateAndTime;
import Main.Time;
import Main.Utility;
import Problemset.Problem;
import Problemset.ProblemSQL;
import Problemset.ProblemViewController;
import java.io.IOException;
import java.net.URL;



import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class DashboardController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane apTarget;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private ImageView ivPieLegend;
    @FXML
    private Label lblSolved;
    @FXML
    private Label lblSubmissions;
    @FXML
    private Label lblTotalDay;
    @FXML
    private Pane pShowSolved;
    @FXML
    private Label lblTitleSolved;
    @FXML
    private Pane pShowSub;
    @FXML
    private Label lblTitleSub;
    @FXML
    private Pane pTotalDay;
    @FXML
    private Label lblTitleDay;
    @FXML
    private Button btnList;
    @FXML
    private Label lblShowTD1;
    @FXML
    private Label lblShowTD2;
    @FXML
    private Label lblShowTD3;
    @FXML
    private Label lblShowTD4;
    @FXML
    private Label lblShowTD5;
    @FXML
    private Label lblShowTD6;
    @FXML
    private Label lblShowTD7;
    @FXML
    private Label lblShowTD8;
    @FXML
    private Label lblShowTD9;
    @FXML
    private Label lblShowTD10;
    public ArrayList<Problem> rec;
    
    @FXML
    private VBox vbShowTD;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            lblSolved.setText(new ProblemSQL().sqlOperation("COUNT", "verdict = 1 AND problemID != 0") + "");
            lblSubmissions.setText(new ProblemSQL().sqlOperation("COUNT", "1") + "");
            pieChart();
            barChart();
            showTotalDay();
            showToDo();           
        } catch (SQLException | ParseException ex) {
            System.out.println(ex);
        }
    }

    public void pieChart() throws SQLException {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        int countC = new ProblemSQL().sqlOperation("COUNT", "lang = 'c'");
        int countCpp = new ProblemSQL().sqlOperation("COUNT", "lang = 'cpp'");
        int countPython = new ProblemSQL().sqlOperation("COUNT", "lang = 'python3'");
        int countJava = new ProblemSQL().sqlOperation("COUNT", "lang = 'java'");
        int countCsharp = new ProblemSQL().sqlOperation("COUNT", "lang = 'csharp'");
        String color[] = {"#00ffff", "#cc66ff", "#0000ff", "#b30086", "#6600ff", "#b3e0ff"};
        if (countC == 0 && countCpp == 0 && countPython == 0 && countJava == 0 && countCsharp == 0) 
        {
            pieChart.setData(list);
            list.add(new PieChart.Data("N/A", 100));
         //   list.get(0).getNode().setStyle("-fx-pie-color : " + color[5] + ";");            
            ivPieLegend.setDisable(true);
            ivPieLegend.setOpacity(0);
            pieChart.setLegendVisible(true);
        }
        else
        {
            pieChart.setData(list);
            ivPieLegend.setDisable(false);
            ivPieLegend.setOpacity(1);
            pieChart.setLegendVisible(false);
            int i = 0;
            
            if (countC != 0) {
                list.addAll(new PieChart.Data("C", countC));
                list.get(i).getNode().setStyle("-fx-pie-color : " + color[0] + ";");
                ++i;
            }
            if (countCpp != 0) {
                list.addAll(new PieChart.Data("C++", countCpp));
                list.get(i).getNode().setStyle("-fx-pie-color : " + color[1] + ";");
                ++i;
            }
            if (countPython != 0) {
                list.addAll(new PieChart.Data("Python", countPython));
                list.get(i).getNode().setStyle("-fx-pie-color : " + color[2] + ";");
                ++i;
            }
            if (countJava != 0) {
                list.addAll(new PieChart.Data("Java", countJava));
                list.get(i).getNode().setStyle("-fx-pie-color : " + color[3] + ";");
                ++i;
            }
            if (countCsharp != 0) {
                list.addAll(new PieChart.Data("C#", countCsharp));
                list.get(i).getNode().setStyle("-fx-pie-color : " + color[4] + ";");
                ++i;
            }
        }
        pieChart.setData(list);
        pieChart.setLabelsVisible(true);
       // pieChart.setLabelLineLength(0.5f);
        pieChart.setStartAngle(0);
    }

    public void barChart() throws SQLException {
        XYChart.Series bar1 = new XYChart.Series();
        XYChart.Series bar2 = new XYChart.Series();
        bar1.getData().add(new XYChart.Data<>("Right", new ProblemSQL().sqlOperation("COUNT", "verdict = 1 AND problemID != 0")));
        bar2.getData().add(new XYChart.Data<>("Wrong", new ProblemSQL().sqlOperation("COUNT", "verdict = -1 AND problemID != 0")));
        barChart.getData().addAll(bar1, bar2);
       
    }
        
    public void showTotalDay() throws SQLException, ParseException
    {
        DateAndTime obj = new DateAndTime();
        obj.connection();
        Time tObj = obj.totalDay();           
        long diff = new Date().getTime() - new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").parse(tObj.signupDate + tObj.signupTime).getTime();
        long mSecPerDay = 86400000;
        int dayCount = Math.round(diff / mSecPerDay);         
        lblTotalDay.setText(dayCount+"");
    }

    @FXML
    private void pShowSolvedClicked(MouseEvent event) {
    }

    @FXML
    private void pShowSubClicked(MouseEvent event) throws IOException {
        new Utility().loadPane("/Dashboard/submission.fxml");
    }

    @FXML
    private void btnListClicked(ActionEvent event) throws IOException {
         new Utility().loadPane("/Dashboard/ToDo.fxml");
    }
    public void showToDo() {
        rec = new ProblemSQL().readToDo("AND trackToDo = -1");
//        System.out.println("size = "+rec.size());
        if(rec.isEmpty())
        {
           lblShowTD5.setText("No todo's to display");
           lblShowTD5.setDisable(true);
           lblShowTD5.setStyle("-fx-opacity: 1");
           lblShowTD1.setDisable(true);
           lblShowTD1.setOpacity(0); 
           lblShowTD2.setDisable(true);
           lblShowTD2.setOpacity(0);
           lblShowTD3.setDisable(true);
           lblShowTD3.setOpacity(0);
           lblShowTD4.setDisable(true);
           lblShowTD4.setOpacity(0);         
           lblShowTD6.setDisable(true);
           lblShowTD6.setOpacity(0);
           lblShowTD7.setDisable(true);
           lblShowTD7.setOpacity(0);
           lblShowTD8.setDisable(true);
           lblShowTD8.setOpacity(0);
           lblShowTD9.setDisable(true);
           lblShowTD9.setOpacity(0);
           lblShowTD10.setDisable(true);
           lblShowTD10.setOpacity(0);
        }
        
        else
        {
        if (rec.size() < 1) {
            lblShowTD1.setDisable(true);
            lblShowTD1.setOpacity(0);            
        } else {
            lblShowTD1.setDisable(false);
            lblShowTD1.setOpacity(1);
            lblShowTD1.setText(rec.get(0).title);           
        }
        if (rec.size() < 2) {
            lblShowTD2.setDisable(true);
            lblShowTD2.setOpacity(0);
        } else {
            lblShowTD2.setDisable(false);
            lblShowTD2.setOpacity(1);
            lblShowTD2.setText(rec.get(1).title);
        }
        if (rec.size() < 3) {
            lblShowTD3.setDisable(true);
            lblShowTD3.setOpacity(0);
        } else {
            lblShowTD3.setDisable(false);
            lblShowTD3.setOpacity(1);
            lblShowTD3.setText(rec.get(2).title);
        }
        if (rec.size() < 4) {
            lblShowTD4.setDisable(true);
            lblShowTD4.setOpacity(0);
        } else {
            lblShowTD4.setDisable(false);
            lblShowTD4.setOpacity(1);
            lblShowTD4.setText(rec.get(3).title);
        }
        if (rec.size() < 5) {
            lblShowTD5.setDisable(true);
            lblShowTD5.setOpacity(0);
        } else {
            lblShowTD5.setDisable(false);
            lblShowTD5.setOpacity(1);
            lblShowTD5.setText(rec.get(4).title);
        }
        if (rec.size() < 6) {
            lblShowTD6.setDisable(true);
            lblShowTD6.setOpacity(0);
        } else {
            lblShowTD6.setDisable(false);
            lblShowTD6.setOpacity(1);
            lblShowTD6.setText(rec.get(5).title);
        }
       if (rec.size() < 7) {
            lblShowTD7.setDisable(true);
            lblShowTD7.setOpacity(0);
        } else {
            lblShowTD7.setDisable(false);
            lblShowTD7.setOpacity(1);
            lblShowTD7.setText(rec.get(6).title);
        }
        if (rec.size() < 8) {
            lblShowTD8.setDisable(true);
            lblShowTD8.setOpacity(0);
        } else {
            lblShowTD8.setDisable(false);
            lblShowTD8.setOpacity(1);
            lblShowTD8.setText(rec.get(7).title);
        }
        if (rec.size() < 9) {
            lblShowTD9.setDisable(true);
            lblShowTD9.setOpacity(0);
        } else {
            lblShowTD9.setDisable(false);
            lblShowTD9.setOpacity(1);
            lblShowTD9.setText(rec.get(8).title);
        }
        if (rec.size() < 10) {
            lblShowTD10.setDisable(true);
            lblShowTD10.setOpacity(0);
        } else {
            lblShowTD10.setDisable(false);
            lblShowTD10.setOpacity(1);
            lblShowTD10.setText(rec.get(9).title);
        }
       }
    }

    @FXML
    private void lblShowTD1Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(0).problemID);
         System.out.println("title = "+ProblemViewController.currProblem.title);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD2Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(1).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD3Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(2).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD4Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(3).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD5Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(4).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD6Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(5).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD7Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(6).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD8Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(7).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD9Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(8).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }

    @FXML
    private void lblShowTD10Clicked(MouseEvent event) throws IOException {
        ProblemViewController.currProblem = new ProblemSQL().readProblemForToDo(rec.get(9).problemID);
        new Utility().loadPane("/Problemset/ProblemView.fxml");
    }
   
  
}
