package Login;

import static Login.Update_pass_otpController.Updatedpassword;
import static Login.Update_pass_otpController.user;
import Main.DateAndTime;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Database {

    public boolean check_same_data(String user, String email) //Checking for same data inputed in creating account 
    
    {

        boolean check = false;
        try {

            ResultSet r;
            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            String qrr = "SELECT COUNT(username) FROM userinfo WHERE email = '" + email + "' OR username = '" + user + "'";

            Statement stmt = conn.createStatement();
            
            r = stmt.executeQuery(qrr);

            System.out.println("sign in  connected for checking same data");

            if (r.next()) {
                if (r.getInt("COUNT(username)") > 0) {
                    System.out.println("Same data inputed");
                    check = true;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Not Checked");
        }
        return check;
    }

    public void setData(String Name, String Username, String email, String Password, String Confirmpassword, String Occupation) throws IOException {
        
        boolean data_taken = false;
        

        try {

            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);

            String qr = "insert into userinfo values(?,?,?,?,?,?,?)";//(suDate,suTime)

            PreparedStatement pstmt = conn.prepareStatement(qr);

            pstmt.setString(1, Name);
            pstmt.setString(2, Username);
            pstmt.setString(3, email);
            pstmt.setString(4, Password);
            pstmt.setString(5, Occupation);
            //Time//
            Main.Time obj = new Main.Time();
            obj.signupDate = new DateAndTime().getDate();
            obj.signupTime = new DateAndTime().getTime();
            pstmt.setString(6, obj.signupDate);
            pstmt.setString(7, obj.signupTime);
            //Time//    
            pstmt.executeUpdate();

            System.out.println("signup database conected");

            conn.close();

        } catch (SQLException ex) {
            System.out.println("sign up not updated");

        }

    }

    public boolean getData(String user, String password) // working to signup
    
    {
        
        boolean data_match = false; //checking for same data inputed
        
        Alert a = new Alert(AlertType.NONE);
        
        try {

            ResultSet r;
            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            String qrr = "SELECT COUNT(username) FROM userinfo WHERE password = '" + password + "' AND username = '" + user + "'";

            Statement stmt = conn.createStatement();
            r = stmt.executeQuery(qrr);

            System.out.println("sign in  connected");

            if (r.next()) {
                if (r.getInt("COUNT(username)") > 0) {
                    System.out.println("Data matched");
                    data_match = true;
                }
            }

            if (data_match == false) {
                a.setAlertType(AlertType.INFORMATION);
                a.setContentText("Username or Password is Invalid");
                a.show();
            }

        } catch (SQLException ex) {
           
            a.setAlertType(AlertType.INFORMATION);
            a.setContentText("Username or Password is Invalid Exception");
            a.show();
            System.out.println("Incorrect");
            System.out.println(ex);
        }
        return data_match;
    }

    
    //checking data truthness
    
    public boolean forgotpassword_data_matching_check(String user, String UpdatePassword, String email) throws IOException {
        
        boolean check_match = false;
        ResultSet r;
        
        Update_pass_otpController.user = user;     
        Update_pass_otpController.Updatedpassword = UpdatePassword;

        try {

            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            //Counting if there is a username and email matching the entry
            String qrr = "SELECT COUNT(username) FROM userinfo WHERE email= '" + email + "' AND username = '" + user + "'";
            Statement stmt = conn.createStatement();
            r = stmt.executeQuery(qrr);
            System.out.println("sign in connected for password");

            if (r.next()) {

                {

                    {

                        if (r.getInt("COUNT(username)") > 0) {

                            System.out.println(" data matched");
                            check_match = true;

                        }

                    }
                }
            }

            if (check_match == false) {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.INFORMATION);
                a.setContentText("invalid Username or Password");
                a.show();
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Password not updated");

        }
        return check_match;
    }

    public void forgot_password_update() throws SQLException {

        try {
            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);

            String qr = "UPDATE `userinfo` SET `password`=? WHERE username=? ";

            PreparedStatement pstmt = conn.prepareStatement(qr);
            pstmt.setString(2, user);
            pstmt.setString(1, Updatedpassword);

            pstmt.executeUpdate();

            Alert a = new Alert(AlertType.NONE);
            a.setAlertType(AlertType.INFORMATION);
            a.setContentText("Password Updated");
            a.show();
            

        } catch (SQLException ex) {
            System.out.println("Password not updated ");
        }
    }

    
}
