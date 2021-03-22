/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Problemset.ProblemSQL;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pieChart();
            barChart();
        } catch (Exception ex) {
            System.out.println("Exception occured in dashboard");
        }
    }

    public void pieChart() throws SQLException {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        int countC = new ProblemSQL().sqlOperation("COUNT", "lang = 'c'");
        int countCpp = new ProblemSQL().sqlOperation("COUNT", "lang = 'cpp'");
        int countPython = new ProblemSQL().sqlOperation("COUNT", "lang = 'python3'");
        int countJava = new ProblemSQL().sqlOperation("COUNT", "lang = 'java'");
        int countCsharp = new ProblemSQL().sqlOperation("COUNT", "lang = 'csharp'");
        if (countC == 0 && countCpp == 0 && countPython == 0 && countJava == 0 && countCsharp == 0) 
        {
            list.add(new PieChart.Data("N/A", 100));
            ivPieLegend.setDisable(true);
            ivPieLegend.setOpacity(0);
            pieChart.setLegendVisible(true);

        }
        else
        {
            ivPieLegend.setDisable(false);
            ivPieLegend.setOpacity(1);
            pieChart.setLegendVisible(false);
            if (countC != 0) {
                list.addAll(new PieChart.Data("C", countC));
            }
            if (countCpp != 0) {
                list.addAll(new PieChart.Data("C++", countCpp));
            }
            if (countPython != 0) {
                list.addAll(new PieChart.Data("Python", countPython));
            }
            if (countJava != 0) {
                list.addAll(new PieChart.Data("Java", 30));
            }
            if (countCsharp != 0) {
                list.addAll(new PieChart.Data("C#", countCsharp));
            }
        }
        pieChart.setData(list);
        pieChart.setStartAngle(0);

    }

    public void barChart() throws SQLException {
        XYChart.Series bar1 = new XYChart.Series();
        XYChart.Series bar2 = new XYChart.Series();
        bar1.getData().add(new XYChart.Data<>("Right", new ProblemSQL().sqlOperation("COUNT", "verdict = 1 AND problemID != 0")));
        bar2.getData().add(new XYChart.Data<>("Wrong", new ProblemSQL().sqlOperation("COUNT", "verdict = -1 AND problemID != 0")));
        barChart.getData().addAll(bar1, bar2);
       
    }
}
