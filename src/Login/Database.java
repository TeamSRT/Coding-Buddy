package Login;

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

    public boolean check_same_data(String user, String email) {

        boolean check = false;
        try {

            ResultSet r;
            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            String qrr = "SELECT * FROM `info`";
            //"SELECT `UserName`, `Password` FROM `info`";
            Statement stmt = conn.createStatement();
            r = stmt.executeQuery(qrr);

            System.out.println("sign in  connected for checking same data");
            while (r.next()) {
                System.out.println("not worked");
                if ((r.getString(2).equals(user)) || (r.getString(3).equals(email))) {
                    System.out.println("same data inputed");
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

            //int rand= (int) Math.random()+1000;
            // EController.sent_otp=rand;
            //data_taken= Email.send("samirsarker055@gmail.com","Samir1234",email,"Emai Varification",rand);
            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);

            String qr = "insert into Info values(?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(qr);

            pstmt.setString(1, Name);
            pstmt.setString(2, Username);
            pstmt.setString(3, email);
            pstmt.setString(4, Password);
            pstmt.setString(5, Occupation);

            pstmt.executeUpdate();

            System.out.println("signup database conected");

            conn.close();

        } catch (SQLException ex) {
            System.out.println("sign up not updated");

        }

    }

    public boolean getData(String user, String password) {
        boolean data_match = false;
        Alert a = new Alert(AlertType.NONE);
        try {

            ResultSet r;
            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            String qrr = "SELECT * FROM `userinfo`";

            Statement stmt = conn.createStatement();
            r = stmt.executeQuery(qrr);

            System.out.println("sign in  connected");
            while (r.next()) {
                if ((r.getString(2).equals(user)) && (r.getString(4).equals(password))) {
                    System.out.println("Log in data matched ");
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

    public void forgotpassword(String user, String UpdatePassword, String email, String confirmpass) {
        //if(UpdatePassword == null ? confirmpass == null : UpdatePassword.equals(confirmpass))

        ResultSet r;

        try {
            boolean c = false;
            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);

            String qrr = "SELECT * FROM `userinfo`";
            Statement stmt = conn.createStatement();
            r = stmt.executeQuery(qrr);
            System.out.println("sign in connected for password");

            while (r.next()) {

                if ((r.getString(2).equals(user)) || (r.getString(3).equals(email))) {

                    // String s = r.getString(2);
                    System.out.println(" data matched");

                    String qr = "UPDATE `username` SET `password`=? WHERE username=? ";

                    PreparedStatement pstmt = conn.prepareStatement(qr);

                    pstmt.setString(1, UpdatePassword);

                    pstmt.setString(2, user);

                    pstmt.executeUpdate();

                    c = true;

                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.INFORMATION);
                    a.setContentText("Password has been Updated");
                    a.show();

                }

            }
            if (c == false) {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.INFORMATION);
                a.setContentText("invalid Username or Password");
                a.show();
            }

            conn.close();
        } catch (SQLException e) {

            System.out.println("Password not updated");

        }
    }

}
