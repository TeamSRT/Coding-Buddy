/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author ktouf
 */
public class Utility {
    
    public static String username;
    public static HomeController Home;
    
    public static void setHome(HomeController Home) {
        Utility.Home = Home;
    }
    
    public static void setUsername(String username) {
        Utility.username = username;
    }
    
    public void loadPane(String path) throws IOException {
        Pane loader = FXMLLoader.load(getClass().getResource(path));
        Home.getTestContent().setCenter(loader);
    }
}
