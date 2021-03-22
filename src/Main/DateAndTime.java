
package Main;


import java.text.SimpleDateFormat;
import java.util.Date;


public class DateAndTime {
    
    public void getDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        System.out.println(sdf.format(new Date()));
        
    }
    public void getTime()
    {
        
    }
}
