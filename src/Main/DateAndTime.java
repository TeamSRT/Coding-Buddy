
package Main;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateAndTime {
    Calendar obj = Calendar.getInstance();
    public String getDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());        
    }
    public String getDateAndTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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
}
