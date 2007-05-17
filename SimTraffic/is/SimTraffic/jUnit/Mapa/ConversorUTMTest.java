package is.SimTraffic.jUnit.Mapa;

import junit.framework.TestCase;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.*;

public class ConversorUTMTest extends TestCase {

	public void testConversionLatLonToXY() {
		double lon = 5.0;
		double lat = 5.0;
		double[] xy;
		int zone = (int) (Math.floor ((lon + 180.0) / 6) + 1);
		xy = ConversorUTM.LatLonToUTMXY(lat, lon, zone);
		assertEquals(Messages.getString("ConversorUTMTest.0"), zone, 31); //$NON-NLS-1$
		assertTrue(Messages.getString("ConversorUTMTest.1"), (xy[0] >= 721753) && (xy[0] <= 721754)); //$NON-NLS-1$
		assertTrue(Messages.getString("ConversorUTMTest.2"), (xy[1] >= 553001) && (xy[1] <= 553002)); //$NON-NLS-1$
		int posX = (int) xy[0];
		int posY = (int) xy[1];
		assertTrue(Messages.getString("ConversorUTMTest.3"), (posX >= 721753) && (posX <= 721754)); //$NON-NLS-1$
		assertTrue(Messages.getString("ConversorUTMTest.4"), (posY >= 553001) && (posY <= 553002)); //$NON-NLS-1$
	
	}

	public void testConversionXYToLatLon() {
		double x = 456734;
		double y = 234634;
		double[] latlon;
		int zone = 2;
		latlon = ConversorUTM.UTMXYToLatLon(x, y, zone, true);
		assertTrue(Messages.getString("ConversorUTMTest.5"), (latlon[1] >= -181.53958) && (latlon[1] <= -181.53956)); //$NON-NLS-1$
		assertTrue(Messages.getString("ConversorUTMTest.6"), (latlon[0] >= -87.880973) && (latlon[0] <= -87.880971));	 //$NON-NLS-1$
	}
	
	public void test2Conversiones() {
		double x = 456734;
		double y = 234634;
		double[] latlon;
		int zone = 2;
		latlon = ConversorUTM.UTMXYToLatLon(x, y, zone, false);
		//Hasta aquí conversión a LatLon, ahora estos valores de nuevo en posX, posY
		//System.out.println(""+latlon[0]+" "+latlon[1]);
		double[] xy;
		zone = (int) (Math.floor ((latlon[1] + 180.0) / 6) + 1);
		xy = ConversorUTM.LatLonToUTMXY(latlon[0], latlon[1], zone);
		double nuevaX=xy[0];
		double nuevaY=xy[1];
		//System.out.println(""+zone+" "+nuevaX+" "+nuevaY);
		//assertTrue("¡Conversiones inexactas en X!", (nuevaX >= 456834) && (nuevaX <= 456634));
		//assertTrue("¡Conversiones inexactas en Y!", (nuevaY >= 234734) && (nuevaX <= 234534));
		assertTrue(Messages.getString("ConversorUTMTest.7"), (nuevaX >= 356634) && (nuevaX <= 556834)); //$NON-NLS-1$
		assertTrue(Messages.getString("ConversorUTMTest.8"), (nuevaY >= 134534) && (nuevaY <= 334734)); //$NON-NLS-1$
	}

	public void test3Conversiones() {

		double[] xy;
		int zone = (int) (Math.floor ((45 + 180.0) / 6) + 1);
		xy = ConversorUTM.LatLonToUTMXY(30, 45, zone);
		double nuevaX=xy[0];
		double nuevaY=xy[1];
		System.out.println(Messages.getString("ConversorUTMTest.9")+zone +Messages.getString("ConversorUTMTest.10") + nuevaX + Messages.getString("ConversorUTMTest.11") + nuevaY); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		assertFalse(Messages.getString("ConversorUTMTest.12"), (nuevaX < 499999 || nuevaX > 500001)); //$NON-NLS-1$

		assertFalse(Messages.getString("ConversorUTMTest.13"), (nuevaY < 3318784 ||nuevaY > 3318786));	} //$NON-NLS-1$

}
