/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

import Main.Utility;
import static Main.Utility.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author ktouf
 */
public class ProblemSQL {

    private Connection conn;

    public ProblemSQL() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingbuddydb", "root", "");
        } catch (SQLException ex) {
            System.out.println(ex + "At ProblemSQL constructor");
        }
    }

    public ArrayList<Problem> readProblem() {
        ArrayList<Problem> problemList = new ArrayList<>();
        try {
            Statement currStatement = conn.createStatement();
            ResultSet currSet = currStatement.executeQuery("SELECT * FROM problemset");
            while (currSet.next()) {
                problemList.add(new Problem(currSet.getString("title"), currSet.getString("problemBody"), currSet.getString("input"), currSet.getString("output"), currSet.getString("username"), currSet.getInt("submission"), currSet.getInt("problemID")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        //System.out.println(problemList.get(0).body);
        return problemList;
    }

    public void writeProblem(String title, String body, String input, String output, CreateProblemController cpcObj) {
        Alert error = new Alert(AlertType.ERROR);
        Alert success = new Alert(AlertType.INFORMATION);
        error.setTitle("Submission Error");
        success.setTitle("Submission Successful");
        success.setContentText("Post created successful");
        if (title.equals("")) {
            error.setContentText("Title can not be empty");
            error.show();
        } else if (body.equals("")) {
            error.setContentText("Problem body can not be empty");
            error.show();
        } else if (output.equals("")) {
            error.setContentText("Output can not be empty");
            error.show();
        } else {
            try {
                PreparedStatement currStatement = conn.prepareStatement("INSERT INTO `problemset`(`title`, `problemBody`, `input`, `output`, `username`) VALUES (?,?,?,?,?)");
                currStatement.setString(1, title);
                currStatement.setString(2, body);
                currStatement.setString(3, input);
                currStatement.setString(4, output);
                currStatement.setString(5, Main.Utility.username);
                currStatement.execute();
                cpcObj.clearFields();
                success.show();
            } catch (SQLException ex) {
                Logger.getLogger(ProblemSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getOutput(int problemID) throws SQLException {
        Statement query = conn.createStatement();
        ResultSet answer = query.executeQuery("SELECT `output` FROM `problemset` WHERE `problemID`= '" + problemID + "'");
        if(answer.next()) {
            return answer.getString(1);
        }
        return null;
    }
    
    public String getInput(int problemID) throws SQLException {
        Statement query = conn.createStatement();
        ResultSet answer = query.executeQuery("SELECT `input` FROM `problemset` WHERE `problemID`= '" + problemID + "'");
        if(answer.next()) {
            return answer.getString(1);
        }
        return null;
    }

}
