package is.SimTraffic.Utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tiempo {
	public static String Hora(){
	    Calendar cal = new GregorianCalendar();
	    // Get the components of the time
	    int hour12 = cal.get(Calendar.HOUR);            // 0..11
	    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	    int min = cal.get(Calendar.MINUTE);             // 0..59
	    int sec = cal.get(Calendar.SECOND);             // 0..59
	    int ms = cal.get(Calendar.MILLISECOND);         // 0..999
	    int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM
	    return "["+hour24+":"+min+":"+sec+"]";
	}
}
