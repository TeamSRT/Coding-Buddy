/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

import java.sql.Connection;
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
        this.conn = Main.Utility.conn;
    }

    public ArrayList<Problem> readProblem() {
        ArrayList<Problem> problemList = new ArrayList<>();
        try {
            Statement currStatement = conn.createStatement();
            ResultSet currSet = currStatement.executeQuery("SELECT * FROM problemset");
            while (currSet.next()) {
                System.out.println("RUNNING");
                problemList.add(new Problem(currSet.getString("title"), currSet.getString("problemBody"), currSet.getString("input1"), currSet.getString("output1"), currSet.getString("input2"), currSet.getString("output2"), currSet.getString("input3"), currSet.getString("output3"), currSet.getString("username"), currSet.getInt("timeLimit"), currSet.getInt("problemID")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        //System.out.println(problemList.get(0).body);
        return problemList;
    }

    public void writeProblem(String title, String body, String input1, String output1, String input2, String output2, String input3, String output3, int Time, CreateProblemController cpcObj) {
        Alert error = new Alert(AlertType.ERROR);
        Alert success = new Alert(AlertType.INFORMATION);
        error.setTitle("Submission Error");
        success.setTitle("Submission Successful");
        error.setContentText("Problem wasn't submitted! An error Occured!");
        success.setContentText("Problem submitted successfully");
        success.setContentText("Post created successful");
        if (title.equals("")) {
            error.setContentText("Title can not be empty");
            error.show();
        } else if (body.equals("")) {
            error.setContentText("Problem body can not be empty");
            error.show();
        } else if (output1.equals("") && output2.equals("") && output3.equals("")) {
            error.setContentText("All Output fields can not be empty");
            error.show();
        } else {
            try {
                PreparedStatement currStatement = conn.prepareStatement("INSERT INTO `problemset`(`title`, `problemBody`, `input1`, `output1`, `input2`, `output2`, `input3`, `output3`, `timeLimit`, `username`) VALUES (?,?,?,?,?,?,?,?,?,?)");
                currStatement.setString(1, title);
                currStatement.setString(2, body);
                currStatement.setString(3, input1);
                currStatement.setString(4, output1);
                currStatement.setString(5, input2);
                currStatement.setString(6, output2);
                currStatement.setString(7, input3);
                currStatement.setString(8, output3);
                currStatement.setInt(9, Time);
                currStatement.setString(10, Main.Utility.username);
                currStatement.execute();
                cpcObj.clearFields();
                success.show();
            } catch (SQLException ex) {
                Logger.getLogger(ProblemSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateProblem(String title, String body, String input1, String output1, String input2, String output2, String input3, String output3, int Time, int problemID, CreateProblemController cpcObj) {
        try {
        String query = "UPDATE problemset SET title = ?, problemBody = ?, input1 = ?, input2 = ?, input3 = ?, output1 = ?, output2 = ?, output3 = ?, timeLimit = ? WHERE problemID = ?";
        PreparedStatement currStatement = conn.prepareStatement(query);
            currStatement.setString(1, title);
            currStatement.setString(2, body);
            currStatement.setString(3, input1);
            currStatement.setString(4, input2);
            currStatement.setString(5, input3);
            currStatement.setString(6, output1);
            currStatement.setString(7, output2);
            currStatement.setString(8, output3);
            currStatement.setInt(9, Time);
            currStatement.setInt(10, problemID);
            currStatement.execute();
            cpcObj.clearFields();
            Alert success = new Alert(AlertType.INFORMATION);
            success.setTitle("Update Successful");
            success.setContentText("Problem updated successfully");
            success.show();
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public boolean isSolved(int problemID) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet ans = st.executeQuery("SELECT COUNT(userName) FROM submission WHERE verdict = 1 AND problemID = " + problemID + " AND userName = '" + Main.Utility.username + "'");
        int cnt = 0;
        if(ans.next()) {
            cnt = ans.getInt(1);
        }
        return cnt > 0;
    }
    
    public String getOutput(int problemID, int num) throws SQLException {
        Statement query = conn.createStatement();
        ResultSet answer = query.executeQuery("SELECT `output" + num + "` FROM `problemset` WHERE `problemID`= '" + problemID + "'");
        if(answer.next()) {
            return answer.getString(1);
        }
        return null;
    }
    
    public String getInput(int problemID, int num) throws SQLException {
        Statement query = conn.createStatement();
        ResultSet answer = query.executeQuery("SELECT `input" + num + "` FROM `problemset` WHERE `problemID`= '" + problemID + "'");
        if(answer.next()) {
            return answer.getString(1);
        }
        return null;
    }
    
    public int sqlOperation(String Operation, String Condition) throws SQLException {
        String Query = "SELECT " + Operation +"(`problemID`) AS ans FROM `submission` WHERE " + Condition + " AND `userName` = '" + Main.Utility.username + "'";
        PreparedStatement st = conn.prepareStatement(Query);
        ResultSet rs = st.executeQuery();
        int ans = 0;
        if(rs.next()) {
            ans = rs.getInt("ans");
        }
        return ans;
    }
    
    public void recordSubmission(int problemID, int Verdict, String lang, String code) throws SQLException {
        if(Verdict == 1 && problemID == -1) {
            int Check = sqlOperation("COUNT", "`verdict` = 1");
            if(Check > 0) {
                return;
            }
        }
        PreparedStatement currStatement = conn.prepareStatement("INSERT INTO `submission`(`problemID`, `userName`, `verdict`, `lang`, `code`, `subdate`, `subtime`) VALUES (?,?,?,?,?,?,?)");
        currStatement.setInt(1, problemID);
        currStatement.setString(2, Main.Utility.username);
        currStatement.setInt(3, Verdict);
        currStatement.setString(4, lang);
        currStatement.setString(5, code);
        currStatement.setString(6, new Main.DateAndTime().getDate());
        currStatement.setString(7, new Main.DateAndTime().getTime());
        currStatement.execute();
    }
    
    public int countSubmission(int problemID) throws SQLException {
        String Query = "SELECT COUNT(problemID) as subCount FROM submission WHERE problemID = " + problemID;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(Query);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt("subCount");
        }
        return count;
    }
    
    public void recordToDo(int problemID) throws SQLException {
        String Query = "INSERT INTO todo(problemID, userName) VALUES ('" + problemID + "', '" + Main.Utility.username + "')";
        Statement st = conn.createStatement();
        st.execute(Query);
    }

    public boolean checkToDo(int problemID) {
        try {
            String Query = "SELECT COUNT(problemID) FROM todo WHERE problemID = " + problemID + " AND username = '" + Main.Utility.username + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    public boolean checkSubmission(int problemID) {
        try {
            String Query = "SELECT COUNT(problemID) FROM submission WHERE problemID = " + problemID + " AND userName = '" + Main.Utility.username + "' AND verdict = 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    public void completeToDo(int problemID) throws SQLException {
        if (checkToDo(problemID) == true) {
            String Query = "UPDATE todo SET trackToDo = 1 WHERE problemID = " + problemID + " AND username = '" + Main.Utility.username + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(Query);
        }
    }

    public ArrayList<Problem> readToDo(String instruct)
    {
        ArrayList<Problem> prob = new ArrayList<>(); 
        try {
            String query = "SELECT trackToDo, problemID as pID,(SELECT title from problemset WHERE problemID = pID) as pTitle FROM todo WHERE username = '" + Main.Utility.username + "' " + instruct + " ORDER BY trackID DESC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                Problem obj = new Problem();
                obj.problemID = rs.getInt("pID");
                obj.title = rs.getString("pTitle");
                obj.trackToDo = rs.getInt("trackToDo");
                prob.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("To do = "+ex);
        }
        return prob;
    }    
    
     
     public Problem readProblemForToDo(int problemID) {
        Problem obj = new Problem();
        try {
            Statement currStatement = conn.createStatement();
            ResultSet currSet = currStatement.executeQuery("SELECT title, problemBody, input1, output1, input2, output2, input3, output3, timeLimit, username FROM problemset WHERE problemID = "+problemID);
            if(currSet.next()) {
                
                obj.title = currSet.getString("title");
                obj.body =  currSet.getString("problemBody");
                obj.input1 = currSet.getString("input1");
                obj.output1 = currSet.getString("output1");
                obj.input2 = currSet.getString("input2");
                obj.output2 = currSet.getString("output2");
                obj.input3 = currSet.getString("input3");
                obj.output3 = currSet.getString("output3");
                obj.timeLimit =  currSet.getInt("timeLimit");
                obj.problemID = problemID;
                obj.author = currSet.getString("username");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return obj;
    }
    
    public ArrayList<Submission> readSubmission() {
        ArrayList<Submission> subObj = new ArrayList<>();
        try {        
            Statement st = conn.createStatement();
            String query = "SELECT problemID as pID, track, verdict, lang, code, subdate, subtime,(SELECT title from problemset WHERE problemID = pID) as pTitle FROM submission WHERE username = '" + Main.Utility.username + "'ORDER BY track DESC";         
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Submission obj = new Submission();
                obj.problemID = rs.getInt("pID");
                obj.track = rs.getInt("track");
                obj.verdict = rs.getInt("verdict");
                obj.lang = rs.getString("lang");
                obj.code = rs.getString("code");
                obj.subdate = rs.getString("subdate");
                obj.subtime = rs.getString("subtime");
                obj.probName = rs.getString("pTitle");
                subObj.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Exception = " + ex);
        }
        return subObj;
    }
    public ArrayList<Submission> readAllSubmission() {
        ArrayList<Submission> subAll = new ArrayList<>();
        try {        
            Statement st = conn.createStatement();
            String query = "SELECT problemID as pID, verdict, userName, track, lang, code, subdate, subtime,(SELECT title from problemset WHERE problemID = pID) as pTitle FROM submission WHERE verdict = 1 OR verdict = -1 ORDER BY track DESC";         
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Submission obj = new Submission();
                obj.problemID = rs.getInt("pID");
                obj.username = rs.getString("userName");
                obj.track = rs.getInt("track");
                obj.verdict = rs.getInt("verdict");
                obj.lang = rs.getString("lang");
                obj.code = rs.getString("code");
                obj.subdate = rs.getString("subdate");
                obj.subtime = rs.getString("subtime");
                obj.probName = rs.getString("pTitle");                
                subAll.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Exception = " + ex);
        }
        return subAll;
    }
    
    
    
}
