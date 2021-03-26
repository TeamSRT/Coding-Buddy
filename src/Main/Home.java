
package Main;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;


public class Home extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Utility.setConnection();
        Utility.hackTooltipStartTiming(new Tooltip(""));
        Parent root = FXMLLoader.load(getClass().getResource("/Login/mainGUI.fxml"));
        Scene scene1 = new Scene(root);
        primaryStage.setTitle("Coding Buddy");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
