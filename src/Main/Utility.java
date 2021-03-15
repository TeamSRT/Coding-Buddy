/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 *
 * @author ktouf
 */
public class Utility {
    
    public static String username;
    public static String name;
    public static String mail;
    public static HomeController Home;
    public static Connection conn;
    
    public static void setConnection() {
        try {
            Utility.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingbuddydb", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initUser() {
        try {
            Statement query = conn.createStatement();
            ResultSet answer = query.executeQuery("SELECT `name`, `email` FROM `userinfo` WHERE `username`= '" + Utility.username + "'");
            if(answer.next()) {
                Utility.name = answer.getString(1);
                Utility.mail = answer.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
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
    
    public static Image getImage(String username, int width, int height) {
        Image img = new Image("/Image/default.png");
        try {
            Statement query = conn.createStatement();
            ResultSet answer = query.executeQuery("select * from profilepic where username = '" + username + "'");
            while (answer.next()) {
                Blob imgBlob = answer.getBlob(2);
                InputStream imgIn = imgBlob.getBinaryStream();
                img = new Image(imgIn, width, height, false, false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}
