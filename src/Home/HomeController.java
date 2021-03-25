/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAll();
    }
    
    public void loadAll() {
        
    }
    
    public int getTopCommPost() {
        int topID = 0;
        try {
            String query = "SELECT postID, COUNT(postID) as postC FROM `comment` GROUP BY postID ORDER BY postC DESC";
            Statement st = Main.Utility.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
                topID = rs.getInt("postID");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error on fucntion getTopPostID()");
        }
        return topID;
    }
    
    public int getTopVotedPost() {
        int topID = 0;
        try {
            String query = "SELECT postID, SUM(postTrack) as postS FROM `postvote` GROUP BY postID ORDER BY postS DESC";
            Statement st = Main.Utility.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
                topID = rs.getInt("postID");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error on fucntion getTopPostID()");
        }
        return topID;
    }
    
}
