package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Messages;
import is.SimTraffic.Vista.Representaciones.Representacion;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;
import junit.framework.TestCase;

public class RepresentacionTest extends TestCase {
	public void testConverionY() {
		Representacion rep = new RepresentacionSimple();
		rep.setLat0(23);
		rep.setLon0(-0.234);
		rep.setZoom(1);
		//System.out.println(Messages.getString("RepresentacionTest.0")+2+ Messages.getString("RepresentacionTest.1") + rep.lon_RepAMapa(rep.x_MapaARep(2.0))); //$NON-NLS-1$ //$NON-NLS-2$
		//System.out.println(Messages.getString("RepresentacionTest.2")+2+ Messages.getString("RepresentacionTest.3") + rep.lat_RepAMapa(rep.y_MapaARep(2.0))); //$NON-NLS-1$ //$NON-NLS-2$
		
	}
}
