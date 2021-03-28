
package Main;


import Login.MainGUIController;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;


public class Home extends Application {
    public static Preferences pre = Preferences.userNodeForPackage(MainGUIController.class);
    @Override
    public void start(Stage primaryStage) throws IOException {
        //pre = 
        Utility.setConnection();
        Utility.tooltipDelay(new Tooltip(""));
        Parent root;
        if (Home.pre.get("username", null) != null && !Home.pre.get("password", null).isEmpty()) {
            Utility.setUsername(pre.get("username", null));
            Utility.initUser();
            root = FXMLLoader.load(getClass().getResource("/Main/Home.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/Login/mainGUI.fxml"));
        }
        Scene scene1 = new Scene(root);
        primaryStage.setTitle("Coding Buddy");
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
