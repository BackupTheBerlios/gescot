package is.SimTraffic.jUnit.Herramientas;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Clase de prueba que llama a todos los tests del paquete Herramientas
 * 
 */
public class HerramientasTestSuite {
	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTestSuite(HA�adirNodoTest.class);
	    suite.addTestSuite(HA�adirTramoTest.class);
	    return suite;
	}
}
