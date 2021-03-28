
package Main;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void connection() throws SQLException
    {
         this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingbuddydb", "root", "");
    }
    
    public Time totalDay() throws SQLException,ParseException
    {
        String query = "SELECT `signupdate`,`signuptime` FROM `userinfo` WHERE username = '"+Main.Utility.username +"'";
        Statement sT = conn.createStatement();
        ResultSet rs = sT.executeQuery(query);
        Time tobj = new Time();        
        while(rs.next())
        {    
            tobj.signupDate = rs.getString("signupDate");         
            tobj.signupTime = rs.getString("signupTime");          
        }
        return tobj;       
        
    }
    public String passedTime(Date currentDate, Date postDate)
    {
        String text;
        long mSecPerMinute = 60 * 1000;//milli
        long mSecPerHour = mSecPerMinute * 60;
        long mSecPerDay = mSecPerHour * 24;
        long mSecPerMonth = mSecPerDay * 30;
        long mSecPerYear = mSecPerDay * 365;
        long diff =currentDate.getTime() - postDate.getTime();
        
        if(diff < mSecPerMinute)//seconds ago
        {
            if(Math.round(diff / 1000) == 1)
            {
                text = String.valueOf(Math.round(diff / 1000)) + " second ago";             
            }
            else
            {
                text = String.valueOf(Math.round(diff / 1000)) + " seconds ago";             
            }           
        }
        else if(diff < mSecPerHour)//min ago
        {
            if(Math.round(diff / mSecPerMinute) == 1)
            {
                text = String.valueOf(Math.round(diff / mSecPerMinute)) + " minute ago";
            }
            else
            {
                text = String.valueOf(Math.round(diff / mSecPerMinute)) + " minutes ago";
            }         
        }
        else if(diff < mSecPerDay)//hours
        {
            if(Math.round(diff / mSecPerHour) == 1)
            {
                text = String.valueOf(Math.round(diff / mSecPerHour)) + " hour ago";
            }
            else
            {
                text = String.valueOf(Math.round(diff / mSecPerHour)) + " hours ago";
            }          
        }
        else if(diff < mSecPerMonth)//day
        {
            if(Math.round(diff / mSecPerDay) == 1)
            {
                text = String.valueOf(Math.round(diff / mSecPerDay)) + " day ago";
            }
            else
            {
                text = String.valueOf(Math.round(diff / mSecPerDay)) + " days ago";
            }          
        }
        else if(diff < mSecPerYear)//month
        {
            if(Math.round(diff / mSecPerMonth) == 1)
            {
                text = String.valueOf(Math.round(diff / mSecPerMonth)) + " month ago";
            }
            else
            {
                text = String.valueOf(Math.round(diff / mSecPerMonth)) + " months ago";
            }
          
        }
        else
        { //year
            if(Math.round(diff / mSecPerYear) == 1)
            {
                text = String.valueOf(Math.round(diff / mSecPerYear)) + " year ago";
            }
            else
            {
                text = String.valueOf(Math.round(diff / mSecPerYear)) + " years ago";
            }
        
        }
        return text;
    }
    
   
}
