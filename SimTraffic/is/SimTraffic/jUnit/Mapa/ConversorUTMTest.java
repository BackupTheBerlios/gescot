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
		assertTrue("longitud equivocada", (latlon[1] >= -181.53958) && (latlon[1] <= -181.53956));
		assertTrue("latitud equivocada", (latlon[0] >= -87.880973) && (latlon[0] <= -87.880971));	
	}

	

}
