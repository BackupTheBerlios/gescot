package is.SimTraffic.jUnit.Mapa;

import junit.framework.TestCase;
import is.SimTraffic.Mapa.*;

public class ConversorUTMTest extends TestCase {

	public void testConversionLatLonToXY() {
		double lon = 5.0;
		double lat = 5.0;
		double[] xy;
		int zone = (int) (Math.floor ((lon + 180.0) / 6) + 1);
		xy = ConversorUTM.LatLonToUTMXY(lat, lon, zone);
		assertEquals("zona equivocada", zone, 31);
		assertTrue("x equivocada", (xy[0] >= 721753) && (xy[0] <= 721754));
		assertTrue("y equivocada", (xy[1] >= 553001) && (xy[1] <= 553002));
		int posX = (int) xy[0];
		int posY = (int) xy[1];
		assertTrue("x equivocada", (posX >= 721753) && (posX <= 721754));
		assertTrue("y equivocada", (posY >= 553001) && (posY <= 553002));
	
	}

	public void testConversionXYToLatLon() {
		double x = 456734;
		double y = 234634;
		double[] latlon;
		int zone = 2;
		latlon = ConversorUTM.UTMXYToLatLon(x, y, zone, true);
		assertTrue("latitud equivocada", (latlon[1] >= -181.53958) && (latlon[1] <= -181.53956));
		assertTrue("longitud equivocada", (latlon[0] >= -87.880973) && (latlon[0] <= -87.880971));	
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
		assertTrue("¡Conversiones MUY inexactas en X!", (nuevaX >= 356634) && (nuevaX <= 556834));
		assertTrue("¡Conversiones MUY inexactas en Y!", (nuevaY >= 134534) && (nuevaY <= 334734));
	}

	public void test3Conversiones() {

		double[] xy;
		int zone = (int) (Math.floor ((45 + 180.0) / 6) + 1);
		xy = ConversorUTM.LatLonToUTMXY(30, 45, zone);
		double nuevaX=xy[0];
		double nuevaY=xy[1];
		System.out.println(""+zone +" " + nuevaX + " " + nuevaY);
		assertFalse("mala conversion x", (nuevaX < 499999 || nuevaX > 500001));

		assertFalse("mala conversion y", (nuevaY < 3318784 ||nuevaY > 3318786));	}

}
