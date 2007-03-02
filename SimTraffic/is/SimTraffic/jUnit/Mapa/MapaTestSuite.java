package is.SimTraffic.jUnit.Mapa;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MapaTestSuite {

	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTestSuite(MapaTest.class);
	    suite.addTestSuite(NodoTest.class);
	    suite.addTestSuite(TramoTest.class);
	    suite.addTestSuite(ConversorUTMTest.class);
	    return suite;
	}

}