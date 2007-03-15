package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Vista.Representaciones.Representacion;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;
import junit.framework.TestCase;

public class RepresentacionTest extends TestCase {
	public void testConverionY() {
		Representacion rep = new RepresentacionSimple();
		rep.setLat0(23);
		rep.setLon0(-0.234);
		rep.setZoom(1);
		System.out.println(""+2+ " " + rep.lon_RepAMapa(rep.x_MapaARep(2.0)));
		System.out.println(""+2+ " " + rep.lat_RepAMapa(rep.y_MapaARep(2.0)));
		
	}
}
