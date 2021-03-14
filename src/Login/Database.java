package Login;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Database {

    /*public Connection conn;

    public static Connection db() {

        try {
            String url = "jdbc:mysql://127.0.0.1/dblogin";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("not connected");
        }

        return null;
    }
     */
    public void setData(String Name, String Username, String Email, String Password, String Confirmpassword, String Occupation) {

        Alert a = new Alert(AlertType.NONE);
        try {

            String url = "jdbc:mysql://127.0.0.1/codingbuddydb";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);

            String qr = "insert into userInfo values(?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(qr);

            pstmt.setString(1, Name);
            pstmt.setString(2, Username);
            pstmt.setString(3, Email);
            pstmt.setString(4, Password);
            pstmt.setString(5, Occupation);

            pstmt.executeUpdate();
            System.out.println("signup database conected");

            conn.close();

        } catch (SQLException ex) {
            System.out.println("sign up not updated");

            a.setAlertType(AlertType.INFORMATION);
            a.setContentText("This Username or Email has been already used");
            a.show();
        }

    }

    /*
    public boolean getData(String user, String password) {
        boolean a = false;
        try {
            ResultSet r;
            String url = "jdbc:mysql://127.0.0.1/dblogin";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            String qrr = "SELECT `UserName`, `Password` FROM `login`";
            //"select *from login";

            Statement stmt = conn.createStatement();

            r = stmt.executeQuery(qrr);
            System.out.println("sign in  connected");
            while (r.next()) {

                if ((r.getString(1).equals(user)) && (r.getString(2).equals(password))) {
                    System.out.println("true");
                }

            }

        } catch (Exception e) {
            System.out.println("sign in not connected");
        }
        return a;

    }
     */
 /* public  void forgotpassword(String UpdatedPassword)
    {
        
        try {
            
           String url = "jdbc:mysql://127.0.0.1/dblogin";
            String username = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, username, pass);
            
           String qr = "UPDATE UserName SET UserName=;";


            PreparedStatement pstmt = conn.prepareStatement(qr);

         
            pstmt.setString(2,UpdatedPassword);

            
            pstmt.executeUpdate();
            System.out.println("sign conection");

            
            conn.close();
         

        } catch (Exception e) {
            System.out.println("sign up not updated");
            // a.setAlertType(AlertType.INFORMATION); 
  
                // set content text 
              //  a.setContentText("This Username has been already used"); 
  
                // show the dialog 
                //a.show(); 
             

        //}

    }
        
        
        
        
    }*/
}
