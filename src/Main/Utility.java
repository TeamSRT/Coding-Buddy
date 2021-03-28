/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Problemset.ProblemSQL;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author ktouf
 */
public class Utility {
    
    public static String username;
    public static String name;
    public static String mail;
    public static String occupation;
    public static String org;
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
            ResultSet answer = query.executeQuery("SELECT `name`, `email`, `occupation`, `organization` FROM `userinfo` WHERE `username`= '" + Utility.username + "'");
            if(answer.next()) {
                Utility.name = answer.getString(1);
                Utility.mail = answer.getString(2);
                Utility.occupation = answer.getString(3) + " ";
                Utility.org = answer.getString(4) + " ";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean checkPrivillage() {
        boolean author = false;
        try {
            int totalSolve = new ProblemSQL().sqlOperation("COUNT", "verdict = 1 AND problemID != 0");
            if (totalSolve >= 0) {
                author = true;
            } else {
                author = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error in Author Privillage Check");
        }
        return author;
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
                img = new Image(imgIn, width, height, true, true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    public static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
        }
    }
}
