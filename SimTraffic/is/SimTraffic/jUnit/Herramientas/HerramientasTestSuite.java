package is.SimTraffic.jUnit.Herramientas;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Clase de prueba que llama a todos los tests del paquete Herramientas
 * 
 */
public class HerramientasTestSuite {
	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTestSuite(HAñadirNodoTest.class);
	    suite.addTestSuite(HAñadirTramoTest.class);
	    return suite;
	}
}
