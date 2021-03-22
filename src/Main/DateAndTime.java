
package Main;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


    public class DateAndTime {
    public Connection conn;
    Calendar obj = Calendar.getInstance();
    public String getDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());        
    }
    public String getTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }   
    public int getHour()
    {
       return obj.get(Calendar.HOUR);
    }
    public int getMinute()
    {
        return obj.get(Calendar.MINUTE);
    }
    public int getSecond()
    {
       return obj.get(Calendar.SECOND);
    }
    public int getDayOfYear()
    {
       return obj.get(Calendar.DAY_OF_YEAR);
    }
    public int getDayOfMonth()
    {
        return obj.get(Calendar.DAY_OF_MONTH);
    }
    public void connection() throws SQLException
    {
         this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingbuddydb", "root", "");
    }
    public void writeLoginTime() throws SQLException
    {
        String query = "INSERT INTO `logintimeinfo`(`username`, `loginDate`, `loginTime`) VALUES (?,?,?)";
        PreparedStatement wLT = conn.prepareStatement(query);
        wLT.setString(1, Main.Utility.username);
        wLT.setString(2, getDate());
        wLT.setString(3, getTime());        
        wLT.execute();
    }
    public void readLoginTime()         
    {
       
        ArrayList<Time> loginTime = new ArrayList<>();
        try {
            
            Statement query = conn.createStatement();            
            ResultSet rs = query.executeQuery("SELECT `loginDate`, `loginTime` FROM `logintimeinfo` WHERE username = '"+Utility.username+"'");
            while(rs.next())
            {
              
                System.out.println("loginDate = "+rs.getDate("loginDate").toString() + " loginTime = " +rs.getTime("loginTime").toString());
                Time tobj = new Time();
                tobj.loginDate = rs.getDate("loginDate").toString();
                tobj.loginTime = rs.getTime("loginTime").toString();        
                loginTime.add(tobj);
            }
        } catch (Exception ex) {
            System.out.println("Login Time Exception Occured");
        }
        
    }
    
   
}
