/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Problemset.Problem;
import Problemset.ProblemSQL;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author Srishti
 */
public class ToDoController implements Initializable {

    @FXML
    private VBox vbToDo;
    ArrayList<Problem> recToDo;
    @FXML
    private Button btnBack;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        loadToDo();
    }    
    public void loadToDo()
    {
        recToDo = new ProblemSQL().readToDo();        
        for(int i = 0; i < recToDo.size(); i++)
        {
            CheckBox todo = new CheckBox(recToDo.get(i).title);
            todo.setAllowIndeterminate(true);
            Separator sp = new Separator();
            sp.isVisible();
            sp.setPrefWidth(658);
            sp.setStyle("-fx-background-color: #b3b3b3;");
            todo.setFont(Font.font("Times New Roman", 18));
            
            if(recToDo.get(i).trackToDo == -1)
            {
               
            }
            else
            {
                
            }
            vbToDo.setAlignment(Pos.BASELINE_LEFT);            
            vbToDo.getChildren().addAll(todo, sp);
            vbToDo.setSpacing(10);
        }
    }

    @FXML
    private void btnCustomBackPressed(ActionEvent event) {
    }
}
