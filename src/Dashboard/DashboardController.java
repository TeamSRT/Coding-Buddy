/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Problemset.ProblemSQL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pieChart();
        barChart();
    }

    public void pieChart() {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.addAll(new PieChart.Data("C", 21));
        list.addAll(new PieChart.Data("C++", 31));
        list.addAll(new PieChart.Data("Python", 22));
        list.addAll(new PieChart.Data("Java", 23));
        list.addAll(new PieChart.Data("C#", 3));
        pieChart.setData(list);
        pieChart.setStartAngle(0);
        //new ProblemSQL().sqlOperation("COUNT", "lang = 'c'");
    }

    public void barChart() {
        XYChart.Series bar1 = new XYChart.Series();
        XYChart.Series bar2 = new XYChart.Series();
        bar1.getData().add(new XYChart.Data<>("Right", 32));
        bar2.getData().add(new XYChart.Data<>("Wrong", 68));
        barChart.getData().addAll(bar1, bar2);
        //new ProblemSQL().sqlOperation("COUNT", "verdict = 1 AND problemID != 0");
    }
}
