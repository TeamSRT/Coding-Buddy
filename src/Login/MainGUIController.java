package Login;

import Main.Utility;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainGUIController implements Initializable {

    // Database d = new Database();
    @FXML
    private AnchorPane anchorLogin;
    @FXML
    private Button sign_In_Btn;
    @FXML
    private JFXButton sign_Up_Btn;
    @FXML
    private JFXButton Forgetpassword_btn;
    @FXML
    private TextField tfLoginUsername;
    @FXML
    private JFXPasswordField tfLoginPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void sign_in_btn_Action(ActionEvent event) throws IOException {
        Utility.setUsername(tfLoginUsername.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/Main/Home.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();
        
        /*  //d.db();
        String username= tfLoginUsername.getText();
            String password = tfLoginPassword.getText();
                 boolean check;
        username=tfLoginUsername .getText();
        password  = tfLoginPassword.getText();
       //exception
     Exception e=new Exception();
     
    check=e.SignUpException(  );
             
       
       
       if(check==false)
       {  
           Alert a = new Alert(Alert.AlertType.NONE); 
          a.setAlertType(Alert.AlertType.INFORMATION); 
  
                 //set content text 
                a.setContentText("Username And Password can not be kept blank "); 
  
                 //show the dialog 
                a.show();   
               
              
       }
       else
      

            
            
            d.getData(username, password);
           /*
          
       
 
      
    }
 /*Parent root=FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene src=new Scene(root);
        Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
       s.setScene(src);
       s.show();
         */
    }

    @FXML
    private void sign_Up_Btn_Action(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene src = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(src);
        s.show();
    }

}