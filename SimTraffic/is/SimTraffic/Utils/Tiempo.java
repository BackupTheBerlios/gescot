package is.SimTraffic.Utils;

import is.SimTraffic.Messages;

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
	    
	    return Messages.getString("Tiempo.1")+convierte(hour24)+Messages.getString("Tiempo.2")+convierte(min)+Messages.getString("Tiempo.3")+convierte(sec)+Messages.getString("Tiempo.4"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
	private static String convierte(int valor){
		if (valor <10){
			return Messages.getString("Tiempo.5")+valor; //$NON-NLS-1$
		}
		else return Messages.getString("Tiempo.6")+valor; //$NON-NLS-1$
	}
}
